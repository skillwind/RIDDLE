package neu.lab.conflict;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

import neu.lab.conflict.util.MavenUtil;

@Mojo(name = "printSize", defaultPhase = LifecyclePhase.VALIDATE)
public class PrintSizeMojo extends ConflictMojo{

	@Override
	public void run() {
		try {
			PrintWriter printer = new PrintWriter(new BufferedWriter(new FileWriter("D:\\ws_testcase\\image\\projectSize.txt",true)));
			printer.println(MavenUtil.i().getBaseDir()+" "+systemSize+" "+(systemFileSize / 1000));
			printer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
