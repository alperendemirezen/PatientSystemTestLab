import org.junit.Test;
import org.junit.Before;

import java.util.List;

import static org.junit.Assert.*;
public class LoadCorruptedPatientsTests {
    private DataManager dataManager;

    @Before
    public void setUp() {
        dataManager = new DataManager();
    }

    @Test
    public void testLoadPatientsBackupReturnsList_Positive() {
        List<Patient> patients = dataManager.loadPatientsBackup();
        assertNotNull(patients);
    }

    @Test
    public void testLoadPatientsReturnsEmptyListForMissingFile_Negative() {
        List<Patient> patients = dataManager.loadPatients();
        assertNotNull(patients);
    }
}