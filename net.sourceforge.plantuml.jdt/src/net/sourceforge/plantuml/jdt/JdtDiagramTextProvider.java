package net.sourceforge.plantuml.jdt;

import java.util.Collection;

import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IMember;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jface.viewers.ISelection;

import net.sourceforge.plantuml.text.AbstractDiagramTextProvider;

public abstract class JdtDiagramTextProvider extends AbstractDiagramTextProvider {

	@Override
	public boolean supportsSelection(ISelection selection) {
		return false;
	}

	public void generateForType(IType type, StringBuilder result, Collection<IType> allTypes) {
		generateForType(type, result, GEN_MEMBERS | GEN_MODIFIERS | GEN_EXTENDS | GEN_IMPLEMENTS, allTypes);
	}

	public void generateForType(IType type, StringBuilder result, int genFlags, Collection<IType> allTypes) {
		result.append(getClassType(type));
		result.append(" ");
		appendNameDeclaration(type.getElementName(), result);
		result.append(" {\n");
		try {
			StringBuilder body = new StringBuilder();
			if (includes(genFlags, GEN_MEMBERS)) {
				for (IField field : type.getFields()) {
					String fieldTypeSignature = getSignature(field.getTypeSignature());
					if (includes(genFlags, GEN_ASSOCIATIONS)) {
						generateRelatedType(type, field.getTypeSignature(), ASSOCIATION_RELATION, null, body, null, field.getElementName(), (isMulti(fieldTypeSignature) ? "*" : "1"));
					} else {
						body.append("\t");
						if (! Flags.isEnum(type.getFlags())) {
							if (includes(genFlags, GEN_MODIFIERS) && (! Flags.isInterface(type.getFlags()))) {
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
					if (includes(genFlags, GEN_MODIFIERS) && (! Flags.isInterface(type.getFlags()))) {
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
			if (includes(genFlags, GEN_EXTENDS)) {
				generateRelatedType(type, type.getSuperclassTypeSignature(), EXTENDS_RELATION, (type.isInterface() ? INTERFACE_TYPE : null), result);
			}
			if (includes(genFlags, GEN_IMPLEMENTS)) {
				String[] interfaceSignatures = type.getSuperInterfaceTypeSignatures();
				for (int i = 0; i < interfaceSignatures.length; i++) {
					generateRelatedType(type, interfaceSignatures[i], (type.isInterface() ? EXTENDS_RELATION : IMPLEMENTS_RELATION), INTERFACE_TYPE, result);
				}
			}
		} catch (JavaModelException e) {
		}
	}

	private boolean isMulti(String typeSignature) {
		if (typeSignature.contains("[]")) {
			return true;
		}
		if (typeSignature.contains("Collection<")) {
			return true;
		}
		if (typeSignature.contains("List<")) {
			return true;
		}
		return false;
	}

	private void generateRelatedType(IType type, String classSignature, String relation, String classType, StringBuilder result) {
		generateRelatedType(type, classSignature, relation, classType, result, null, null, null);
	}
	private void generateRelatedType(IType type, String classSignature, String relation, String classType, StringBuilder result, String startLabel, String middleLabel, String endLabel) {
		if (classSignature != null) {
			String className = getSignature(classSignature);
			if (! className.equals("Object")) {
				appendClassStart(null, (classType != null ? classType : CLASS_TYPE), className, result);
				appendClassEnd(result);
				appendRelation(className, false, startLabel, relation, null, type.getElementName(), false, endLabel, middleLabel, result);
			}
		}
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
	
//	private String getClassType(String signature, IType relativeTo, String def) {
//		IType type = null;
//		if (currentContext != null && currentContext.javaProject != null) {
//			String typeName = Signature.toString(signature);
//			if (typeName.lastIndexOf('.') < 0) {
//				try {
//					String[][] typeNames = relativeTo.resolveType(typeName);
//					if (typeNames != null && typeNames.length > 0 && typeNames[0].length >= 2) {
//						typeName = (typeNames[0][0] != null ? typeNames[0][0] + "." : "") + typeNames[0][1];
//					}
//				} catch (JavaModelException e) {
//				}
//			}
//			try {
//				type = currentContext.javaProject.findType(typeName);
//			} catch (JavaModelException e) {
//			} catch (IllegalArgumentException e) {
//			}
//		}
//		return (type != null ? getClassType(type) : def);
//	}

	private String getClassType(IType type) {
		try {
			int flags = type.getFlags();
			if (Flags.isEnum(flags)) {
				return ENUM_TYPE;
			} else if (Flags.isInterface(flags)) {
				return INTERFACE_TYPE;
			} else if (Flags.isAbstract(flags)) {
				return "abstract " + CLASS_TYPE;
			} else {
				return CLASS_TYPE;
			}
		} catch (JavaModelException e) {
			return "";
		}
	}
}
