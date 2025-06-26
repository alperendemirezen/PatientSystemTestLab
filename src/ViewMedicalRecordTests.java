import org.junit.Test;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
public class ViewMedicalRecordTests {
    private List<Patient> patients;

    @Before
    public void setUp() {
        patients = new ArrayList<>();
        patients.add(new Patient("Seda", "03/03/1983", "Migraine", "333444"));
    }

    @Test
    public void testViewExistingMedicalRecord_Positive() {
        Patient p = patients.get(0);
        assertTrue(p.getMedicalHistory().contains("Migraine"));
    }

    @Test
    public void testViewMedicalRecordForUnknownPatient_Negative() {
        boolean found = patients.stream().anyMatch(p -> p.getName().equalsIgnoreCase("Elif"));
        assertFalse(found);
    }
}