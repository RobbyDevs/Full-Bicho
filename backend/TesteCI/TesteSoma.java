package testeci;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTest {

    @Test
    void shouldSumTwoNumbers() {
        Calculator calculator = new Calculator();
        int result = calculator.sum(1, 1);

        assertEquals(2, result);
    }
}