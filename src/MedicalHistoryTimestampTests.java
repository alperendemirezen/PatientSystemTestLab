import org.junit.Test;

import static org.junit.Assert.*;
public class MedicalHistoryTimestampTests {
    @Test
    public void testMedicalHistoryContainsDate_Positive() {
        Patient patient = new Patient("Furkan", "01/01/1990", "", "111222");
        patient.addMedicalHistory("Allergy");
        assertTrue(patient.getMedicalHistory().matches("(?s).*\\(\\d{2}/\\d{2}/\\d{4}\\).*"));
    }

    @Test
    public void testEmptyMedicalHistoryInitially_Negative() {
        Patient patient = new Patient("Furkan", "01/01/1990", "", "111222");
        assertEquals("", patient.getMedicalHistory());
    }
}