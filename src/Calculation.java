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


		for (Task task : taskList) {
			ListIterator<Estimation> iterator = estimationList.listIterator();
			while (iterator.hasNext()) {
				Estimation est = iterator.next();
				if (est.taskCounter < counter) {
					iterator.add(new Estimation(est, task.estimateOptimistic, DELTA));
					iterator.add(new Estimation(est, task.estimateNormal, NORMAL));
					iterator.add(new Estimation(est, task.estimatePessimistic, DELTA));

				}
			}
			ListIterator<Estimation> iterator2 = estimationList.listIterator();
			while (iterator2.hasNext()) {
				Estimation est = iterator2.next();
				if (est.taskCounter < counter) {
					iterator2.remove();
				}
			}

			counter++;
		}

		Collections.sort(estimationList, Estimation.COMPARE_BY_DURATION);

		for (Estimation est : estimationList) {
			System.out.println(est.toString());
		}

		//сортировку и свертку списка перенести в цикл (чтобы не было так толсто)

	}

}