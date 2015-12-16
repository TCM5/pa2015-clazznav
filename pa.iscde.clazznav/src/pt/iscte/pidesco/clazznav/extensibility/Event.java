package pt.iscte.pidesco.clazznav.extensibility;

import java.io.File;

public interface Event {

	/**
	 * This method should be called when the event of an opened file is called.
	 * Here is possible to define how the back feature works in historic navigation,
	 * giving the opportunity to define new rules.
	 * 
	 *  
	 * @param from
	 * @param to
	 */
	void backNavigation(File from, File to);
	
	
	/**
	 * This method should be called when the event of an opened file is called.
	 * Here is possible to define how the forward feature works in historic navigation,
	 * giving the opportunity to define new rules.
	 * 
	 *  
	 * @param from
	 * @param to
	 */
	void forwardNavigation(File from, File to);
}
