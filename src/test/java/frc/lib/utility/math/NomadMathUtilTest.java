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

    @Test
    public void lerpTestWithFloats(){
        assertEquals(7.5f, NomadMathUtil.lerp(5f, 10f, 0.5f), 0.001f);
    }
    
    @Test
    public void lerpTestWithDoubles(){
        assertEquals(7.5, NomadMathUtil.lerp(5, 10, 0.5), 0.001);
    }

    @Test
    public void inverseLerpTest(){
        assertEquals(0.75, NomadMathUtil.inverseLerp(0, 10, 7.5), 0.001);
        assertEquals(0.35, NomadMathUtil.inverseLerp(0, 10, 3.5), 0.001);
        assertEquals(1.5, NomadMathUtil.inverseLerp(0, 10, 15), 0.001);
    }

    @Test
    public void clampTestInteger(){
        // There is no assertEquals for an int, so cast from int to long
        // It still tests clamping an integer type number, thus the test name
        assertEquals(5L, (long)NomadMathUtil.clamp(0, 5, 10));        
    }

    @Test
    public void clampTestFloat(){
        assertEquals(0.5f, NomadMathUtil.clamp(0.5f, 2.5f, -0.5f), 0.001f);
    }

    @Test
    public void clampTestDouble(){
        assertEquals(2.5, NomadMathUtil.clamp(0.0, 2.5, 50.0), 0.001);
    }

    @Test
    public void clampTestChar(){
        assertEquals((double)(int)'Z', (double)(int)NomadMathUtil.clamp('A', 'Z', 'b'), 0.001);
    }    
}
