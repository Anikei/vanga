import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

class DataLoader {

    static void load(File file) {
        try {
            CSVParser parser = CSVParser.parse(file, Charset.defaultCharset(), CSVFormat.RFC4180);
            for (CSVRecord record : parser) {
                if (record.getRecordNumber() != 1) {
                    Calculation.taskList.add(new Task(record.get(0),
                            Double.parseDouble(record.get(1).replace(",", ".")),
                            Double.parseDouble(record.get(2).replace(",", ".")),
                            Double.parseDouble(record.get(3).replace(",", "."))));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}