package frc.template.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.constants.AutoConstants;
import frc.lib.constants.DriveConstants;
import frc.lib.utility.drivebase.DrivebaseWheelPercentages;
import frc.lib.wrappers.motorcontrollers.NomadSparkMax;

public class DrivebaseS extends SubsystemBase {
  public NomadSparkMax leftMax;
  public NomadSparkMax rightMax;
  private DriveConstants driveConstants;
  private AutoConstants autoConstants;

  /** Creates a new DrivebaseS. */
  public DrivebaseS(DriveConstants driveConstants, AutoConstants autoConstants) {
    this.setDriveConstants(driveConstants);
    this.setAutoConstants(autoConstants);
    leftMax = new NomadSparkMax(driveConstants.getCanIDLeftDriveMaster(), null);
    rightMax = new NomadSparkMax(driveConstants.getCanIDRightDriveMaster(), null);
  }

  public DriveConstants getDriveConstants() {
    return driveConstants;
  }

  public void setDriveConstants(DriveConstants driveConstants) {
    this.driveConstants = driveConstants;
  }

  public AutoConstants getAutoConstants() {
	return autoConstants;
}

public void setAutoConstants(AutoConstants autoConstants) {
	this.autoConstants = autoConstants;
}

public DrivebaseS(NomadSparkMax leftSparkMax, NomadSparkMax rightSparkMax){
    leftMax = leftSparkMax;
    rightMax = rightSparkMax;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  /**
   * Basic arcade drive. Converts joystick inputs into percent outputs for each side of the drivebase.
   * @param fwdBack The joystick input for the forward/back axis. It is assumed that a value of 1 represents forward, and -1 represents backward.
   * @param leftRight The joystick input for the left/right axis. It is assumed that 1 represents left point turn, and -1 represents right point turn.
   */
  public DrivebaseWheelPercentages arcadeDriveController(double fwdBack, double leftRight) {
    double leftPercent = fwdBack + leftRight; 
    double rightPercent = fwdBack - leftRight;
    return new DrivebaseWheelPercentages().setLeftPercentage(leftPercent).setRightPercentage(rightPercent);
  }

  public void drivePercentages(DrivebaseWheelPercentages percentages){
    leftMax.set(ControlMode.PercentOutput, percentages.getLeftPercentage());
    rightMax.set(ControlMode.PercentOutput, percentages.getRightPercentage());
  }
}
