package grader.config;

import framework.grading.ProjectRequirements;
import framework.utils.GradingEnvironment;
import grader.assignment.AnAssignmenDataFolder;
import grader.requirements.interpreter.AnInterpretedRequirements;
import grader.requirements.interpreter.specification.CSVRequirementsSpecification;
import grader.sakai.project.ASakaiProjectDatabase;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsManager;
import grader.settings.GraderSettingsManagerSelector;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.configuration.PropertiesConfiguration;

import util.misc.Common;

public class StaticConfigurationUtils {

    public static final String DEFAULT = "default";
    public static final String VISIT_ACTIONS = "visitActions";
    public static final String AUTO_GRADE = "autoGrade";
    public static final String AUTO_RUN = "autoRun";
    public static final String LOAD_CLASSES = "loadClasses";
    public static final String ALLOW_COMPILE_CLASSES = "compileMissingObjectCode";
    public static final String PRE_COMPILE_CLASSES = "precompileMissingObjectCode";

    public static final String FORCE_COMPILE_CLASSES = "forceCompile";

    public static final String PRIVACY = "privacy";
    public static final String EXECUTION_COMMAND = "execution";
    public static final String LANGUAGE = "language";
    public static final String REQUIREMENTS = "requirements";
    public static final String ENTRY_POINT = "entryPoint";
    public static final String BUILD_FOLDER = "buildFolder";

    public static final String CLASS_PATH = "classPath";

    public static final String PROCESS_TEAMS = "processTeams";

    public static final String ENTRY_TAG = "entryTag";
    public static final String ENTRY_TAGS = "entryTags";
    public static final String SLEEP_TIME = "sleepTime";
    public static final String ARGS = "args";
    public static final String START_TAGS = "startTags";
    public static final String TERMINATING = "terminating";
    public static final String GENERATE_TRACE_FILES = "trace";

    public static final String JAVA = "Java";

    public static String toVariable(String aVariableName) {
        return "{" + aVariableName + "}";
    }

    public static List<String> autoVisitActions(PropertiesConfiguration configuration, GraderSettingsManager graderSettingsManager) {
        String module = graderSettingsManager.getModule();
        String problem = graderSettingsManager.getNormalizedProblem(module);
        List retVal = configuration.getList(module + "." + problem + "." + VISIT_ACTIONS);
        if (retVal.isEmpty()) {
            retVal = configuration.getList(module + "." + VISIT_ACTIONS);
        }
        if (retVal.isEmpty()) {
            retVal = configuration.getList(DEFAULT + "." + VISIT_ACTIONS);
        }

        return retVal;

    }

    public static boolean getLoadClasses(PropertiesConfiguration configuration, GraderSettingsManager graderSettingsManager) {

        return getInheritedBooleanModuleProblemProperty(configuration, graderSettingsManager, LOAD_CLASSES, false);

    }

    public static boolean getAllowCompileClasses(PropertiesConfiguration configuration, GraderSettingsManager graderSettingsManager) {

        return getInheritedBooleanModuleProblemProperty(configuration, graderSettingsManager, ALLOW_COMPILE_CLASSES, false);

    }

    public static boolean getPrecompileClasses(PropertiesConfiguration configuration, GraderSettingsManager graderSettingsManager) {

        return getInheritedBooleanModuleProblemProperty(configuration, graderSettingsManager, PRE_COMPILE_CLASSES, false);

    }

    public static boolean getForceCompileClasses(PropertiesConfiguration configuration, GraderSettingsManager graderSettingsManager) {

        return getInheritedBooleanModuleProblemProperty(configuration, graderSettingsManager, FORCE_COMPILE_CLASSES, false);

    }

    public static boolean getPrivacy(PropertiesConfiguration configuration, GraderSettingsManager graderSettingsManager) {
//		String module = graderSettingsManager.getModule();
//		String problem = graderSettingsManager.getNormalizedProblem(module);
//		Boolean retVal = configuration.getBoolean(module+"." + problem + "." + MAKE_CLASS_DESCRIPTION, null);
//			
//		if (retVal == null)
//			retVal = configuration.getBoolean(module+"." + MAKE_CLASS_DESCRIPTION, null);
//		if (retVal == null)
//			retVal = configuration.getBoolean(DEFAULT+"." + MAKE_CLASS_DESCRIPTION, false);
//		
//		return retVal;
        return getInheritedBooleanModuleProblemProperty(configuration, graderSettingsManager, PRIVACY, false);

    }

    public static boolean getPrivacy(PropertiesConfiguration configuration, String aModule, String aProblem) {
//		String module = graderSettingsManager.getModule();
//		String problem = graderSettingsManager.getNormalizedProblem(module);
//		Boolean retVal = configuration.getBoolean(module+"." + problem + "." + MAKE_CLASS_DESCRIPTION, null);
//			
//		if (retVal == null)
//			retVal = configuration.getBoolean(module+"." + MAKE_CLASS_DESCRIPTION, null);
//		if (retVal == null)
//			retVal = configuration.getBoolean(DEFAULT+"." + MAKE_CLASS_DESCRIPTION, false);
//		
//		return retVal;
        return getInheritedBooleanModuleProblemProperty(configuration, aModule, aProblem, PRIVACY, false);

    }

    public static Boolean getInheritedBooleanModuleProblemProperty(PropertiesConfiguration configuration, GraderSettingsManager graderSettingsManager, String property, Boolean defaultValue) {
        String module = graderSettingsManager.getModule();
        String problem = graderSettingsManager.getNormalizedProblem(module);

        return getInheritedBooleanModuleProblemProperty(configuration, module, problem, property, defaultValue);
//		Boolean retVal = configuration.getBoolean(module+"." + problem + "." + property, null);
//			
//		if (retVal == null)
//			retVal = configuration.getBoolean(module+"." + property, null);
//		if (retVal == null)
//			retVal = configuration.getBoolean(DEFAULT+"." + property, defaultValue);
//		
//		return retVal;

    }

    public static Boolean getInheritedBooleanModuleProblemProperty(PropertiesConfiguration configuration, String module, String problem, String property, Boolean defaultValue) {

        Boolean retVal = configuration.getBoolean(module + "." + problem + "." + property, null);

        if (retVal == null) {
            retVal = configuration.getBoolean(module + "." + property, null);
        }
        if (retVal == null) {
            retVal = configuration.getBoolean(DEFAULT + "." + property, defaultValue);
        }

        return retVal;

    }

    public static Integer getInheritedIntegerModuleProblemProperty(PropertiesConfiguration configuration, String module, String problem, String property, Integer defaultValue) {

        Integer retVal = configuration.getInteger(module + "." + problem + "." + property, null);

        if (retVal == null) {
            retVal = configuration.getInteger(module + "." + property, null);
        }
        if (retVal == null) {
            retVal = configuration.getInteger(DEFAULT + "." + property, defaultValue);
        }

        return retVal;

    }

//	public static String[] getExecutionCommand(File buildFolder, String entryPoint) {
//		List<String> basicCommand = getInheritedListModuleProblemProperty(EXECUTION_COMMAND);
//		String[] retVal = new String[basicCommand.size()];
//		for (int i = 0; i < basicCommand.size(); i++) {
////		String withClassPath = basicCommand.get(i).replace("{classPath}", GradingEnvironment.get().getClasspath());
////		String withEntryPoint = withClassPath.replace("{entryPoint}", entryPoint);
////		String withBuildFolder = withEntryPoint.replace("{buildFolder}", buildFolder.getAbsolutePath());
//		
//		String withClassPath = basicCommand.get(i).replace(toVariable(CLASS_PATH), GradingEnvironment.get().getClasspath());
//		String withEntryPoint = withClassPath.replace(toVariable(ENTRY_POINT), entryPoint);
//		String withBuildFolder = withEntryPoint.replace(toVariable(BUILD_FOLDER), buildFolder.getAbsolutePath());
//		retVal[i] = withBuildFolder;
//		}
//		return retVal;
//		
//	}
    public static String[] getExecutionCommand(File aBuildFolder) {
        String anEntryPoint = getInheritedStringModuleProblemProperty(toVariable(ENTRY_POINT), null);

        return getExecutionCommand(aBuildFolder, anEntryPoint);
    }

    public static String[] getExecutionCommand(File aBuildFolder, String anEntryPoint) {

        return getExecutionCommand(null, aBuildFolder, anEntryPoint, "", new String[0]);
//		List<String> basicCommand = getInheritedListModuleProblemProperty(EXECUTION_COMMAND);
//		List<String> retVal = new ArrayList(basicCommand.size());
//		for (int i = 0; i < basicCommand.size(); i++) {
////		String withClassPath = basicCommand.get(i).replace("{classPath}", GradingEnvironment.get().getClasspath());
////		String withEntryPoint = withClassPath.replace("{entryPoint}", entryPoint);
////		String withBuildFolder = withEntryPoint.replace("{buildFolder}", buildFolder.getAbsolutePath());
//		
//		String withClassPath = basicCommand.get(i).replace(toVariable(CLASS_PATH), GradingEnvironment.get().getClasspath());
//		String withEntryPoint = withClassPath.replace(toVariable(ENTRY_POINT), entryPoint);
//		String withBuildFolder = withEntryPoint.replace(toVariable(BUILD_FOLDER), buildFolder.getAbsolutePath());
//		retVal.add(i, withBuildFolder);
//		}
//		return retVal.toArray(new String[0]);

    }

    public static List<String> getBasicCommand() {
        return getInheritedListModuleProblemProperty(EXECUTION_COMMAND);
    }

    public static List<String> getBasicCommand(String aProcessName) {
        List<String> retVal = getInheritedListModuleProblemProperty(aProcessName + "." + EXECUTION_COMMAND);
        if (retVal.isEmpty()) {
            return getBasicCommand();
        } else {
            return retVal;
        }
    }

    public static boolean hasEntryPoint(List<String> aCommand) {
        return hasSubString(aCommand, ENTRY_POINT);
    }

    public static boolean hasSubString(List<String> aCommand, String aSubString) {
        for (String aCommmandComponent : aCommand) {
            if (aCommmandComponent.contains(aSubString)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasEntryTag(List<String> aProcessCommand) {
        return hasSubString(aProcessCommand, ENTRY_TAG);
    }

    public static boolean hasEntryTags(List<String> aProcessCommand) {
        return hasSubString(aProcessCommand, ENTRY_TAGS);
    }

    public static boolean haArgs(String aProcessCommand) {
        return aProcessCommand.contains(ARGS);
    }

    public static String[] getExecutionCommand(String aProcessName, File aBuildFolder, String anEntryPoint, String anEntryTagTarget, String[] anArgs) {

        List<String> basicCommand = null;
        if (aProcessName == null || aProcessName.isEmpty()) {
//			basicCommand = getInheritedListModuleProblemProperty(EXECUTION_COMMAND);
            basicCommand = getBasicCommand();
        } else {
//			basicCommand = getInheritedListModuleProblemProperty(aProcessName + "." + EXECUTION_COMMAND);
            basicCommand = getBasicCommand(aProcessName);

        }
        List<String> retVal = new ArrayList(basicCommand.size());
        for (int aCommandIndex = 0; aCommandIndex < basicCommand.size(); aCommandIndex++) {
//		String withClassPath = basicCommand.get(i).replace("{classPath}", GradingEnvironment.get().getClasspath());
//		String withEntryPoint = withClassPath.replace("{entryPoint}", entryPoint);
//		String withBuildFolder = withEntryPoint.replace("{buildFolder}", buildFolder.getAbsolutePath());

//		String withClassPath = basicCommand.get(aCommandIndex).replace(toVariable(CLASS_PATH), GradingEnvironment.get().getClasspath());
//		String withEntryPoint = withClassPath.replace(toVariable(ENTRY_POINT), anEntryPoint);
//		String withBuildFolder = withEntryPoint.replace(toVariable(BUILD_FOLDER), aBuildFolder.getAbsolutePath());
//		String withEntryTag = withBuildFolder.replace(toVariable(ENTRY_TAG), anEntryTagTarget);		
            String command = basicCommand.get(aCommandIndex).replace(toVariable(CLASS_PATH), GradingEnvironment.get().getClasspath());
            if (anEntryPoint != null) {
                command = command.replace(toVariable(ENTRY_POINT), anEntryPoint);
            }
            if (anEntryTagTarget != null) {
                command = command.replace(toVariable(ENTRY_TAGS), anEntryTagTarget);
                command = command.replace(toVariable(ENTRY_TAG), anEntryTagTarget);	// will match tags also

            }

//		if (anEntryTagTarget != null)
            command = command.replace(toVariable(BUILD_FOLDER), aBuildFolder.getAbsolutePath());

            retVal.add(command);
        }
        int argsIndex = retVal.indexOf(toVariable(ARGS));
        if (argsIndex >= 0) {
            retVal.remove(argsIndex);
            for (int i = 0; i < anArgs.length; i++) {
                retVal.add(argsIndex + i, anArgs[i]);
            }

        }
        return retVal.toArray(new String[0]);

    }

    public static String getInheritedStringModuleProblemProperty(String property, String defaultValue) {
        PropertiesConfiguration configuration = ConfigurationManagerSelector.getConfigurationManager().getStaticConfiguration();
        GraderSettingsManager graderSettingsManager = GraderSettingsManagerSelector.getGraderSettingsManager();
        String aModule = graderSettingsManager.getModule();
        String aProblem = graderSettingsManager.getNormalizedProblem(aModule);
        return getInheritedStringModuleProblemProperty(configuration, aModule,
                aProblem, property, defaultValue);

    }

    public static Boolean getInheritedBooleanModuleProblemProperty(String property, boolean defaultValue) {
        PropertiesConfiguration configuration = ConfigurationManagerSelector.getConfigurationManager().getStaticConfiguration();
        GraderSettingsManager graderSettingsManager = GraderSettingsManagerSelector.getGraderSettingsManager();
        String aModule = graderSettingsManager.getModule();
        String aProblem = graderSettingsManager.getNormalizedProblem(aModule);
        return getInheritedBooleanModuleProblemProperty(configuration, aModule,
                aProblem, property, defaultValue);

    }

    public static Integer getInheritedIntegerModuleProblemProperty(String property, Integer defaultValue) {
        PropertiesConfiguration configuration = ConfigurationManagerSelector.getConfigurationManager().getStaticConfiguration();
        GraderSettingsManager graderSettingsManager = GraderSettingsManagerSelector.getGraderSettingsManager();
        String aModule = graderSettingsManager.getModule();
        String aProblem = graderSettingsManager.getNormalizedProblem(aModule);
        return getInheritedIntegerModuleProblemProperty(configuration, aModule,
                aProblem, property, defaultValue);

    }

    public static String getLanguage() {
        return getInheritedStringModuleProblemProperty(LANGUAGE, JAVA);

    }

    public static List<String> getInheritedListModuleProblemProperty(String property) {
        PropertiesConfiguration configuration = ConfigurationManagerSelector.getConfigurationManager().getStaticConfiguration();
        GraderSettingsManager graderSettingsManager = GraderSettingsManagerSelector.getGraderSettingsManager();
        String aModule = graderSettingsManager.getModule();
        String aProblem = graderSettingsManager.getNormalizedProblem(aModule);
        return getInheritedListModuleProblemProperty(configuration, aModule,
                aProblem, property);

    }

//public static String getInheritedStringModuleProblemProperty( String property, String defaultValue) {
//	PropertiesConfiguration configuration = ConfigurationManagerSelector.getConfigurationManager().getStaticConfiguration();
//	GraderSettingsManager graderSettingsManager = GraderSettingsManagerSelector.getGraderSettingsManager();
//	String aModule = graderSettingsManager.getModule();
//	String aProblem = graderSettingsManager.getNormalizedProblem(aModule);
//		return getInheritedStringModuleProblemProperty(configuration, aModule , 
//				aProblem, property, null);
//		
//	}
    public static String getInheritedStringModuleProblemProperty(PropertiesConfiguration configuration, String module, String problem, String property, String defaultValue) {

        String retVal = configuration.getString(module + "." + problem + "." + property, null);

        if (retVal == null) {
            retVal = configuration.getString(module + "." + property, null);
        }
        if (retVal == null) {
            retVal = configuration.getString(DEFAULT + "." + property, defaultValue);
        }

        return retVal;

    }

    public static List<String> getInheritedListModuleProblemProperty(PropertiesConfiguration configuration, String module, String problem, String property) {

        List retVal = configuration.getList(module + "." + problem + "." + property);

        if (retVal.isEmpty()) {
            retVal = configuration.getList(module + "." + property);
        }
        if (retVal.isEmpty()) {
            retVal = configuration.getList(DEFAULT + "." + property);
        }

        return retVal;

    }

    public static ProjectRequirements getProjectRequirements(PropertiesConfiguration configuration,
        GraderSettingsManager graderSettingsManager) {

        ProjectRequirements requirements = null;

        String requirementsSpec = "";

        try {
            requirementsSpec = getInheritedStringModuleProblemProperty(REQUIREMENTS, configuration.getString("project.requirements"));

            // compatibility with Josh's spec
//            String module = graderSettingsManager.getModule();
//            requirementsSpec = configuration.getString(module + ".requirements");
//            if (requirementsSpec == null) {
//                requirementsSpec = configuration.getString(module.toLowerCase() + ".requirements");
//                if (requirementsSpec == null) {
//                    requirementsSpec = configuration.getString("default.requirements");
//                    if (requirementsSpec == null) {
//                        requirementsSpec = configuration.getString("project.requirements"); // upward compatibilty
//                    }
//                }
//            }
            String normalizedRequirementsSpec = graderSettingsManager.replaceModuleProblemVars(requirementsSpec);

//            }
//            Class<?> _class = Class.forName(configuration.getString("project.requirements"));
            Class<?> _class = Class.forName(normalizedRequirementsSpec);

            requirements = (ProjectRequirements) _class.newInstance();
        } catch (ClassNotFoundException e) {
        	requirements = getInterpretedRequirements();
        	if (requirements == null)        	
                System.err.println("Could not find project requirements:" + requirementsSpec);
//            System.err.println(e.getMessage());
        } catch (InstantiationException e) {
            System.err.println("Could not create project requirements." + requirements);
            System.err.println(e.getMessage());
        } catch (IllegalAccessException e) {
            System.err.println("Could not create project requirements." + requirements);
            System.err.println(e.getMessage());
        }
        return requirements;

    }
    
    public static ProjectRequirements getInterpretedRequirements() {
//    	SakaiProjectDatabase aDatabase = null;
    	
    		SakaiProjectDatabase aDatabase = ASakaiProjectDatabase.getCurrentSakaiProjectDatabase();
    	try {
        	CSVRequirementsSpecification aSpecification = aDatabase.getCSVRequirementsSpecification();
        	return new AnInterpretedRequirements(aSpecification);

    		
    	} catch (Exception e) {
    		System.out.println("Could not find interpreted requirements " + AnAssignmenDataFolder.DEFAULT_REQUIREMENTS_SPREADHEET_NAME + " in assignment data folder:" + aDatabase.getAssignmentDataFolder().getMixedCaseAbsoluteName() );
    		return null;
//    		e.printStackTrace();
    	}
    	
    }

    public static List<String> getProcessTeams() {
        return getInheritedListModuleProblemProperty(PROCESS_TEAMS);
    }

    public static List<String> getProcessArgs(String aProcess) {
        return getInheritedListModuleProblemProperty(aProcess + "." + ARGS);
    }

    public static List<String> getProcessStartTags(String aProcess) {
        return getInheritedListModuleProblemProperty(aProcess + "." + START_TAGS);
    }

    public static Boolean getTrace() {
        return getInheritedBooleanModuleProblemProperty(GENERATE_TRACE_FILES, false);
    }

    public static final int DEFAULT_SLEEP_TIME = 2000;

    public static Integer getSleepTime(String aProcess) {
        return getInheritedIntegerModuleProblemProperty(aProcess + "." + SLEEP_TIME, DEFAULT_SLEEP_TIME);
    }

    public static String getEntryTag(String aProcess) {
        return getInheritedStringModuleProblemProperty(aProcess + "." + ENTRY_TAG, null);
    }

    public static List<String> getEntryTags(String aProcess) {
        return getInheritedListModuleProblemProperty(aProcess + "." + ENTRY_TAGS);
    }

    public static String getEntryPoint(String aProcess) {
        return getInheritedStringModuleProblemProperty(aProcess + "." + ENTRY_POINT, null);
    }

    public static List<String> getProcesses(String aProcessTeam) {
        return getInheritedListModuleProblemProperty(aProcessTeam);
    }

    public static List<String> getTerminatingProcesses(String aProcessTeam) {
        return getInheritedListModuleProblemProperty(aProcessTeam + "." + TERMINATING);
    }

}
