package pt.iscte.pidesco.clazznav.ui;

import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

import pt.iscte.pidesco.extensibility.PidescoView;
import pt.iscte.pidesco.javaeditor.service.JavaEditorServices;

/**
 * 
 * @author tiagocms
 *
 */
public class ClazznavView implements PidescoView{

	ViewManager viewManager;

	public ClazznavView() {
		//TODO Maybe later
	}

	/**
	 * 
	 */
	@Override
	public void createContents(Composite viewArea, Map<String, Image> imageMap) {

		Composite child = new Composite(viewArea, SWT.NONE);

		viewManager = new ViewManager( child );
		viewManager.setup();

//		viewArea.addDisposeListener(new DisposeListener() {
//
//			@Override
//			public void widgetDisposed(DisposeEvent e) {
//				if(!e.widget.isDisposed()){
//					viewManager.disposeAll();
//					e.widget.dispose();
//				}
//			}
//		});
	}



}
