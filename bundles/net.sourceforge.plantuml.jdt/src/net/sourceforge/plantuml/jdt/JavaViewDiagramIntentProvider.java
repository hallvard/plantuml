package net.sourceforge.plantuml.jdt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IViewPart;

import net.sourceforge.plantuml.eclipse.utils.WorkbenchPartDiagramIntentProviderContext;
import net.sourceforge.plantuml.text.AbstractDiagramIntentProvider;
import net.sourceforge.plantuml.util.DiagramIntent;

public class JavaViewDiagramIntentProvider extends AbstractDiagramIntentProvider {

	public JavaViewDiagramIntentProvider() {
		setViewType(IViewPart.class);
	}

	@Override
	protected Collection<DiagramIntent> getDiagramInfos(final WorkbenchPartDiagramIntentProviderContext context) {
		final Collection<IType> javaElements = getSelectedTypes(context.getSelection());
		return Collections.singletonList(new JdtDiagramIntent(javaElements));
	}

	@Override
	public boolean supportsSelection(final ISelection selection) {
		final Collection<IType> selectedTypes = getSelectedTypes(selection);
		return selectedTypes != null && (! selectedTypes.isEmpty());
	}

	protected Collection<IType> getSelectedTypes(final ISelection selection) {
		final Collection<IType> javaElements = new ArrayList<IType>();
		if (selection instanceof IStructuredSelection) {
			final Iterator<?> it = ((IStructuredSelection) selection).iterator();
			while (it.hasNext()) {
				final Object next = it.next();
				if (next instanceof IJavaElement) {
					addJavaElements((IJavaElement) next, javaElements);
				}
			}
		}
		return javaElements;
	}

	protected void addJavaElements(final IJavaElement javaElement, final Collection<IType> javaElements) {
		if (javaElement instanceof ICompilationUnit) {
			try {
				final IType[] types = ((ICompilationUnit) javaElement).getTypes();
				if (types != null && types.length > 0) {
					javaElements.add(types[0]);
				}
			} catch (final JavaModelException e) {
			}
		} else if (javaElement instanceof IPackageFragment) {
			try {
				// add immediate compilation units
				for (final ICompilationUnit compUnit : ((IPackageFragment) javaElement).getCompilationUnits()) {
					addJavaElements(compUnit, javaElements);
				}
				// add sub-packages
				for (final IJavaElement packageFragment : ((IPackageFragmentRoot) javaElement.getParent()).getChildren()) {
					if (packageFragment != javaElement && javaElement.getPath().isPrefixOf(packageFragment.getPath())) {
						// add compilation units of sub-package fragment
						addJavaElements(packageFragment, javaElements);
					}
				}
			} catch (final JavaModelException e) {
			}
		} else if (javaElement instanceof IType) {
			javaElements.add((IType) javaElement);
		}
	}
}
