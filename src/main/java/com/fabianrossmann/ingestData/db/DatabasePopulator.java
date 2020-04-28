package com.fabianrossmann.ingestData.db;

import com.fabianrossmann.ingestData.io.ConferenceReader;
import com.fabianrossmann.ingestData.io.JournalReader;
import com.fabianrossmann.ingestData.io.PersonReader;
import com.fabianrossmann.ingestData.io.PublicationReader;
import com.fabianrossmann.ingestData.io.ThesisReader;
import com.fabianrossmann.ingestData.objects.Conference;
import com.fabianrossmann.ingestData.objects.Journal;
import com.fabianrossmann.ingestData.objects.Person;
import com.fabianrossmann.ingestData.objects.Publication;
import com.fabianrossmann.ingestData.objects.Thesis;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

public class DatabasePopulator {
    public static final String COUNTRY_SELECT = "SELECT * FROM countries WHERE Name=?";
    public static final String COUNTRY_INSERT = "INSERT INTO countries(Name) VALUES (?)";

    public static final String INSTITUTION_SELECT = "SELECT * FROM institutions WHERE Name=?";
    public static final String INSTITUTION_INSERT = "INSERT INTO institutions(Name,cokey) VALUES (?,?)";

    public static final String PERSON_INSERT = "INSERT INTO persons(AKey,Name,Website,IKey) VALUES (?,?,?,?)";

    public static final String THESIS_INSERT = "INSERT INTO Theses(TKey,Title,Year,NPages,Type,isbn,AKey,IKey) VALUES(?,?,?,?,?,?,?,?)";

    public static final String CONFERENCES_INSERT = "INSERT INTO Conferences(CKey,SName,Title,City,CoKey,Year,ISBN) VALUES(?,?,?,?,?,?,?)";

    public static final String JOURNALS_INSERT = "INSERT INTO Journals(JKey,SName,Title,Volume,Issue,Year) VALUES(?,?,?,?,?,?)";

    public static final String PAPERS_INSERT = "INSERT INTO papers(PKey,Title,Pages,CKey,JKey) VALUES (?,?,?,?,?)";

    public static final String AUTH_PAPERS_INSERT = "INSERT INTO AuthPapers(PKey,AKey,rank) VALUES (?,?,?)";

    private final String conferenceCSVPath;
    private final String journalCSVPath;
    private final String personCSVPath;
    private final String publicationCSVPath;
    private final String thesisCSVPath;

    private List<Conference> conferences;
    private List<Journal> journals;
    private List<Person> persons;
    private List<Publication> publications;
    private List<Thesis> theses;

    private DataSource dataSource;

    public DatabasePopulator(String conferenceCSVPath, String journalCSVPath, String personCSVPath, String publicationCSVPath, String thesisCSVPath) throws IOException {
        this.conferenceCSVPath = conferenceCSVPath;
        this.journalCSVPath = journalCSVPath;
        this.personCSVPath = personCSVPath;
        this.publicationCSVPath = publicationCSVPath;
        this.thesisCSVPath = thesisCSVPath;
        retrieveData();
    }

    //<host> <port> <database> <user> <password>
    public void populateDatabase(String host, int port, String database, String user, String password) throws SQLException {
        ConnectionManager connectionManager = new ConnectionManager(host, port, database, user, password);
        dataSource = connectionManager.setupDataSource();

        Connection connection = dataSource.getConnection();

        System.out.println("[INFO] Getting countries");
        HashSet<String> countries = this.getCountries();
        System.out.println("[INFO] Starting to insert countries");
        for (String country : countries) {
            PreparedStatement preparedStatement = connection.prepareStatement(COUNTRY_INSERT);
            preparedStatement.setString(1, country);
            int row = preparedStatement.executeUpdate();
            System.out.println("[INFO] Inserted " + row + " Row to Countries");
        }

        HashMap<String, Integer> institiutions = getInstitutions();

        for (Map.Entry<String, Integer> entry : institiutions.entrySet()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSTITUTION_INSERT);
            preparedStatement.setString(1, entry.getKey());
            preparedStatement.setInt(2, entry.getValue());
            int row = preparedStatement.executeUpdate();
            System.out.println("[INFO] Inserted " + row + " Row to Institutions");
        }

        for (Person person : persons) {
            int IKey = -1;
            if (!person.getAffiliation().isEmpty()) {
                IKey = getInstitutionId(person.getAffiliation());
            }
            PreparedStatement preparedStatement = connection.prepareStatement(PERSON_INSERT);
            preparedStatement.setInt(1, Integer.parseInt(person.getAKey().replaceAll("A", "")));
            preparedStatement.setString(2, person.getName());
            preparedStatement.setString(3, person.getWebsite());
            if (!person.getAffiliation().isEmpty()) {
                preparedStatement.setInt(4, IKey);
            } else {
                preparedStatement.setNull(4, Types.INTEGER);
            }
            int row = preparedStatement.executeUpdate();
            System.out.println("[INFO] Inserted " + row + " Row to Persons");
        }

        for (Thesis thesis : theses) {
            PreparedStatement preparedStatement = connection.prepareStatement(THESIS_INSERT);

            preparedStatement.setInt(1, Integer.parseInt(thesis.getTKey().replaceAll("T", "")));
            preparedStatement.setString(2, thesis.getTitle());
            preparedStatement.setInt(3, thesis.getYear());

            int pages = 0;
            if (!thesis.getPages().isEmpty()) {
                pages = Integer.parseInt(thesis.getPages());
                preparedStatement.setInt(4, pages);
            } else {
                preparedStatement.setNull(4, Types.INTEGER);
            }
            preparedStatement.setString(5, thesis.getType());
            preparedStatement.setString(6, thesis.getIsbn());
            preparedStatement.setInt(7, Integer.parseInt(thesis.getAuthor().replaceAll("A", "")));
            if (!thesis.getSchool().isEmpty()) {
                preparedStatement.setInt(8, getInstitutionId(thesis.getSchool()));
            } else {
                preparedStatement.setNull(8, Types.INTEGER);
            }

            int row = preparedStatement.executeUpdate();
            System.out.println("[INFO] Inserted " + row + " Row to Theses");
        }

        for(Conference conference : conferences){
            PreparedStatement preparedStatement = connection.prepareStatement(CONFERENCES_INSERT);
            preparedStatement.setInt(1, Integer.parseInt(conference.getCKey().replaceAll("C", "")));
            preparedStatement.setString(2, conference.getShortName());
            preparedStatement.setString(3, conference.getTitle());
            preparedStatement.setString(4, conference.getCity());
            preparedStatement.setInt(5, getCountryId(conference.getCountry()));
            preparedStatement.setInt(6,conference.getYear());
            preparedStatement.setString(7,conference.getIsbn());
            int row = preparedStatement.executeUpdate();
            System.out.println("[INFO] Inserted " + row + " Row to Conferences");
        }

        for(Journal journal : journals){
            PreparedStatement preparedStatement = connection.prepareStatement(JOURNALS_INSERT);
            preparedStatement.setInt(1, Integer.parseInt(journal.getJKey().replaceAll("J", "")));
            preparedStatement.setString(2, journal.getShortName());
            preparedStatement.setString(3, journal.getTitle());
            preparedStatement.setInt(4,journal.getVolume());
            preparedStatement.setInt(5,journal.getNumber());
            preparedStatement.setInt(6,journal.getYear());
            int row = preparedStatement.executeUpdate();
            System.out.println("[INFO] Inserted " + row + " Row to Journals");
        }

        for(Publication publication : publications){
            PreparedStatement preparedStatement = connection.prepareStatement(PAPERS_INSERT);
            preparedStatement.setInt(1, Integer.parseInt(publication.getPKey().replaceAll("P", "")));
            preparedStatement.setString(2, publication.getTitle());
            preparedStatement.setString(3,publication.getPages());
            if(publication.getCJKey().startsWith("C")){
                preparedStatement.setInt(4, Integer.parseInt(publication.getCJKey().replaceAll("C", "")));
                preparedStatement.setNull(5,Types.INTEGER);;
            }else{
                preparedStatement.setNull(4,Types.INTEGER);
                preparedStatement.setInt(5, Integer.parseInt(publication.getCJKey().replaceAll("J", "")));
            }
            int row = preparedStatement.executeUpdate();
            System.out.println("[INFO] Inserted " + row + " Row to Papers");
            int i=1;
            for(String author: publication.getAuthors()){
                PreparedStatement preparedStatement2 = connection.prepareStatement(AUTH_PAPERS_INSERT);
                preparedStatement2.setInt(1, Integer.parseInt(publication.getPKey().replaceAll("P", "")));
                preparedStatement2.setInt(2, Integer.parseInt(author.replaceAll("A", "")));
                preparedStatement2.setInt(3,i);
                int row2 = preparedStatement2.executeUpdate();
                System.out.println("[INFO] Inserted " + row2 + " Row to AuthPapers");
                i++;
            }
        }
        connectionManager.closeDataSource(dataSource);
    }

    private void retrieveData() throws IOException {
        ConferenceReader conferenceReader = new ConferenceReader();
        JournalReader journalReader = new JournalReader();
        PersonReader personReader = new PersonReader();
        PublicationReader publicationReader = new PublicationReader();
        ThesisReader thesisReader = new ThesisReader();

        conferences = conferenceReader.readCSV(conferenceCSVPath);
        journals = journalReader.readCSV(journalCSVPath);
        persons = personReader.readCSV(personCSVPath);
        publications = publicationReader.readCSV(publicationCSVPath);
        theses = thesisReader.readCSV(thesisCSVPath);
    }

    private HashSet<String> getCountries() {
        HashSet<String> countries = new HashSet<>();
        for (Conference conference : this.conferences) {
            String country = conference.getCountry();
            countries.add(country);
        }

        for (Person person : this.persons) {
            String country = person.getCountry();
            countries.add(country);
        }

        for (Thesis thesis : this.theses) {
            String country = thesis.getCountry();
            countries.add(country);
        }

        return countries;
    }

    private int getCountryId(String name) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(COUNTRY_SELECT);
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        int result = resultSet.getInt(1);
        connection.close();
        return result;
    }

    private HashMap<String, Integer> getInstitutions() throws SQLException {
        HashMap<String, Integer> institutions = new HashMap<>();
        Connection connection = dataSource.getConnection();
        for (Person person : this.persons) {
            if (!person.getAffiliation().isEmpty()) {
                int CoKey = getCountryId(person.getCountry());
                String institution = person.getAffiliation();

                institutions.put(institution, CoKey);
            }
        }

        for (Thesis thesis : this.theses) {
            if (!thesis.getSchool().isEmpty()) {
                int CoKey = getCountryId(thesis.getCountry());
                String institution = thesis.getSchool();

                institutions.put(institution, CoKey);
            }
        }
        connection.close();
        return institutions;
    }

    private int getInstitutionId(String name) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSTITUTION_SELECT);
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        int result = resultSet.getInt(1);
        connection.close();
        return result;
    }
}
