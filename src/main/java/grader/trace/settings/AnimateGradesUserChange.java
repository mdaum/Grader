package grader.trace.settings;

import java.util.Date;

import grader.settings.GraderSettingsModel;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class AnimateGradesUserChange extends GraderSettingsInfo {
	
	boolean animateGrades;
	
	
	public AnimateGradesUserChange(String aMessage, boolean aAnimateGrades, GraderSettingsModel aGradingSettingsModel, Object aFinder) {
		super(aMessage, aGradingSettingsModel, aFinder);
		animateGrades = aAnimateGrades;
//		 gradingSettingsModel = aGradingSettingsModel;
	}
	public boolean getAnimateGrades() {
		return animateGrades;
	}
	public void setAnimateGrades(boolean animateGrades) {
		this.animateGrades = animateGrades;
	}
	@Override
	public String toCSVRow() {
		return super.toCSVRow() + "," + animateGrades;
	}
	
	
	public static AnimateGradesUserChange newCase(boolean aAnimateGrades, GraderSettingsModel aGradingSettingsModel, Object aFinder) {
		String aMessage = "AnimateGrades Changed:" + aAnimateGrades;
		AnimateGradesUserChange retVal = new AnimateGradesUserChange(aMessage, aAnimateGrades,  aGradingSettingsModel, aFinder);
		retVal.announce();		
		return retVal;
	}

}
