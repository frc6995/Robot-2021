package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.constants.ColumnConstants;
import frc.lib.wrappers.motorcontrollers.NomadTalonSRX;
import frc.lib.wrappers.motorcontrollers.NomadVictorSPX;

public class ColumnS extends SubsystemBase {
  private NomadTalonSRX front;
  private NomadVictorSPX back;
  private DoubleSolenoid solenoid; 
  private ColumnConstants constants;
  
  /** Creates a new ColumnS. */
  public ColumnS(ColumnConstants constants, NomadTalonSRX front, NomadVictorSPX back, DoubleSolenoid solenoid) {
    this.constants = constants;
    this.front = front;
    this.back = back;
    this.solenoid = solenoid;
  }

  public ColumnConstants getConstants(){
    return constants;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public Value getSolenoidPosition(){
    return solenoid.get();
  }

  public void setFrontSpeed(double speed){
    front.set(speed);
  }

  public void setBackSpeed(double speed){
    back.set(speed);
  }

  public void enableStopper(){
    solenoid.set(Value.kForward);
  }
  
  public void disableStopper(){
    solenoid.set(Value.kReverse);
  }
}
