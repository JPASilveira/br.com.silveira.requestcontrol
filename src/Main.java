import entities.Applicant;
import entities.Request;
import repository.DataAccessObject;
import utilities.DateFormatter;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        DataAccessObject dataAcessObject = new DataAccessObject();

        /*
        Applicant applicant = new Applicant();
        applicant.setApplicantName("Jane due");
        applicant.setApplicantDocument("123.000.000-11");
        dataAcessObject.addApplicant(applicant);*/

        Applicant applicant = dataAcessObject.getApplicantById(1);

        LocalDate teste = DateFormatter.formatDate("20/01/2024");
        System.out.println(teste);

        Request request = new Request();
        request.setApplicant(applicant);
        request.setOpeningDate("31/07/2024");
        request.setDescription("teste2");
        dataAcessObject.addRequest(request);
    }
}