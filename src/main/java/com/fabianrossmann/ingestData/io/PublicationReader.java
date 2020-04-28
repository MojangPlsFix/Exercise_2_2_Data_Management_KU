package com.fabianrossmann.ingestData.io;

import com.fabianrossmann.ingestData.objects.Publication;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class PublicationReader implements CSVFileReader<Publication> {
    @Override
    public List<Publication> readCSV(String path) throws IOException {
        FileReader reader = new FileReader(path);
        Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(reader);
        ArrayList<Publication> list = new ArrayList<>();

        for (CSVRecord record : records) {
            //PKey,Authors,Title,Pages,CJKey
            String PKey = record.get("PKey");
            String[] authors = record.get("Authors").split(":");
            String title = record.get("Title");
            String pages = record.get("Pages");
            String CJKey = record.get("CJKey");

            Publication publication = new Publication(PKey, Arrays.asList(authors), title, pages, CJKey);
            list.add(publication);
            System.out.println("[INFO] Read in: " + publication.toString());
        }
        return list;
    }
}
