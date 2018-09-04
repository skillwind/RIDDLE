package neu.lab.conflict;


import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;


@Mojo(name = "debug2", defaultPhase = LifecyclePhase.VALIDATE)
public class Debug2Mojo extends ConflictMojo {

	@Override
	public void run() {
		TestCaseGenerator testCaseGenerator = new TestCaseGenerator("D:\\ws_testcase\\image\\path\\", append);
		testCaseGenerator.writePath();
	}

}
