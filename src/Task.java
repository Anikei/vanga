class Task {
	public final double estimateOptimistic;
	public final double estimateNormal;
	public final double estimatePessimistic;
	private final String name;

	@Override
	public String toString() {
		return "Task{name=" + name +
				", " + estimateOptimistic +
				"/" + estimateNormal +
				"/" + estimatePessimistic + "}";
	}

	public Task(String name, double opt, double norm, double pes) {
		this.name = name;
		this.estimateNormal = norm;
		this.estimateOptimistic = opt;
		this.estimatePessimistic = pes;
	}

}