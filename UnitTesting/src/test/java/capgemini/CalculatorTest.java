package capgemini;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class CalculatorTest {

    @Test
     void testAdd() {
        Calculator c = new Calculator();
        assertEquals(8, c.add(5,3));
    }

    
}

