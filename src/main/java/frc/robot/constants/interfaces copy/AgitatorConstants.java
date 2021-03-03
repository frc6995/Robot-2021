

package frc.robot.constants.interfaces;

/** Add your docs here. */
public interface AgitatorConstants {
    /**
     * the can id of the right motor
     * @return can id
     */
    int getRightMotorID();

    /**
     * the can id of the left motor
     * @return can id
     */
    int getLeftMotorID();

    /**
     * the speed of the right agitator roller
     * @return speed 
     */
    double getRightSpeed();

    /**
     * the speed of the left agitator roller
     * @return speed
     */
    double getLeftSpeed();
}
