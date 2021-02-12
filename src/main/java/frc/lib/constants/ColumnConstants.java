package frc.lib.constants;

/** Constants for the column subsystem*/
public interface ColumnConstants {
    /**
     * the column front motor id
     * @return can id
     */
    int getFrontMotorID();

    /**
     * the colummn back motor id
     * @return can id
     */
    int getBackMotorID();

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