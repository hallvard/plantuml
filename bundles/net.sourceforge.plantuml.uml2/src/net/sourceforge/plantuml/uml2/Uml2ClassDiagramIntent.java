/**
 * Copyright (c) 2025 CEA LIST and others
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package net.sourceforge.plantuml.uml2;

import static net.sourceforge.plantuml.uml2.IndentUtils.indentBlock;
import static net.sourceforge.plantuml.uml2.IndentUtils.indentOne;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.uml2.uml.Actor;
import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.Extend;
import org.eclipse.uml2.uml.Include;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.MultiplicityElement;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UseCase;
import org.eclipse.uml2.uml.ValueSpecification;

import net.sourceforge.plantuml.eclipse.utils.DiagramIntentProperty;
import net.sourceforge.plantuml.text.AbstractClassDiagramIntent;

/**
 * Produce a PlantUML class diagram from a UML package
 */
public class Uml2ClassDiagramIntent extends AbstractClassDiagramIntent<Collection<Element>> {

	protected Uml2ClassDiagramIntent(final Collection<Element> source) {
		super(source, "Class diagram"); //$NON-NLS-1$
	}

	public Uml2ClassDiagramIntent(final Package source) {
		this(Collections.singletonList(source));
	}

	public Uml2ClassDiagramIntent(final Classifier source) {
		this(Collections.singletonList(source));
	}

	@Override
	public String getDiagramText() {
		StringBuilder buffer = new StringBuilder();
		// TODO GEN_CLASS_HYPERLINKS useful later? (but not with standard URI)
		int genFlags = GEN_MEMBERS | GEN_EXTENDS | GEN_IMPLEMENTS | GEN_ASSOCIATIONS;
		for (Element source : getSource()) {
			if (source instanceof Package) {
				buffer.append(getDiagramText((Package) source, genFlags));
			} else if (source instanceof Classifier) {
				buffer.append(getClassifier((Classifier) source, genFlags));
			}
		}
		// reset filter
		PlantUmlOptions.filterList = null;
		return buffer.toString();
	}

	private Map<String, String> skinParams = null;

	protected boolean shouldSuppress(Element element, final String role) {
		return false;
	}

	protected String getDiagramText(Package pack, final int genFlags) {
		final StringBuilder buffer = new StringBuilder();
		if (skinParams != null) {
			appendSkinParams(skinParams, buffer);
		}
		buffer.append(String.format("package \"%s\" {\n", pack.getName()));
		List<Classifier> classifiers = new ArrayList<>();
		for (PackageableElement pe : pack.getPackagedElements()) {
			if (pe instanceof Classifier) {
				classifiers.add((Classifier) pe);
			}
		}

		for (final Package nestedPkg : pack.getNestedPackages()) {
			if (hasClassifiers(nestedPkg)) {
				String nestedPkgText = getDiagramText(nestedPkg, genFlags);
				indentBlock(buffer, nestedPkgText);
			}
		}
		for (final Classifier classifier : classifiers) {
			if (!shouldSuppress(classifier, null)) {
				String classifierText = getClassifier(classifier, genFlags);
				if (classifierText != null) {
					indentBlock(buffer, classifierText);
				}
			}
		}
		if (includes(genFlags, GEN_EXTENDS) || includes(genFlags, GEN_IMPLEMENTS)) {
			for (final Classifier classifier : classifiers) {
				appendRelationships(classifier, genFlags, buffer);
			}
		}

		if (includes(genFlags, GEN_ASSOCIATIONS)) {
			for (final Classifier classifier : classifiers) {
				if (classifier instanceof Association) {
					appendAssociation((Association) classifier, buffer);
				}
			}
		}

		buffer.append("}\n");
		return buffer.toString();
	}

	/**
	 * Generate the PlantUML text of a classifier
	 * 
	 * @param classifier the classifier
	 * @param genFlags   generation flags
	 * @return
	 */
	protected String getClassifier(Classifier classifier, int genFlags) {
		if (PlantUmlOptions.filterList != null) {
			// filter all elements not in the filter list
			if (!PlantUmlOptions.filterList.contains(classifier)) {
				return null;
			}
		}
		if (classifier instanceof OpaqueBehavior) {
			return null;
		}
		if (classifier instanceof Class || classifier instanceof Interface) {
			return getClassText(classifier, genFlags);
		} else if (classifier instanceof UseCase) {
			return getUseCaseText(classifier, genFlags);
		} else if (classifier instanceof Actor) {
			return getActorText(classifier, genFlags);
		} else if (classifier instanceof Enumeration) {
			return getEnumText((Enumeration) classifier, genFlags);
		} else if (classifier instanceof DataType) {
			return getDataTypeText((DataType) classifier, genFlags);
		}
		return null;
	}

	/**
	 * Append the PlantUML text for generalization and dependency relationships
	 */
	protected void appendRelationships(final Classifier classifier, final int genFlags, StringBuilder buffer) {
		List<Classifier> generals = new ArrayList<Classifier>();
		generals.addAll(classifier.getGenerals());
		// no distinction between extension and implements in plantUML
		generals.addAll(classifier.allRealizedInterfaces());
		for (final Classifier superClass : generals) {
			if (!shouldSuppress(superClass, "superType")) {
				final boolean isImplements = superClass instanceof Interface && !(classifier instanceof Interface);
				if (includes(genFlags, isImplements ? GEN_IMPLEMENTS : GEN_EXTENDS)) {
					appendGeneralisation(classifier, superClass, isImplements, buffer);
				}
			}
		}
		// dependencies
		for (final Dependency dep : classifier.getClientDependencies()) {
			if (dep.getSuppliers().size() > 0) {
				NamedElement supplier = dep.getSuppliers().get(0);
				appendDependency(classifier, supplier, buffer);
			}
		}
	}

	/** 
	 * Override in order not to escape the "." in the qualified name, but do escape spaces
	 */
	protected String getLogicalName(final String name) {
		String lName = super.getLogicalName(name);
		String qName = "";
		for (int i = 0; i<lName.length(); i++) {
			if (name.charAt(i) == '.') {
				qName += '.';
			}
			else {
				qName += lName.charAt(i);
			}
		}
		return qName;
	}
	
	protected void appendGeneralisation(final Classifier subClass, final Classifier superClass,
			final boolean isImplements, final StringBuilder buffer) {
		indentOne(buffer);
		Package cp = subClass.getNearestPackage();	// generalization is added in pkg of sub class
		appendGeneralisation(NamingUtils.getName(subClass, cp), NamingUtils.getName(superClass, cp), isImplements, buffer);
	}

	protected void appendDependency(NamedElement source, NamedElement target, StringBuilder buffer) {
		indentOne(buffer);
		Package cp = source.getNearestPackage();	// generalization is added in pkg of source
		appendRelation(getLogicalName(NamingUtils.getName(source, cp)), false, null, "..>", null, getLogicalName(NamingUtils.getName(target, cp)),
				false, null, null, buffer);

	}

	protected String getClassifierColor(final Classifier classifier) {
		// final Map<String, String> skinParams =
		// diagramHelper.getSkinParams(classifier, null);
		// return (skinParams != null ? skinParams.get("BackgroundColor") : null);
		return null;
	}

	protected String getClassText(final Classifier classifier, final int genFlags) {
		final StringBuilder buffer = new StringBuilder();

		CommentUtils.append(classifier, buffer);

		Package currentPkg = classifier.getNearestPackage();
		boolean isInterface = classifier instanceof Interface;
		final String modifiers = classifier.isAbstract() && !isInterface ? "abstract" : null;
		String link = null;

		String color = getClassifierColor(classifier);

		// don't use appendClassStart, as it does not support stereotypes
		if (modifiers != null) {
			buffer.append(modifiers);
			buffer.append(" ");
		}
		buffer.append(isInterface ? "interface" : "class");
		buffer.append(" ");
		appendNameDeclaration(NamingUtils.declName(classifier.getName()), buffer);
		buffer.append(StereotypeUtils.stereoNames(classifier, false));
		appendLink(link, false, buffer);
		if (color != null) {
			buffer.append(" #");
			buffer.append(color);
		}
		buffer.append(" {\n");

		if (classifier instanceof Class) {
			for (Classifier nested : ((Class) classifier).getNestedClassifiers()) {
				if (nested instanceof Enumeration) {
					String enumText = getEnumText((Enumeration) nested, genFlags);
					indentBlock(buffer, enumText);
				}
			}
		}
		if (includes(genFlags, GEN_MEMBERS)) {
			for (final Property attribute : ClassifierUtils.getOwnedAttributes(classifier)) {
				final Type eType = attribute.getType();
				if (!(shouldSuppress(attribute, null) || (eType != null && shouldSuppress(eType, "attribute")))) {
					String attrName = StereotypeUtils.stereoNames(attribute, true);
					attrName += attribute.getName();

					CommentUtils.append(attribute, buffer, true);
					appendAttribute(attribute.isDerived(), ModifiersUtil.modifiers(attribute),
							VisibilityUtils.visibility(attribute), NamingUtils.typeName(attribute, currentPkg), attrName, buffer);
					ValueSpecification df = attribute.getDefaultValue();
					if (df != null && df.stringValue() != null) {
						// ignore empty default values and multi-line ones which are likely containing
						// code
						if (df.stringValue().length() > 0 && !df.stringValue().contains("\n")) {
							// insert at position -1, since buffer already contains line break
							buffer.insert(buffer.length() - 1,
									String.format(" = %s", attribute.getDefaultValue().stringValue()));
						}
					}
				}
			}
			for (final Operation op : ClassifierUtils.getOwnedOperations(classifier)) {
				final Type retType = op.getType();
				if (!(shouldSuppress(op, null) || (retType != null && shouldSuppress(retType, "operation")))) {
					final Collection<String> parameters = new ArrayList<String>();
					Parameter retParam = null;
					for (final Parameter parameter : op.getOwnedParameters()) {
						if (parameter.getDirection() != ParameterDirectionKind.RETURN_LITERAL) {
							// return already handled by type of operation
							String paramString;
							if (parameter.getType() != null) {
								paramString = String.format("%s : %s", parameter.getName(),
										NamingUtils.typeName(parameter, currentPkg));
							} else {
								paramString = parameter.getName();
							}
							parameters.add(paramString);
						} else {
							retParam = parameter;
						}
					}
					String opName = StereotypeUtils.stereoNames(op, true);
					opName += op.getName();
					// do not append "void", if no return type
					CommentUtils.append(op, buffer, true);
					appendOperation(ModifiersUtil.modifiers(op), VisibilityUtils.visibility(op),
							retParam != null ? NamingUtils.typeName(retParam, currentPkg) : null, opName, parameters, buffer);
				}
			}
		}
		appendClassEnd(buffer);
		return buffer.toString();
	}

	protected String getUseCaseText(final Classifier classifier, final int genFlags) {
		final StringBuilder buffer = new StringBuilder();

		UseCase uc = (UseCase) classifier;
		buffer.append(String.format("(%s)\n", classifier.getName()));
		for (Extend ex : uc.getExtends()) {
			if (ex.getTargets().iterator().hasNext()) {
				Element target = ex.getTargets().iterator().next();
				if (target instanceof NamedElement) {
					buffer.append(String.format("%s .> %s : extends\n", refName(uc), refName((NamedElement) target)));
				}
			}
		}
		for (Include inc : uc.getIncludes()) {
			if (inc.getTargets().iterator().hasNext()) {
				Element target = inc.getTargets().iterator().next();
				if (target instanceof NamedElement) {
					buffer.append(String.format("%s .> %s : include\n", refName(uc), refName((NamedElement) target)));
				}
			}
		}
		return buffer.toString();
	}

	protected String refName(final NamedElement ne) {
		if (ne instanceof UseCase) {
			return String.format("(%s)", ne.getName());
		} else if (ne instanceof Actor) {
			return String.format(":%s:", ne.getName());
		} else {
			return String.format("%s", ne.getName());
		}
	}

	protected String getActorText(final Classifier classifier, final int genFlags) {
		final StringBuilder buffer = new StringBuilder();

		buffer.append(String.format(":%s:\n", classifier.getName()));
		return buffer.toString();
	}

	protected String getDataTypeText(final DataType dataType, final int genFlags) {
		final StringBuilder buffer = new StringBuilder();
		String link = null;

		appendClassStart(null, "class", dataType.getName(), link, getClassifierColor(dataType), buffer);
		if (dataType.getName() != null) {
			appendAttribute(null, null, null, dataType.getName(), buffer);
		}
		appendClassEnd(buffer);
		return buffer.toString();
	}

	protected String getEnumText(final Enumeration uEnum, final int genFlags) {
		final StringBuilder buffer = new StringBuilder();
		String link = null;

		appendClassStart(null, "enum", uEnum.getName(), link, getClassifierColor(uEnum), buffer);
		for (final EnumerationLiteral literal : uEnum.getOwnedLiterals()) {
			appendAttribute(null, null, null /* type: */, literal.getName(), buffer);
		}
		appendClassEnd(buffer);
		return buffer.toString();
	}

	private final static String CLASS_DIAGRAM__USE_DATA_TYPE_INSTANCE_CLASS_NAME = "ecoreClassDiagram.useDataTypeInstanceClassName";

	@DiagramIntentProperty(name = CLASS_DIAGRAM__USE_DATA_TYPE_INSTANCE_CLASS_NAME, type = Boolean.class)
	protected boolean useDataTypeInstanceClassName() {
		return getIntentProperties().getProperty(CLASS_DIAGRAM__USE_DATA_TYPE_INSTANCE_CLASS_NAME, Boolean.class,
				false);
	}

	protected String getTypeName(final Type type, final String def) {
		String typeName = null;
		if (type != null) {
			typeName = type.getName();
		} else {
			typeName = def;
		}
		return getSimpleName(typeName);
	}

	protected void appendAssociation(final Association assoc, final StringBuilder buffer) {
		final Property source = assoc.getMemberEnds().get(0);
		final Type sourceClass = source.getType();
		final Property target = assoc.getMemberEnds().get(1);
		final Type targetClass = target.getType();
		String relation = BIDIRECTIONAL_ASSOCIATION_RELATION;
		// define relation, as definition in abstract base class is broken
		String leftEnd = getRelationEnd(target, source, true);
		String rightEnd = getRelationEnd(source, target, false);

		if (leftEnd != null) {
			relation = leftEnd + relation;
		}
		if (rightEnd != null) {
			relation = relation + rightEnd;
		}

		final String startLabel = getRoleLabel(source.getName(), getMultiplicity(source));
		final String endLabel = getRoleLabel(target.getName(), getMultiplicity(target));
		// appendAssociation(
		// sourceClass.getName(), source.getAggregation() ==
		// AggregationKind.COMPOSITE_LITERAL, source.getName(), getMultiplicity(source),
		// direction,
		// targetClass.getName(), target.getAggregation() ==
		// AggregationKind.COMPOSITE_LITERAL, target.getName(), getMultiplicity(target),
		// assoc.getName(), buffer);
		indentOne(buffer);
		Package cp = assoc.getNearestPackage();
		appendRelation(NamingUtils.getName(sourceClass, cp), false, startLabel, relation, null,
					NamingUtils.getName(targetClass, cp), false, endLabel, assoc.getName(), buffer);
	}

	protected String getRelationEnd(Property from, Property to, boolean left) {
		if (to.isNavigable()) {
			// PlantUML does not support combination of navigation and symbol
			return left ? "<" : ">";
		} else if (from.getAggregation() == AggregationKind.SHARED_LITERAL) {
			return "o";
		} else if (from.getAggregation() == AggregationKind.COMPOSITE_LITERAL) {
			return "*";
		}
		return null;
	}

	/**
	 * define our version, as association implementation is broken in abstract base
	 * class (and this implementation is private)
	 * 
	 * @param role name of association end
	 * @param mult multiplicity
	 * @return the role label
	 */
	protected String getRoleLabel(final String role, final String mult) {
		String label = null;
		if (role != null) {
			label = role;
			if (mult != null) {
				label += " " + mult;
			}
		}
		return label;
	}

	// private final boolean suppressSingleMultiplicity = true;

	/**
	 * Calculate multiplicity according to UML rules (also take lower multiplicity
	 * into account)
	 * 
	 * @param feature
	 * @return
	 */
	protected String getMultiplicity(final MultiplicityElement feature) {
		int lower = feature.getLower();
		int upper = feature.getUpper();
		if (lower == 1 && upper == 1) {
			// no for default values
			return null;
		}
		String upperStr = (upper >= 0) ? String.format("%d", upper) : "*";
		if (lower == 1) {
			return String.format("%s", upperStr);
		} else {
			return String.format("%d..%s", lower, upperStr);
		}
	}
	
	/**
	 * check recursively, if a package has any classifiers that should be shown, i.e. are not filtered
	 * @param pkg a UML package
	 * @return true, if the package contains directly or indirectly classifiers that should be shown
	 */
	protected boolean hasClassifiers(Package pkg) {
		for (PackageableElement pe : pkg.getPackagedElements()) {
			if (pe instanceof Classifier) {
				if (PlantUmlOptions.filterList != null) {
					// filter all elements not in the filter list
					if (PlantUmlOptions.filterList.contains(pe)) {
						return true;
					}
				}
				else {
					return true;
				}
			}
			if (pe instanceof Package) {
				boolean classifiersToShow = hasClassifiers((Package) pe);
				if (classifiersToShow) {
					return true;
				}
			}
		}
		return false;
	}
}
