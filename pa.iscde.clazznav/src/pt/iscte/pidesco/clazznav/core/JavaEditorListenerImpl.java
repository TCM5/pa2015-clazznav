package pt.iscte.pidesco.clazznav.core;

import java.io.File;

import pt.iscte.pidesco.clazznav.ui.AbstractNavigator;
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

		if( historyManager.navigatedFiles != null ){
			if (historyManager.isAdding){
				historyManager.navigatedFiles.add(file);
				historyManager.currentFilePosition++;
				System.out.println("Added file " + file + " to the navigation history.");
				System.out.println("Numero de ficheiros no historico: " + historyManager.navigatedFiles.size() + " - Ficheiro actual: " + historyManager.currentFilePosition);
			}
		}

		//		if(!AbstractNavigator.files.isEmpty()){
		//			if(!(AbstractNavigator.files.get(AbstractNavigator.files.size() - 1 ).getName().equals(file.getName()))){
		//				AbstractNavigator.files.add(file);
		//				GraphicNavigator.getInstance().refresh();
		//				System.out.println(AbstractNavigator.files.get(AbstractNavigator.files.size()-1).getName());
		//			}
		//		}
		//		else {
		//			AbstractNavigator.files.add(file);
		////			GraphicNavigator.getInstance().refresh();
		//			System.out.println(AbstractNavigator.files.get(AbstractNavigator.files.size()-1).getName());
		//		}

	}

	/**
	 * 
	 */
	@Override
	public void fileClosed(File file) {
		super.fileClosed(file);
		System.out.println("ENTREI");
		if( historyManager.navigatedFiles != null ){
			if (historyManager.isAdding){
				historyManager.navigatedFiles.add(file);
				historyManager.currentFilePosition++;
				System.out.println("Added file " + file + " to the navigation history.");
			}
		}
	}


	/**
	 * 
	 */
	@Override
	public void selectionChanged(File file, String text, int offset, int length) {
		super.selectionChanged(file, text, offset, length);

		if( historyManager.navigatedFiles.isEmpty() ){

			historyManager.navigatedFiles.add(file);
			historyManager.currentFilePosition++;
			System.out.println("Added file " + file + " to the navigation history.");
			System.out.println("Numero de ficheiros no historico: " + historyManager.navigatedFiles.size() + " - Ficheiro actual: " + historyManager.currentFilePosition);
		}
		else {
			System.out.println("xx");
		}
	}






}
