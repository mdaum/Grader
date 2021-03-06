package grader.trace.interaction_logger;

import grader.interaction_logger.manual_grading_stats.AllStudentsProblemHistory;
import grader.interaction_logger.manual_grading_stats.StudentProblemHistory;
import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.trace.GraderInfo;
import grader.trace.stepper.AutoAutoGradeSet;

public class SavedStudentProblemGradingHistoryInfo extends GraderInfo{
	StudentProblemHistory savedStudentProblemGradingHistory;
	
	public SavedStudentProblemGradingHistoryInfo(String aMessage, 
			StudentProblemHistory aSavedStudentProblemGradingHistory,			
			Object aFinder) {
		super(aMessage, aFinder);
	}

	public StudentProblemHistory getSavedStudentProblemGradingHistory() {
		return savedStudentProblemGradingHistory;
	}

	public void setSavedStudentProblemGradingHistory(
			StudentProblemHistory savedStudentProblemGradingHistory) {
		this.savedStudentProblemGradingHistory = savedStudentProblemGradingHistory;
	}

	
	
	

}
