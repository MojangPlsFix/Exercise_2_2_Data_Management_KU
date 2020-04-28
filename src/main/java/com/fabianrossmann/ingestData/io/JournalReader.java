package com.fabianrossmann.ingestData.io;

import com.fabianrossmann.ingestData.objects.Journal;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class JournalReader implements CSVFileReader<Journal> {
    @Override
    public List<Journal> readCSV(String path) throws IOException {
        FileReader reader = new FileReader(path);
        Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(reader);
        ArrayList<Journal> list = new ArrayList<>();

        for (CSVRecord record : records) {
            //JKey,Shortname,Title,Volume,Number,Year
            String JKey = record.get("JKey");
            String shortName = record.get("Shortname");
            String title = record.get("Title");
            int volume;
            int number;
            int year;

            try {
                year = Integer.parseInt(record.get("Year"));
            } catch (NumberFormatException e) {
                year = 0;

            }
            try {
                volume = Integer.parseInt(record.get("Volume"));
            } catch (NumberFormatException e) {
                volume = 0;

            }
            try {
                number = Integer.parseInt(record.get("Number"));
            } catch (NumberFormatException e) {
                number = 0;

            }
            Journal journal = new Journal(JKey, shortName, title, volume, number, year);
            System.out.println("[INFO] Read in: " + journal.toString());
            list.add(journal);
        }
        return list;
    }
}
