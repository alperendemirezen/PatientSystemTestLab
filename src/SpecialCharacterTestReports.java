import org.junit.Test;

import static org.junit.Assert.*;
public class SpecialCharacterTestReports {
    @Test
    public void testAddTestReportWithUnicode_Positive() {
        Patient patient = new Patient("Derya", "20/05/1993", "", "444555");
        patient.addTestReport("Ödem Testi");
        assertTrue(patient.getTestReports().contains("Ödem Testi"));
    }

    @Test
    public void testAddTestReportWithEmptyString_Negative() {
        Patient patient = new Patient("Derya", "20/05/1993", "", "444555");
        patient.addTestReport("");
        assertTrue(patient.getTestReports().contains(""));
    }
}