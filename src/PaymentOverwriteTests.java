import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;
public class PaymentOverwriteTests {
    private Payment payment;

    @Before
    public void setUp() {
        payment = new Payment("Arda", "Checkup", 100.0);
    }

    @Test
    public void testDoublePayHasNoEffect_Positive() {
        payment.pay();
        String firstPayDate = payment.toString().split("\\|")[4];
        payment.pay();
        String secondPayDate = payment.toString().split("\\|")[4];
        assertEquals(firstPayDate, secondPayDate);
    }

    @Test
    public void testUnpaidInitially_Negative() {
        assertFalse(payment.isPaid());
    }
}