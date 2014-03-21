/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces;

import java.io.IOException;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import rmiserver.ServerOS;

/**
 *
 * @author Damien
 */
public class IActivityMonitorTest {
    
    public IActivityMonitorTest() {
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
     * Test of getListOfProcesses method, of class IActivityMonitor.
     */
    @Test
    public void testGetListOfProcesses() throws Exception {
        System.out.println("getListOfProcesses");
        IActivityMonitor instance = new IActivityMonitorImpl();
        ArrayList<IProcess> expResult = null;
        ArrayList<IProcess> result = instance.getListOfProcesses();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPhysicalMemory method, of class IActivityMonitor.
     */
    @Test
    public void testGetPhysicalMemory() throws IOException, InterruptedException {
        System.out.println("getPhysicalMemory");
        IActivityMonitor instance = ServerOS.getOSActivityMonitor();
//        ArrayList<IProcess> expResult = null;
        ArrayList<IProcess> result = instance.getListOfProcesses();
//        assertEquals(expResult, result);
        IProcess proc = result.get(0);
        
        assertNotNull(proc);
        assertNotNull(proc.getName());
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCPU method, of class IActivityMonitor.
     */
    @Test
    public void testGetCPU() {
        System.out.println("getCPU");
        IActivityMonitor instance = new IActivityMonitorImpl();
        ICPU expResult = null;
        ICPU result = instance.getCPU();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class IActivityMonitorImpl implements IActivityMonitor {

        public ArrayList<IProcess> getListOfProcesses() throws IOException, InterruptedException {
            return null;
        }

        public IPhysicalMemory getPhysicalMemory() {
            return null;
        }

        public ICPU getCPU() {
            return null;
        }
    }
    
}
