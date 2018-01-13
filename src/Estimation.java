import java.io.Serializable;
import java.util.Comparator;

class Estimation implements Serializable {
    private final String name;
    final double duration;
    final Fraction probability;
    public final int taskCounter;


    Estimation(String name) {
        this.name = name;
        this.duration = 0;
        this.probability = new Fraction(1, 1);
        this.taskCounter = 0;
    }

    Estimation(Estimation estimation, Double duration, Fraction probability) {
        this.name = estimation.name;
        this.duration = estimation.duration + duration;
        this.probability = estimation.probability.multiply(probability);
        this.taskCounter = estimation.taskCounter + 1;
    }

    void mergeProbability(Fraction probability) {
        this.probability.add(probability);
    }

    @Override
    public String toString() {
        return "Estimation{" +
                //"name='" + name + '\'' +
                ",\tduration = " + duration +
                ",\tprobability=" + probability.toString()
                //+ format("%.50f", probability.toDecimal())
                //+ ", taskCounter=" + taskCounter +
                + '}';
    }

    public String toFile() {
        return "duration = \t" + duration
                + "\tprobability=\t"
                + probability.toString();
    }

	/*
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
	*/


    public static final Comparator<Estimation> COMPARE_BY_DURATION = new Comparator<Estimation>() {
        @Override
        public int compare(Estimation lhs, Estimation rhs) {
            return (int) ((lhs.duration - rhs.duration) * 10);//костыль сравнения дробных показателей
        }
    };

}