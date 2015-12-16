package pt.iscte.pidesco.clazznav;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import pt.iscte.pidesco.clazznav.core.JavaEditorListenerImpl;
import pt.iscte.pidesco.clazznav.utils.ServiceLogger;
import pt.iscte.pidesco.javaeditor.service.JavaEditorServices;
/**
 * 
 * @author tiagocms
 * 
 */
public class ClazznavActivator implements BundleActivator {

	/**
	 * 
	 */
	private static ClazznavActivator instance;

	/**
	 * 
	 */
	private ServiceLogger log = new ServiceLogger();

	/**
	 * 
	 */
	private static BundleContext context;

	/**
	 * 
	 */
	public ServiceReference<JavaEditorServices> javaEditorReference;
	
	/**
	 * 
	 */
	public JavaEditorServices javaEditorService;

	/**
	 * 
	 */
	public static JavaEditorListenerImpl listener;

	/**
	 * 
	 * @return
	 */
	public static ClazznavActivator getInstance(){
		return instance == null ? new ClazznavActivator() : instance;
	}

	
	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(final BundleContext bundleContext) throws Exception {
		instance = this;

		log.log(0, "Starting bundle" );

		javaEditorReference = bundleContext.getServiceReference( JavaEditorServices.class );
		log.log(javaEditorReference, 0, "Starting...");
		javaEditorService = bundleContext.getService( javaEditorReference );

		listener = new JavaEditorListenerImpl();
		javaEditorService.addListener(listener);
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		ClazznavActivator.context = null;
		bundleContext.ungetService(javaEditorReference);
	}

	/**
	 * 
	 * @return The context of the bundle
	 */
	static BundleContext getContext() {
		return context;
	}

}
