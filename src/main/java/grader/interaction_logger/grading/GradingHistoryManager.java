package grader.interaction_logger.grading;

import grader.interaction_logger.InteractionLogListener;

public interface GradingHistoryManager extends InteractionLogListener{

	public abstract void readInteractionDirectory();

	public abstract void buildHistories();

	public static final int PARTS_IN_LOG_FILE_NAME = 4;

	//	public  SavedAllStudentsProblemGradingHistory createSavedAllStudentsProblemGradingHistory(File aFile) {
	//		String fileName = aFile.getName();
	//		String[] parts = fileName.split(AnInteractionLogWriter.SEPARATOR);
	//		if (parts.length != PARTS_IN_LOG_FILE_NAME)
	//			return null;
	//		String aGraderName = parts[0];
	//		String aModuleName = parts[2];
	//		String aProblemName = parts[3];
	//		return new ASavedAllStudentsProblemGradingHistory(aGraderName, aModuleName, aProblemName);
	//		
	//		
	//	}

	public abstract void buildProblemHistories();

	public abstract void unparseProblemHistories();

	public abstract void buildCurrentProblemHistory();

	public abstract void newCSVRow(String[] aRow);

	public abstract void buildStudentHistories();

	void connectToCurrentHistory();

}