package net.sourceforge.plantuml.jdt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IMember;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jdt.internal.debug.ui.actions.OpenTypeAction;
import org.eclipse.jface.viewers.ISelection;

import net.sourceforge.plantuml.text.AbstractClassDiagramTextProvider;

public abstract class JdtDiagramTextProvider extends AbstractClassDiagramTextProvider {

	@Override
	public boolean supportsSelection(ISelection selection) {
		return false;
	}

	public void generateForType(IType type, StringBuilder result, Collection<IType> allTypes) {
		generateForType(type, result, GEN_MEMBERS | GEN_MODIFIERS | GEN_EXTENDS | GEN_IMPLEMENTS | GEN_ASSOCIATIONS | GEN_CLASS_LINKS , allTypes);
	}

	private boolean isInTypes(String typeName, Collection<IType> allTypes) {
		for (IType type : allTypes) {
			if (type.getElementName().equals(typeName) || type.getFullyQualifiedName().equals(typeName)) {
				return true;
			}
		}
		return false;
	}
	
	private static class Assoc {
		String name;
		String targetName;
		boolean multi;
	}
	
	private Collection<String> multiAssociationClassNames = new ArrayList<String>(Arrays.asList("java.util.Collection", "java.util.List", "java.util.Set"));
	
	public void addMultiAssociationClassName(String className) {
		multiAssociationClassNames.add(className);
	}
	
	public boolean isMultiAssociationClassName(String className) {
		return multiAssociationClassNames.contains(className);
	}
	
	private boolean useJavaLinks = true;
	
	protected String getLink(IType type) {
		if (useJavaLinks) {
			return JavaLinkOpener.JAVA_LINK_PREFIX + type.getFullyQualifiedName();
		} else {
			IResource resource = type.getResource();
			if (resource != null) {
				return resource.getFullPath().toString();
			}
		}
		return null;
	}

	public void generateForType(IType type, StringBuilder result, int genFlags, Collection<IType> allTypes) {
		Collection<Assoc> associations = (includes(genFlags, GEN_ASSOCIATIONS) ? new ArrayList<Assoc>() : null);
		result.append(getClassType(type));
		result.append(" ");
		appendNameDeclaration(type.getElementName(), result);
		if (includes(genFlags, GEN_CLASS_LINKS)) {
			appendLink(getLink(type), false, result);
		}
		result.append(" {\n");
		try {
			StringBuilder body = new StringBuilder();
			if (includes(genFlags, GEN_MEMBERS)) {
				for (IField field : type.getFields()) {
					Assoc assoc = null;
					if (includes(genFlags, GEN_ASSOCIATIONS) && acceptAssociation(type, field)) {
						assoc = generateAssociation(type, field);
					}
					if (assoc != null && isInTypes(assoc.targetName, allTypes) && associations != null) {
						assoc.name = field.getElementName();
						associations.add(assoc);
					} else {
						body.append("\t");
						if (! Flags.isEnum(type.getFlags())) {
							if (includes(genFlags, GEN_MODIFIERS) && (! Flags.isInterface(type.getFlags()))) {
								body.append(getMemberModifiers(field));
							}
							body.append(getTypeName(field.getTypeSignature()));
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
						body.append(getTypeName(method.getReturnType()));
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
						body.append(getTypeName(parameterTypes[i]));
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
		if (includes(genFlags, GEN_ASSOCIATIONS) && associations != null) {
			for (Assoc assoc : associations) {
				generateRelatedType(type, assoc.targetName, ASSOCIATION_RELATION, null, result, genFlags, null, assoc.name, assoc.multi ? "*" : "1");
			}
		}
		try {
			if (includes(genFlags, GEN_EXTENDS)) {
				generateRelatedType(type, getTypeName(type.getSuperclassTypeSignature()), EXTENDS_RELATION, (type.isInterface() ? INTERFACE_TYPE : null), result, genFlags);
			}
			if (includes(genFlags, GEN_IMPLEMENTS)) {
				String[] interfaceSignatures = type.getSuperInterfaceTypeSignatures();
				for (int i = 0; i < interfaceSignatures.length; i++) {
					generateRelatedType(type, getTypeName(interfaceSignatures[i]), (type.isInterface() ? EXTENDS_RELATION : IMPLEMENTS_RELATION), INTERFACE_TYPE, result, genFlags);
				}
			}
		} catch (JavaModelException e) {
		}
	}
	
	protected boolean acceptAssociation(IType type, IField field) {
		try {
			int flags = field.getFlags();
			return (! Flags.isEnum(flags)) && (! Flags.isStatic(flags));
		} catch (JavaModelException e) {
		}
		return false;
	}

	protected Assoc generateAssociation(IType type, IField field) throws JavaModelException {
		String fieldSignature = field.getTypeSignature(), fieldTypeName = getTypeName(fieldSignature);
		Assoc assoc = null;
		if (fieldTypeName.endsWith("[]")) {
			assoc = new Assoc();
			assoc.targetName = fieldTypeName.substring(0, fieldTypeName.length() - 2);
			assoc.multi = true;
		} else {
			String[][] resolvedFieldType = type.resolveType(fieldTypeName);
			String[] typeArguments = Signature.getTypeArguments(fieldSignature);
			if (resolvedFieldType != null && resolvedFieldType.length > 0 && typeArguments != null && typeArguments.length == 1 && isMultiAssociationClassName(Signature.toQualifiedName(resolvedFieldType[0]))) {
				assoc = new Assoc();
				assoc.multi = true;
				assoc.targetName = getTypeName(typeArguments[0]);
			} else {
				assoc = new Assoc();
				assoc.multi = false;
				assoc.targetName = fieldTypeName;								
			}
		}
		return assoc;
	}

	private void generateRelatedType(IType type, String className, String relation, String classType, StringBuilder result, int genFlags) {
		generateRelatedType(type, className, relation, classType, result, genFlags, null, null, null);
	}
	private void generateRelatedType(IType type, String className, String relation, String classType, StringBuilder result, int genFlags, String startLabel, String middleLabel, String endLabel) {
		if (className != null && (! className.equals("Object"))) {
			String link = null;
			if (includes(genFlags, GEN_CLASS_LINKS)) {
				try {
					IType relatedType = OpenTypeAction.findTypeInWorkspace(className, false);
					if (relatedType != null) {
						link = getLink(relatedType);
					}
				} catch (CoreException e) {
				}
			}
			appendClassStart(null, (classType != null ? classType : CLASS_TYPE), className, link, result);
			appendClassEnd(result);
			if (relation == ASSOCIATION_RELATION) {
				appendRelation(type.getElementName(), false, startLabel, relation, null, className, false, endLabel, middleLabel, result);				
			} else {
				appendRelation(className, false, startLabel, relation, null, type.getElementName(), false, endLabel, middleLabel, result);
			}
		}
	}

	private String getTypeName(String signature) {
		return signature != null ? Signature.toString(signature).replace("java.lang.", "") : null;
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
