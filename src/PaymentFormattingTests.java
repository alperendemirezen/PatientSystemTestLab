import org.junit.Test;

import static org.junit.Assert.*;
public class PaymentFormattingTests {
    @Test
    public void testPaymentDisplayWithCorrectFormat_Positive() {
        Payment payment = new Payment("Cem", "Scan", 120.0);
        String display = payment.display();
        assertTrue(display.contains("TL"));
    }

    @Test
    public void testPaymentDisplayWithZeroAmount_Negative() {
        Payment payment = new Payment("Cem", "Scan", 0.0);
        String display = payment.display();
        assertTrue(display.contains("0.0"));
    }
}