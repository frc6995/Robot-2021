package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.interfaces.ColumnConstants;
import frc.lib.wrappers.motorcontrollers.NomadSparkMax;
import frc.lib.wrappers.motorcontrollers.NomadTalonSRX;
import frc.lib.wrappers.motorcontrollers.NomadVictorSPX;

public class ColumnS extends SubsystemBase {
  private NomadSparkMax front;
  private NomadTalonSRX back;
  private DoubleSolenoid solenoid; 
  private ColumnConstants constants;
  
  /** Creates a new ColumnS. */
  public ColumnS(ColumnConstants constants, NomadSparkMax front, NomadTalonSRX back, DoubleSolenoid solenoid) {
    this.constants = constants;
    this.front = front;
    this.back = back;
    back.configContinuousCurrentLimit(6);
    back.configPeakCurrentDuration(10);
    back.configPeakCurrentLimit(7);
    back.enableCurrentLimit(true);
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

  public void setAcceleratorSpeed(double speed){
    front.set(speed);
  }

  public void setColumnSpeed(double speed){
    back.set(speed);
  }

  public void enableStopper(){
    solenoid.set(Value.kForward);
  }
  
  public void disableStopper(){
    solenoid.set(Value.kReverse);
  }
}
