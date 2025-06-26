import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;
public class MedicalHistoryTests {
    private Patient patient;

    @Before
    public void setUp() {
        patient = new Patient("Mehmet", "10/10/1980", "", "998877");
    }

    @Test
    public void testAddValidMedicalHistory_Positive() {
        patient.addMedicalHistory("Flu");
        assertTrue(patient.getMedicalHistory().contains("Flu"));
    }

    @Test
    public void testAddEmptyMedicalHistory_Negative() {
        patient.addMedicalHistory("");
        assertFalse(patient.getMedicalHistory().contains("Flu"));
    }
}