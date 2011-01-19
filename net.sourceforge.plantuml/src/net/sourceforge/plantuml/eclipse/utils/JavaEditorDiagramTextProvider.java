package net.sourceforge.plantuml.eclipse.utils;

import java.util.List;

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
		ICompilationUnit compUnit = JavaCore.createCompilationUnitFrom(project.getFile(path.removeFirstSegments(1)));
		StringBuilder result = new StringBuilder();
		try {
			compUnit.open(new NullProgressMonitor());
			for (IType type: compUnit.getTypes()) {
				generateForType(type, result, GEN_MEMBERS | GEN_MODIFIERS | GEN_SUPERCLASS | GEN_INTERFACES, null);
			}
		} catch (JavaModelException e) {
			System.err.println(e);
		}
		return (result.length() > 0 ? result.toString() : null);
	}

	private static int GEN_MODIFIERS = 1<<0, GEN_MEMBERS = 1<<1, GEN_SUPERCLASS = 1<<2, GEN_INTERFACES = 1<<3, GEN_ASSOCIATIONS = 1<<4;
	
	private void generateForType(IType type, StringBuilder result, int genFlags, List<IType> allTypes) {
		result.append(getClassType(type));
		result.append(" ");
		result.append(type.getElementName());
		result.append(" {\n");
		try {
			StringBuilder body = new StringBuilder();
			if ((genFlags & GEN_MEMBERS) > 0) {
				for (IField field : type.getFields()) {
					String fieldTypeSignature = getSignature(field.getTypeSignature());
					if ((genFlags & GEN_ASSOCIATIONS) > 0) {
						generateRelatedClass(type, field.getTypeSignature(), "-->", body, null, field.getElementName(), (isMulti(fieldTypeSignature) ? "*" : "1"));
					} else {
						body.append("\t");
						if (! Flags.isEnum(type.getFlags())) {
							if ((genFlags & GEN_MODIFIERS) > 0 && (! Flags.isInterface(type.getFlags()))) {
								body.append(getMemberModifier(field));
							}
							body.append(fieldTypeSignature);
							body.append(" ");
						}
						body.append(field.getElementName());
						body.append("\n");
					}
				}
				for (IMethod method : type.getMethods()) {
					body.append("\t");
					if ((genFlags & GEN_MODIFIERS) > 0 && (! Flags.isInterface(type.getFlags()))) {
						body.append(getMemberModifier(method));
					}
					// don't show the return type for constructors
					if (! method.getElementName().equals(type.getElementName())) {
						body.append(getSignature(method.getReturnType()));
						body.append(" ");
					}
					body.append(method.getElementName());
					body.append("(");
					String[] parameterTypes = method.getParameterTypes();
					String[] parameterNames = null;
					parameterNames = method.getParameterNames();
					for (int i = 0; i < method.getNumberOfParameters(); i++) {
						if (body.charAt(body.length() - 1) != '(') {
							body.append(", ");
						}
						body.append(getSignature(parameterTypes[i]));
						if (parameterNames != null) {
							body.append(" ");
							body.append(parameterNames[i]);
						}
					}
					body.append(")\n");
				}
			}
			result.append(body);
		} catch (JavaModelException e) {
		}
		result.append("}\n");
		try {
			if ((genFlags & GEN_SUPERCLASS) > 0) {
				generateRelatedClass(type, type.getSuperclassTypeSignature(), "<|--", result);
			}
			if ((genFlags & GEN_INTERFACES) > 0) {
				String[] interfaceSignatures = type.getSuperInterfaceTypeSignatures();
				for (int i = 0; i < interfaceSignatures.length; i++) {
					generateRelatedClass(type, interfaceSignatures[i], "<|..", result);
				}
			}
		} catch (JavaModelException e) {
		}
	}

	private boolean isMulti(String typeSignature) {
		if (typeSignature.contains("[]")) {
			return true;
		}
		if (typeSignature.contains("List<")) {
			return true;
		}
		return false;
	}

	private void generateRelatedClass(IType type, String superClassSignature, String relation, StringBuilder result) {
		generateRelatedClass(type, superClassSignature, relation, result, null, null, null);
	}
	private void generateRelatedClass(IType type, String superClassSignature, String relation, StringBuilder result, String start, String middle, String end) {
		if (superClassSignature != null) {
			String superClassName = getSignature(superClassSignature);
			if (! superClassName.equals("Object")) {
				result.append("class ");
				result.append(superClassName);
				result.append(" {\n}\n");
				result.append(superClassName);
				if (start != null) {
					result.append(" \"" + start + "\" ");
				}
				result.append(" " + relation + " ");
				if (end != null) {
					result.append(" \"" + end + "\" ");
				}
				result.append(type.getElementName());
				if (middle != null) {
					result.append(" : " + middle);
				}
				result.append("\n");
			}
		}
	}

	private String getSignature(String signature) {
		return Signature.toString(signature).replace("java.lang.", "");
	}

	private String getMemberModifier(IMember member) {
		try {
			int flags = member.getFlags();
			if (Flags.isPrivate(flags)) {
				return "-";
			} else if (Flags.isProtected(flags)) {
				return "#";
			} else if (Flags.isPublic(flags)) {
				return "+";
			} else {
				return "~";
			}
		} catch (JavaModelException e) {
			return "";
		}
	}

	private String getClassType(IType type) {
		try {
			int flags = type.getFlags();
			if (Flags.isEnum(flags)) {
				return "enum";
			} else if (Flags.isInterface(flags)) {
				return "interface";
			} else if (Flags.isAbstract(flags)) {
				return "abstract class";
			} else {
				return "class";
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
