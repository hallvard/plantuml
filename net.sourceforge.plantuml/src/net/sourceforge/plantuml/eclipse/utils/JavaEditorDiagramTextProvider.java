package net.sourceforge.plantuml.eclipse.utils;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IMember;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.texteditor.ITextEditor;

public class JavaEditorDiagramTextProvider extends AbstractDiagramTextProvider {

	public JavaEditorDiagramTextProvider() {
		setEditorType(ITextEditor.class);
	}

	@Override
	protected String getDiagramText(IEditorPart editorPart, IEditorInput editorInput) {
		if (! (editorInput instanceof IFileEditorInput)) {
			return null;
		}
		IPath path = ((IFileEditorInput) editorInput).getFile().getFullPath();
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(path.segment(0));
		//		try {
		//			if (project == null || (! project.isNatureEnabled("org.eclipse.jdt.core.javanature"))) {
		//				return null;
		//			}
		//		} catch (CoreException e) {
		//			return null;
		//		}
		//		IJavaProject javaProject = JavaCore.create(project);
		//		IDocument document = ((ITextEditor) editorPart).getDocumentProvider().getDocument(editorInput);
		ICompilationUnit compUnit = JavaCore.createCompilationUnitFrom(project.getFile(path.removeFirstSegments(1)));
		StringBuilder result = new StringBuilder();
		try {
			compUnit.open(new NullProgressMonitor());
			for (IType type: compUnit.getTypes()) {
				result.append("class ");
				result.append(type.getElementName());
				result.append("{\n");
				for (IField field : type.getFields()) {
					result.append("\t");
					result.append(getModifier(field));
					result.append(getSignature(field.getTypeSignature()));
					result.append(" ");
					result.append(field.getElementName());
					result.append("\n");
				}
				for (IMethod method : type.getMethods()) {
					result.append("\t");
					result.append(getModifier(method));
					result.append(method.getElementName());
					result.append("(");
					String[] parameterTypes = method.getParameterTypes();
					String[] parameterNames = null;
					parameterNames = method.getParameterNames();
					for (int i = 0; i < method.getNumberOfParameters(); i++) {
						if (result.charAt(result.length() - 1) != '(') {
							result.append(", ");
						}
						result.append(getSignature(parameterTypes[i]));
						if (parameterNames != null) {
							result.append(" ");
							result.append(parameterNames[i]);
						}
					}
					result.append(")\n");
				}
				result.append("}\n");
			}
		} catch (JavaModelException e) {
			System.err.println(e);
		}
		return (result.length() > 0 ? result.toString() : null);
	}

	private String getSignature(String signature) {
		return Signature.toString(signature).replace("java.lang.", "");
	}

	private String getModifier(IMember member) {
		try {
			if ((member.getFlags() & Flags.AccPrivate) != 0) {
				return "-";
			} else if ((member.getFlags() & Flags.AccProtected) != 0) {
				return "#";
			} else if ((member.getFlags() & Flags.AccPublic) != 0) {
				return "+";
			} else {
				return "~";
			}
		} catch (JavaModelException e) {
			return "";
		}
	}

	/*
	public static boolean isJavaProject(IProject project) {
		try {
			return project != null && project.isNatureEnabled("org.eclipse.jdt.core.javanature"); // or hasNature
		} catch (CoreException e) {
		}
		return false;
	}
	 */
}
