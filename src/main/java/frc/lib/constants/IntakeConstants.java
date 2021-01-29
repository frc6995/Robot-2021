package frc.lib.constants;

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
     * The pcm port for the forward channel of the intake solenoid
     * @return pcm id
     */
    int getSolenoidFwdPort();

    /**
     * The pcm id for the reverse channel of the intake solenoid
     * @return pcm id
     */
    int getSolenoidRevPort();
}
