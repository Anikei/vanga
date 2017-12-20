import java.io.File;
import java.util.*;

class Calculation {
	private final static double NORMAL = 0.666666666;//4/6
	private final static double DELTA = 0.166666666;//1/6

	static final ArrayList<Task> taskList = new ArrayList<>();
	private static final ArrayList<Estimation> estimationList = new ArrayList<>();


	public static void main(String[] args) {

		DataLoader.load(new File("data/in.csv"));
		//System.out.println(taskList.toString());

		estimationList.add(new Estimation("1"));

		int counter = 0;

		//long startTime = System.currentTimeMillis();

		for (Task task : taskList) {
			counter++;

			map(counter, task);
			reduceList(counter);

			Collections.sort(estimationList, Estimation.COMPARE_BY_DURATION);

			ListIterator<Estimation> iterator3 = estimationList.listIterator();
			Estimation estLeft = null;
			if (iterator3.hasNext()) {
				estLeft = iterator3.next();
			}
			while (iterator3.hasNext()) {
				Estimation estRight = iterator3.next();
				if (estLeft.duration == estRight.duration) {
					estLeft.mergeProbability(estRight.probability);
					iterator3.remove();
				}
				if (iterator3.hasNext()) {
					estLeft = estRight;//сдвиг к следующему
				}
			}

		}

		for (Estimation est : estimationList) {
			//System.out.println(est.toString());
			System.out.println(est.toCSV());
		}

	}

	private static void map(int counter, Task task) {
		ListIterator<Estimation> iterator = estimationList.listIterator();
		while (iterator.hasNext()) {
			Estimation est = iterator.next();
			if (est.taskCounter < counter) {
				iterator.add(new Estimation(est, task.estimateOptimistic, new Fraction(1, 6)));
				iterator.add(new Estimation(est, task.estimateNormal, new Fraction(4, 6)));
				iterator.add(new Estimation(est, task.estimatePessimistic, new Fraction(1, 6)));
			}
		}
	}

	private static void reduceList(int counter) {
		ListIterator<Estimation> iterator2 = estimationList.listIterator();
		while (iterator2.hasNext()) {
			Estimation est = iterator2.next();
			if (est.taskCounter < counter) {
				iterator2.remove();
			}
		}
	}

}