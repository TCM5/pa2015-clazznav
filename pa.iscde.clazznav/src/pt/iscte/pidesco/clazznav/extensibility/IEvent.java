package pt.iscte.pidesco.clazznav.extensibility;

import pt.iscte.pidesco.clazznav.core.HistoryEntry;

public interface IEvent {

	/**
	 * This method should be called when the event of an opened file is called.
	 * Here is possible to define how the back feature works in historic navigation,
	 * giving the opportunity to define new rules.
	 * 
	 *  
	 * @param from {@link HistoryEntry}
	 * @param to {@link HistoryEntry}
	 */
	void backNavigation(HistoryEntry from, HistoryEntry to);
	
	
	/**
	 * This method should be called when the event of an opened file is called.
	 * Here is possible to define how the forward feature works in historic navigation,
	 * giving the opportunity to define new rules.
	 * 
	 *  
	 * @param from {@link HistoryEntry}
	 * @param to {@link HistoryEntry}
	 */
	void forwardNavigation(HistoryEntry from, HistoryEntry to);
}
