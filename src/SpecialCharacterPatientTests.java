import org.junit.Test;
import static org.junit.Assert.*;
public class SpecialCharacterPatientTests {
    @Test
    public void testPatientNameWithSpecialCharacters_Positive() {
        Patient patient = new Patient("Çağrı Üğur", "10/10/1990", "", "555999");
        assertTrue(patient.getName().contains("Ü"));
    }

    @Test
    public void testPatientNameWithoutSpecialCharacters_Negative() {
        Patient patient = new Patient("Ahmet", "10/10/1990", "", "555999");
        assertFalse(patient.getName().contains("Ü"));
    }
}