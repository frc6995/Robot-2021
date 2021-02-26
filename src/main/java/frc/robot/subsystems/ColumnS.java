package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.wrappers.motorcontrollers.NomadSparkMax;
import frc.lib.wrappers.motorcontrollers.NomadTalonSRX;
import frc.robot.constants.interfaces.ColumnConstants;

public class ColumnS extends SubsystemBase {
  private NomadTalonSRX columnBelts;
  private DoubleSolenoid solenoid; 
  private ColumnConstants constants;
  private NomadSparkMax acceleratorWheels;

  /** Creates a new ColumnS. Moves the power cells up from the agitator to the shooter*/
  public ColumnS(ColumnConstants constants, NomadTalonSRX columnBelts, NomadSparkMax acceleratorWheels, DoubleSolenoid solenoid) {
    this.constants = constants;
    this.columnBelts = columnBelts;

    this.solenoid = solenoid;
    this.acceleratorWheels = acceleratorWheels; // id 43
  }

  /**
   * Gets the constants for the column
   * @return column constants
   */
  public ColumnConstants getConstants(){
    return constants;
  }

  /**
   * Get whether the solenoid is extended or retracted
   * @return solenoid position
   */
  public Value getSolenoidPosition(){
    return solenoid.get();
  }

  /**
   * Set the speed of the column belts as a percent
   * @param speed of the column belts
   */
  public void setColumnBeltsSpeed(double speed){
    columnBelts.set(speed);
  }

  /**
   * Enable the stopper at the top of the column
   */
  public void enableStopper(){
    solenoid.set(Value.kForward);
  }
  
  /**
   * Disable the stopper at the top of the column
   */
  public void disableStopper(){
    solenoid.set(Value.kReverse);
  }

  public void spinAcceleratorUpwards(){
    acceleratorWheels.set(1); // TODO - Make sure that the wheels are not inverted
  }

  public void spinAcceleratorDownwards(){
    acceleratorWheels.set(-1);
  }

  public void stopAccelerator(){
    acceleratorWheels.set(0);
  }
 
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
