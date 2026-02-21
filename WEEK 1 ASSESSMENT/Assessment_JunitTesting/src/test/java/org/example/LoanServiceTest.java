package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoanServiceTest {


    LoanService service = new LoanService();


    @Test
    void testValidEligibility() {
        assertTrue(service.isEligible(30, 40000));
    }

    @Test
    void testInvalidAge() {
        assertFalse(service.isEligible(18, 40000));
    }

    @Test
    void testInvalidSalary() {
        assertFalse(service.isEligible(30, 20000));
    }

    @Test
    void testBoundaryAgeLower() {
        assertTrue(service.isEligible(21, 25000));
    }

    @Test
    void testBoundaryAgeUpper() {
        assertTrue(service.isEligible(60, 30000));
    }

    @Test
    void testValidEMI() {
        double emi = service.calculateEMI(120000, 1);
        assertEquals(10000, emi);
    }


    @Test
    void testInvalidLoanAmount() {
        assertThrows(IllegalArgumentException.class,() -> service.calculateEMI(0, 2));
    }

    @Test
    void testInvalidTenure() {
        assertThrows(IllegalArgumentException.class,() -> service.calculateEMI(100000, 0));
    }



    @Test
    void testCreditCategories() {
        assertAll(
                () -> assertEquals("Premium", service.getLoanCategory(800)),
                () -> assertEquals("Standard", service.getLoanCategory(650)),
                () -> assertEquals("High Risk", service.getLoanCategory(500))
        );
    }




    @Test
    void testCategoryNotNull() {
        assertNotNull(service.getLoanCategory(700));
    }
}
