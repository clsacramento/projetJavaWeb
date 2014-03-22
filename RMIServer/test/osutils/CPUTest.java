/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package osutils;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Damien
 */
public class CPUTest {
    
    public CPUTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
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
        assertEquals(expResult, result, 0.0);
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
        assertEquals(expResult, result, 0.0);
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
        assertEquals(expResult, result, 0.0);
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
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
