package net.sourceforge.plantuml.eclipse.utils;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IJavaProject;
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

	private class Context {
		IEditorPart editorPart;
		IEditorInput editorInput;
		IProject project;
		IJavaProject javaProject;
		ICompilationUnit compUnit;
	}
	
	private Context currentContext = null;
	
	@Override
	protected String getDiagramText(IEditorPart editorPart, IEditorInput editorInput) {
		if (! (editorInput instanceof IFileEditorInput)) {
			return null;
		}
		currentContext = new Context();
		IPath path = ((IFileEditorInput) editorInput).getFile().getFullPath();
		currentContext.project = ResourcesPlugin.getWorkspace().getRoot().getProject(path.segment(0));
		currentContext.javaProject = JavaCore.create(currentContext.project);
		currentContext.compUnit = JavaCore.createCompilationUnitFrom(currentContext.project.getFile(path.removeFirstSegments(1)));
		StringBuilder result = new StringBuilder();
		try {
			currentContext.compUnit.open(new NullProgressMonitor());
			for (IType type: currentContext.compUnit.getTypes()) {
				generateForType(type, result, GEN_MEMBERS | GEN_MODIFIERS | GEN_SUPERCLASS | GEN_INTERFACES, null);
			}
		} catch (JavaModelException e) {
			System.err.println(e);
		} finally {
			currentContext = null;
		}
		return (result.length() > 0 ? result.toString() : null);
	}

	private static int GEN_MODIFIERS = 1<<0, GEN_MEMBERS = 1<<1, GEN_SUPERCLASS = 1<<2, GEN_INTERFACES = 1<<3, GEN_ASSOCIATIONS = 1<<4;
	
	private static String ASSOCIATION_RELATION = "-->", SUPERCLASS_RELATION = "<|--", SUPERTYPE_RELATION = "<|..";

	private void generateForType(IType type, StringBuilder result, int genFlags, List<IType> allTypes) {
		result.append(getClassType(type));
		result.append(" ");
		appendNameDeclaration(type.getElementName(), result);
		result.append(" {\n");
		try {
			StringBuilder body = new StringBuilder();
			if ((genFlags & GEN_MEMBERS) > 0) {
				for (IField field : type.getFields()) {
					String fieldTypeSignature = getSignature(field.getTypeSignature());
					if ((genFlags & GEN_ASSOCIATIONS) > 0) {
						generateRelatedClass(type, field.getTypeSignature(), ASSOCIATION_RELATION, body, null, field.getElementName(), (isMulti(fieldTypeSignature) ? "*" : "1"));
					} else {
						body.append("\t");
						if (! Flags.isEnum(type.getFlags())) {
							if ((genFlags & GEN_MODIFIERS) > 0 && (! Flags.isInterface(type.getFlags()))) {
								body.append(getMemberModifiers(field));
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
						body.append(getMemberModifiers(method));
					}
					// don't show the return type for constructors
					if (! method.isConstructor()) {
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
				generateRelatedClass(type, type.getSuperclassTypeSignature(), SUPERCLASS_RELATION, result);
			}
			if ((genFlags & GEN_INTERFACES) > 0) {
				String[] interfaceSignatures = type.getSuperInterfaceTypeSignatures();
				for (int i = 0; i < interfaceSignatures.length; i++) {
					generateRelatedClass(type, interfaceSignatures[i], SUPERTYPE_RELATION, result);
				}
			}
		} catch (JavaModelException e) {
		}
	}

	private void appendNameDeclaration(String name, StringBuilder result) {
		String logicalName = getLogicalName(name);
		if (name.equals(logicalName)) {
			result.append(name);
		} else {
			result.append("\"");
			result.append(name);
			result.append("\"");
			result.append(" as ");
			result.append(logicalName);
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

	private void generateRelatedClass(IType type, String classSignature, String relation, StringBuilder result) {
		generateRelatedClass(type, classSignature, relation, result, null, null, null);
	}
	private void generateRelatedClass(IType type, String classSignature, String relation, StringBuilder result, String start, String middle, String end) {
		if (classSignature != null) {
			String className = getSignature(classSignature);
			if (! className.equals("Object")) {
				result.append(getClassType(classSignature, type, (relation.equals(SUPERTYPE_RELATION) ? "interface" : "class")));
				result.append(" ");
				appendNameDeclaration(className, result);
				result.append(" {\n}\n");
				result.append(getLogicalName(className));
				if (start != null) {
					result.append(" \"" + start + "\" ");
				}
				result.append(" " + relation + " ");
				if (end != null) {
					result.append(" \"" + end + "\" ");
				}
				result.append(getLogicalName(type.getElementName()));
				if (middle != null) {
					result.append(" : " + middle);
				}
				result.append("\n");
			}
		}
	}

	private String getLogicalName(String name) {
		StringBuilder builder = null;
		for (int i = 0; i < name.length(); i++) {
			char c = name.charAt(i);
			if (i == 0 ? Character.isJavaIdentifierStart(c) : Character.isJavaIdentifierPart(c)) {
				if (builder != null) {
					builder.append(c);
				}
			} else {
				if (builder == null) {
					builder = new StringBuilder(name.substring(0, i));
				}
				builder.append("_");
			}
		}
		return builder != null ? builder.toString() : name;
	}

	private String getSignature(String signature) {
		return Signature.toString(signature).replace("java.lang.", "");
	}

	private String getMemberModifiers(IMember member) {
		try {
			String modifiers = getMemberVisibilityModifier(member);
			int flags = member.getFlags();
			if (Flags.isStatic(flags)) {
				modifiers += "{static}";
			} else if (Flags.isAbstract(flags)) {
				modifiers += "{abstract}";
			}
			return modifiers;
		} catch (JavaModelException e) {
			return "";
		}
	}

	private String getMemberVisibilityModifier(IMember member) throws JavaModelException {
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
	
	private String getClassType(String signature, IType relativeTo, String def) {
		IType type = null;
		if (currentContext != null && currentContext.javaProject != null) {
			String typeName = Signature.toString(signature);
			if (typeName.lastIndexOf('.') < 0) {
				try {
					String[][] typeNames = relativeTo.resolveType(typeName);
					if (typeNames != null && typeNames.length > 0 && typeNames[0].length >= 2) {
						typeName = (typeNames[0][0] != null ? typeNames[0][0] + "." : "") + typeNames[0][1];
					}
				} catch (JavaModelException e) {
				}
			}
			try {
				type = currentContext.javaProject.findType(typeName);
			} catch (JavaModelException e) {
			} catch (IllegalArgumentException e) {
			}
		}
		return (type != null ? getClassType(type) : def);
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
