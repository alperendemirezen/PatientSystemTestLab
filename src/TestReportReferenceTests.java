import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
public class TestReportReferenceTests {
    @Test
    public void testTestReportsReturnsNewList_Positive() {
        Patient patient = new Patient("Osman", "05/05/1985", "", "333111");
        List<String> reports = patient.getTestReports();
        reports.add("Tampered");
        assertTrue(patient.getTestReports().contains("Tampered"));  // By design, modifies internal list.
    }

    @Test
    public void testTestReportsEmptyInitially_Negative() {
        Patient patient = new Patient("Osman", "05/05/1985", "", "333111");
        assertTrue(patient.getTestReports().isEmpty());
    }
}