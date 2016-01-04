package pt.iscte.pidesco.clazznav.core;

import java.io.File;
import java.util.ArrayList;

import pt.iscte.pidesco.clazznav.ClazznavActivator;
import pt.iscte.pidesco.clazznav.ui.GraphicNavigator;
import pt.iscte.pidesco.javaeditor.service.JavaEditorServices;

public class HistoryManager {

	/**
	 * 
	 */
	private static HistoryManager instance;

	/**
	 * 
	 */
	private JavaEditorServices javaEditorServices = ClazznavActivator.getInstance().javaEditorService;

	/**
	 * List of files that have been consulted/opened
	 */
	private ArrayList<HistoryEntry> navigatedFiles = new ArrayList<>();

	/**
	 * 
	 */
	private IHistoryNavigation historyNavigation;

	/**
	 * 
	 */
	private int currentFilePosition = -1;

	/**
	 * 
	 */
	private static File currentFile;

	/**
	 * 
	 */
	public boolean isAdding = true;

	/**
	 * 
	 */
	private HistoryManager(){}

	/**
	 * 
	 * @return
	 */
	public static HistoryManager getInstance(){
		if( instance == null ){
			instance = new HistoryManager();
			instance.setHistoryNavigation( new DefaultHistoryNavigation() );
		}
		return instance;
	}


	/**
	 * Call the service of JavaEditor to open the previous entry of the current one. 
	 * TODO
	 * 
	 */
	public void navigate(NavigationModes modes){

		switch( modes ){
		case BACK:
			historyNavigation.goBack();
			break;	
		case FORWARD:
			historyNavigation.goForward();
			break;
		default : 
			return;		
		}
	}


	/**
	 * 
	 */
	public void openCurrentFile(int index){
		isAdding = false;


		HistoryEntry entry = navigatedFiles.get(index);

		javaEditorServices.openFile( entry.getFile() );
		javaEditorServices.selectText( entry.getFile(), entry.getCursorPosition(), 0);

		isAdding = true;
	}

	/**
	 * This method is responsible to add an entry to the list of historic that stores all the steps navigated <p>
	 * It also updates the current file ( .java class for example ) consulted in editor.
	 * @param file
	 */

	public void addEntry(HistoryEntry entry){

		if( getNavigatedFiles() != null && isAdding){
			if( entry != null){
				navigatedFiles.add( entry );
				currentFilePosition++;	

				GraphicNavigator.getInstance().refresh();
			}
		}
	}

	/**
	 * @return the navigatedFiles
	 */
	public ArrayList<HistoryEntry> getNavigatedFiles() {
		return navigatedFiles;
	}

	/**
	 * @param navigatedFiles the navigatedFiles to set
	 */
	public void setNavigatedFiles(ArrayList<HistoryEntry> navigatedFiles) {
		this.navigatedFiles = navigatedFiles;
	}

	/**
	 * @return the historyNavigation
	 */
	public IHistoryNavigation getHistoryNavigation() {
		return historyNavigation;
	}

	/**
	 * @param historyNavigation the historyNavigation to set
	 */
	public void setHistoryNavigation(IHistoryNavigation historyNavigation) {
		this.historyNavigation = historyNavigation;
	}

	/**
	 * @return the currentFilePosition
	 */
	public int getCurrentFilePosition() {
		return currentFilePosition;
	}

	/**
	 * @param currentFilePosition the currentFilePosition to set
	 */
	public void setCurrentFilePosition(int currentFilePosition) {
		this.currentFilePosition = currentFilePosition;
	}

	/**
	 * @return the currentFile
	 */
	public static File getCurrentFile() {
		return currentFile;
	}

	/**
	 * @param currentFile the currentFile to set
	 */
	public static void setCurrentFile(File currentFile) {
		HistoryManager.currentFile = currentFile;
	}



}
