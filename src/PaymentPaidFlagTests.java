import org.junit.Test;

import static org.junit.Assert.*;
public class PaymentPaidFlagTests {
    @Test
    public void testPaidFlagTrueAfterPay_Positive() {
        Payment payment = new Payment("Haluk", "Surgery", 1000);
        payment.pay();
        assertTrue(payment.isPaid());
    }

    @Test
    public void testPaidFlagInitiallyFalse_Negative() {
        Payment payment = new Payment("Haluk", "Surgery", 1000);
        assertFalse(payment.isPaid());
    }
}
