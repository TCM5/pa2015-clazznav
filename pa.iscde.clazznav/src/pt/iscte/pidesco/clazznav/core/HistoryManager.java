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
	 * 
	 */
	private ServiceReference<JavaEditorServices> javaEditorReference = ClazznavActivator.getInstance().javaEditorReference;


	/**
	 * List of files that have been consulted/opened
	 */
	public ArrayList<File> navigatedFiles = new ArrayList<>();

	/**
	 * 
	 */
	public int currentFilePosition = -1;
	
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
	 * Call the service of JavaEditor to open the previous entry of the current one. 
	 * TODO
	 * 
	 */
	public void goBack(){

		if( navigatedFiles.size() > 1 && currentFilePosition > 0 ){

			currentFile = navigatedFiles.get( --currentFilePosition ); 
			currentFilePosition = navigatedFiles.indexOf( currentFile );
			openCurrentFile( currentFilePosition );
			System.out.println("Numero de ficheiros no historico: " + navigatedFiles.size() + " - Ficheiro actual: " + currentFilePosition);
		}
	}


	/** 
	 * TODO
	 * 
	 */
	public void goForward(){

		if( navigatedFiles.size() > 1 && currentFilePosition <= navigatedFiles.size() - 2 ){

			currentFile = navigatedFiles.get( ++currentFilePosition ) ; 
//			currentFilePosition = navigatedFiles.indexOf( currentFile ); //bug??
			openCurrentFile( currentFilePosition );
			System.out.println("Numero de ficheiros no historico: " + navigatedFiles.size() + " - Ficheiro actual: " + currentFilePosition);
		}
	}

	/**
	 * 
	 */
	private void openCurrentFile(int index){
		isAdding = false;

		File file = navigatedFiles.get(index);
		javaEditorServices.openFile(file);

		isAdding = true;
	}

	/**
	 * This method is responsible to add an entry to the list of historic that stores all the steps navigated <p>
	 * It also updates the current file ( .java class for example ) consulted in editor.
	 * @param file
	 */
	public void addEntry(File file) {
		navigatedFiles.add(file);
		currentFilePosition++;		
		System.out.println("Added file " + file + " to the navigation history.");
		System.out.println("Numero de ficheiros no historico: " + navigatedFiles.size() + " - Ficheiro actual: " + currentFilePosition);
	}
}
