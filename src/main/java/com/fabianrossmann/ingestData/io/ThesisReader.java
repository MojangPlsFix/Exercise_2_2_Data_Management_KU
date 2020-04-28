package com.fabianrossmann.ingestData.io;

import com.fabianrossmann.ingestData.objects.Thesis;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class ThesisReader implements CSVFileReader<Thesis> {
    @Override
    public List<Thesis> readCSV(String path) throws IOException {
        FileReader reader = new FileReader(path);
        Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(reader);
        ArrayList<Thesis> list = new ArrayList<>();

        for (CSVRecord record : records) {
            //TKey,Author,Title,Year,Type,School,Country,Pages,ISBN
            String TKey = record.get("TKey");
            String author = record.get("Author");
            String title = record.get("Title");
            int year;
            try {
                year = Integer.parseInt(record.get("Year"));
            } catch (NumberFormatException e) {
                year = 0;
            }
            String type = record.get("Type");
            String school = record.get("School");
            String country = record.get("Country");
            String pages = record.get("Pages");
            String isbn = record.get("ISBN");

            Thesis thesis = new Thesis(TKey, author, title, year, type, school, country, pages, isbn);
            System.out.println("[INFO] Read in: " + thesis.toString());
            list.add(thesis);
        }
        return list;
    }
}
