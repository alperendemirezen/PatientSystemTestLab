import org.junit.Test;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
public class PatientSearchTests {
    private List<Patient> patients;

    @Before
    public void setUp() {
        patients = new ArrayList<>();
        patients.add(new Patient("Kemal", "01/01/1970", "", "111111"));
    }

    @Test
    public void testSearchExistingPatient_Positive() {
        String keyword = "kemal";
        boolean found = patients.stream().anyMatch(p -> p.display().toLowerCase().contains(keyword));
        assertTrue(found);
    }

    @Test
    public void testSearchNonExistentPatient_Negative() {
        String keyword = "unknown";
        boolean found = patients.stream().anyMatch(p -> p.display().toLowerCase().contains(keyword));
        assertFalse(found);
    }
}