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

	private static ClazznavActivator instance;

	private ServiceLogger log = new ServiceLogger();

	private static BundleContext context;

	public  ServiceReference<JavaEditorServices> javaEditorReference;
	public JavaEditorServices javaEditorService;

//	public static ServiceReference<ProjectBrowserServices> projectBrowserReference;
//	public static ProjectBrowserServices projectBrowserService;

	public static JavaEditorListenerImpl listener;


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

		//Chamada a serviço JavaEditor
		javaEditorReference = bundleContext.getServiceReference( JavaEditorServices.class );
		log.log(javaEditorReference, 0, "Starting...");
		javaEditorService = bundleContext.getService( javaEditorReference );

		//Chamada a serviço ProjectBrowser
//		projectBrowserReference = bundleContext.getServiceReference( ProjectBrowserServices.class );
//		log.log(projectBrowserReference, 0, "Starting...");
//		projectBrowserService = bundleContext.getService( projectBrowserReference );

		//
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
//		bundleContext.ungetService();
	}

	/**
	 * 
	 * @return
	 */
	static BundleContext getContext() {
		return context;
	}

}
