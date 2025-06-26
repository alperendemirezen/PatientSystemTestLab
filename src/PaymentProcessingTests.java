import org.junit.Test;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
public class PaymentProcessingTests {
    private List<Payment> payments;

    @Before
    public void setUp() {
        payments = new ArrayList<>();
        Payment p = new Payment("Levent", "Check-up", 100.0);
        payments.add(p);
    }

    @Test
    public void testSuccessfulPayment_Positive() {
        Payment payment = payments.get(0);
        payment.pay();
        assertTrue(payment.isPaid());
    }

    @Test
    public void testMakePaymentForWrongPatient_Negative() {
        boolean found = payments.stream().anyMatch(p -> p.getPatientName().equalsIgnoreCase("Selim"));
        assertFalse(found);
    }
}