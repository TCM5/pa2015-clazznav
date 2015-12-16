package pt.iscte.pidesco.clazznav.ui;

import java.io.InputStream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import pt.iscte.pidesco.clazznav.core.HistoryManager;

public class SimpleNavigator extends AbstractNavigator implements NavigatorInterface {

	private static SimpleNavigator instance;

	private Button previousButton;
	private Button afterButton;
	private Button graphicModeButton;

	private HistoryManager historyManager = HistoryManager.getInstance();

	private SimpleNavigator(Composite composite){
		super(composite);
	}


	public static  SimpleNavigator getInstance(Composite composite) {
		if( instance == null)
			return instance = new SimpleNavigator(composite);
		return instance;
	} 

	public static  SimpleNavigator getInstance() {
		return instance;
	} 


	/**
	 * 
	 */
	@Override
	public void build() {

		previousButton = new Button(getComposite(),  SWT.ARROW | SWT.LEFT);

		graphicModeButton = new Button(getComposite(), SWT.ARROW_UP);


		InputStream input = this.getClass().getResourceAsStream("/images/navigator_icon2.png");

		if( input != null ){
			Image image = new Image(getComposite().getDisplay() , input);
			graphicModeButton.setImage(image);
		}

		afterButton = new Button(getComposite(),  SWT.ARROW | SWT.RIGHT);

		layout();
		buttonListeners();
	}

	/**
	 * 
	 */
	@Override
	public void layout() {
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.grabExcessHorizontalSpace = true;
		data.heightHint = 50;

		previousButton.setLayoutData(data);
		afterButton.setLayoutData(data);
		graphicModeButton.setLayoutData(data);;
	}

	/**
	 * 
	 */
	private void buttonListeners(){

		previousButton.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				historyManager.goBack();
			}
		});


		graphicModeButton.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				if(GraphicNavigator.getInstance().isEnabled()){
					GraphicNavigator.getInstance().disable();
				}
				else{
					GraphicNavigator.getInstance().enable();
				}
			}
		});


		afterButton.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				historyManager.goForward();
			}
		});
	}

	@Override
	public void dispose() {
		previousButton.dispose();
		afterButton.dispose();
		graphicModeButton.dispose();
	}

	/**
	 * @return the previousButton
	 */
	public Button getPreviousButton() {
		return previousButton;
	}

	/**
	 * @param previousButton the previousButton to set
	 */
	public void setPreviousButton(Button previousButton) {
		this.previousButton = previousButton;
	}

	/**
	 * @return the afterButton
	 */
	public Button getAfterButton() {
		return afterButton;
	}

	/**
	 * @param afterButton the afterButton to set
	 */
	public void setAfterButton(Button afterButton) {
		this.afterButton = afterButton;
	}

	/**
	 * @return the graphicModeButton
	 */
	public Button getGraphicModeButton() {
		return graphicModeButton;
	}

	/**
	 * @param graphicModeButton the graphicModeButton to set
	 */
	public void setGraphicModeButton(Button graphicModeButton) {
		this.graphicModeButton = graphicModeButton;
	}

}
