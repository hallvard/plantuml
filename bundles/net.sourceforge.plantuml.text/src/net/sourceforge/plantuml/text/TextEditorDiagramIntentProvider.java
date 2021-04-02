package net.sourceforge.plantuml.text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.eclipse.core.runtime.IPath;

public class TextEditorDiagramIntentProvider extends AbstractTextDiagramIntentProvider {

	private final String diagramPrefix;
	private String diagramPrefixRegex;
	private final String diagramSuffix;
	private String diagramSuffixRegex;
	private final Collection<String> fileExtensions = new ArrayList<String>();

	public TextEditorDiagramIntentProvider(final String diagramPrefix, final String diagramSuffix, final String... fileExtensions) {
		this.diagramPrefix = diagramPrefix;
		this.diagramSuffix = diagramSuffix;
		setFileExtensions(fileExtensions);
	}

	protected void setFileExtensions(final String... fileExtensions) {
		this.fileExtensions.addAll(Arrays.asList(fileExtensions));
	}

	public void setDiagramPrefixRegex(final String diagramPrefixRegex) {
		this.diagramPrefixRegex = diagramPrefixRegex;
	}

	public void setDiagramSuffixRegex(final String diagramSuffixRegex) {
		this.diagramSuffixRegex = diagramSuffixRegex;
	}

	@Override
	protected String getStartPlantUml() {
		return diagramPrefix;
	}
	@Override
	protected String getStartPlantUmlRegex() {
		return diagramPrefixRegex;
	}

	@Override
	protected String getEndPlantUml() {
		return diagramSuffix;
	}
	@Override
	protected String getEndPlantUmlRegex() {
		return diagramSuffixRegex;
	}

	public boolean supportsPath(final IPath path) {
		return fileExtensions == null || fileExtensions.isEmpty() || fileExtensions.contains(path.getFileExtension());
	}
}
