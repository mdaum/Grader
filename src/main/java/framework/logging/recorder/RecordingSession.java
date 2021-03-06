package framework.logging.recorder;

import framework.grading.testing.CheckResult;
import grader.assignment.GradingFeature;
import grader.assignment.GradingFeatureList;

import java.util.List;

/**
 * A class that can be converted to JSON.
 */
public class RecordingSession {

    private String userId;
    private List<CheckResult> featureResults;
    private List<CheckResult> restrictionResults;
    private String comments = "";
    private double latePenalty;
    double sourcePoints;
    
	double score;
    String sourceCodeComments = "";
    

	

	List<GradingFeature> gradingFeatures;

    
	public RecordingSession(String userId, List<CheckResult> featureResults, List<CheckResult> restrictionResults,
                            String comments, double latePenalty, GradingFeatureList newGradingFeatures) {
        this.userId = userId;
        this.featureResults = featureResults;
        this.restrictionResults = restrictionResults;
        this.comments = comments;
        this.latePenalty = latePenalty;
        gradingFeatures = newGradingFeatures;
    }

    public String getUserId() {
        return userId;
    }

    public List<CheckResult> getFeatureResults() {
        return featureResults;
    }

    public List<CheckResult> getRestrictionResults() {
        return restrictionResults;
    }

    public String getComments() {
        return comments;
    }

    public double getLatePenalty() {
        return latePenalty;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setFeatureResults(List<CheckResult> featureResults) {
        this.featureResults = featureResults;
    }

    public void setRestrictionResults(List<CheckResult> restrictionResults) {
        this.restrictionResults = restrictionResults;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setLatePenalty(double latePenalty) {
        this.latePenalty = latePenalty;
    }
    
    public List<GradingFeature> getGradingFeatures() {
		return gradingFeatures;
	}

	public void setGradingFeatures(List<GradingFeature> gradingFeatures) {
		this.gradingFeatures = gradingFeatures;
	}
	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}
	
	public String getSourceCodeTAComments() {
		return sourceCodeComments;
	}
	
	public void setSourceCodeComments(String sourceCodeComments) {
		this.sourceCodeComments = sourceCodeComments;
	}
	public double getSourcePoints() {
		return sourcePoints;
	}

	public void setSourcePoints(double sourcePoints) {
		this.sourcePoints = sourcePoints;
	}


}
