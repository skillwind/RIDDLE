package neu.lab.conflict;

import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

import neu.lab.conflict.util.UserConf;
import neu.lab.conflict.writer.RiskPathWriter;

@Mojo(name = "writePath", defaultPhase = LifecyclePhase.VALIDATE)
public class PathMojo extends ConflictMojo{

	@Override
	public void run() {
		new RiskPathWriter().writePath(UserConf.getOutDir() + "riskCallPath.txt", false);		
	}

}