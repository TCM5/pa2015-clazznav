package pt.iscte.pidesco.clazznav;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import pt.iscte.pidesco.clazznav.core.ExtensionManager;
import pt.iscte.pidesco.clazznav.core.JavaEditorListenerImpl;
import pt.iscte.pidesco.javaeditor.service.JavaEditorServices;
/**
 * ClazznavActivator Object
 * 
 * <p>This class consists on definition of the activator for ClazzNav Plugin. 
 * All the the services from dependent plugin are invoked here, and their services implementation
 * 
 * @author tiagocms
 * @verrsion 1.0
 */

public class ClazznavActivator implements BundleActivator {

	/**
	 * The unique instance of ClazznavActivator
	 */
	private static ClazznavActivator instance;


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
	public JavaEditorListenerImpl javaEditorListener;

	/**
	 * TODO
	 * 
	 * @return	unique instance of the ClazznavActivator.
	 * @see 	ClazznavActivator
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
		bundleContext.ungetService(javaEditorReference);
	}

}
