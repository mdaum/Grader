package grader.trace.feature.manual_notes;

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

public class FeatureManualNotesInfo extends GradingFeatureInfo {
String featureManualNotes;



public FeatureManualNotesInfo(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject,
			GradingFeature aFeature,
			String aNotes, Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFeature, aFinder);
		featureManualNotes = aNotes;
		// TODO Auto-generated constructor stub
	}

public String getFeatureManualNotes() {
	return featureManualNotes;
}



public void setFeatureManualNotes(String featureManualNotes) {
	this.featureManualNotes = featureManualNotes;
}

//@Override
//public String toCSVRow() {
//	return super.toCSVRow() 
//			+ "," + featureManualNotes;
//}

	
//	public static SerializableFeatureManualNotesInfo newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
//			OverviewProjectStepper aProjectStepper, 
//			SakaiProject aProject,
//			String aNotes,
//			Object aFinder) {
//		String aMessage = "Overview Notes Manually Changed to:" + aNotes;
//		SerializableFeatureManualNotesInfo retVal = new SerializableFeatureManualNotesInfo(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aNotes, aFinder);
//		retVal.announce();		
//		return retVal;
//	}
	

}
