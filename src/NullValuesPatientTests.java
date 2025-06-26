import org.junit.Test;

import static org.junit.Assert.*;
public class NullValuesPatientTests {
    @Test
    public void testPatientCreationWithValidName_Positive() {
        Patient patient = new Patient("Gamze", "01/01/1990", "", "555666");
        assertEquals("Gamze", patient.getName());
    }

    @Test
    public void testPatientCreationWithNullValues_Negative() {
        Patient patient = new Patient(null, null, null, null);
        assertNull(patient.getName());
    }
}