package pt.iscte.pidesco.clazznav.core;

import java.io.File;
import java.util.ArrayList;

public class HistoryManager {

	/**
	 * 
	 */
	private static HistoryManager instance;
	
	/**
	 * List of files that have been consulted/opened
	 */
	public ArrayList<File> navigatedFiles = new ArrayList<>();
	
	private static int currentFilePosition = 0;
	private static File currentFile;
	
	
	private HistoryManager(){}
	
	public static HistoryManager getInstance(){
		return instance == null ? instance = new HistoryManager() : instance;
	}
	
	/**
	 * 
	 */
	public void goBack(){
		if( navigatedFiles.size() > 1 && currentFilePosition > 033){
			currentFile = navigatedFiles.get(currentFilePosition--); 
			currentFilePosition = navigatedFiles.indexOf(currentFile);
		}
		
	}
	
	/**
	 * 
	 */
	public void goAfter(){
		if( navigatedFiles.size() > 1 && currentFilePosition < navigatedFiles.size() - 2){
			currentFile = navigatedFiles.get(currentFilePosition++); 
			currentFilePosition = navigatedFiles.indexOf(currentFile);
		}
	}
}
