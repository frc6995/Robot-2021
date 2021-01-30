package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.wrappers.motorcontrollers.NomadTalonSRX;
import frc.lib.wrappers.motorcontrollers.NomadVictorSPX;

public class ColumnS extends SubsystemBase {
  private NomadTalonSRX front;
  private NomadVictorSPX back;
  private DoubleSolenoid solenoid; 
  /** Creates a new ColumnS. */
  public ColumnS() {
    front = new NomadTalonSRX(41);
    back = new NomadVictorSPX(42);
    solenoid = new DoubleSolenoid(1, 4, 5);
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

  
}
