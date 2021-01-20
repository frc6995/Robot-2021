package frc.lib.wrappers.limelight;

import edu.wpi.first.hal.HALValue;
import edu.wpi.first.hal.SimBoolean;
import edu.wpi.first.hal.SimDevice;
import edu.wpi.first.hal.SimDouble;
import edu.wpi.first.hal.SimEnum;
import edu.wpi.first.hal.SimValue;
import edu.wpi.first.hal.SimDevice.Direction;
import frc.lib.utility.util.NomadEnumUtil;

/**
 * This class represents a limelight.
 * 
 * @author Sammcdo
 * @author JoeyFabel
 * 
 * @version Pre Unit Test
 */
public class LimelightSim extends Limelight{
    /**
     * The possible states of the limelight LEDs.
     */
    

    /** The vertical field of view of the limelight in degrees. */
    public double kVertFOV = 41.0;
    /** The horizontal field of view of the limelight in degrees. */
    public double kHorFOV = 54.0;

    private String tableName;
    private boolean mode3d = false;

    private SimDevice  limelightSim = null;
    private SimBoolean hasTargetSim = null;
    private SimDouble  simTx = null;
    private SimDouble  simTy = null;
    private SimDouble  simTs = null;
    private SimValue   simPipeline = null;
    private SimEnum    simCamMode = null;
    private SimEnum    simLedMode = null;
    private SimBoolean sim3dMode = null;
    private SimValue   simCamTran = null;

    /**
     * A class representing a limelight.
     * 
     * @param name The name of the limelight in the simGUI.
     */
    public LimelightSim(String name) {
        super(name, false, false);
        limelightSim = SimDevice.create(name);
        hasTargetSim = limelightSim.createBoolean("tv", Direction.kInput, false);
        simTx = limelightSim.createDouble("tx", Direction.kInput, 0.0);
        simTy = limelightSim.createDouble("ty", Direction.kInput, 0.0);
        simTs = limelightSim.createDouble("ts", Direction.kInput, 0.0);
        simPipeline = limelightSim.createValue("pipeline", Direction.kBidir, HALValue.makeInt(0));
        simCamMode = limelightSim.createEnum("mode", Direction.kBidir, NomadEnumUtil.enumToStringArray(CameraMode.class), CameraMode.Driver.getValue());
        sim3dMode = limelightSim.createBoolean("3Dmode", Direction.kBidir, true);
    }

    /**
     * Returns whether the limelight sees a valid target. This is the value of entry
     * 'tv' as a boolean.
     * 
     * @return True if the limelight sees a target.
     */
    public boolean hasTarget() {        
        return hasTargetSim.get();
    }

    /**
     * Get the Horizontal Offset from the target. The value will be a between +-29.8
     * degrees. This is the value of entry 'tx'.
     * 
     * @return The horizontal offset in degrees.
     */
    public double getXOffset() {
        return simTx.get();
    }

    /**
     * Get the Vertical Offset from the target. The value will be between +- 24.85
     * degrees. This is the value of entry 'ty'.
     * 
     * @return The vertical offset in degrees.
     */
    public double getYOffset() {
        return simTy.get();
    }

    /**
     * Get the estimated skew from the target. The value will be between -90 and 0
     * degrees. This is the value of 'ts'.
     * 
     * @return The skew in degrees.
     */
    public double getSkew() {
        return simTs.get();
    }

    /**
     * Return the current vision pipeline, from 0-9.
     * 
     * @return
     */
    public double getPipeline() {
        return simPipeline.getValue().getDouble();
    }

    /**
     * Set the limelight pipeline. This must be a whole number between 0 and 9.
     * 
     * @param index
     */
    public void setPipeline(double index) {
        simPipeline.setValue(HALValue.makeDouble(index));
    }

    /**
     * Set the state of the LEDs. This changes the value of the 'ledMode' entry.
     * 
     * @param state The desired {@link LedState}
     */
    public void setLedMode(LedState state) {
        // Set the state without a switch
        simLedMode.setValue(HALValue.makeEnum(state.getValue()));
    }

    /**
     * Set the camera mode to the specified state. This sets the network table entry
     * 'camMode'.
     * 
     * @param state The desired {@link CameraMode}.
     */
    public void setCamMode(CameraMode state) {        
        simCamMode.setValue(HALValue.makeEnum(state.getValue()));
    }

    private String getTableName() {
        return limelightSim.toString();
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