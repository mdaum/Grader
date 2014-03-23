package grader.settings;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import framework.utils.GraderSettings;
import framework.utils.GradingEnvironment;
import grader.config.ConfigurationManagerSelector;
import grader.modules.AModuleProblemSelector;
import grader.modules.ModuleProblemManager;
import grader.modules.ModuleProblemManagerSelector;
import grader.modules.ModuleProblemSelector;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.folders.AGraderFilesSetterModel;
import grader.settings.folders.AnOnyenRangeModel;
import grader.settings.folders.GraderFilesSetterModel;
import grader.settings.folders.OnyenRangeModel;
import grader.settings.navigation.ANavigationFilterSetter;
import grader.settings.navigation.ANavigationSetter;
import grader.settings.navigation.NavigationSetter;
import util.annotations.ComponentHeight;
import util.annotations.Label;
import util.annotations.Row;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Visible;
import util.misc.Common;
import util.models.DynamicEnum;
import util.trace.Tracer;
import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@Label("Starter")
public class AGraderSettingsModel implements GraderSettingsModel{
	GraderFilesSetterModel fileBrowsing = new AGraderFilesSetterModel();
	NavigationSetter navigationSetter = new ANavigationSetter(this);
	OnyenRangeModel onyens = new AnOnyenRangeModel(this);
	ModuleProblemSelector moduleProblemSelector; 
	List<String> modules = new ArrayList();
	List<String> problems = new ArrayList();
	String editor;
	String currentModule;
	List<String> currentProblems;
	String currentProblem;
	String currentModulePrefix;
//	PropertiesConfiguration configuration, dynamicConfiguration;
//	PropertiesConfiguration dynamicConfiguration;
	GraderSettingsManager graderSettingsManager = GraderSettingsManagerSelector.getGraderSettingsManager();

	ModuleProblemManager moduleProblemManager;
	String problemDownloadPath;
	String moduleDownloadPath;
//	DynamicEnum moduleEnum, problemEnum;
	


	//	BeginActionModel beginActionModel = new ABeginActionModel();
	boolean graderStarted;
	PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	SakaiProjectDatabase database;
	
	public AGraderSettingsModel(SakaiProjectDatabase aDatabase) {
		database = aDatabase;
//		configuration = GradingEnvironment.get().getConfigurationManager().getStaticConfiguration();
//		dynamicConfiguration = GradingEnvironment.get().getConfigurationManager().getDynamicConfiguration();
		moduleProblemManager = ModuleProblemManagerSelector.getModuleProblemManager();
		
//		configuration = ConfigurationManagerSelector.getConfigurationManager().getStaticConfiguration();
//		dynamicConfiguration = ConfigurationManagerSelector.getConfigurationManager().getDynamicConfiguration();
		
//		loadSettings();
		loadDynamicConfigurationSettings();
	}
	public AGraderSettingsModel() {
//		database = aDatabase;
		
//		AModuleProblemSelector moduleProblem = new AModuleProblemSelector(modules, problems);
		
		loadSettings();
	}
	
	void setCurrentModule(String newValue) {
		
		 currentModule = newValue;
		 refreshAll();
////		 currentModulePrefix = configuration.getString(currentModule + ".problems.prefix")	;
////		if (currentModulePrefix == null)
////			currentModulePrefix = configuration.getString("default.problems.prefix", "Assignment");
//		 currentModulePrefix = moduleProblemManager.getModulePrefix(currentModule);
//		problems.clear();
//		if (downloadPath != null) {
//			File folder = new File(downloadPath);
//			if (!folder.exists()) {
//				Tracer.error("No folder found for:" + downloadPath);				
//			} else {
//				File gradesFile = new File(downloadPath + "/grades.csv"); // is this a sakai assignment folder
//				if (gradesFile.exists()) 
//					folder = folder.getParentFile();
//				File[] children = folder.listFiles();
//				for (File child:children) {
//					if (child.getName().startsWith(currentModulePrefix))
//						problems.add(child.getName());
//				}
//			}
//		}
		
		
		
	}
	
	void noDownloadPath() {
		JOptionPane.showMessageDialog(null, "Please enter download path for current problem in module:" + currentModule);

	}
	public void refreshAll() {
		refreshOnyens(currentModule);
		problems.clear();
//		List<String> problems = new ArrayList();
		String currentModulePrefix = moduleProblemManager.getModulePrefix(currentModule);
		problems.clear();
		problemDownloadPath = graderSettingsManager.getDownloadPath(currentModule);
//		problemDownloadPath = moduleDownloadPath + "\\" +  currentProblem;
		if (problemDownloadPath != null) {
			File folder = new File(problemDownloadPath);
			if (!folder.exists()) {
//				JOptionPane.showMessageDialog(null, "Please enter download path for current problem in module:" + currentModule);
				noDownloadPath();
				return;
//				Tracer.error("No folder found for:" + downloadPath);				
			} else {
				File gradesFile = new File(problemDownloadPath + "/grades.csv"); // is this a sakai assignment folder
				if (gradesFile.exists()) 
					folder = folder.getParentFile();
//				try {
					moduleDownloadPath = folder.getAbsolutePath();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				File[] children = folder.listFiles();
				long latestTime = 0;
//				currentProblem;
				for (File child:children) {
					if (child.getName().startsWith(currentModulePrefix) && !child.getName().equals("AssignmentsData")) {
						String normalizedName = child.getName().replaceAll("\\s+", "");
						problems.add(normalizedName);
						if (child.lastModified() > latestTime) {
							currentProblem = normalizedName;
//							try {
								problemDownloadPath = child.getAbsolutePath();
//							} catch (IOException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
							
						}
					}
				}
			}
		}
//		fileBrowsing.getDownloadFolder().setText(problemDownloadPath);
		refreshProblemDownloadPath();
			if (moduleProblemSelector != null) {
				moduleProblemSelector.getModule().setValue(currentModule);
				moduleProblemSelector.getProblem().setChoices(problems); // it is the same object but we need to fire property change
				moduleProblemSelector.getProblem().setValue(currentProblem);
				
			}
//		if (problems.size() > 0) {
//			currentProblem = problems.get(problems.size() - 1);
//		else
//			currentProblem = null;
//		return problems;
	}
	
	void refreshProblemDownloadPath() {
		fileBrowsing.getDownloadFolder().setText(problemDownloadPath);

	}
	
	void loadSettings() {
		
		
		String editor;
		if (GraderSettings.get().has("editor")) {
			editor = GraderSettings.get().get("editor");
            GradingEnvironment.get().setEditor(editor); // why not for path also, perhaps its not used later?
        } else
            editor = GradingEnvironment.get().getEditor();
        fileBrowsing.getTextEditor().getLabel().setText(editor);
//        String downloadPath;
        if (GraderSettings.get().has("path")) {
            String downloadPath = GraderSettings.get().get("path");
            fileBrowsing.getDownloadFolder().getLabel().setText(downloadPath);
        }
        if (GraderSettings.get().has("start")) {
        	String startingOnyen = GraderSettings.get().get("start");
        	onyens.setStartingOnyen(startingOnyen);
        }
        if (GraderSettings.get().has("end")) {
        	String endingOnyen = GraderSettings.get().get("end");
        	onyens.setEndingOnyen(endingOnyen);
        }
//        List objectModules = GradingEnvironment.get().getConfigurationManager().getStaticConfiguration().getList("modules");
//		modules = objectModules;
//		if (objectModules.size() == 0) {
//			Tracer.error("No modules specified in configuration file!");
//			System.exit(-1);
//		}
//		setCurrentModule(GradingEnvironment.get().getConfigurationManager().getDynamicConfiguration().getString("currentModule", modules.get(0)));
//		String currentModulePrefix =  GradingEnvironment.get().getConfigurationManager().getStaticConfiguration().getString(currentModule + ".problems.prefix")	;
//		if (currentModulePrefix == null)
//			currentModulePrefix = GradingEnvironment.get().getConfigurationManager().getStaticConfiguration().getString("default.problems.prefix", "Assignment");
//				Common.arrayToArrayList(new String[] {"Comp110", "Comp401"});
//		List<String> problems = Common.arrayToArrayList(new String[] {"A1", "A2"});
//		moduleProblemSelector = new AModuleProblemSelector(objectModules, problems);
	}
//	   void maybeConvertToDynamicConfiguration() {
//		 	Map<String, String> settings = GraderSettings.get().getSettings();
////	    	PropertiesConfiguration dynamicConfiguration = GradingEnvironment.get().getConfigurationManager().getDynamicConfiguration();
//	    	if (!dynamicConfiguration.isEmpty()) return;
//	    	for (String key : settings.keySet())
//	            dynamicConfiguration.setProperty(key, settings.get(key));
//	    	try {
//				dynamicConfiguration.save();
//			} catch (ConfigurationException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//	    	
//	    }
	   
	   public static final String EDITOR = "editor";	   
	   public static final String MODULE = "currentModule";
	   public static final String PROBLEM_PATH = "path";
	   public static final String START_ONYEN = "start";
	   public static final String END_ONYEN = "end";

	void refreshOnyens(String aModule) {
		String startingOnyen =  graderSettingsManager.getStartingOnyen(aModule);
        
        if (startingOnyen != null) {
//        	String startingOnyen = GraderSettings.get().get("start");
        	onyens.setStartingOnyen(startingOnyen);
        }
//        String endingOnyen = dynamicConfiguration.getString(aModule + "." + END_ONYEN,
//        		dynamicConfiguration.getString(END_ONYEN));
        String endingOnyen = graderSettingsManager.getEndingOnyen(aModule);
        if (endingOnyen != null) {
        	onyens.setEndingOnyen(endingOnyen);
        }
	}

	void loadDynamicConfigurationSettings() {
		
//		maybeConvertToDynamicConfiguration();
		editor = graderSettingsManager.getEditor();

//		 editor = dynamicConfiguration.getString(EDITOR);
//		if (editor != null) {
////			editor = GraderSettings.get().get("editor");
//            GradingEnvironment.get().setEditor(editor); // why not for path also, perhaps its not used later?
//        } else
//            editor = GradingEnvironment.get().getEditor();
        fileBrowsing.getTextEditor().getLabel().setText(editor);
        String aModule = graderSettingsManager.getModule();
        modules = moduleProblemManager.getModules();
//
//		String aModule = dynamicConfiguration.getString(MODULE, modules.get(0));
//		setCurrentModule(dynamicConfiguration.getString("currentModule", modules.get(0)));

//		if (aModule == null) {
//			if (modules != null && modules.size() > 0)
//				aModule = modules.get(0);
//		} else {
//			aModule = "YourCourse";
//			if (modules == null) modules = new ArrayList();
//			modules.add(aModule);
//		}
//
//		problemDownloadPath =  dynamicConfiguration.getString(aModule + "." + PROBLEM_PATH,
//				dynamicConfiguration.getString(PROBLEM_PATH));
		problemDownloadPath = graderSettingsManager.getDownloadPath(aModule);
//		if (problemDownloadPath == null)
//        
////        String downloadPath;
//        problemDownloadPath =  ;
        if (problemDownloadPath != null) {
//            String downloadPath = GraderSettings.get().get("path");
            fileBrowsing.getDownloadFolder().getLabel().setText(problemDownloadPath);
        } else {
        	noDownloadPath();
        }
//        else {
//        	 fileBrowsing.getDownloadFolder().getLabel().setText("Please enter  folder");
//        }
//        String startingOnyen =  dynamicConfiguration.getString(aModule + "." + START_ONYEN,     	
//        		
//        		dynamicConfiguration.getString(START_ONYEN));
//        refreshOnyens(aModule);
//        String startingOnyen =  graderSettingsManager.getStartingOnyen(aModule);
//        
//        if (startingOnyen != null) {
////        	String startingOnyen = GraderSettings.get().get("start");
//        	onyens.setStartingOnyen(startingOnyen);
//        }
////        String endingOnyen = dynamicConfiguration.getString(aModule + "." + END_ONYEN,
////        		dynamicConfiguration.getString(END_ONYEN));
//        String endingOnyen = graderSettingsManager.getEndingOnyen(aModule);
//        if (endingOnyen != null) {
//        	onyens.setEndingOnyen(endingOnyen);
//        }
//        List objectModules = configuration.getList("modules");
//        
//		modules = objectModules;
//		if (objectModules.size() == 0) {
//			Tracer.error("No modules specified in configuration file!");
//			System.exit(-1);
//		}
//		 currentModulePrefix =  configuration.getString(currentModule + ".problems.prefix")	;
//
//		if (currentModulePrefix == null)
//			currentModulePrefix = configuration.getString("default.problems.prefix", "Assignment");
//				Common.arrayToArrayList(new String[] {"Comp110", "Comp401"});
//		List<String> problems = Common.arrayToArrayList(new String[] {"A1", "A2"});
//		setCurrentModule(dynamicConfiguration.getString("currentModule", modules.get(0)));

		setCurrentModule(aModule);

		moduleProblemSelector = new AModuleProblemSelector(modules, problems);
		moduleProblemSelector.getProblem().setValue(currentProblem);
		moduleProblemSelector.getModule().setValue(currentModule);
		moduleProblemSelector.getModule().addPropertyChangeListener(this);
		moduleProblemSelector.getProblem().addPropertyChangeListener(this);
		fileBrowsing.getDownloadFolder().getLabel().addPropertyChangeListener(this);

		
	}
	
	
	void saveSettings() {
        // Update the settings
		String editor = fileBrowsing.getTextEditor().getLabel().getText();
		String downloadPath = fileBrowsing.getDownloadFolder().getLabel().getText();
		String startingOnyen = onyens.getStartingOnyen();
		String endingOnyen = onyens.getEndingOnyen();
        GraderSettings.get().set(START_ONYEN, startingOnyen);
        GraderSettings.get().set(END_ONYEN, endingOnyen);
        GraderSettings.get().set(PROBLEM_PATH, downloadPath);
//        ASakaiProjectDatabase.setCurrentSakaiProjectDatabase(new ASakaiProjectDatabase(downloadPath, null));
        GraderSettings.get().set("editor", editor);
        GraderSettings.get().save();
        graderSettingsManager.setEditor(editor);
        graderSettingsManager.setModule(currentModule);
        graderSettingsManager.setDownloadPath(currentModule, downloadPath);
        graderSettingsManager.setStartingOnyen(currentModule, startingOnyen);
        graderSettingsManager.setEndingOnyen(currentModule, endingOnyen);
        graderSettingsManager.setProblem(currentModule, currentProblem);
        graderSettingsManager.save();
        

        
//
//        
//        
//        
//        dynamicConfiguration.setProperty(EDITOR, editor);
//        dynamicConfiguration.setProperty(MODULE, currentModule);
//        dynamicConfiguration.setProperty(currentModule + "." + PROBLEM_PATH, downloadPath);
//        dynamicConfiguration.setProperty(PROBLEM_PATH, downloadPath);
//        dynamicConfiguration.setProperty(START_ONYEN, startingOnyen);
//        dynamicConfiguration.setProperty(currentModule + "." + START_ONYEN, startingOnyen);
//        dynamicConfiguration.setProperty(END_ONYEN, endingOnyen);
//        dynamicConfiguration.setProperty(currentModule + "." + END_ONYEN, endingOnyen);
//        try {
//			dynamicConfiguration.save();
//		} catch (ConfigurationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	@Row(0)
	public ModuleProblemSelector getModuleProblemSelector() {
		return moduleProblemSelector;
	}
	public void setModuleProblemSelector(ModuleProblemSelector moduleProblemSelector) {
		this.moduleProblemSelector = moduleProblemSelector;
	}
	
	@Row(1)
	public GraderFilesSetterModel getFileBrowsing() {
		return fileBrowsing;
	}
	public void setFileBrowsing(GraderFilesSetterModel fileBrowsing) {
		this.fileBrowsing = fileBrowsing;
	}
	@Row(2)
	public OnyenRangeModel getOnyens() {
		return onyens;
	}
	public void setOnyens(OnyenRangeModel onyens) {
		this.onyens = onyens;
	}	
	@Row(3)
	@Override
//	@Visible(false)
	public NavigationSetter getNavigationSetter() {
		return navigationSetter;
	}
	@Override
	public void setNavigationSetter(NavigationSetter navigationSetter) {
		this.navigationSetter = navigationSetter;
	}
	
	public boolean preBegin() {
		return !isGraderStarted();
	}

	@Row(4)
	@ComponentHeight(25)
	public synchronized void begin() {
		notify();
		
	}
	
	
	@Visible(false)
	public synchronized void awaitBegin() {
		graderStarted = false;
		// see comment about race conditions
//		propertyChangeSupport.firePropertyChange("this", null, this); // evaluate pre conditions
		try {
			wait();
			saveSettings();
			graderStarted = true;
			// this can cause concurrent changed to object editor  leading to race conditions
//			propertyChangeSupport.firePropertyChange("this", null, this); // evaluate pre conditions
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	public BeginActionModel getBeginActionModel() {
//		return beginActionModel;
//	}
//	public void setBeginActionModel(BeginActionModel beginActionModel) {
//		this.beginActionModel = beginActionModel;
//	}
	@Visible(false)
	@Override
	public boolean isGraderStarted() {
		return graderStarted;
	}
	@Override
	@Visible(false)
	public void setGraderStarted(boolean graderStarted) {
		this.graderStarted = graderStarted;
		propertyChangeSupport.firePropertyChange("this", null, this); // evaluate pre conditions

	}
//	
//	public void removeFeatureSpreadsheet() {
//		boolean retVal = database.getAssigmentDataFolder().removeFeatureGradeFile();
//		
//	}
//	
//	public boolean preRestoreFeatureSpreadsheet() {
//		return database.getAssigmentDataFolder().backupExists();
//	}
//	
//	public void restoreFeatureSpreadsheet() {
//		boolean retVal = database.getAssigmentDataFolder().restoreFeatureGradeFile();
//		
//	}

	void setCurrentProblem (String aProblem) {
		currentProblem = aProblem;
		problemDownloadPath = moduleDownloadPath + "\\" +  currentProblem;
		refreshProblemDownloadPath();
	}
	@Override
	@Visible(false)
	public String getCurrentProblem() {
		return currentProblem;
	}
	@Override
	public void addPropertyChangeListener(PropertyChangeListener aListener) {
		propertyChangeSupport.addPropertyChangeListener(aListener);
		
	}
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getSource() == moduleProblemSelector.getProblem()) {
			if (currentProblem.equals(moduleProblemSelector.getProblem().getValue())) return;
			setCurrentProblem(moduleProblemSelector.getProblem().getValue());
//			currentProblem = moduleProblemSelector.getProblem().getValue();
//			problemDownloadPath = moduleDownloadPath + "/" +  currentModule;
//			refreshProblemDownloadPath();
		} else if (evt.getSource() == moduleProblemSelector.getModule()) {
			if (currentModule.equals(moduleProblemSelector.getModule().getValue())) return;
			setCurrentModule(moduleProblemSelector.getModule().getValue());
			
		} else if (evt.getSource() == fileBrowsing.getDownloadFolder().getLabel()) {
			String newPath = fileBrowsing.getDownloadFolder().getLabel().getText();
			if (problemDownloadPath.equals(newPath)) return; // bounce back
			graderSettingsManager.setDownloadPath(currentModule, newPath);
			refreshAll();
		}
		
	}
	
	public static void main (String[] args) {
		AGraderSettingsModel startModel = new AGraderSettingsModel(null);
		OEFrame frame = ObjectEditor.edit(startModel);
		frame.setTitle("Grader Settings");
		frame.setSize(550, 475);
	}

}
