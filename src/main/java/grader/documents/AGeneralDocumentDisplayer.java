package grader.documents;


import grader.trace.file.open.DefaultProgramOpenedFile;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AGeneralDocumentDisplayer implements DocumentDisplayer {


    public void displayFile(String aFileName) {
        if (Desktop.isDesktopSupported()) {
            try {
                File aFile = new File(aFileName);
                if (!aFile.exists()) {
                    BufferedWriter output = new BufferedWriter(new FileWriter(aFile));
                    output.close();
                }
                Desktop.getDesktop().open(aFile);
                DefaultProgramOpenedFile.newCase(aFileName, this);
            } catch (IOException ex) {
                // no application registered for PDFs
                ex.printStackTrace();
            }
        }
    }


}
