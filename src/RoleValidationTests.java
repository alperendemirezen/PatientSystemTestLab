import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;
public class RoleValidationTests {
    private UserManager userManager;

    @Before
    public void setUp() {
        userManager = new UserManager();
    }

    @Test
    public void testDoctorRoleValidation_Positive() {
        User doctor = userManager.authenticate("doctor", "doc123");
        assertNotNull(doctor);
        assertEquals("DOCTOR", doctor.getRole());
    }

    @Test
    public void testDoctorWrongPassword_Negative() {
        User doctor = userManager.authenticate("doctor", "wrongpass");
        assertNull(doctor);
    }
}