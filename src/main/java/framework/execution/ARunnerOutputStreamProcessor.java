package framework.execution;

import java.io.InputStream;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

import tools.TimedProcess;

public class ARunnerOutputStreamProcessor extends ARunnerErrorOrOutStreamProcessor implements Runnable {
//	protected Scanner scanner ;
//	protected InputStream out;
//	protected RunningProject runner;
	public ARunnerOutputStreamProcessor(InputStream aProcessErrorOut, RunningProject aRunner, Semaphore aSemaphore) {
		super(aProcessErrorOut, aRunner, aSemaphore);
	}

@Override
void append(String s) {
	runner.appendOutput(s);
	
}
		

//			@Override
//			public void run() {
//				while (scanner.hasNextLine()) {
//					String line = scanner.nextLine();
//					System.err.println(line);
//					runner.appendErrorOutput(line + "\n");
//				}
//				scanner.close();
//			}
		
	}
