package utilities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateFormatter {

    public static LocalDate formatDate(String date) {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        if (date == null || date.isEmpty()) {
            throw new IllegalArgumentException("Date cannot be null or empty");
        }

        // Remove caracteres não numéricos
        date = date.replaceAll("\\D", "");

        // Verifica se a string tem 8 caracteres após a remoção dos não numéricos
        if (date.length() != 8) {
            throw new IllegalArgumentException("Date format should be dd/MM/yyyy");
        }

        // Formata a data
        String formattedDate = String.format("%s/%s/%s",
                date.substring(0, 2),
                date.substring(2, 4),
                date.substring(4, 8));

        try {
            return LocalDate.parse(formattedDate, pattern);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format: " + formattedDate, e);
        }
    }

    public static String formatDate(LocalDate date) {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(pattern);
    }

    public static LocalDate sqlDateToLocalDate(String date) {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (date == null || date.isEmpty()) {
            return null;
        }
        try {
            return LocalDate.parse(date, pattern);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format: " + date, e);
        }
    }
}
