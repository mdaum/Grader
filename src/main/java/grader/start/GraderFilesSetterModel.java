package grader.start;

import javax.swing.JFrame;

public interface GraderFilesSetterModel {
	public FileSetterModel getDownloadFolder() ;
	public FileSetterModel getTextEditor() ;
	
	public void initFrame(JFrame aFrame) ;
}
