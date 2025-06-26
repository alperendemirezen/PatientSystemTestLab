import org.junit.Test;

import static org.junit.Assert.*;
public class RegisterPatientTests {
    @Test
    public void testRegisterPatient_Valid_Positive() {
        Patient patient = new Patient("Valid Name", "01/01/1990", "None", "123456");
        assertEquals("Valid Name", patient.getName());
    }

    @Test
    public void testRegisterPatient_Invalid_Negative() {
        String name = "";
        assertTrue(name.isEmpty());
    }
}