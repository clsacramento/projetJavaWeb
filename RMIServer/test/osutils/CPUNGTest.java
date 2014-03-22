/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package osutils;

import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Damien
 */
public class CPUNGTest {
    
    public CPUNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of getTotalUsed method, of class CPU.
     */
    @Test
    public void testGetTotalUsed() throws Exception {
        System.out.println("getTotalUsed");
        CPU instance = new CPU();
        float expResult = 0.0F;
        float result = instance.getTotalUsed();
        assertEquals(result, expResult, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserLoad method, of class CPU.
     */
    @Test
    public void testGetUserLoad() throws Exception {
        System.out.println("getUserLoad");
        CPU instance = new CPU();
        float expResult = 0.0F;
        float result = instance.getUserLoad();
        assertEquals(result, expResult, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSystemLoad method, of class CPU.
     */
    @Test
    public void testGetSystemLoad() throws Exception {
        System.out.println("getSystemLoad");
        CPU instance = new CPU();
        float expResult = 0.0F;
        float result = instance.getSystemLoad();
        assertEquals(result, expResult, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIdle method, of class CPU.
     */
    @Test
    public void testGetIdle() throws Exception {
        System.out.println("getIdle");
        CPU instance = new CPU();
        float expResult = 0.0F;
        float result = instance.getIdle();
        assertEquals(result, expResult, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
