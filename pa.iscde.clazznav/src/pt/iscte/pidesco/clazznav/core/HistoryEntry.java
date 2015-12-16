package pt.iscte.pidesco.clazznav.core;

import java.io.File;

public class HistoryEntry {
	
	
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
	public HistoryEntry(File file, int inFilePosition){
		this.file = file;
		this.inFilePosition = inFilePosition > 1 ? inFilePosition : 0;
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
