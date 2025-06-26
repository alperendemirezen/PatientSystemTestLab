import org.junit.Test;

import static org.junit.Assert.*;
public class UnknownRoleAuthenticationTests {
    @Test
    public void testAuthenticateKnownRole_Positive() {
        UserManager userManager = new UserManager();
        User admin = userManager.authenticate("admin", "admin123");
        assertEquals("ADMIN", admin.getRole());
    }

    @Test
    public void testAuthenticateUnknownRole_Negative() {
        User unknown = new User("ghost", "ghost123", "UNKNOWN");
        assertNotEquals("ADMIN", unknown.getRole());
    }
}