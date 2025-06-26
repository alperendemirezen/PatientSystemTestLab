import org.junit.Test;

import static org.junit.Assert.*;
public class DisplayPatientTests {
    @Test
    public void testPatientDisplayContainsName_Positive() {
        Patient patient = new Patient("Ayşe", "12/12/1992", "Cold", "112233");
        assertTrue(patient.display().contains("Ayşe"));
    }

    @Test
    public void testPatientDisplayWrongName_Negative() {
        Patient patient = new Patient("Ayşe", "12/12/1992", "Cold", "112233");
        assertFalse(patient.display().contains("Zeynep"));
    }
}