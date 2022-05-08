package core;

public class EdgeKruskal implements Comparable<EdgeKruskal> {

	public String init;
	public String end;
	public float weight;

	public void setInit(String init) {
		this.init = init;
	}

	public String getInit() {
		return init;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getEnd() {
		return end;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public float getWeight() {
		return weight;
	}

	@Override
	public int compareTo(EdgeKruskal outherEdgeKruskal) {
		if (this.weight < outherEdgeKruskal.getWeight()) {
			return -1;
		}
		if (this.weight > outherEdgeKruskal.getWeight()) {
			return 1;
		}
		return 0;
	}
}
