package pt.iscte.pidesco.clazznav.core;

import java.io.File;
import java.util.ArrayList;

import org.osgi.framework.ServiceReference;

import pt.iscte.pidesco.clazznav.ClazznavActivator;
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

	private IHistoryNavigation historyNavigation;

	/**
	 * 
	 */
	private int currentFilePosition = -1;

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
	 * 
	 */
	private static File currentFile;


	public boolean isAdding = true;


	private HistoryManager(){}

	public static HistoryManager getInstance(){

		return instance == null ? instance = new HistoryManager() : instance;
	}

	/**
	 * 
	 * @param historyNavigation
	 */
	public void setNavigationMode(IHistoryNavigation historyNavigation){
		this.historyNavigation = historyNavigation;
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
	//	@Override
	//	public void goBack(){
	//		if( navigatedFiles.size() > 1 && currentFilePosition > 0 ){
	//
	//			HistoryEntry a = navigatedFiles.get( --currentFilePosition ); 
	//			currentFilePosition = navigatedFiles.indexOf( a );
	//			openCurrentFile( currentFilePosition );
	//			
	////			currentFile = navigatedFiles.get( --currentFilePosition ).getFile(); 
	////			currentFilePosition = navigatedFiles.indexOf( currentFile );
	////			openCurrentFile( currentFilePosition );
	//			System.out.println("Numero de ficheiros no historico: " + navigatedFiles.size() + " - Ficheiro actual: " + currentFilePosition);
	//		}
	//	}
	//
	//
	//	/** 
	//	 * TODO
	//	 * 
	//	 */
	//	@Override
	//	public void goForward(){
	//
	//		if( navigatedFiles.size() > 1 && currentFilePosition <= navigatedFiles.size() - 2 ){
	//
	//			currentFile = navigatedFiles.get( ++currentFilePosition ).getFile() ; 
	////			currentFilePosition = navigatedFiles.indexOf( currentFile ); //bug??
	//			openCurrentFile( currentFilePosition );
	//			System.out.println("Numero de ficheiros no historico: " + navigatedFiles.size() + " - Ficheiro actual: " + currentFilePosition);
	//		}
	//	}

	/**
	 * 
	 */
	private void openCurrentFile(int index){
		isAdding = false;


		HistoryEntry entry = navigatedFiles.get(index);

		javaEditorServices.openFile( entry.getFile() );
		javaEditorServices.selectText( entry.getFile(), entry.getInFilePosition(), 0);

		isAdding = true;
	}

	/**
	 * This method is responsible to add an entry to the list of historic that stores all the steps navigated <p>
	 * It also updates the current file ( .java class for example ) consulted in editor.
	 * @param file
	 */
	public void addEntry(HistoryEntry entry) {
		navigatedFiles.add( entry );
		currentFilePosition++;		
		//		System.out.println("Added file " + entry.getFile() + " to the navigation history.");
		//		System.out.println("Numero de ficheiros no historico: " + navigatedFiles.size() + " - Ficheiro actual: " + currentFilePosition);
	}


}
