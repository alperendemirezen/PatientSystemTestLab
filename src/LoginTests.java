import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

public class LoginTests {
    private UserManager userManager;

    @Before
    public void setUp() {
        userManager = new UserManager();
    }

    @Test
    public void testAdminLogin_Positive() {
        User user = userManager.authenticate("admin", "admin123");
        assertNotNull(user);
        assertEquals("ADMIN", user.getRole());
    }

    @Test
    public void testAdminLogin_Negative_InvalidUsername() {
        User user = userManager.authenticate("nonadmin", "admin123");
        assertNull(user);
    }
}