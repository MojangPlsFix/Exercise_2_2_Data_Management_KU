package com.fabianrossmann.ingestData;

import com.fabianrossmann.ingestData.db.DatabasePopulator;
import java.io.IOException;
import java.sql.SQLException;

public class IngestData {
    public static void main(String[] args) {
        if(args.length==10){

            try {
                DatabasePopulator populator
                    = new DatabasePopulator(args[0], args[1], args[2], args[3], args[4]);
                System.out.println("[INFO] Populator is initialized");
                populator.populateDatabase(args[5], Integer.parseInt(args[6]), args[7], args[8], args[9]);
            } catch (IOException | SQLException e) {
                e.printStackTrace();
                System.out.println("Usage: IngestData ./confs.csv ./journals.csv ./persons.csv ./pubs.csv ./theses.csv <host> <port> <database> <user> <password>");
            }
        }
        else{
            System.out.println("Usage: IngestData ./confs.csv ./journals.csv ./persons.csv ./pubs.csv ./theses.csv <host> <port> <database> <user> <password>");
        }
    }
}
