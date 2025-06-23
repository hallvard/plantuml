package net.sourceforge.plantuml.uml2;

import static net.sourceforge.plantuml.uml2.IndentUtils.indentBlock;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.uml2.uml.CombinedFragment;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.InteractionFragment;
import org.eclipse.uml2.uml.InteractionOperand;
import org.eclipse.uml2.uml.InteractionOperatorKind;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.MessageEnd;
import org.eclipse.uml2.uml.MessageOccurrenceSpecification;
import org.eclipse.uml2.uml.MessageSort;
import org.eclipse.uml2.uml.MultiplicityElement;
import org.eclipse.uml2.uml.OccurrenceSpecification;
import org.eclipse.uml2.uml.Type;

import net.sourceforge.plantuml.eclipse.utils.DiagramIntentProperty;
import net.sourceforge.plantuml.text.AbstractClassDiagramIntent;

/**
 * Produce a PlantUML sequence diagram from a UML interaction
 */
public class Uml2SeqDiagramIntent extends AbstractClassDiagramIntent<Collection<Interaction>> {

	protected Uml2SeqDiagramIntent(final Collection<Interaction> source) {
		super(source, "SequenceDiagram diagram"); //$NON-NLS-1$
	}

	public Uml2SeqDiagramIntent(final Interaction source) {
		this(Collections.singletonList(source));
	}

	@Override
	public String getDiagramText() {
		return getDiagramText(getSource().iterator().next());
	}

	private Map<String, String> skinParams = null;

	/**
	 * avoid message duplication
	 */
	protected Map<Message, Boolean> msgMap;

	protected String getDiagramText(Interaction ia) {
		// TODO GEN_CLASS_HYPERLINKS useful later? (but not with standard URI)
		final String result = getDiagramText(ia, GEN_MEMBERS);
		return result;
	}

	protected boolean shouldSuppress(Element element, final String role) {
		return false;
	}

	protected String getDiagramText(Interaction ia, final int genFlags) {
		final StringBuilder buffer = new StringBuilder();
		if (skinParams != null) {
			appendSkinParams(skinParams, buffer);
		}

		for (final Lifeline lifeline : ia.getLifelines()) {
			appendLL(lifeline, genFlags, buffer);
		}

		msgMap = new HashMap<Message, Boolean>();

		for (InteractionFragment fragment : ia.getFragments()) {
			// contains also MessageOccurenceSpecifications
			if (fragment instanceof CombinedFragment) {
				CombinedFragment cf = (CombinedFragment) fragment;
				InteractionOperatorKind opKind = cf.getInteractionOperator();
				buffer.append(String.format("%s ", opKind.getLiteral()));
				boolean first = true;
				for (InteractionOperand io : cf.getOperands()) {
					// recursive??
					if (first) {
						first = false;
					} else {
						buffer.append("else ");
					}
					buffer.append(io.getGuard().getSpecification().stringValue());
					buffer.append("\n");
					for (InteractionFragment nestedFragment : io.getFragments()) {
						String nestedFragText = getFragmentText(nestedFragment);
						indentBlock(buffer, nestedFragText);
					}
				}
				buffer.append(String.format("end\n", opKind.getLiteral()));
			} else {
				String fragmentText = getFragmentText(fragment);
				buffer.append(fragmentText);
			}
		}
		return buffer.toString();
	}

	protected String getFragmentText(InteractionFragment fragment) {
		final StringBuilder buffer = new StringBuilder();
		if (fragment instanceof MessageOccurrenceSpecification) {
			MessageOccurrenceSpecification mos = (MessageOccurrenceSpecification) fragment;
			Message message = mos.getMessage();
			if (!msgMap.containsKey(message)) {
				msgMap.put(message, true);
				appendMessage(message, buffer);
			}
		}
		return buffer.toString();
	}

	protected void appendMessage(Message message, StringBuilder buffer) {
		MessageEnd source = message.getSendEvent();
		MessageEnd target = message.getReceiveEvent();
		if (source instanceof OccurrenceSpecification && target instanceof OccurrenceSpecification) {

			MessageSort sort = message.getMessageSort();
			String arrow;
			if (sort == MessageSort.ASYNCH_CALL_LITERAL) {
				arrow = "->>";
			} else if (sort == MessageSort.REPLY_LITERAL) {
				arrow = "-->";
			} else {
				arrow = "->";
			}

			OccurrenceSpecification sourceOS = (OccurrenceSpecification) source;
			OccurrenceSpecification targetOS = (OccurrenceSpecification) target;
			if (sourceOS.getCovered() != null && targetOS.getCovered() != null) {
				buffer.append(String.format("%s%s%s : %s\n",
						sourceOS.getCovered().getName(),
						arrow,
						targetOS.getCovered().getName(),
						message.getName()));
			}
		} else if (source instanceof MessageOccurrenceSpecification) {
			// no target - message lost
			buffer.append(String.format("%s->x] : %s\n",
					((MessageOccurrenceSpecification) source).getCovered().getName(),
					message.getName()));
		} else if (target instanceof MessageOccurrenceSpecification) {
			// no source - message found
			buffer.append(String.format("%s->x[ : %s\n",
					((MessageOccurrenceSpecification) target).getCovered().getName(),
					message.getName()));
		}
		// appendLL(lifeline, genFlags, buffer);
	}

	/**
	 * Append a lifeline
	 * 
	 * @param ia
	 *            an interaction
	 * @param genFlags
	 * @param buffer
	 */
	protected void appendLL(final Lifeline ll, final int genFlags, final StringBuilder buffer) {
		buffer.append("participant " + ll.getName() + "\n");
	}

	private final static String CLASS_DIAGRAM__USE_DATA_TYPE_INSTANCE_CLASS_NAME = "ecoreClassDiagram.useDataTypeInstanceClassName";

	@DiagramIntentProperty(name = CLASS_DIAGRAM__USE_DATA_TYPE_INSTANCE_CLASS_NAME, type = Boolean.class)
	protected boolean useDataTypeInstanceClassName() {
		return getIntentProperties().getProperty(CLASS_DIAGRAM__USE_DATA_TYPE_INSTANCE_CLASS_NAME, Boolean.class, false);
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

	private final boolean suppressSingleMultiplicity = true;

	protected int getMultiplicity(final MultiplicityElement feature) {
		return feature.getUpper() > 1 ? -1 : (suppressSingleMultiplicity ? 0 : 1);
	}
}
