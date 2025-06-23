package net.sourceforge.plantuml.uml2;

import static net.sourceforge.plantuml.uml2.IndentUtils.indentBlock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.eclipse.uml2.uml.CallEvent;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Event;
import org.eclipse.uml2.uml.FinalState;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.Pseudostate;
import org.eclipse.uml2.uml.PseudostateKind;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.SignalEvent;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.TimeEvent;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.Trigger;
import org.eclipse.uml2.uml.Vertex;

import net.sourceforge.plantuml.text.AbstractClassDiagramIntent;

/**
 * Produce a PlantUML state diagram from a UML state-machine
 */
public class Uml2StateDiagramIntent extends AbstractClassDiagramIntent<Collection<StateMachine>> {

	private static final String INITIAL = "[*]"; //$NON-NLS-1$
	private static final String SHALLOW_HISTORY = "[H]"; //$NON-NLS-1$
	private static final String DEEP_HISTORY = "[H*]"; //$NON-NLS-1$

	protected Uml2StateDiagramIntent(final Collection<StateMachine> source) {
		super(source, "SequenceDiagram diagram"); //$NON-NLS-1$
	}

	public Uml2StateDiagramIntent(final StateMachine source) {
		this(Collections.singletonList(source));
	}

	@Override
	public String getDiagramText() {
		return getDiagramText(getSource().iterator().next());
	}

	private Map<String, String> skinParams = null;

	protected String getDiagramText(StateMachine sm) {
		// TODO GEN_CLASS_HYPERLINKS useful later? (but not with standard URI)
		final String result = getDiagramText(sm, GEN_MEMBERS);
		return result;
	}

	protected boolean shouldSuppress(Element element, final String role) {
		return false;
	}

	protected String getDiagramText(StateMachine sm, final int genFlags) {
		final StringBuilder buffer = new StringBuilder();
		if (skinParams != null) {
			appendSkinParams(skinParams, buffer);
		}

		for (Region region : sm.getRegions()) {
			String regionText = getDiagramText("", region, genFlags);
			buffer.append(regionText);
		}
		return buffer.toString();
	}

	protected String getDiagramText(String prefix, Region region, final int genFlags) {
		final StringBuilder buffer = new StringBuilder();
		for (Vertex vertex : region.getSubvertices()) {
			if (needsDeclaration(vertex)) {
				buffer.append(String.format("state %s", NamingUtils.declName(vertex.getName())));
				// TODO? stereotype before as?
				buffer.append(StereotypeUtils.stereoNames(vertex, false));
				if (vertex instanceof State) {
					// handle nested states
					State state = (State) vertex;
					if (state.getRegions().size() > 0) {
						buffer.append(" {\n");
						for (Region subRegion : ((State) vertex).getRegions()) {
							String subRegionText = getDiagramText(String.format("%s.", state.getName()), subRegion, genFlags);
							indentBlock(buffer, subRegionText);
						}
						buffer.append("}\n");
					}
				}
				buffer.append("\n");
			}
		}
		for (Transition transition : region.getTransitions()) {
			buffer.append(String.format("%s -> %s", stateName(prefix, transition.getSource()), stateName(prefix, transition.getTarget())));
			String label = getTransitionLabel(transition);
			if (label != null && label.length() > 0) {
				buffer.append(String.format(" : %s", label));
			}
			buffer.append("\n");
		}
		return buffer.toString();
	}

	/**
	 * Return whether a state needs to be declared in PlantUML. This is not the case for initial, terminate and history states, they are
	 * only referenced via a pseudo name
	 * 
	 * @param vertex
	 *            a vertex
	 * @return true, if needs declaration
	 */

	public boolean needsDeclaration(Vertex vertex) {
		if (vertex instanceof Pseudostate) {
			PseudostateKind kind = ((Pseudostate) vertex).getKind();
			if (kind == PseudostateKind.INITIAL_LITERAL ||
					kind == PseudostateKind.TERMINATE_LITERAL ||
					kind == PseudostateKind.SHALLOW_HISTORY_LITERAL ||
					kind == PseudostateKind.DEEP_HISTORY_LITERAL) {
				return false;
			}
		}
		if (vertex instanceof FinalState) {
			return false;
		}
		return true;
	}

	/**
	 * @param prefix
	 *            prefix of current container (of a transition)
	 * @param vertex
	 *            a vertex
	 * @return the (qualified) name to use when referencing a state
	 */
	public String stateName(String prefix, Vertex vertex) {
		String smQName = "";
		// calculate qualified name within state machine (i.e. only take composite states as path into account)
		Region container = vertex.getContainer();
		while (container != null) {
			if (container.getOwner() instanceof State) {
				State composite = (State) container.getOwner();
				container = composite.getContainer();
				smQName = String.format("%s.%s", composite.getName(), smQName);
			} else {
				break;
			}
		}
		if (smQName.startsWith(prefix)) {
			// remove prefix, as we're already in the respective container
			smQName = smQName.replaceFirst(prefix, "");
		}
		if (vertex instanceof Pseudostate) {
			PseudostateKind kind = ((Pseudostate) vertex).getKind();
			if (kind == PseudostateKind.INITIAL_LITERAL || kind == PseudostateKind.TERMINATE_LITERAL) {
				return smQName + INITIAL;
			} else if (kind == PseudostateKind.SHALLOW_HISTORY_LITERAL) {
				return smQName + SHALLOW_HISTORY;
			} else if (kind == PseudostateKind.DEEP_HISTORY_LITERAL) {
				return smQName + DEEP_HISTORY;
			}
		}
		if (vertex instanceof FinalState) {
			// PlantUML uses same name
			return INITIAL;
		}
		if (vertex.getName() == null || vertex.getName().contains("null")) {
			return "unamed vertex";
		}
		return smQName + NamingUtils.refName(vertex.getName());
	}

	protected static String getTransitionLabel(Transition transition) {
		String guard = "";
		String trigger = "";
		for (Trigger umlTrigger : transition.getTriggers()) {
			if (umlTrigger.getEvent() != null) {
				Event evt = umlTrigger.getEvent();
				if (evt instanceof TimeEvent) {
					trigger += String.format("after %s", ((TimeEvent) evt).getWhen().stringValue());
				} else if (evt instanceof CallEvent) {
					trigger += String.format("%s(...)", ((CallEvent) evt).getOperation().getName());
				} else if (evt instanceof SignalEvent) {
					trigger += ((SignalEvent) evt).getSignal().getName();
				}
			}
		}
		if (transition.getGuard() != null) {
			guard = String.format("[%s]", transition.getGuard().getSpecification().stringValue());
		}
		String effect = "";
		if (transition.getEffect() != null && transition.getEffect().getName() != null) {
			// use name of effect, if provided
			effect = transition.getEffect().getName();
		} else if (transition.getEffect() instanceof OpaqueBehavior) {
			// else print first line of an opaque behavior
			OpaqueBehavior ob = (OpaqueBehavior) transition.getEffect();
			if (ob.getBodies().size() > 0) {
				try {
					effect = new BufferedReader(new StringReader(ob.getBodies().get(0))).readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		if (trigger.length() > 0 || guard.length() > 0 || effect.length() > 0) {
			return String.format("%s%s/%s", trigger, guard, effect);
		} else {
			return transition.getName();
		}
	}
}
