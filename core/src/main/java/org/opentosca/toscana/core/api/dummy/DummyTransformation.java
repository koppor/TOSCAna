package org.opentosca.toscana.core.api.dummy;

import org.opentosca.toscana.core.logging.Log;
import org.opentosca.toscana.core.transformation.Platform;
import org.opentosca.toscana.core.transformation.Transformation;
import org.opentosca.toscana.core.transformation.TransformationListener;
import org.opentosca.toscana.core.transformation.TransformationState;
import org.opentosca.toscana.core.transformation.artifacts.TargetArtifact;

import java.util.Map;

public class DummyTransformation implements Transformation {

	private TransformationState state = TransformationState.INPUT_REQUIRED;
	private Platform platform;
	private Log log = new DummyLog();
	private boolean returnTargetArtifact = true;

	public DummyTransformation(Platform platform) {
		this.platform = platform;
	}

	@Override
	public TransformationState getState() {
		return state;
	}

	@Override
	public Platform getPlatform() {
		return platform;
	}

	@Override
	public void setProperty(String key, String value) {
		
	}

	@Override
	public Map<String, String> getProperties() {
		return null;
	}

	@Override
	public Log getLog() {
		return log;
	}

	@Override
	public TargetArtifact getTargetArtifact() {
		return returnTargetArtifact ? new TargetArtifact() : null;
	}

	@Override
	public void setOnStateChange(TransformationListener listener) {

	}

	@Override
	public void removeOnStateChange(TransformationListener listener) {

	}

	public void setReturnTargetArtifact(boolean returnTargetArtifact) {
		this.returnTargetArtifact = returnTargetArtifact;
	}
}