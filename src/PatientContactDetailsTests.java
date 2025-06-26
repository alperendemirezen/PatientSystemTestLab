import org.junit.Test;
import static org.junit.Assert.*;
public class PatientContactDetailsTests {
    @Test
    public void testContactDetailsSet_Positive() {
        Patient patient = new Patient("Bora", "12/12/1995", "", "998877");
        String display = patient.display();
        assertTrue(display.contains("998877"));
    }

    @Test
    public void testContactDetailsEmpty_Negative() {
        Patient patient = new Patient("Bora", "12/12/1995", "", "");
        String display = patient.display();
        assertTrue(display.contains("Bora"));
        assertFalse(display.contains("998877"));
    }
}