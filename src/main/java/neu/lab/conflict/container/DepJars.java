package neu.lab.conflict.container;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import neu.lab.conflict.util.MavenUtil;
import neu.lab.conflict.vo.DepJar;
import neu.lab.conflict.vo.NodeAdapter;

public class DepJars {
	private static DepJars instance;

	public static DepJars i() {
		return instance;
	}

	public static void init(NodeAdapters nodeAdapters) throws Exception {
		if (instance == null) {
			instance = new DepJars(nodeAdapters);
		}
	}

	private Set<DepJar> container;
	private DepJar hostDepJar;

	private DepJars(NodeAdapters nodeAdapters) throws Exception {
		container = new HashSet<DepJar>();
		for (NodeAdapter nodeAdapter : nodeAdapters.getAllNodeAdapter()) {
			container.add(new DepJar(nodeAdapter.getGroupId(), nodeAdapter.getArtifactId(), nodeAdapter.getVersion(),
					nodeAdapter.getClassifier(), nodeAdapter.getFilePath()));
		}

	}

	public Set<DepJar> getUsedDepJars() {
		Set<DepJar> usedDepJars = new HashSet<DepJar>();
		for (DepJar depJar : container) {
			if (depJar.isSelected()) {
				usedDepJars.add(depJar);
			}
		}
		return usedDepJars;
	}

	public DepJar getHostDepJar() {
		if (hostDepJar == null) {

			for (DepJar depJar : container) {
				if (depJar.isHost()) {
					if (hostDepJar != null) {
						MavenUtil.i().getLog().warn("multiple depjar for host ");
					}
					hostDepJar = depJar;
				}
			}
		}
		return hostDepJar;
	}

	public DepJar getDep(String groupId, String artifactId, String version, String classifier) {
		for (DepJar dep : container) {
			if (dep.isSame(groupId, artifactId, version, classifier)) {
				return dep;
			}
		}
		MavenUtil.i().getLog().warn("cant find dep:" + groupId + ":" + artifactId + ":" + version + ":" + classifier);
		return null;
	}

	public Set<DepJar> getAllDepJar() {
		return container;
	}

	public DepJar getDep(NodeAdapter nodeAdapter) {
		return getDep(nodeAdapter.getGroupId(), nodeAdapter.getArtifactId(), nodeAdapter.getVersion(),
				nodeAdapter.getClassifier());
	}

	public List<String> getUsedJarPaths() {
		List<String> usedJarPaths = new ArrayList<String>();
		for (DepJar depJar : DepJars.i().getAllDepJar()) {
			if (depJar.isSelected()) {
				for (String path : depJar.getJarFilePaths(true)) {
					usedJarPaths.add(path);
				}
			}
		}
		return usedJarPaths;
	}
	
	/**
	 * @return path1;path2;path3
	 */
	public String getUsedJarPathsStr() {
		Set<String> usedJarPath = new LinkedHashSet<String>();
		StringBuilder sb = new StringBuilder();
		for(String path:getUsedJarPaths()) {
			sb.append(path+File.pathSeparator);
		}
		String paths = sb.toString();
		paths = paths.substring(0,paths.length()-1);//delete last ;
		return paths;
	}
	
	/**
	 * @param cls
	 * @return usedDepJar that has class.
	 */
	public DepJar getClassJar(String cls) {
		for (DepJar depJar : DepJars.i().getAllDepJar()) {
			if (depJar.isSelected()) {
				if(depJar.containsCls(cls))
					return depJar;
			}
		}
		return null;
	}

}
