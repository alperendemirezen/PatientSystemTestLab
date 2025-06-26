import org.junit.Test;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class SavePatientsFileTests {
    private DataManager dataManager;

    @Before
    public void setUp() {
        dataManager = new DataManager();
    }

    @Test
    public void testSavePatientsBackup_Positive() {
        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient("Serkan", "03/03/1999", "Healthy", "123456"));
        assertDoesNotThrow(() -> dataManager.savePatients(patients));
    }

    @Test
    public void testSaveEmptyPatientList_Positive() {
        List<Patient> patients = new ArrayList<>();
        assertDoesNotThrow(() -> dataManager.savePatients(patients));
    }
}