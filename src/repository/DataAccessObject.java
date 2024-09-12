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

    public void editApplicant(int requestId, Applicant applicant) throws DataAccessException {
        StringBuilder sql = new StringBuilder("UPDATE Applicant SET ");
        boolean hasApplicantName = false, hasApplicantDocument = false;
        boolean isFirst = true;


        if (applicant.getApplicantName() != null) {
            sql.append("applicant_name = ?");
            hasApplicantName = true;
            isFirst = false;
        }

        if (applicant.getApplicantDocument() != null) {
            if (!isFirst) {
                sql.append(", ");
            }
            sql.append("applicant_document = ?");
            hasApplicantDocument = true;
        }

        sql.append(" WHERE id = ?");

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql.toString())) {

            int parameterIndex = 1;

            if (hasApplicantName) {
                preparedStatement.setString(parameterIndex++, applicant.getApplicantName());
            }

            if (hasApplicantDocument) {
                preparedStatement.setString(parameterIndex++, applicant.getApplicantDocument());
            }

            preparedStatement.setInt(parameterIndex, requestId);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DataAccessException("Error updating applicant with ID: " + requestId, e);
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

    public void addRequest(Request request) throws DataAccessException {
        StringBuilder sql = new StringBuilder("INSERT INTO Request (applicant_id, opening_date");
        StringBuilder values = new StringBuilder("VALUES (?, ?");

        boolean hasClosingDate = request.getClosingDate() != null && !request.getClosingDate().isEmpty();

        if (hasClosingDate) {
            sql.append(", closing_date");
            values.append(", ?");
        }

        sql.append(", description) ");
        values.append(", ?)");

        sql.append(values.toString());

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql.toString())) {

            preparedStatement.setInt(1, request.getApplicant().getId());
            preparedStatement.setString(2, request.getOpeningDate());

            int parameterIndex = 3;

            if (hasClosingDate) {
                preparedStatement.setString(parameterIndex++, request.getClosingDate());
            }

            preparedStatement.setString(parameterIndex, request.getDescription());

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

    public Request getRequestById(int requestId) {
        String sql = "SELECT * FROM Request WHERE id = ?";

        try(Connection connection = ConnectionFactory.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, requestId);

            try (ResultSet resultSet = preparedStatement.executeQuery()){
                Request request = new Request();
                while (resultSet.next()){
                    request.setId(resultSet.getInt("id"));
                    request.setApplicant(getApplicantById(resultSet.getInt("applicant_id")));
                    request.setOpeningDate(DateFormatter.sqlDateToLocalDate(resultSet.getString("opening_date")));
                    request.setClosingDate(DateFormatter.sqlDateToLocalDate(resultSet.getString("closing_date")));
                    request.setDescription(resultSet.getString("description"));
                }
                return request;
            }
        }catch(SQLException e){
            throw new DataAccessException("Error list request by id",e);
        }
    }

    public void editRequest(int requestId, Request request) throws DataAccessException {
        StringBuilder sql = new StringBuilder("UPDATE Request SET ");
        boolean isFirst = true;

        if (request.getApplicant() != null) {
            sql.append("applicant_id = ?");
            isFirst = false;
        }
        if (request.getOpeningDate() != null) {
            if (!isFirst) {
                sql.append(", ");
            }
            sql.append("opening_date = ?");
            isFirst = false;
        }
        if (request.getClosingDate() != null) {
            if (!isFirst) {
                sql.append(", ");
            }
            sql.append("closing_date = ?");
            isFirst = false;
        }
        if (request.getDescription() != null && !request.getDescription().isEmpty()) {
            if (!isFirst) {
                sql.append(", ");
            }
            sql.append("description = ?");
        }

        sql.append(" WHERE id = ?");

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql.toString())) {

            int parameterIndex = 1;

            if (request.getApplicant() != null) {
                preparedStatement.setInt(parameterIndex++, request.getApplicant().getId());
            }
            if (request.getOpeningDate() != null) {
                preparedStatement.setString(parameterIndex++, request.getOpeningDate());
            }
            if (request.getClosingDate() != null) {
                preparedStatement.setString(parameterIndex++, request.getClosingDate());
            }
            if (request.getDescription() != null && !request.getDescription().isEmpty()) {
                preparedStatement.setString(parameterIndex++, request.getDescription());
            }

            preparedStatement.setInt(parameterIndex, requestId);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DataAccessException("Error updating request with ID: " + requestId, e);
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
