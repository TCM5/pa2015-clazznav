package pt.iscte.pidesco.clazznav.core;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.e4.core.di.annotations.Execute;

import pt.iscte.pidesco.clazznav.Properties;
import pt.iscte.pidesco.clazznav.extensibility.IEvent;

/**
 * 
 * @author santostc
 *
 */
public class ExtensionManager {



	public void integrateExtensions(){
		IExtensionRegistry contributedExtensions = Platform.getExtensionRegistry();

		IExtensionPoint extensionPoints = contributedExtensions.getExtensionPoint(Properties.getInstance().IEVENT_POINT_ID);

		IExtension[] extensions =  extensionPoints.getExtensions();

		for(IExtension e : extensions) {

			IConfigurationElement[] confElements = e.getConfigurationElements();

			for(IConfigurationElement c : confElements) {
				System.out.println(c.getDeclaringExtension().getContributor());
				for(int i = 0 ; i < c.getAttributeNames().length; i++)
					System.out.println(c.getAttributeNames()[i]);
				String id = c.getAttribute("id");
				String name = c.getAttribute("name");

				System.out.println(id + " " + name);


				try {
					Object o = c.createExecutableExtension("class");


					if ( o instanceof IEvent ) {
						System.out.println("OHHHHHH");
					}

				} catch (CoreException e1) {
					//TODO
				}
			}
		}
	}


	@SuppressWarnings("Not tested. Null point shold be throw")
	@Execute
	public void execute(IExtensionRegistry registry) {

		IConfigurationElement[] configElements = registry.getConfigurationElementsFor( Properties.getInstance().IEVENT_POINT_ID );

		try {

			for (IConfigurationElement element : configElements) {

				final Object object = element.createExecutableExtension("class");

				if (object != null && object instanceof IEvent ) {

					new ISafeRunnable() {						
						@Override
						public void run() throws Exception {

							((IEvent) object ).backNavigation(null,null);
							((IEvent) object ).forwardNavigation(null,null);

							System.out.println("RUNNING...");
						}						

						@Override
						public void handleException(Throwable exception) {
							//TODO							
						}
					};
				}
			}
		} catch (CoreException ex) {
			//TODO
		}
	}

	/**
	 * 
	 */
	private void checkContributedExtensions(){
		//TODO
	}


}
