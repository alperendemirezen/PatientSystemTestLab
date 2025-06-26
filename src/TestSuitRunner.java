import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginTests.class,
        RegisterPatientTests.class,
        MedicalHistoryTests.class,
        DisplayPatientTests.class,
        PatientSearchTests.class,
        ViewMedicalRecordTests.class,
        PaymentCreationTests.class,
        PaymentProcessingTests.class,
        RoleValidationTests.class,
        PaymentDisplayTests.class,
        LoadCorruptedPatientsTests.class,
        SavePatientsFileTests.class,
        UpcomingAppointmentsTests.class,
        MultipleTestReportsTests.class,
        PaymentPaidDisplayTests.class,
        SavePaymentsBackupTests.class,
        PaymentOverwriteTests.class,
        SpecialCharacterPatientTests.class,
        TestReportReferenceTests.class,
        DataRecoveryPatientsTests.class,
        PatientToStringFormatTests.class,
        PatientContactDetailsTests.class,
        UnknownRoleAuthenticationTests.class,
        PaymentFormattingTests.class,
        SpecialCharacterTestReports.class,
        PaymentAmountValidationTests.class,
        MedicalHistoryTimestampTests.class,
        NullValuesPatientTests.class,
        PaymentPaidFlagTests.class,
        AppointmentComparisonTests.class
})
public class TestSuitRunner{
}
