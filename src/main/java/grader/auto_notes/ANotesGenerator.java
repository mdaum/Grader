package grader.auto_notes;

import grader.assignment.GradingFeature;
import grader.sakai.project.ProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;

public class ANotesGenerator implements NotesGenerator{
	
	public ANotesGenerator (SakaiProjectDatabase aDatabase) {
		
	}
	
	public String scoreNotes (ProjectStepper aProjectStepper, double score) {
		return "";
	}	
	public String validationNotes (ProjectStepper aProjectStepper, GradingFeature aGradingFeature) {
//		return " " + aGradingFeature.getFeature() + " auto grading validated.";
		return "Validated.";
	}
	
	public String scoreOverrideNotes (ProjectStepper aProjectStepper, double oldVal, double newVal) {
		return  "Total score manually changed from " + oldVal + " to " + newVal + "." ;
	}
	
	public String multiplierOverrideNotes (ProjectStepper aProjectStepper, double oldVal, double newVal) {
		return  "Multiplier manually changed from " + oldVal + " to " + newVal + "." ;
	}
	
	

}