package pt.iscte.pidesco.clazznav.core;

import java.util.ArrayList;

public class DefaultHistoryNavigation implements IHistoryNavigation{
	
	private HistoryManager historyManager = HistoryManager.getInstance();
	
	private ArrayList<HistoryEntry> navigatedFiles = historyManager.getNavigatedFiles();

	@Override
	public void goBack() {
				if( navigatedFiles.size() > 1 && historyManager.getCurrentFilePosition() > 0 ){
		
					int currentFilePosition = historyManager.getCurrentFilePosition();
					
					HistoryEntry a = navigatedFiles.get( --currentFilePosition ); 
					currentFilePosition = navigatedFiles.indexOf( a );
//					openCurrentFile( currentFilePosition );
					
					System.out.println("Numero de ficheiros no historico: " + navigatedFiles.size() + " - Ficheiro actual: " + currentFilePosition);
				}
	}

	@Override
	public void goForward() {
		// TODO Auto-generated method stub
		
	}

}
