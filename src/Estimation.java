import java.io.Serializable;
import java.util.Comparator;

class Estimation implements Serializable {
	private final String name;
	private final double duration;
	private final double probability;
	public final int taskCounter;


	Estimation(String name) {
		this.name = name;
		this.duration = 0;
		this.probability = 1;
		this.taskCounter = 0;
	}

	Estimation(Estimation estimation, Double duration, double probability) {
		this.name = estimation.name;
		this.duration = estimation.duration + duration;
		this.probability = estimation.probability * probability;
		this.taskCounter = estimation.taskCounter + 1;
	}

	@Override
	public String toString() {
		return "Estimation{" +
				//"name='" + name + '\'' +
				", duration=" + duration +
				", probability=" + probability +
				//", taskCounter=" + taskCounter +
				'}';
	}

	public static final Comparator<Estimation> COMPARE_BY_DURATION = new Comparator<Estimation>() {
		@Override
		public int compare(Estimation lhs, Estimation rhs) {
			return (int) ((lhs.duration - rhs.duration) * 10);//костыль сравнения дробных показателей
		}
	};

}