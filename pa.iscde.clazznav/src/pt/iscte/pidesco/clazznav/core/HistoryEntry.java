package pt.iscte.pidesco.clazznav.core;

import java.io.File;

import pt.iscte.pidesco.clazznav.ClazznavActivator;
import pt.iscte.pidesco.javaeditor.service.JavaEditorServices;

public class HistoryEntry {
	
	
	/**
	 * 
	 */
	private JavaEditorServices javaEditorServices = ClazznavActivator.getInstance().javaEditorService;
	
	/**
	 * 
	 */
	private File file;
	
	/**
	 * 
	 */
	private int inFilePosition;
	
	/**
	 * 
	 * @param file
	 * @param inFilePositionin
	 */
	public HistoryEntry(File file){
		this.file = file;
	}
	
	public HistoryEntry(File file, int inFilePosition ){
		this.file = file;
		this.inFilePosition = inFilePosition;
	}
	
	
	/**
	 * 
	 * @return
	 */
	public File getFile() {
		return file;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getInFilePosition() {
		return inFilePosition;
	}

	
	
}
