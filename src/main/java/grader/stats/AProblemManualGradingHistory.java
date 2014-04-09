package grader.stats;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AProblemManualGradingHistory implements ProblemManualGradingHistory{
	double elapsedAutoTime;
	double elapsedManualTime;
	double averageAutoTime;
	double averageManualTime;
	double totalManualFeatureScoreOverrides;
	double totalOverallScoreOverrides;
	double totalManualFeatureNotes;
	double totaleManualOverallNotes;
	double averagelManualFeatureScoreOverrides;
	double averageOverallScoreOverrides;
	double averageManualFeatureNotes;
	double averageManualOverallNotes;
	List<String> visitedStudents = new ArrayList();
	

	Map<String, StudentManualGradingHistory> onyenToStudentHistory = new HashMap();
	List<StudentManualGradingHistory> studentsHistory = new ArrayList();
	
	public double getElapsedAutoTime() {
		return elapsedAutoTime;
	}
	public void setElapsedAutoTime(double elapsedAutoTime) {
		this.elapsedAutoTime = elapsedAutoTime;
	}
	public double getElapsedManualTime() {
		return elapsedManualTime;
	}
	public void setElapsedManualTime(double elapsedManualTime) {
		this.elapsedManualTime = elapsedManualTime;
	}
	public double getAverageAutoTime() {
		return averageAutoTime;
	}
	public void setAverageAutoTime(double averageAutoTime) {
		this.averageAutoTime = averageAutoTime;
	}
	public double getAverageManualTime() {
		return averageManualTime;
	}
	public void setAverageManualTime(double averageManualTime) {
		this.averageManualTime = averageManualTime;
	}
	public double getTotalManualFeatureScoreOverrides() {
		return totalManualFeatureScoreOverrides;
	}
	public void setTotalManualFeatureScoreOverrides(
			double totalManualFeatureScoreOverrides) {
		this.totalManualFeatureScoreOverrides = totalManualFeatureScoreOverrides;
	}
	public double getTotalOverallScoreOverrides() {
		return totalOverallScoreOverrides;
	}
	public void setTotalOverallScoreOverrides(double totalOverallScoreOverrides) {
		this.totalOverallScoreOverrides = totalOverallScoreOverrides;
	}
	public double getTotalManualFeatureNotes() {
		return totalManualFeatureNotes;
	}
	public void setTotalManualFeatureNotes(double totalManualFeatureNotes) {
		this.totalManualFeatureNotes = totalManualFeatureNotes;
	}
	public double getTotaleManualOverallNotes() {
		return totaleManualOverallNotes;
	}
	public void setTotaleManualOverallNotes(double totaleManualOverallNotes) {
		this.totaleManualOverallNotes = totaleManualOverallNotes;
	}
	public double getAveragelManualFeatureScoreOverrides() {
		return averagelManualFeatureScoreOverrides;
	}
	public void setAveragelManualFeatureScoreOverrides(
			double averagelManualFeatureScoreOverrides) {
		this.averagelManualFeatureScoreOverrides = averagelManualFeatureScoreOverrides;
	}
	public double getAverageOverallScoreOverrides() {
		return averageOverallScoreOverrides;
	}
	public void setAverageOverallScoreOverrides(double averageOverallScoreOverrides) {
		this.averageOverallScoreOverrides = averageOverallScoreOverrides;
	}
	public double getAverageManualFeatureNotes() {
		return averageManualFeatureNotes;
	}
	public void setAverageManualFeatureNotes(double averageManualFeatureNotes) {
		this.averageManualFeatureNotes = averageManualFeatureNotes;
	}
	public double getAverageManualOverallNotes() {
		return averageManualOverallNotes;
	}
	public void setAverageManualOverallNotes(double averageManualOverallNotes) {
		this.averageManualOverallNotes = averageManualOverallNotes;
	}
	public List<StudentManualGradingHistory> getStudentsHistory() {
		return studentsHistory;
	}
	public void setStudentsHistory(List<StudentManualGradingHistory> studentsHistory) {
		this.studentsHistory = studentsHistory;
	}
	public void newStudentHistory(String anOnyen, StudentManualGradingHistory aHistory) {
		StudentManualGradingHistory existingHistory = onyenToStudentHistory.get(anOnyen);
		if (existingHistory != null)
			existingHistory.merge(aHistory);
		else {
			onyenToStudentHistory.put(anOnyen, aHistory);	
			visitedStudents.add(anOnyen);
		}
	}
	public List<String> getVisitedStudents() {
		return visitedStudents;
	}
	public void setVisitedStudents(List<String> visitedStudents) {
		this.visitedStudents = visitedStudents;
	}
	public Map<String, StudentManualGradingHistory> getOnyenToStudentHistory() {
		return onyenToStudentHistory;
	}
	public void setOnyenToStudentHistory(
			Map<String, StudentManualGradingHistory> onyenToStudentHistory) {
		this.onyenToStudentHistory = onyenToStudentHistory;
	}
	@Override
	public void merge(ProblemManualGradingHistory other) {
		// TO DO the numerical statuses
		List<String> otherOnyens = other.getVisitedStudents();
		Map<String, StudentManualGradingHistory> otherMap = other.getOnyenToStudentHistory();
		for (String onyen:otherOnyens) {
			newStudentHistory(onyen, otherMap.get(onyen));
		}
	}
	
	

}