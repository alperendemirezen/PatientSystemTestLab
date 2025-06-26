import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class AppointmentComparisonTests {
    @Test
    public void testFutureAppointmentComparison_Positive() {
        Patient patient = new Patient("Irem", "05/05/1985", "", "999888");
        patient.setAppointmentDate("2099-12-31");
        assertTrue(patient.getAppointmentDate().compareTo("2025-01-01") > 0);
    }

    @Test
    public void testPastAppointmentComparison_Negative() {
        Patient patient = new Patient("Irem", "05/05/1985", "", "999888");
        patient.setAppointmentDate("2000-01-01");
        assertTrue(patient.getAppointmentDate().compareTo("2025-01-01") < 0);
    }
}