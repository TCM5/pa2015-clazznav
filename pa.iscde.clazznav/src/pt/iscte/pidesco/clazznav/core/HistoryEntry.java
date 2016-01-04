package pt.iscte.pidesco.clazznav.core;

import java.io.File;

import pt.iscte.pidesco.clazznav.ClazznavActivator;
import pt.iscte.pidesco.javaeditor.service.JavaEditorServices;

/**
 * 
 * @author santostc
 *
 */
public class HistoryEntry {
		
	/**
	 * 
	 */
	private File file;
	
	/**
	 * 
	 */
	private int cursorPosition;
	
	/**
	 * 
	 * @param file
	 * @param inFilePositionin
	 */
	public HistoryEntry(File file){
		this.file = file;
	}
	
	public HistoryEntry(File file, int cursorPosition ){
		this.file = file;
		this.cursorPosition = cursorPosition;
	}
	
	/**
	 * @return the file
	 */
	public File getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(File file) {
		this.file = file;
	}

	/**
	 * @return the cursorPosition
	 */
	public int getCursorPosition() {
		return cursorPosition;
	}

	/**
	 * @param cursorPosition the cursorPosition to set
	 */
	public void setCursorPosition(int cursorPosition) {
		this.cursorPosition = cursorPosition;
	}

	
	
}
