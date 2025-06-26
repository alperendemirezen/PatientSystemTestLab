import org.junit.Test;

import static org.junit.Assert.*;
public class PatientToStringFormatTests {
    @Test
    public void testToStringIncludesAllFields_Positive() {
        Patient patient = new Patient("Alper", "01/01/1990", "None", "123456");
        patient.setAppointmentDate("2025-07-01");
        String result = patient.toString();
        assertTrue(result.contains("Alper") && result.contains("2025-07-01"));
    }

    @Test
    public void testToStringMissingFieldsHandled_Negative() {
        Patient patient = new Patient("Alper", "", "", "");
        String result = patient.toString();
        assertTrue(result.contains("Alper"));
    }
}