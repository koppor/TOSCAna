package org.opentosca.toscana.core.util;

import org.opentosca.toscana.core.transformation.Platform;

import java.util.List;

/**
 * This interface is used by the Platform Controller (REST API) to get a list of supported platforms
 */
public interface PlatformProvider {
	/**
	 * @return Returns a list of all supported Platforms
	 */
	List<Platform> getSupportedPlatforms();

	/**
	 * Finds a specific platform by its identifier
	 * @param id the id to look for
	 * @return the resulting platform object, null if the platform does not exist
	 */
	Platform findById(String id);
}
