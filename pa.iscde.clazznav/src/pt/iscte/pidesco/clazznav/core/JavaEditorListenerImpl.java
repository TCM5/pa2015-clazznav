package pt.iscte.pidesco.clazznav.core;

import java.io.File;

import pt.iscte.pidesco.clazznav.ui.GraphicNavigator;
import pt.iscte.pidesco.javaeditor.service.JavaEditorListener;
import pt.iscte.pidesco.javaeditor.service.JavaEditorServices;

public class JavaEditorListenerImpl extends JavaEditorListener.Adapter{

	private HistoryManager historyManager = HistoryManager.getInstance();

	/**
	 * 
	 * 
	 */
	@Override
	public void fileOpened(File file) {
		super.fileOpened(file);

		HistoryEntry entry = new HistoryEntry( file );
		historyManager.addEntry(entry);
	}

	/**
	 * 
	 */
	@Override
	public void fileClosed(File file) {
		super.fileClosed(file);

		HistoryEntry entry = new HistoryEntry( file );
		historyManager.addEntry(entry);
	}


	//



	/**
	 * 
	 */
	@Override
	public void selectionChanged(File file, String text, int offset, int length) {
		super.selectionChanged(file, text, offset, length);


		if( historyManager.getNavigatedFiles().isEmpty() ){

			HistoryEntry entry = new HistoryEntry(file , offset);
			historyManager.addEntry( entry );

			System.out.println("CHANGED");
		}

	}


}
