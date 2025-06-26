import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
public class UpcomingAppointmentsTests {
    private Patient patient;

    @Before
    public void setUp() {
        patient = new Patient("Selim", "22/11/1975", "", "777666");
    }

    @Test
    public void testAppointmentDateInFuture_Positive() {
        String futureDate = "2099-01-01";
        patient.setAppointmentDate(futureDate);
        assertEquals(futureDate, patient.getAppointmentDate());
    }

    @Test
    public void testAppointmentDatePast_Negative() {
        String pastDate = "2000-01-01";
        patient.setAppointmentDate(pastDate);
        assertEquals(pastDate, patient.getAppointmentDate());
    }
}