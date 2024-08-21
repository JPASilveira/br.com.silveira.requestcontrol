import entities.Applicant;
import entities.Request;
import repository.DataAccessObject;

public class Main {
    public static void main(String[] args) {
        DataAccessObject dataAcessObject = new DataAccessObject();

        Applicant applicant = new Applicant();
        applicant.setApplicantName("Jane due");
        applicant.setApplicantDocument("123.000.000-11");
        applicant.setId(1);
        Request request = new Request();
        request.setApplicant(applicant);
        request.setOpeningDate("31/07/2024");
        request.setClosingDate("31/07/2024");
        request.setDescription("teste");
        dataAcessObject.addRequest(request);
    }
}