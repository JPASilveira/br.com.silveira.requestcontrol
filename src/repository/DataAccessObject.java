package repository;

import entities.Applicant;
import entities.Request;
import exceptions.ConnectionFactoryException;
import exceptions.DataAccessException;
import utilities.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataAccessObject {

    public void addApplicant(Applicant applicant) {
        String sql = "INSERT INTO Applicant (applicant_name, applicant_document) VALUES (?, ?)";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, applicant.getApplicantName());
            preparedStatement.setString(2, applicant.getApplicantDocument());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DataAccessException("Error adding applicant", e);
        } catch (ConnectionFactoryException e) {
            throw new DataAccessException("Error obtaining database connection", e);
        }
    }

    public void addRequest(Request request) {
        String sql = "INSERT INTO Request (applicant_id, opening_date, closing_date, description) VALUES (?, ?, ?, ?)";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, request.getApplicant().getId());
            preparedStatement.setString(2, request.getOpeningDate());
            preparedStatement.setString(3, request.getClosingDate());
            preparedStatement.setString(4, request.getDescription());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DataAccessException("Error adding request", e);
        } catch (ConnectionFactoryException e) {
            throw new DataAccessException("Error obtaining database connection", e);
        }
    }
}
