package pt.iscte.pidesco.clazznav.core;

import java.util.ArrayList;

public class DefaultHistoryNavigation implements IHistoryNavigation{

	private HistoryManager historyManager = HistoryManager.getInstance();

	private ArrayList<HistoryEntry> navigatedFiles = historyManager.getNavigatedFiles();

	@Override
	public void goBack() {

		if( navigatedFiles.size() > 1 && historyManager.getCurrentFilePosition() > 0 ){

			int currentFilePosition = historyManager.getCurrentFilePosition();

			HistoryEntry entry = navigatedFiles.get( --currentFilePosition ); 
			currentFilePosition = navigatedFiles.indexOf( entry );
			historyManager.openCurrentFile( currentFilePosition );


		}
	}

	@Override
	public void goForward() {

		if( navigatedFiles.size() > 1 && historyManager.getCurrentFilePosition() <= navigatedFiles.size() - 2 ){

			int currentFilePosition = historyManager.getCurrentFilePosition();

			HistoryEntry entry = navigatedFiles.get( ++currentFilePosition ); 
			currentFilePosition = navigatedFiles.indexOf( entry );
			historyManager.openCurrentFile( currentFilePosition );
		}
	}

}
