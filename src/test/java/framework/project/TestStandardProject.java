package framework.project;

import java.io.File;
import java.io.FileNotFoundException;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import tools.TestConfig;

/**
 * Created with IntelliJ IDEA.
 * User: josh
 * Date: 10/4/13
 * Time: 7:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestStandardProject {

    private File directory;
    private String name;

    @Before
    public void setUp() throws Exception {
        String directoryPath = TestConfig.getConfig().getString("test.exampleSakai.example1.project");
        directory = new File(directoryPath);
        name = TestConfig.getConfig().getString("test.exampleSakai.projectName");
    }

    @Test
    public void testCreation() throws FileNotFoundException {
        Project project = new StandardProject(null, directory, name);
        assertTrue("Creation succeeds", true);
    }

    @Test
    public void testCreationFail() throws FileNotFoundException {
        try {
            //Project project = new StandardProject(null, new File("/"), name);
            //fail("Creation should fail");
        } catch (Exception e) {
        }
    }

    @Test
    public void testGetClassesManager() throws FileNotFoundException {
        StandardProject project = new StandardProject(null, directory, name);
        assertTrue("ClassesManager should exist", project.getClassesManager().isDefined());
        assertFalse("ClassesManager should have class descriptions", project.getClassesManager().get().getClassDescriptions().isEmpty());
    }


}
