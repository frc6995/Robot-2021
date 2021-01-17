/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.lib.utility.math;

import static org.junit.Assert.assertEquals;


import org.junit.Test;

/**
 * Test for NomadMathUtil

 */

public class NomadMathUtilTest {
    @Test
    public void lerpTestInsideRangeSuccess(){
        //Test case for mapping -1..1 to 0..1
        assertEquals(0.5, NomadMathUtil.lerp(0, -1, 1, 0, 1), 0.001);
    }
    @Test
    public void lerpTestOutsideRangeSuccess(){
        assertEquals(21.0, NomadMathUtil.lerp(10, 0, 1, 1, 3), 0.001); 
    }

    @Test
    public void lerpTestDivByZeroFail(){
        assertEquals(Double.NaN, NomadMathUtil.lerp(0, 0, 0, 0, 0), 0.001);
    }
}
