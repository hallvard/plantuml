package net.sourceforge.plantuml.jdt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IViewPart;

public class JavaViewDiagramTextProvider extends JdtDiagramTextProvider {

	public JavaViewDiagramTextProvider() {
		setViewType(IViewPart.class);
	}

	@Override
	public String getDiagramText(final IViewPart viewPart, final ISelection selection) {
		final Collection<IType> javaElements = getSelectedTypes(selection);
		final StringBuilder result = new StringBuilder();
		for (final IType javaElement : javaElements) {
			generateForType(javaElement, result, javaElements);
		}
		return result.length() > 0 ? ensureDiagramText(result.toString()) : null;
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
				for (final ICompilationUnit compUnit : ((IPackageFragment) javaElement).getCompilationUnits()) {
					addJavaElements(compUnit, javaElements);
				}
			} catch (final JavaModelException e) {
			}
		} else if (javaElement instanceof IType) {
			javaElements.add((IType) javaElement);
		}
	}
}
