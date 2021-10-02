package frc.robot.commands.cannon;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LimelightS;
import frc.robot.subsystems.cannon.CannonS;
import frc.robot.subsystems.cannon.Turret.TurretRequestedStates;

/**
 * A command that aims the turret.
 * 
 * @author JoeyFabel
 */
public class AimTurretC extends CommandBase {
  private LimelightS limelight;
  private CannonS cannon;
  private double offset;

  /** Creates a new AimTurretC. */
  public AimTurretC(LimelightS limelight, CannonS cannon, boolean requireCannon) {

    this.limelight = limelight;
    this.cannon = cannon;
    if (requireCannon) {
      addRequirements(cannon);
    }
  }

  public AimTurretC(LimelightS limelight, CannonS cannon) {
    this(limelight, cannon, true);
  }

  @Override
  public void initialize() {
    limelight.register();
    offset = cannon.turret.getTurretEncoderPosition() - (limelight.getFilteredXOffset() * (limelight.isTargetFound() ? 1:0));
    SmartDashboard.putNumber("Turret LL Offset", offset);
    cannon.turret.setSetpoint(offset);
    cannon.turret.runPID();
  }

  @Override
  public void execute() { // if offset > 0, turn left. if offset < 0, turn right
    /*cannon.requestTurretState(TurretRequestedStates.MOVE_TO_SETPOINT, 
        cannon.turret.getTurretEncoderPosition() - 
        limelight.getFilteredXOffset());*/
    offset = cannon.turret.getTurretEncoderPosition() - (limelight.getFilteredXOffset() * (limelight.isTargetFound() ? 1:0));
    cannon.turret.setSetpoint(offset);
    cannon.turret.runPID();
  }

  @Override
  public void end(boolean interrupted) {
    if (interrupted)
      cannon.requestTurretState(TurretRequestedStates.STOP, 0);
      //cannon.requestTurretState(TurretRequestedStates.HOME, 0);
    limelight.deregister();
  }

  @Override
  public boolean isFinished() {
    // cannon can tell when it is at setpoint, so it has an exit condition and runs
    // in execute.
    return false;
    //return (cannon.isTurretAtSetpoint() && limelight.getFilteredXOffset() < 1);
  }
}
