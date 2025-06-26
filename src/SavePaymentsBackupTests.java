import org.junit.Test;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class SavePaymentsBackupTests {
    private DataManager dataManager;

    @Before
    public void setUp() {
        dataManager = new DataManager();
    }

    @Test
    public void testSavePaymentsBackup_Positive() {
        List<Payment> payments = new ArrayList<>();
        payments.add(new Payment("Kemal", "ECG", 150));
        assertDoesNotThrow(() -> dataManager.savePayments(payments));
    }

    @Test
    public void testSaveEmptyPaymentsBackup_Positive() {
        List<Payment> payments = new ArrayList<>();
        assertDoesNotThrow(() -> dataManager.savePayments(payments));
    }
}