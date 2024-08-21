package repository;

import entities.Applicant;
import entities.Request;
import exceptions.ConnectionFactoryException;
import exceptions.DataAccessException;
import utilities.ConnectionFactory;
import utilities.DateFormatter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<Applicant> listAllApplicants() {
        String sql = "SELECT * FROM Applicant";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            List<Applicant> applicants = new ArrayList<>();

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

    public List<Applicant> listApplicantsByName(String applicantName) {
        String sql = "SELECT * FROM Applicant WHERE applicant_name LIKE ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, "%" + applicantName + "%");

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                List<Applicant> applicants = new ArrayList<>();

                while (resultSet.next()) {
                    Applicant applicant = new Applicant();
                    applicant.setId(resultSet.getInt("id"));
                    applicant.setApplicantName(resultSet.getString("applicant_name"));
                    applicant.setApplicantDocument(resultSet.getString("applicant_document"));
                    applicants.add(applicant);
                }
                return applicants;

            }

        } catch (SQLException e) {
            throw new DataAccessException("Error listing applicants by name", e);
        }
    }

    public List<Applicant> listApplicantsByDocument(String applicantDocument) {
        String sql = "SELECT * FROM Applicant WHERE applicant_document LIKE ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, "%" + applicantDocument + "%");

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                List<Applicant> applicants = new ArrayList<>();

                while (resultSet.next()) {
                    Applicant applicant = new Applicant();
                    applicant.setId(resultSet.getInt("id"));
                    applicant.setApplicantName(resultSet.getString("applicant_name"));
                    applicant.setApplicantDocument(resultSet.getString("applicant_document"));
                    applicants.add(applicant);
                }
                return applicants;

            }

        } catch (SQLException e) {
            throw new DataAccessException("Error listing applicants by document", e);
        }
    }

    public Applicant getApplicantById(int id) {
        String sql = "SELECT * FROM Applicant WHERE id = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Applicant applicant = new Applicant();
                    applicant.setId(resultSet.getInt("id"));
                    applicant.setApplicantName(resultSet.getString("applicant_name"));
                    applicant.setApplicantDocument(resultSet.getString("applicant_document"));
                    return applicant;
                } else {
                    throw new DataAccessException("Applicant not found");
                }
            }

        } catch (SQLException e) {
            throw new DataAccessException("Error getting applicant", e);
        }
    }

    public void removeApplicant(int applicantId) {
        String sql = "DELETE FROM Applicant WHERE id = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, applicantId);
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new DataAccessException("Applicant not found or not deleted");
            }

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

    public List<Request> listAllRequests() {
        String sql = "SELECT * FROM Request";
        List<Request> requests = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Applicant applicant = getApplicantById(resultSet.getInt("applicant_id"));
                Request request = new Request();
                request.setId(resultSet.getInt("id"));
                request.setApplicant(applicant);
                request.setOpeningDate(DateFormatter.sqlDateToLocalDate(resultSet.getString("opening_date")));
                request.setClosingDate(DateFormatter.sqlDateToLocalDate(resultSet.getString("closing_date")));
                request.setDescription(resultSet.getString("description"));
                requests.add(request);
            }

            return requests;

        } catch (SQLException e) {
            throw new DataAccessException("Error listing requests", e);
        }
    }

    public List<Request> listRequestsByApplicantId(int applicantId) {
        String sql = "SELECT * FROM Request WHERE applicant_id = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, applicantId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                List<Request> requests = new ArrayList<>();
                while (resultSet.next()) {
                    Applicant applicant = getApplicantById(resultSet.getInt("applicant_id"));
                    Request request = new Request();
                    request.setId(resultSet.getInt("id"));
                    request.setApplicant(applicant);
                    request.setOpeningDate(DateFormatter.sqlDateToLocalDate(resultSet.getString("opening_date")));
                    request.setClosingDate(DateFormatter.sqlDateToLocalDate(resultSet.getString("closing_date")));
                    request.setDescription(resultSet.getString("description"));
                    requests.add(request);
                }

                return requests;

            }

        } catch (SQLException e) {
            throw new DataAccessException("Error listing requests by applicant ID", e);
        }
    }

    public List<Request> listRequestsByOpeningDate(String openingDate) {
        String sql = "SELECT * FROM Request WHERE opening_date = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, openingDate);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                List<Request> requests = new ArrayList<>();

                while (resultSet.next()) {
                    Applicant applicant = getApplicantById(resultSet.getInt("applicant_id"));
                    Request request = new Request();
                    request.setId(resultSet.getInt("id"));
                    request.setApplicant(applicant);
                    request.setOpeningDate(DateFormatter.sqlDateToLocalDate(resultSet.getString("opening_date")));
                    request.setClosingDate(DateFormatter.sqlDateToLocalDate(resultSet.getString("closing_date")));
                    request.setDescription(resultSet.getString("description"));
                    requests.add(request);
                }
                return requests;
            }

        } catch (SQLException e) {
            throw new DataAccessException("Error listing requests by opening date", e);
        }
    }

    public void removeRequest(int requestId) {
        String sql = "DELETE FROM Request WHERE id = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, requestId);
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new DataAccessException("Request not found or not deleted");
            }

        } catch (SQLException e) {
            throw new DataAccessException("Error removing request", e);
        }
    }

}
