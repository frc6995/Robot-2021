package frc.robot.constants.interfaces;

/** 
 * An interface containing constants for the Intake
 * 
 * @author NoSoup
*/
public interface IntakeConstants {
    /**
     * The CAN id of the intake motor
     * @return The can ID
     */
    int getIntakeMotorPort();

    /**
     * The CAN id of the intake back roller
     * @return The CAN id
     */
    int getIntakeBackMotorPort();

    /**
     * The pcm port for the forward channel of the intake solenoid
     * @return pcm id
     */
    int getSolenoidFwdPort();

    /**
     * The pcm id for the reverse channel of the intake solenoid
     * @return pcm id
     */
    int getSolenoidRevPort();

    /**
     * The speed of the intake 
     * @return intake speed
     */
    double getIntakeSpeed();

    /**
     * The speed of the top roller of the intake
     * @return intake speed
     */
    double getIntakeBackRollerSpeed();
}
