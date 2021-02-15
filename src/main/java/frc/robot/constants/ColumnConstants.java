package frc.robot.constants;

/** Constants for the column subsystem*/
public interface ColumnConstants {
    /**
     * the column front motor id
     * @return can id
     */
    int getTalonID();

    /**
     * the colummn back motor id
     * @return can id
     */
    int getAcceleratorID();

    /**
     * the double solenoid forward port 
     * @return pcm id
     */
    int getFwdPort();

    /**
     * the double solenoid reverse port
     * @return pcm id
     */
    int getRevPort();

    /**
     * the speed that the column spins at
     * @return speed
     */
    double getColumnSpeed();
}