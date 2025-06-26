import org.junit.Test;
import org.junit.Before;
import java.util.List;
import static org.junit.Assert.*;
public class DataRecoveryPatientsTests {
    private DataManager dataManager;

    @Before
    public void setUp() {
        dataManager = new DataManager();
    }

    @Test
    public void testRecoveryPatientsBackup_Positive() {
        List<Patient> patients = dataManager.loadPatientsBackup();
        assertNotNull(patients);
    }

    @Test
    public void testRecoveryPaymentsBackup_Positive() {
        List<Payment> payments = dataManager.loadPaymentsBackup();
        assertNotNull(payments);
    }
}