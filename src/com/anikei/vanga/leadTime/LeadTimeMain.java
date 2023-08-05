package com.anikei.vanga.leadTime;

import com.anikei.vanga.util.Fraction;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * VANGA - kanban lead time
 *
 * ВХОД
 * для каждого из прошедших дней - фиксируем сколько часов+минут ушло на задачи
 * для текущих задач - имя задачи + ожидаемая длительность
 *
 * ЛОГИКА
 * на основании прошедшей статистики - строим вероятностное распределение
 * для первой текущей задачи
 * 	* разделяем на каждую длительность из списка (получаем число дней), перемножаем на вероятность, аггрегируем
 */
public class LeadTimeMain {

    static final ArrayList<TimeEntry> timeEntries = new ArrayList<>();
    static final ArrayList<KanbanTask> kanbanTasks = new ArrayList<>();

    public static void main(String[] args) {

        try {
            File file = new File("data/timing");
            CSVParser parser = CSVParser.parse(file, Charset.defaultCharset(), CSVFormat.TDF);
            for (CSVRecord record : parser) {
                if (record.getRecordNumber() != 1) {
                    timeEntries.add(new TimeEntry(record.get(0), record.get(1)));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        timeEntries.sort(TimeEntry.DESC_BY_TIME);

        try {
            File file = new File("data/tasks.csv");
            CSVParser parser = CSVParser.parse(file, Charset.defaultCharset(), CSVFormat.TDF);
            for (CSVRecord record : parser) {
                if (record.getRecordNumber() != 1) {
                    kanbanTasks.add(new KanbanTask(record.get(0), record.get(1)));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < kanbanTasks.size(); i++) {
            KanbanTask kanbanTask = kanbanTasks.get(i);
            if (i > 0) {
                kanbanTask.duration = kanbanTask.duration + kanbanTasks.get(i - 1).duration;
            }
        }

        for (KanbanTask kanbanTask : kanbanTasks) {
            int day = 1;
            int dailyActivity = kanbanTask.duration / day;
            int counter = 0;

            int i = 0;
            while (i < timeEntries.size()) {
                TimeEntry timeEntry = timeEntries.get(i);

                if (timeEntry.time < dailyActivity) {
                    kanbanTask.probabilities.put(day, new Fraction(counter, timeEntries.size()));
                    day = day + 1;
                    dailyActivity = kanbanTask.duration / day;
                    counter = 0;
                } else {
                    counter++;
                    i++;
                }

                if (i == timeEntries.size()) {
                    kanbanTask.probabilities.put(day, new Fraction(counter, timeEntries.size()));
                }
            }
        }

        for (KanbanTask kanbanTask : kanbanTasks) {
            System.out.println(kanbanTask.showProbabilities());
        }

        int maxLength = 0;
        for (KanbanTask kanbanTask : kanbanTasks) {
            if (kanbanTask.probabilities.size() > maxLength) {
                maxLength = kanbanTask.probabilities.size();
            }
        }

        maxLength = maxLength;
        for (int i = 1; i <= maxLength; i++) {
            String result = "T+" + i;

            for(KanbanTask kanbanTask:kanbanTasks) {
                try {
                    float chance = kanbanTask.probabilities.get(i).toDecimal().floatValue();
                    if (chance != 0.0) {
                        result = result + " " + kanbanTask.name + ": " + chance * 100 + "%";
                    }
                } catch (NullPointerException e) {}

            }

            if (!(result.equals("T+" + i))) {
                System.out.println(result);
            }
        }

    }

}