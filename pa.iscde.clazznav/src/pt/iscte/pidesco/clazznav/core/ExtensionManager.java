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


	/**
	 * 
	 */
	public void integrateExtensions(){
		IExtensionRegistry registredExtensions = Platform.getExtensionRegistry();

		IExtensionPoint extensionPoints = registredExtensions.getExtensionPoint(Properties.getInstance().IEVENT_POINT_ID);

		IExtension[] contributedExtensions =  extensionPoints.getExtensions();

		for(IExtension contributedExtension : contributedExtensions) {

			IConfigurationElement[] configElements = contributedExtension.getConfigurationElements();

			for(IConfigurationElement configElement : configElements) {
				
				checkContributedExtensions( configElement );

				try {
					Object object = configElement.createExecutableExtension("class");

					if ( object instanceof IEvent ) {
						//						( (IEvent) object ).backNavigation(from, to);
						//						( (IEvent) object ).forwardNavigation(from, to);(from, to);
					}

				} catch (CoreException e1) {
					//TODO
				}
			}
		}
	}


	@SuppressWarnings("Not tested. Null point should be throw")
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
	private void checkContributedExtensions(IConfigurationElement configElement){

		System.out.println("Extension contributer: " + configElement.getDeclaringExtension().getContributor());

		for(int i = 0 ; i < configElement.getAttributeNames().length; i++)
			System.out.println("Extension elements: " + configElement.getAttributeNames()[i]);
	}


}
