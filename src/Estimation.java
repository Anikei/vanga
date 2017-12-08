import java.io.Serializable;
import java.util.Comparator;

import static java.lang.String.format;

class Estimation implements Serializable {
	private final String name;
	final double duration;
	double probability;
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

	void mergeProbability(double probability) {
		this.probability = this.probability + probability;
	}

	@Override
	public String toString() {
		return "Estimation{" +
				//"name='" + name + '\'' +
				",\tduration = " + duration +
				",\tprobability=" + format("%.50f", probability) +
				//", taskCounter=" + taskCounter +
				'}';
	}

	public String toCSV() {
		return "\"" +
				//name + "\"," +
				"\",\"" +
				duration +
				"\",\"" +
				format("%.50f", probability) +
				//", taskCounter=" + taskCounter +
				"\"";
	}


	public static final Comparator<Estimation> COMPARE_BY_DURATION = new Comparator<Estimation>() {
		@Override
		public int compare(Estimation lhs, Estimation rhs) {
			return (int) ((lhs.duration - rhs.duration) * 10);//костыль сравнения дробных показателей
		}
	};

}