import org.junit.Test;

import static org.junit.Assert.*;
public class PaymentDisplayTests {
    @Test
    public void testPaymentDisplayPaid_Positive() {
        Payment payment = new Payment("Sibel", "Consultation", 150.0);
        payment.pay();
        assertTrue(payment.display().contains("Paid"));
    }

    @Test
    public void testPaymentDisplayUnpaid_Negative() {
        Payment payment = new Payment("Sibel", "Consultation", 150.0);
        assertTrue(payment.display().contains("Pending"));
    }
}