import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;
public class PaymentPaidDisplayTests {
    private Payment payment;

    @Before
    public void setUp() {
        payment = new Payment("Canan", "Eye Checkup", 250);
    }

    @Test
    public void testDisplayShowsPaidAfterPayment_Positive() {
        payment.pay();
        assertTrue(payment.display().contains("Paid"));
    }

    @Test
    public void testDisplayShowsPendingBeforePayment_Negative() {
        assertTrue(payment.display().contains("Pending"));
    }
}