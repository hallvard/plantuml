package net.sourceforge.plantuml.jdt.ui;

import static net.sourceforge.plantuml.text.AbstractClassDiagramTextProvider.GEN_ASSOCIATIONS;
import static net.sourceforge.plantuml.text.AbstractClassDiagramTextProvider.GEN_EXTENDS;
import static net.sourceforge.plantuml.text.AbstractClassDiagramTextProvider.GEN_IMPLEMENTS;
import static net.sourceforge.plantuml.text.AbstractClassDiagramTextProvider.GEN_MEMBERS;
import static net.sourceforge.plantuml.text.AbstractClassDiagramTextProvider.GEN_MODIFIERS;
import static net.sourceforge.plantuml.text.AbstractClassDiagramTextProvider.GEN_CLASS_HYPERLINKS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IParent;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;

import net.sourceforge.plantuml.eclipse.utils.PlantumlConstants;
import net.sourceforge.plantuml.eclipse.views.PlantUmlView;
import net.sourceforge.plantuml.jdt.Activator;
import net.sourceforge.plantuml.jdt.JdtDiagramTextProvider;

public class JdtPlantUmlView extends PlantUmlView implements IPropertyChangeListener {

	@Override
	public boolean isLinkedToActiveEditor() {
		return false;
	}
	
	private JdtDiagramTextProvider jdtDiagramTextProvider = new JdtDiagramTextProvider() {
		
		@Override
		protected String getDiagramText(IEditorPart editorPart, IEditorInput editorInput, ISelection selection, Map<String, Object> markerAttributes) {
			return null;
		}
	};
	
	private Collection<IJavaElement> rootSet = new ArrayList<IJavaElement>();
	
	private IPreferenceStore preferenceStore;

	@Override
	public void dispose() {
		if (preferenceStore != null) {
			preferenceStore.removePropertyChangeListener(this);
			preferenceStore = null;
		}
	}
	
	public void createPartControl(Composite parent) {
		preferenceStore = Activator.getDefault().getPreferenceStore();
		preferenceStore.addPropertyChangeListener(this);

		super.createPartControl(parent);
		
		DropTarget dropTarget = new DropTarget(parent, DND.DROP_DEFAULT | DND.DROP_COPY);
		final Transfer[] transfers = {
				LocalSelectionTransfer.getTransfer()
//				, FileTransfer.getInstance()
//				, ResourceTransfer.getInstance()
		};
		dropTarget.setTransfer(transfers);
		dropTarget.addDropListener(new DropTargetAdapter() {
			
			@Override
			public void dragEnter(DropTargetEvent event) {
				if (event.detail == DND.DROP_DEFAULT) {
					event.detail = DND.DROP_COPY;
				}
			}

			@Override
			public void drop(DropTargetEvent event) {
				Collection<IJavaElement> rootSet = null;
				if (LocalSelectionTransfer.getTransfer().isSupportedType(event.currentDataType)) {
					ISelection data = LocalSelectionTransfer.getTransfer().getSelection();
					if (data instanceof IStructuredSelection) {
						Iterator<?> elements = ((IStructuredSelection) data).iterator();
						while (elements.hasNext()) {
							Object next = elements.next();
							Object javaElement = Platform.getAdapterManager().getAdapter(next, IJavaElement.class);
							if (javaElement != null) {
								if (rootSet == null) {
									rootSet = new ArrayList<IJavaElement>();
								}
								if (! rootSet.contains(javaElement)) {
									rootSet.add((IJavaElement) javaElement);
								}
							}
						}
					}
				}
				if (rootSet != null && (! rootSet.isEmpty())) {
					setRootSet(rootSet);
				}
			}
		});
	}

	protected void setRootSet(Collection<IJavaElement> col) {
		this.rootSet = new ArrayList<IJavaElement>(col);
		updateView();
	}

	private int genFlags = GEN_MEMBERS | GEN_MODIFIERS | GEN_EXTENDS | GEN_IMPLEMENTS | GEN_ASSOCIATIONS | GEN_CLASS_HYPERLINKS;

	public final static String[] ALL_PACKAGE_STYLES = { "Folder", "Rectangle", "Frame", "Cloud", "Database" };
	
	private String packageStyle = null; 
	private String packageColor = null;  // #DDDDDD

	@Override
	protected void contributeToActionBars() {
		IToolBarManager toolBarManager = getViewSite().getActionBars().getToolBarManager();
		addZoomActions(toolBarManager);
	}

	private void updateView() {
		boolean generatePackages = (preferenceStore != null && preferenceStore.getBoolean(ProjectClassDiagramPreferencePage.GENERATE_PACKAGES_KEY));
		packageStyle = (preferenceStore != null ? preferenceStore.getString(ProjectClassDiagramPreferencePage.PACKAGE_STYLE_KEY) : null);
		if (packageStyle == null || packageStyle.length() == 0) {
			packageStyle = ALL_PACKAGE_STYLES[0];
		}

		StringBuilder result = new StringBuilder();
		result.append(PlantumlConstants.START_UML + "\n");
		Collection<IType> allTypes = new HashSet<IType>();
		addTypes(this.rootSet, allTypes);
		if (generatePackages) {
			Map<String, Collection<IType>> packageTypes = new HashMap<String, Collection<IType>>();
			for (IType type : allTypes) {
				String packageName = type.getPackageFragment().getElementName();
				Collection<IType> types = packageTypes.get(packageName);
				if (types == null) {
					types = new ArrayList<IType>();
					packageTypes.put(packageName, types);
				}
				types.add(type);
			}
			for (Map.Entry<String, Collection<IType>> packageTypesEntry : packageTypes.entrySet()) {
				generatePackage(packageTypesEntry.getKey(), packageTypesEntry.getValue(), result);
			}
		} else {
			generateTypes(allTypes, result);
		}
		result.append("\n" + PlantumlConstants.END_UML);
		updateDiagramText(result.toString(), null, null);
	}

	private void generatePackage(String packageName, Collection<IType> types, StringBuilder result) {
		result.append("package ");
		result.append(packageName);
		if (packageStyle != null) {
			result.append(" <<");
			result.append(packageStyle);
			result.append(">>");
		}
		if (packageColor != null) {
			result.append(" #");
			result.append(packageColor);
		}
		result.append(" {\n");
		generateTypes(types, result);
		result.append("\n}\n");
	}

	private void generateTypes(Collection<IType> types, StringBuilder result) {
		for (IType type : types) {
			jdtDiagramTextProvider.generateForType(type, result, genFlags, types);
		}
	}

	private void addTypes(Iterable<IJavaElement> elements, Collection<IType> result) {
		for (IJavaElement javaElement : elements) {
			if (javaElement instanceof ICompilationUnit) {
				IType[] types = null;
				try {
					types = ((ICompilationUnit) javaElement).getTypes();
				} catch (JavaModelException e) {
				}
				for (int i = 0; types != null && i < types.length; i++) {
					result.add(types[i]);
				}
			} else if (javaElement instanceof IParent && (! javaElement.getElementName().endsWith(".jar"))) {
				try {
					addTypes(Arrays.asList(((IParent) javaElement).getChildren()), result);
				} catch (JavaModelException e) {
				}
			}
		}
	}

	//

	@Override
	public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
		updateView();
	}
}
