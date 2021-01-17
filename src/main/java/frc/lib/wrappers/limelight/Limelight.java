package frc.lib.wrappers.limelight;

import java.util.Map;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;

/**
 * This class represents a limelight.
 * 
 * @author Sammcdo
 * @author JoeyFabel
 * 
 * @version Pre Unit Test
 */
public class Limelight implements Sendable {
    /**
     * The possible states of the limelight LEDs.
     */
    public enum LedState {        
        On(3),
        Off(1),
        Blink(2),
        Preset(0);

        private final int value;

        LedState(final int newValue)
        {
            value = newValue;
        }

        /**
         * The integer value of the LED state
         * @return the int value of this LED state, as used by the limelight
         */
        public int getValue() 
        {
             return value;
        }    

        public static LedState getState(int value){
            LedState state = null;

            switch (value){
                case 0:
                    state = Preset;
                    break;
                case 1:
                    state = Off;
                    break;
                case 2:
                    state = Blink;
                    break;
                case 3:
                    state = On;
                    break;
                default:
                    state = null;
                    break;
            }

            return state;
        }
    }

    /**
     * The possible camera modes of a limelight.
     */
    public enum CameraMode {
        Driver(1), 
        Vision(0);

        private final int value;

        CameraMode(int newValue)
        {
            value = newValue;
        }

        /**
         * The integer value of the Camera Mode
         * @return the int value of this Camera Mode, as used by the limelight
         */
        public int getValue() 
        {
            return value;
        }
    }

    /** The vertical field of view of the limelight in degrees. */
    public double kVertFOV = 41.0;
    /** The horizontal field of view of the limelight in degrees. */
    public double kHorFOV = 54.0;

    private String tableName;
    private boolean mode3d = false;

    private boolean isReal = true;

    private ShuffleboardTab tab = null;
    private NetworkTableEntry hasTargetSim = null;
    private NetworkTableEntry simTx = null;
    private NetworkTableEntry simTy = null;
    private NetworkTableEntry simTs = null;
    private NetworkTableEntry simPipeline = null;
    private NetworkTableEntry simCamMode = null;
    private NetworkTableEntry simLedMode = null;

    /**
     * A class representing a limelight.
     * 
     * @param name The name of the limelight network table.
     */
    public Limelight(String name) {
        tableName = name;
    }

    /**
     * A class representing a limelight.
     * 
     * @param name   The name of the limelight network table.
     * @param mode3D whether the limelight is in 3d mode.
     */
    public Limelight(String name, boolean mode3D) {
        this(name);
        this.mode3d = mode3D; 
    }

    public Limelight(String name, boolean mode3D, boolean isReal) {
        this(name, mode3D);
        this.isReal = isReal; 

        if (!isReal) {
            tab = Shuffleboard.getTab(name.concat("Sim"));

            hasTargetSim = tab.add("Has Taget", false)
                    .withWidget(BuiltInWidgets.kToggleSwitch)
                    .getEntry();

            simTx = tab.add("tx", 0.0)
                    .withWidget(BuiltInWidgets.kNumberSlider)
                    .withProperties(Map.of("min", -29.8, "max", 29.8))
                    .getEntry();
            
            simTy = tab.add("ty", 0.0)
                    .withWidget(BuiltInWidgets.kNumberSlider)
                    .withProperties(Map.of("min", -24.85, "max", 24.85))
                    .getEntry();
            
            simTs = tab.add("ts", 0.0)
                    .withWidget(BuiltInWidgets.kNumberSlider)
                    .withProperties(Map.of("min", -90, "max", 0))
                    .getEntry();
        }
    }

    /**
     * Returns whether the limelight sees a valid target. This is the value of entry
     * 'tv' as a boolean.
     * 
     * @return True if the limelight sees a target.
     */
    public boolean hasTarget() {        
        if (isReal) {
            return get("tv") == 1; // cleaned return statement up
        } else {
            return hasTargetSim.getBoolean(false);
        }
    }

    /**
     * Get the Horizontal Offset from the target. The value will be a between +-29.8
     * degrees. This is the value of entry 'tx'.
     * 
     * @return The horizontal offset in degrees.
     */
    public double getXOffset() {
        if (isReal) {
            return get("tx");
        } else {
            return simTx.getDouble(0.0);
        }
    }

    /**
     * Get the Vertical Offset from the target. The value will be between +- 24.85
     * degrees. This is the value of entry 'ty'.
     * 
     * @return The vertical offset in degrees.
     */
    public double getYOffset() {
        if (isReal) {
            return get("ty");
        } else {
            return simTy.getDouble(0.0);
        }
    }

    /**
     * Get the estimated skew from the target. The value will be between -90 and 0
     * degrees. This is the value of 'ts'.
     * 
     * @return The skew in degrees.
     */
    public double getSkew() {
        if (isReal) {
            return get("ts");
        } else {
            return simTs.getDouble(0.0);
        }
    }

    /**
     * Return the current vision pipeline, from 0-9.
     * 
     * @return
     */
    public double getPipeline() {
        return get("getpipe");
    }

    /**
     * Set the limelight pipeline. This must be a whole number between 0 and 9.
     * 
     * @param index
     */
    public void setPipeline(double index) {
        set("pipeline", index);
    }

    /**
     * Set the state of the LEDs. This changes the value of the 'ledMode' entry.
     * 
     * @param state The desired {@link LedState}
     */
    public void setLedMode(LedState state) {
        // Set the state without a switch
        set("ledMode", state != null ? state.getValue() : LedState.Off.getValue());
    }

    public LedState getLEDMode(){
        int state = (int)get("ledMode");

        return LedState.getState(state);
    }

    /**
     * Set the camera mode to the specified state. This sets the network table entry
     * 'camMode'.
     * 
     * @param state The desired {@link CameraMode}.
     */
    public void setCamMode(CameraMode state) {        
        set("camMode", state.getValue());
    }

    public Limelight3dPos get3dPosition() {
        if (mode3d) {
            return new Limelight3dPos(getArray("camtran"));
        } else {
            return new Limelight3dPos(new double[6]);
        }
    }

    private double get(String varName) {
        return NetworkTableInstance.getDefault().getTable(tableName).getEntry(varName).getDouble(0);
    }

    private double[] getArray(String varName) {
        return NetworkTableInstance.getDefault().getTable(tableName).getEntry(varName)
                .getDoubleArray(new double[0]);
    }

    private void set(String varName, double value) {
        NetworkTableInstance.getDefault().getTable(tableName).getEntry(varName).setNumber(value);
    }

    private String getTableName() {
        return tableName;
    }

    public void initSendable(SendableBuilder builder) {
        builder.setSmartDashboardType("Limelight");
        builder.addDoubleProperty("Horizontal Offset", this::getXOffset, null); 
        builder.addDoubleProperty("Vertical Offset", this::getYOffset, null);
        builder.addDoubleProperty("Skew", this::getSkew, null);
        builder.addDoubleProperty("Pipeline", this::getPipeline, this::setPipeline);
        builder.addStringProperty("Table Name", this::getTableName, null);
    }

    /**
     * A class representing the results of the limelights 3d calculations.
     */
    public class Limelight3dPos {
        public double x, y, z, pitch, yaw, roll;
        public double[] camtran;

        public Limelight3dPos(double[] camtran) {
            this.camtran = camtran;
            x = camtran[0];
            y = camtran[1];
            z = camtran[2];
            pitch = camtran[3];
            yaw = camtran[4];
            roll = camtran[5];
        }
    }
}