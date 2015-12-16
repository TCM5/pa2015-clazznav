package pt.iscte.pidesco.clazznav.utils;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.e4.core.di.annotations.Execute;

import pt.iscte.pidesco.clazznav.Properties;
import pt.iscte.pidesco.clazznav.extensibility.Event;

/**
 * 
 * @author santostc
 *
 */
public class RegisteredExtensions {


	@SuppressWarnings("Not tested. Null point shold be throw")
	@Execute
	public void execute(IExtensionRegistry registry) {

		IConfigurationElement[] configElements = registry.getConfigurationElementsFor( Properties.getInstance().IEVENT_POINT_ID );

		try {

			for (IConfigurationElement element : configElements) {

				final Object object = element.createExecutableExtension("class");

				if (object != null && object instanceof Event ) {

					new ISafeRunnable() {						
						@Override
						public void run() throws Exception {

							((Event) object ).backNavigation(null,null);
							((Event) object ).forwardNavigation(null,null);

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

	private void checkContributedExtensions(){
		//TODO
	}

	public static void main(String[] args) {
		new RegisteredExtensions().execute(Platform.getExtensionRegistry());
	}


} 
