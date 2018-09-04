package neu.lab.conflict.risk.jar;

import java.util.ArrayList;
import java.util.List;

import neu.lab.conflict.vo.Conflict;
import neu.lab.conflict.vo.DepJar;

public class ConflictJRisk {
	
	private Conflict conflict;
	private List<DepJarJRisk> jarRisks;

	public ConflictJRisk(Conflict conflict) {
		this.conflict = conflict;
		jarRisks = new ArrayList<DepJarJRisk>();
		for (DepJar jar : conflict.getDepJars()) {
			jarRisks.add(new DepJarJRisk(jar, this));
		}
	}

	public DepJar getUsedDepJar() {
		return conflict.getUsedDepJar();
	}

	public Conflict getConflict() {
		return conflict;
	}

	public List<DepJarJRisk> getJarRisks() {
		return jarRisks;
	}

}
