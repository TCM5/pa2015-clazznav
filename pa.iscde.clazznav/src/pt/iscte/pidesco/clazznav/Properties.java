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

	//Extension points
	public final String IEVENT_POINT_ID = "pa.iscde.clazznav.event";
	

}

