package com.fabianrossmann.ingestData.io;

import com.fabianrossmann.ingestData.objects.Conference;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class ConferenceReader implements CSVFileReader<Conference> {
    @Override
    public List<Conference> readCSV(String path) throws IOException {
        FileReader reader = new FileReader(path);
        Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(reader);
        ArrayList<Conference> list = new ArrayList<>();

        for (CSVRecord record : records) {
            //CKey,Shortname,Title,City,Country,Editors,Year,ISBN
            String CKey = record.get("CKey");
            String shortName = record.get("Shortname");
            String title = record.get("Title");
            String city = record.get("City");
            String country = record.get("Country");
            String[] editors = record.get("Editors").split(":");
            int year;
            String isbn = record.get("ISBN");
            try {
                year = Integer.parseInt(record.get("Year"));
            } catch (NumberFormatException e) {
                year = 0;
            }
            Conference conference = new Conference(CKey, shortName, title, city, country, Arrays.asList(editors), year, isbn);
            System.out.println("[INFO] Read in: " + conference.toString());
            list.add(conference);
        }
        return list;
    }
}
