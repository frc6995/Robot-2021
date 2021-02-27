package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.cannon.AimHoodC;
import frc.robot.commands.cannon.AimTurretC;
import frc.robot.commands.cannon.FindTargetC;
import frc.robot.commands.cannon.SpinUpShooterC;
import frc.robot.subsystems.LimelightS;
import frc.robot.subsystems.cannon.CannonS;

public class AimCannonCG extends SequentialCommandGroup {
  /** Creates a new AimCannonCG. */
  public AimCannonCG(LimelightS limelight, CannonS cannon) {
    addCommands(new FindTargetC(limelight),
    new ParallelCommandGroup(new AimHoodC(limelight, cannon, false), new AimTurretC(limelight, cannon, false),
        new SpinUpShooterC(cannon, false)));
    addRequirements(cannon);
  }
}
