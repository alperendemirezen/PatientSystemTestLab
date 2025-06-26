import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;
public class MultipleTestReportsTests {
    private Patient patient;

    @Before
    public void setUp() {
        patient = new Patient("Gizem", "04/05/1988", "", "555444");
    }

    @Test
    public void testAddMultipleTestReports_Positive() {
        patient.addTestReport("X-ray");
        patient.addTestReport("Bloodwork");
        assertEquals(2, patient.getTestReports().size());
    }

    @Test
    public void testAddNullTestReport_Negative() {
        patient.addTestReport(null);
        assertTrue(patient.getTestReports().contains(null));
    }
}