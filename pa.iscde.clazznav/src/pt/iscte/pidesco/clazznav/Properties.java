package pt.iscte.pidesco.clazznav;

/**
 * 
 * @author tiagocms
 *
 */
public class Properties {

	private static Properties instance;

	public static Properties getInstance() {
		return instance == null ? new Properties() : instance;
	}

	private Properties(){}

	// App configuration
	private boolean DEBUG_MODE = false;
	
	//Extension points
	public final String IEVENT_POINT_ID = "pt.iscte.pidesco.clazznav.event";


}

