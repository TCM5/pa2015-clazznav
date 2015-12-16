package pt.iscte.pidesco.clazznav.utils;

import org.osgi.framework.ServiceReference;
import org.eclipse.equinox.log.*;
/**
 * 
 * @author santostc
 *
 */
public class ServiceLogger implements Logger {

	/**
	 * 
	 */
	private static Logger instance = null;


	@Override
	public void log(ServiceReference<?> sr, int level, String message) {
		System.out.println("Status: " + message + " - Service: " + sr.getClass().getSimpleName() + " (" + level + ") - " + sr );
	}

	@Override
	public void log(ServiceReference<?> sr, int level, String message, Throwable exception) {
		System.out.println("Service: " + sr + " (" + level + ") - Status: " + message + " - Error: " + exception);
	}

	@Override
	public void log(Object context, int level, String message) {
		System.out.println("Service: " + context + " (" + level + ") - Status: " + message );
	}

	@Override
	public void log(Object context, int level, String message, Throwable exception) {
		System.out.println("Service: " + context + " (" + level + ") - Status: " + message + " - Error: " + exception);
	}

	@Override
	public void log(int level, String message) {}
	
	@Override
	public void log(int level, String message, Throwable exception) {}

	
	@Override
	public boolean isLoggable(int level) {
		return false;
	}

	@Override
	public String getName() {
		return "";
	}

	public static Logger getInstance() {
		if(instance == null) {
			instance = new ServiceLogger();
		}
		return instance;
	}

}
