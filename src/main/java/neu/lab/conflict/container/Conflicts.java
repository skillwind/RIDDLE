package neu.lab.conflict.container;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import neu.lab.conflict.vo.NodeAdapter;
import neu.lab.conflict.Conf;
import neu.lab.conflict.vo.Conflict;

public class Conflicts {
	private static Conflicts instance;

	public static void init(NodeAdapters nodeAdapters) {
		if (instance == null) {
			instance = new Conflicts(nodeAdapters);
		}
	}

	public static Conflicts i() {
		return instance;
	}

	private List<Conflict> container;

	/**
	 * must initial NodeAdapters before this construct
	 */
	private Conflicts(NodeAdapters nodeAdapters) {
		container = new ArrayList<Conflict>();
		for (NodeAdapter node : nodeAdapters.getAllNodeAdapter()) {
				addNodeAdapter(node);
		}

		// delete conflict if there is only one version
		Iterator<Conflict> ite = container.iterator();
		while (ite.hasNext()) {
			Conflict conflict = ite.next();
			if (!conflict.isConflict()||!wantCal( conflict)) {
				ite.remove();
			}
		}
	}
	
	/**this method use to debug.
	 * @param conflict
	 * @return
	 */
	private boolean wantCal(Conflict conflict) {

		if(Conf.callConflict==null||"".equals(Conf.callConflict)) {
			return true;
		}else {
			if(conflict.getSig().equals(Conf.callConflict.replace("+", ":"))) 
				return true;
			return false;
		}
		
//		
//		return true;
	}

	public List<Conflict> getConflicts() {
		return container;
	}

	private void addNodeAdapter(NodeAdapter nodeAdapter) {
		Conflict conflict = null;
		for (Conflict existConflict : container) {
			if (existConflict.sameArtifact(nodeAdapter.getGroupId(), nodeAdapter.getArtifactId())) {
				conflict = existConflict;
			}
		}
		if (null == conflict) {
			conflict = new Conflict(nodeAdapter.getGroupId(), nodeAdapter.getArtifactId());
			container.add(conflict);
		}
		conflict.addNode(nodeAdapter);
	}

	@Override
	public String toString() {
		String str = "project has " + container.size() + " conflict-dependency:+\n";
		for (Conflict conflictDep : container) {
			str = str + conflictDep.toString() + "\n";
		}
		return str;
	}
}
