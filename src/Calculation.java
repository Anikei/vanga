import java.io.File;
import java.util.*;

class Calculation {

    private final static int NORMAL = 4;
    private final static int DELTA = 1;
    private final static int DENOMINATOR = 6;

    static final ArrayList<Task> taskList = new ArrayList<>();
    private static final ArrayList<Estimation> estimationList = new ArrayList<>();

    public static void main(String[] args) {

        DataLoader.load(new File("data/in.csv"));

        estimationList.add(new Estimation("1"));
        int counter = 0;

        for (Task task : taskList) {
            counter++;

            map(counter, task);
            reduceList(counter);

            estimationList.sort(Estimation.COMPARE_BY_DURATION);

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

        print();

    }

    private static void print() {
        for (Estimation est : estimationList) {
            //System.out.println(est.toString());
            System.out.println(est.toFile());
            //System.out.println(est.toCSV());
        }
    }

    private static void map(int counter, Task task) {
        ListIterator<Estimation> iterator = estimationList.listIterator();
        while (iterator.hasNext()) {
            Estimation est = iterator.next();
            if (est.taskCounter < counter) {
                iterator.add(new Estimation(est, task.estimateOptimistic, new Fraction(DELTA,
                        DENOMINATOR)));
                iterator.add(new Estimation(est, task.estimateNormal, new Fraction(NORMAL,
                        DENOMINATOR)));
                iterator.add(new Estimation(est, task.estimatePessimistic, new Fraction(DELTA,
                        DENOMINATOR)));
            }
        }
    }

    private static void reduceList(int counter) {
        estimationList.removeIf(est -> est.taskCounter < counter);
    }

}