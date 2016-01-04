package pt.iscte.pidesco.clazznav.ui;

import org.eclipse.swt.widgets.Composite;

import pt.iscte.pidesco.clazznav.core.HistoryManager;
/**
 * 
 * @author santostc
 *
 */
public class AbstractNavigator {

	/**
	 * 
	 */
	protected HistoryManager historyManager = HistoryManager.getInstance();

	/**
	 * 
	 */
	private Composite composite;
	
	/**
	 * 
	 * @param composite
	 */
	public AbstractNavigator(Composite composite) {
		this.composite = composite;
	}

	/**
	 * 
	 * @return
	 */
	public Composite getComposite() {
		return composite;
	}

}
