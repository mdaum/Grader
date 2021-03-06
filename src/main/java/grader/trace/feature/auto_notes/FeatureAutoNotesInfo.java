package grader.trace.feature.auto_notes;

import grader.assignment.GradingFeature;
import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.trace.feature.GradingFeatureInfo;
import grader.trace.stepper.SerializableStepperInfo;
import grader.trace.stepper.StepperInfo;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class FeatureAutoNotesInfo extends GradingFeatureInfo {
String featureAutoNotes;



public FeatureAutoNotesInfo(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject, GradingFeature aFeature,
			String aNotes,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFeature, aFinder);
		featureAutoNotes = aNotes;
		// TODO Auto-generated constructor stub
	}

public String getFeatureAutoNotes() {
	return featureAutoNotes;
}



public void setFeatureAutoNotes(String featureAutoNotes) {
	this.featureAutoNotes = featureAutoNotes;
}

//@Override
//public String toCSVRow() {
//	return super.toCSVRow() 
//			+ "," + featureAutoNotes;
//}

	
//	public static SerializableFeatureAutoNotesInfo newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
//			OverviewProjectStepper aProjectStepper, 
//			SakaiProject aProject,
//			String aNotes,
//			Object aFinder) {
//		String aMessage = "Overview Notes Autoly Changed to:" + aNotes;
//		SerializableFeatureAutoNotesInfo retVal = new SerializableFeatureAutoNotesInfo(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aNotes, aFinder);
//		retVal.announce();		
//		return retVal;
//	}
	

}
