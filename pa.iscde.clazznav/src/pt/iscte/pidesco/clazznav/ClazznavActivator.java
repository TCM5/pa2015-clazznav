package pt.iscte.pidesco.clazznav;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import pt.iscte.pidesco.clazznav.core.ExtensionManager;
import pt.iscte.pidesco.clazznav.core.JavaEditorListenerImpl;
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
	public static JavaEditorListenerImpl javaEditorListener;

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

		javaEditorReference = bundleContext.getServiceReference( JavaEditorServices.class );
		javaEditorService = bundleContext.getService( javaEditorReference );

		javaEditorListener = new JavaEditorListenerImpl();
		javaEditorService.addListener( javaEditorListener );
		
		//Apply contributed extensions
		ExtensionManager extensionManager = new ExtensionManager();
		extensionManager.integrateExtensions();

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
