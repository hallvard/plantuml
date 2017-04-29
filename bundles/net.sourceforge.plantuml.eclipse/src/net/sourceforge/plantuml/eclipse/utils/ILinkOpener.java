package net.sourceforge.plantuml.eclipse.utils;

public interface ILinkOpener {
	public int NO_SUPPORT = -1, DEFAULT_SUPPORT = 0, CUSTOM_SUPPORT = 1;
	public int supportsLink(LinkData link);
	public void openLink(LinkData link);
}
