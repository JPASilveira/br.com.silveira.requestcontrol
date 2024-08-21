package utilities;

import exceptions.ConnectionFactoryException;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionFactory {

    private static final String DATABASE_NAME = "requestControl.sqlite";
    private static final String CONNECTION_URL = "jdbc:sqlite:" + DATABASE_NAME;

    public static Connection getConnection() {
        try {
            // Verifica se o arquivo do banco de dados existe
            File dbFile = new File(DATABASE_NAME);
            boolean databaseExists = dbFile.exists();

            // Tenta estabelecer a conexão (o SQLite criará o banco se ele não existir)
            Connection connection = DriverManager.getConnection(CONNECTION_URL);

            // Se o banco de dados não existia, cria as tabelas
            if (!databaseExists) {
                System.out.println("Database file not found. Creating a new database...");
                createTables(connection);
            }

            return connection;
        } catch (SQLException e) {
            throw new ConnectionFactoryException("Could not connect to database", e);
        }
    }

    private static void createTables(Connection connection) {
        // Criação da tabela Applicant
        String createApplicantTableSQL = """
        CREATE TABLE IF NOT EXISTS Applicant (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            applicant_name TEXT NOT NULL,
            applicant_document TEXT NOT NULL
        );
        """;

        // Criação da tabela Request
        String createRequestTableSQL = """
        CREATE TABLE IF NOT EXISTS Request (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            applicant_id INTEGER NOT NULL,
            opening_date TEXT NOT NULL,
            closing_date TEXT,
            description TEXT,
            FOREIGN KEY(applicant_id) REFERENCES Applicant(id)
        );
        """;

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createApplicantTableSQL);
            stmt.execute(createRequestTableSQL);
            System.out.println("Tables Applicant and Request created successfully.");
        } catch (SQLException e) {
            throw new ConnectionFactoryException("Could not create tables in database", e);
        }
    }

}
