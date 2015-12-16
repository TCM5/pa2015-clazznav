
/**
 * 
 * @author santostc
 *
 */
public aspect clazznavLogging {

	pointcut startingServices() : call (void pt.iscte.pidesco.clazznav.ClazznavActivator.start(*) );	
	
	before() : startingServices() {
		System.out.println("starting");
	}
	
	after() : startingServices() {
		System.out.println("running");
	}
	
}
