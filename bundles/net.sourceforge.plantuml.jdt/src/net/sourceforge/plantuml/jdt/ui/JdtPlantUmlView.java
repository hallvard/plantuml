package net.sourceforge.plantuml.jdt.ui;

import static net.sourceforge.plantuml.text.AbstractClassDiagramIntent.GEN_ASSOCIATIONS;
import static net.sourceforge.plantuml.text.AbstractClassDiagramIntent.GEN_CLASS_HYPERLINKS;
import static net.sourceforge.plantuml.text.AbstractClassDiagramIntent.GEN_EXTENDS;
import static net.sourceforge.plantuml.text.AbstractClassDiagramIntent.GEN_IMPLEMENTS;
import static net.sourceforge.plantuml.text.AbstractClassDiagramIntent.GEN_MEMBERS;
import static net.sourceforge.plantuml.text.AbstractClassDiagramIntent.GEN_MODIFIERS;

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
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
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

import net.sourceforge.plantuml.eclipse.utils.PlantumlConstants;
import net.sourceforge.plantuml.eclipse.views.PlantUmlView;
import net.sourceforge.plantuml.jdt.Activator;
import net.sourceforge.plantuml.jdt.JdtDiagramIntent;
import net.sourceforge.plantuml.util.SimpleDiagramIntent;

public class JdtPlantUmlView extends PlantUmlView implements IPropertyChangeListener {

	@Override
	public boolean isLinkedToActivePart() {
		return false;
	}

	private Collection<IJavaElement> rootSet = new ArrayList<IJavaElement>();

	private IPreferenceStore preferenceStore;

	@Override
	public void dispose() {
		if (preferenceStore != null) {
			preferenceStore.removePropertyChangeListener(this);
			preferenceStore = null;
		}
	}

	@Override
	public void createPartControl(final Composite parent) {
		preferenceStore = Activator.getDefault().getPreferenceStore();
		preferenceStore.addPropertyChangeListener(this);

		super.createPartControl(parent);

		final DropTarget dropTarget = new DropTarget(parent, DND.DROP_DEFAULT | DND.DROP_COPY);
		final Transfer[] transfers = { LocalSelectionTransfer.getTransfer()
				// , FileTransfer.getInstance()
				// , ResourceTransfer.getInstance()
		};
		dropTarget.setTransfer(transfers);
		dropTarget.addDropListener(new DropTargetAdapter() {

			@Override
			public void dragEnter(final DropTargetEvent event) {
				if (event.detail == DND.DROP_DEFAULT) {
					event.detail = DND.DROP_COPY;
				}
			}

			@Override
			public void drop(final DropTargetEvent event) {
				Collection<IJavaElement> rootSet = null;
				if (LocalSelectionTransfer.getTransfer().isSupportedType(event.currentDataType)) {
					final ISelection data = LocalSelectionTransfer.getTransfer().getSelection();
					if (data instanceof IStructuredSelection) {
						final Iterator<?> elements = ((IStructuredSelection) data).iterator();
						while (elements.hasNext()) {
							final Object next = elements.next();
							final Object javaElement = Platform.getAdapterManager().getAdapter(next,
									IJavaElement.class);
							if (javaElement != null) {
								if (rootSet == null) {
									rootSet = new ArrayList<IJavaElement>();
								}
								if (!rootSet.contains(javaElement)) {
									rootSet.add((IJavaElement) javaElement);
								}
							}
						}
					}
				}
				// note the view is visible, otherwise the diagram won't be shown
				setVisible(true);
				if (rootSet != null && (!rootSet.isEmpty())) {
					setRootSet(rootSet);
				}
			}
		});
	}

	protected void setRootSet(final Collection<IJavaElement> col) {
		this.rootSet = new ArrayList<IJavaElement>(col);
		updateView();
	}

	private final int genFlags = GEN_MEMBERS | GEN_MODIFIERS | GEN_EXTENDS | GEN_IMPLEMENTS | GEN_ASSOCIATIONS
			| GEN_CLASS_HYPERLINKS;

	public final static String[] ALL_PACKAGE_STYLES = { "Folder", "Rectangle", "Frame", "Cloud", "Database" };

	private String packageStyle = null;
	private final String packageColor = null; // #DDDDDD

	@Override
	protected void contributeToActionBars() {
		final IToolBarManager toolBarManager = getViewSite().getActionBars().getToolBarManager();
		addZoomActions(toolBarManager);
	}

	private void updateView() {
		final boolean generatePackages = (preferenceStore != null
				&& preferenceStore.getBoolean(ProjectClassDiagramPreferencePage.GENERATE_PACKAGES_KEY));
		packageStyle = (preferenceStore != null
				? preferenceStore.getString(ProjectClassDiagramPreferencePage.PACKAGE_STYLE_KEY)
						: null);
		if (packageStyle == null || packageStyle.length() == 0) {
			packageStyle = ALL_PACKAGE_STYLES[0];
		}

		final StringBuilder result = new StringBuilder();
		result.append(PlantumlConstants.START_UML + "\n");
		final Collection<IType> allTypes = new HashSet<IType>();
		addTypes(this.rootSet, allTypes);
		if (generatePackages) {
			final Map<String, Collection<IType>> packageTypes = new HashMap<String, Collection<IType>>();
			for (final IType type : allTypes) {
				final String packageName = type.getPackageFragment().getElementName();
				Collection<IType> types = packageTypes.get(packageName);
				if (types == null) {
					types = new ArrayList<IType>();
					packageTypes.put(packageName, types);
				}
				types.add(type);
			}
			for (final Map.Entry<String, Collection<IType>> packageTypesEntry : packageTypes.entrySet()) {
				generatePackage(packageTypesEntry.getKey(), packageTypesEntry.getValue(), result);
			}
		} else {
			generateTypes(allTypes, result);
		}
		result.append("\n" + PlantumlConstants.END_UML);
		updateDiagramText(new SimpleDiagramIntent(result.toString()));
	}

	private void generatePackage(final String packageName, final Collection<IType> types, final StringBuilder result) {
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

	private void generateTypes(final Collection<IType> types, final StringBuilder result) {
		final JdtDiagramIntent jdtDiagramIntent = new JdtDiagramIntent(types);
		for (final IType type : types) {
			jdtDiagramIntent.generateForType(type, result, genFlags, types);
		}
	}

	private void addTypes(final Iterable<IJavaElement> elements, final Collection<IType> result) {
		for (final IJavaElement javaElement : elements) {
			if (javaElement instanceof ICompilationUnit) {
				try {
					result.addAll(Arrays.asList(((ICompilationUnit) javaElement).getTypes()));
				} catch (final JavaModelException e) {
				}
			} else if (javaElement instanceof IPackageFragment) {
				final Collection<IJavaElement> compilationUnits = new ArrayList<IJavaElement>();
				try {
					// add immediate compilation units
					compilationUnits.addAll(Arrays.asList(((IPackageFragment) javaElement).getChildren()));
				} catch (final JavaModelException e1) {
				}
				try {
					for (final IJavaElement packageFragment : ((IPackageFragmentRoot) javaElement.getParent()).getChildren()) {
						if (packageFragment != javaElement && javaElement.getPath().isPrefixOf(packageFragment.getPath())) {
							try {
								// add compilation units of sub-package fragment
								compilationUnits.addAll(Arrays.asList(((IPackageFragment) packageFragment).getChildren()));
							} catch (final JavaModelException e) {
							}
						}
					}
				} catch (final Exception e) {
				}
				addTypes(compilationUnits, result);
			} else if (javaElement instanceof IParent && (!javaElement.getElementName().endsWith(".jar"))) {
				try {
					addTypes(Arrays.asList(((IParent) javaElement).getChildren()), result);
				} catch (final JavaModelException e) {
				}
			}
		}
	}

	//

	@Override
	public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
		updateView();
	}
}
