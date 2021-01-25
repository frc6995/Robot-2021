/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.lib.wrappers.limelight;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import frc.lib.wrappers.limelight.Limelight.LedState;

/**
 * Limelight unit test
 */
public class LimelightUnitTest {

    /**
     * Test to see if the Limelight's hasTarget method works.
     * A non-existent (fake) Limelight should not have a target.
     */
    @Test
    public void targetTest(){
        
        Limelight tester = new Limelight("name");

        assertEquals("Has target test", false, tester.hasTarget());        
    }
    
    /**
     * Test the getXOffset() method.
     * A non-existent (fake) Limelight should have no x-offset
     */
    @Test
    public void offsetTest(){
        Limelight tester = new Limelight("name");        

        assertEquals("X offset test", 0, tester.getXOffset(), 0.1);
    }

    /**
     * Test the Limelight's LED Mode by setting it to Blink.
     * The result should be the LED Mode equaling Blink.
     */
    @Test
    public void testLEDModesValid(){
        Limelight tester = new Limelight("name");

        tester.setLedMode(Limelight.LedState.Blink);

        assertEquals("LED Mode test (valid)", LedState.Blink, tester.getLEDMode());
    }

    /**
     * Tests the Limelight's LED Mode by setting it to null.
     * The result should be the mode getting set to Off instead.
     */
    @Test
    public void testLEDModesInvalid1(){
        Limelight tester = new Limelight("name");

        tester.setLedMode(null);

        assertEquals("Null LED Mode test", LedState.Off, tester.getLEDMode());
    }

    /**
     * Tests the Limelight's LED Mode by setting it to a non-existent state.
     * The result should be the mode getting set to Off instead.
     */
    @Test
    public void testLEDModesInvalid2(){
        Limelight tester = new Limelight("name");

        tester.setLedMode(LedState.getState(15));

        assertEquals("Out of bounds LED Mode test", LedState.Off, tester.getLEDMode());
    }
}
