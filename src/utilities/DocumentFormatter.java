package utilities;

public class DocumentFormatter {

    public static String formatCpfOrCnpj(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Input cannot be null or empty");
        }

        input = input.replaceAll("\\D", ""); // Remove qualquer caractere que não seja número

        if (input.length() == 11) {
            return formatCpf(input);
        } else if (input.length() == 14) {
            return formatCnpj(input);
        } else {
            throw new IllegalArgumentException("Invalid input length. Expected 11 digits for CPF or 14 digits for CNPJ.");
        }
    }

    private static String formatCpf(String cpf) {
        return cpf.substring(0, 3) + "." +
                cpf.substring(3, 6) + "." +
                cpf.substring(6, 9) + "-" +
                cpf.substring(9, 11);
    }

    private static String formatCnpj(String cnpj) {
        return cnpj.substring(0, 2) + "." +
                cnpj.substring(2, 5) + "." +
                cnpj.substring(5, 8) + "/" +
                cnpj.substring(8, 12) + "-" +
                cnpj.substring(12, 14);
    }
}