package net.sourceforge.plantuml.jdt.ui;

import static net.sourceforge.plantuml.text.AbstractClassDiagramTextProvider.GEN_ASSOCIATIONS;
import static net.sourceforge.plantuml.text.AbstractClassDiagramTextProvider.GEN_EXTENDS;
import static net.sourceforge.plantuml.text.AbstractClassDiagramTextProvider.GEN_IMPLEMENTS;
import static net.sourceforge.plantuml.text.AbstractClassDiagramTextProvider.GEN_MEMBERS;
import static net.sourceforge.plantuml.text.AbstractClassDiagramTextProvider.GEN_MODIFIERS;
import static net.sourceforge.plantuml.text.AbstractClassDiagramTextProvider.GEN_CLASS_LINKS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IParent;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.util.LocalSelectionTransfer;
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
import net.sourceforge.plantuml.jdt.JdtDiagramTextProvider;

public class JdtPlantUmlView extends PlantUmlView {

	@Override
	public boolean isLinkedToActiveEditor() {
		return false;
	}
	
	private JdtDiagramTextProvider jdtDiagramTextProvider = new JdtDiagramTextProvider() {
		
		@Override
		protected String getDiagramText(IEditorPart editorPart, IEditorInput editorInput, ISelection selection) {
			return null;
		}
	};
	
	private Collection<IJavaElement> rootSet = new ArrayList<IJavaElement>();
	
	public void createPartControl(Composite parent) {
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
		System.out.println(this.rootSet);
		updateView();
	}

	private void updateView() {
		StringBuilder result = new StringBuilder();
		result.append(PlantumlConstants.START_UML + "\n");
		Collection<IType> allTypes = new HashSet<IType>();
		addTypes(this.rootSet, allTypes);
		for (IType type : allTypes) {
			jdtDiagramTextProvider.generateForType(type, result, GEN_MEMBERS | GEN_MODIFIERS | GEN_EXTENDS | GEN_IMPLEMENTS | GEN_ASSOCIATIONS | GEN_CLASS_LINKS, allTypes);
		}
		result.append("\n@enduml");
		updateDiagramText(result.toString());
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
}
