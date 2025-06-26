import org.junit.Test;

import static org.junit.Assert.*;
public class PaymentAmountValidationTests {
    @Test
    public void testPositiveAmountPayment_Positive() {
        Payment payment = new Payment("Emre", "CT Scan", 300.0);
        assertEquals(300.0, Double.parseDouble(payment.toString().split("\\|")[2]), 0.001);
    }

    @Test
    public void testNegativeAmountPayment_Negative() {
        Payment payment = new Payment("Emre", "CT Scan", -100.0);
        assertEquals(-100.0, Double.parseDouble(payment.toString().split("\\|")[2]), 0.001);
    }
}