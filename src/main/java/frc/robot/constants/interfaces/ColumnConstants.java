package frc.robot.constants.interfaces;

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
     * the speed that the column loads at
     * @return speed
     */
    double getColumnLoadSpeed();

    /**
     * the speed that the column feeds into the accelerator at
     * @return speed
     */
    double getColumnFeedSpeed();

    /**
     * the RPM that the accelerator wheels spin
     * @return rpm
     */
    double getAcceleratorRPM();

    /**
     * the current limit for the accerator wheels
     * @return amps
     */
    double getAcceleratorCurrentLimit();

    /**
     * Get the Simple Motor Feed Forward Constants for the accelerator wheels.
     * The format is [kS, kV, kA]
     * @return The Simple Motor Feed Forward Constants
     */
    double[] getFeedForwardConstants();
}