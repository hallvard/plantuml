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
import org.eclipse.jdt.core.ITypeParameter;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jdt.internal.debug.ui.actions.OpenTypeAction;
import org.eclipse.jface.viewers.ISelection;

import net.sourceforge.plantuml.text.AbstractClassDiagramTextProvider;

public abstract class JdtDiagramTextProvider extends AbstractClassDiagramTextProvider {

	@Override
	public boolean supportsSelection(final ISelection selection) {
		return false;
	}

	private final int genFlags = GEN_MEMBERS | GEN_MODIFIERS | GEN_EXTENDS | GEN_IMPLEMENTS | GEN_ASSOCIATIONS | GEN_CLASS_HYPERLINKS;

	public void generateForType(final IType type, final StringBuilder result, final Collection<IType> allTypes) {
		generateForType(type, result, genFlags, allTypes);
	}

	private boolean isInTypes(final String typeName, final Collection<IType> allTypes) {
		for (final IType type : allTypes) {
			if (getTypeName(type, false).equals(typeName) || getTypeName(type, true).equals(typeName) || type.getFullyQualifiedName().equals(typeName)) {
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

	private final Collection<String> multiAssociationClassNames = new ArrayList<String>(Arrays.asList("java.util.Collection", "java.util.List", "java.util.Set"));

	public void addMultiAssociationClassName(final String className) {
		multiAssociationClassNames.add(className);
	}

	public boolean isMultiAssociationClassName(final String className) {
		return multiAssociationClassNames.contains(className);
	}

	private final boolean useJavaLinks = true;

	protected String getHyperlink(final IType type) {
		if (useJavaLinks) {
			return JavaLinkOpener.JAVA_LINK_PREFIX + type.getFullyQualifiedName();
		} else {
			final IResource resource = type.getResource();
			if (resource != null) {
				return resource.getFullPath().toString();
			}
		}
		return null;
	}

	public void generateForType(final IType type, final StringBuilder result, final int genFlags, final Collection<IType> allTypes) {
		final Collection<Assoc> associations = (includes(genFlags, GEN_ASSOCIATIONS) ? new ArrayList<Assoc>() : null);
		result.append(getClassType(type));
		result.append(" ");
		appendNameDeclaration(getTypeName(type, true), result);
		if (includes(genFlags, GEN_CLASS_HYPERLINKS)) {
			appendLink(getHyperlink(type), false, result);
		}
		result.append(" {\n");
		try {
			final StringBuilder body = new StringBuilder();
			if (includes(genFlags, GEN_MEMBERS)) {
				for (final IField field : type.getFields()) {
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
							body.append(getTypeName(field.getTypeSignature(), true));
							body.append(" ");
						}
						body.append(field.getElementName());
						body.append("\n");
					}
				}
				for (final IMethod method : type.getMethods()) {
					Assoc assoc = null;
					if (includes(genFlags, GEN_ASSOCIATIONS) && acceptAssociation(type, method)) {
						assoc = generateAssociation(type, method);
					}
					if (assoc != null && isInTypes(assoc.targetName, allTypes) && associations != null) {
						assoc.name = method.getElementName() + "()";
						associations.add(assoc);
					} else {
						body.append("\t");
						if (includes(genFlags, GEN_MODIFIERS) && (! Flags.isInterface(type.getFlags()))) {
							body.append(getMemberModifiers(method));
						}
						// don't show the return type for constructors
						if (! method.isConstructor()) {
							body.append(getTypeName(method.getReturnType(), true));
							body.append(" ");
						}
						body.append(method.getElementName());
						body.append("(");
						final String[] parameterTypes = method.getParameterTypes();
						String[] parameterNames = null;
						parameterNames = method.getParameterNames();
						for (int i = 0; i < method.getNumberOfParameters(); i++) {
							if (body.charAt(body.length() - 1) != '(') {
								body.append(", ");
							}
							body.append(getTypeName(parameterTypes[i], true));
							if (parameterNames != null) {
								body.append(" ");
								body.append(parameterNames[i]);
							}
						}
						body.append(")\n");
					}
				}
			}
			result.append(body);
		} catch (final JavaModelException e) {
		}
		result.append("}\n");
		if (includes(genFlags, GEN_ASSOCIATIONS) && associations != null) {
			for (final Assoc assoc : associations) {
				generateRelatedType(type, assoc.targetName, ASSOCIATION_RELATION, null, result, genFlags, null, assoc.name, assoc.multi ? "*" : "1");
			}
		}
		try {
			if (includes(genFlags, GEN_EXTENDS)) {
				generateRelatedType(type, getTypeName(type.getSuperclassTypeSignature(), true), EXTENDS_RELATION, (type.isInterface() ? INTERFACE_TYPE : null), result, genFlags);
			}
			if (includes(genFlags, GEN_IMPLEMENTS)) {
				final String[] interfaceSignatures = type.getSuperInterfaceTypeSignatures();
				for (int i = 0; i < interfaceSignatures.length; i++) {
					generateRelatedType(type, getTypeName(interfaceSignatures[i], true), (type.isInterface() ? EXTENDS_RELATION : IMPLEMENTS_RELATION), INTERFACE_TYPE, result, genFlags);
				}
			}
		} catch (final JavaModelException e) {
		}
	}

	protected String getTypeName(final IType type, final boolean includeTypeParameters) {
		String typeName = type.getElementName();
		if (includeTypeParameters) {
			try {
				final ITypeParameter[] typeParameters = type.getTypeParameters();
				for (int i = 0; i < typeParameters.length; i++) {
					final ITypeParameter typeParameter = typeParameters[i];
					typeName += (i == 0 ? "<" : ",");
					typeName += typeParameter.getElementName();
					if (i == typeParameters.length - 1) {
						typeName += ">";
					}
				}
			} catch (final JavaModelException e1) {
			}
		}
		return typeName;
	}

	protected boolean acceptAssociation(final IType type, final IMember member) {
		try {
			final int flags = member.getFlags();
			return (! Flags.isEnum(flags)) && (! Flags.isStatic(flags));
		} catch (final JavaModelException e) {
		}
		return false;
	}

	protected Assoc generateAssociation(final IType type, final IField field) throws JavaModelException {
		final String fieldSignature = field.getTypeSignature();
		return generateAssociation(type, fieldSignature);
	}

	protected Assoc generateAssociation(final IType type, final IMethod method) throws JavaModelException {
		if (method.getNumberOfParameters() == 0) {
			final String fieldSignature = method.getReturnType();
			return generateAssociation(type, fieldSignature);
		}
		else
			return null;
	}

	protected Assoc generateAssociation(final IType type, final String fieldSignature) throws JavaModelException {
		final String fieldTypeName = getTypeName(fieldSignature, true);
		Assoc assoc = null;
		if (fieldTypeName.endsWith("[]")) {
			assoc = new Assoc();
			assoc.targetName = fieldTypeName.substring(0, fieldTypeName.length() - 2);
			assoc.multi = true;
		} else {
			final String[][] resolvedFieldType = type.resolveType(fieldTypeName);
			final String[] typeArguments = Signature.getTypeArguments(fieldSignature);
			if (resolvedFieldType != null && resolvedFieldType.length > 0 && typeArguments != null && typeArguments.length == 1 && isMultiAssociationClassName(Signature.toQualifiedName(resolvedFieldType[0]))) {
				assoc = new Assoc();
				assoc.multi = true;
				assoc.targetName = getTypeName(typeArguments[0], true);
			} else {
				assoc = new Assoc();
				assoc.multi = false;
				assoc.targetName = fieldTypeName;
			}
		}
		return assoc;
	}

	private void generateRelatedType(final IType type, final String className, final String relation, final String classType, final StringBuilder result, final int genFlags) {
		generateRelatedType(type, className, relation, classType, result, genFlags, null, null, null);
	}
	private void generateRelatedType(final IType type, final String className, final String relation, final String classType, final StringBuilder result, final int genFlags, final String startLabel, final String middleLabel, final String endLabel) {
		if (className != null && (! className.equals("Object"))) {
			String link = null;
			if (includes(genFlags, GEN_CLASS_HYPERLINKS)) {
				try {
					final IType relatedType = OpenTypeAction.findTypeInWorkspace(className, false);
					if (relatedType != null) {
						link = getHyperlink(relatedType);
					}
				} catch (final CoreException e) {
				}
			}
			appendClassStart(null, (classType != null ? classType : CLASS_TYPE), className, link, result);
			appendClassEnd(result);
			final String typeName = getTypeName(type, true);
			if (relation == ASSOCIATION_RELATION) {
				appendRelation(typeName, false, startLabel, relation, null, className, false, endLabel, middleLabel, result);
			} else {
				appendRelation(className, false, startLabel, relation, null, typeName, false, endLabel, middleLabel, result);
			}
		}
	}

	protected String getTypeName(String signature, final boolean includeTypeParameters) {
		if (signature != null) {
			if (! includeTypeParameters) {
				final int pos = signature.indexOf('<');
				if (pos > 0) {
					signature = signature.substring(0,  pos);
				}
			}
			return Signature.toString(signature).replace("java.lang.", "");
		}
		return null;
	}

	private String getMemberModifiers(final IMember member) {
		try {
			String modifiers = getMemberVisibilityModifier(member);
			final int flags = member.getFlags();
			if (Flags.isStatic(flags)) {
				modifiers += "{static}";
			} else if (Flags.isAbstract(flags)) {
				modifiers += "{abstract}";
			}
			return modifiers;
		} catch (final JavaModelException e) {
			return "";
		}
	}

	private String getMemberVisibilityModifier(final IMember member) throws JavaModelException {
		try {
			final int flags = member.getFlags();
			if (Flags.isPrivate(flags)) {
				return "-";
			} else if (Flags.isProtected(flags)) {
				return "#";
			} else if (Flags.isPublic(flags)) {
				return "+";
			} else {
				return "~";
			}
		} catch (final JavaModelException e) {
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

	private String getClassType(final IType type) {
		try {
			final int flags = type.getFlags();
			if (Flags.isEnum(flags)) {
				return ENUM_TYPE;
			} else if (Flags.isInterface(flags)) {
				return INTERFACE_TYPE;
			} else if (Flags.isAbstract(flags)) {
				return ABSTRACT_MODIFIER + " " + CLASS_TYPE;
			} else {
				return CLASS_TYPE;
			}
		} catch (final JavaModelException e) {
			return "";
		}
	}
}
