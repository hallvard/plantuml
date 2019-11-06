package net.sourceforge.plantuml.eclipse.test.util;

import org.eclipse.core.resources.IProject;

public interface ProjectConfigurer {
	public void configureProject(IProject project) throws Exception;
}
