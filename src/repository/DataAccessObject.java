package repository;

import entities.Applicant;
import entities.Request;
import exceptions.ConnectionFactoryException;
import exceptions.DataAccessException;
import utilities.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
        }
    }

    public ArrayList<Applicant> listAllApplicants() {
        String sql = "SELECT * FROM Applicant";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            ArrayList<Applicant> applicants = new ArrayList<>();

            while (resultSet.next()) {
                Applicant applicant = new Applicant();
                applicant.setId(resultSet.getInt("id"));
                applicant.setApplicantName(resultSet.getString("applicant_name"));
                applicant.setApplicantDocument(resultSet.getString("applicant_document"));
                applicants.add(applicant);
            }
            return applicants;

        } catch (SQLException e) {
            throw new DataAccessException("Error listing applicants", e);
        }
    }

    public ArrayList<Applicant> listApplicantsByName(String applicantName) {
        String sql = "SELECT * FROM Applicant WHERE applicant_name LIKE ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, "%" + applicantName + "%");
            ArrayList<Applicant> applicants = new ArrayList<>();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    Applicant applicant = new Applicant();
                    applicant.setId(resultSet.getInt("id"));
                    applicant.setApplicantName(resultSet.getString("applicant_name"));
                    applicant.setApplicantDocument(resultSet.getString("applicant_document"));
                    applicants.add(applicant);
                }
            }
            return applicants;

        } catch (SQLException e) {
            throw new DataAccessException("Error listing applicants by name", e);
        }
    }

    public ArrayList<Applicant> listApplicantsByDocument(String applicantDocument) {
        String sql = "SELECT * FROM Applicant WHERE applicant_document LIKE ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, "%" + applicantDocument + "%");
            ArrayList<Applicant> applicants = new ArrayList<>();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    Applicant applicant = new Applicant();
                    applicant.setId(resultSet.getInt("id"));
                    applicant.setApplicantName(resultSet.getString("applicant_name"));
                    applicant.setApplicantDocument(resultSet.getString("applicant_document"));
                    applicants.add(applicant);
                }
            }
            return applicants;

        } catch (SQLException e) {
            throw new DataAccessException("Error listing applicants by document", e);
        }
    }

    public void removeApplicant(Applicant applicant) {
        String sql = "DELETE FROM Applicant WHERE id = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, applicant.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DataAccessException("Error removing applicant", e);
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
        }
    }
}
