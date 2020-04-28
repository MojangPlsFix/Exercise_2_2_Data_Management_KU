package com.fabianrossmann.ingestData.io;

import com.fabianrossmann.ingestData.objects.Person;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class PersonReader implements CSVFileReader<Person> {
    @Override
    public List<Person> readCSV(String path) throws IOException {
        FileReader reader = new FileReader(path);
        Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(reader);
        ArrayList<Person> list = new ArrayList<>();

        for (CSVRecord record : records) {
            //AKey,Name,Aliases,Affiliation,Country,Website
            String AKey = record.get("AKey");
            String name = record.get("Name");
            String aliases = record.get("Aliases");
            String affiliation = record.get("Affiliation");
            String country = record.get("Country");
            String website = record.get("Website");

            Person person = new Person(AKey, name, aliases, affiliation, country, website);
            System.out.println("[INFO] Read in: " + person.toString());
            list.add(person);
        }
        return list;
    }
}
