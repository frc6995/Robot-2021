package frc.lib.wrappers.hood;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.wpi.first.wpilibj.Servo;
import frc.robot.subsystems.cannon.Hood;
/** Add your docs here. */
public class HoodUnitTest {
    @Test
    public void lerpTest(){
        Hood hood = new Hood(new Servo(0), new Servo(1));
        
        //  X |   Y
        // 0  |   0 *
        // 0.5| 0.5
        // 1  |   1 *
        // 1.5| 1.5
        // 2  |   2 *
        // 2.5|   3
        // 3  |   4 *
        // 3.5|   7
        // 4  |  10 *
        // * means known data point
        
        assertEquals(0.5, hood.getAngleBasedOnDistance(0.5), 0.001);
        
        assertEquals(3, hood.getAngleBasedOnDistance(2.5), 0.001);
                
        assertEquals(7, hood.getAngleBasedOnDistance(3.5), 0.001);
    }
}
