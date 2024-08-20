package utilities;

import exceptions.ConnectionFactoryException;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String DATABASE_NAME = "requestControl.sqlite";
    private static final String CONNECTION_URL = "jdbc:sqlite:" + DATABASE_NAME;

    public static Connection getConnection(){
        try {
            // Verifica se o arquivo do banco de dados existe
            File dbFile = new File(DATABASE_NAME);
            if (!dbFile.exists()) {
                System.out.println("Database file not found. Creating a new database...");
            }

            // Tenta estabelecer a conexão (o SQLite criará o banco se ele não existir)
            return DriverManager.getConnection(CONNECTION_URL);
        } catch (SQLException e) {
            throw new ConnectionFactoryException("Could not connect to database", e);
        }
    }
}
