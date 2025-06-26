import org.junit.Test;

import static org.junit.Assert.*;
public class PaymentCreationTests {
    @Test
    public void testCreateValidPayment_Positive() {
        Payment payment = new Payment("Zara", "X-ray", 300.0);
        assertEquals("Zara", payment.getPatientName());
        assertFalse(payment.isPaid());
    }

    @Test
    public void testCreateInvalidPaymentAmount_Negative() {
        String invalidAmount = "thirty";
        assertThrows(NumberFormatException.class, () -> {
            Double.parseDouble(invalidAmount);
        });
    }
}