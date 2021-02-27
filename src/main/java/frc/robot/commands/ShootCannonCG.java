package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.cannon.LaunchBallC;
import frc.robot.commands.cannon.SpinDownShooterC;
import frc.robot.commands.cannon.SpinUpShooterC;
import frc.robot.subsystems.cannon.CannonS;

public class ShootCannonCG extends SequentialCommandGroup {
  /** Creates a new ShootCannonCG. */
  public ShootCannonCG(CannonS cannonS) {
    addCommands(new SequentialCommandGroup(new SpinUpShooterC(cannonS, false),
      new LaunchBallC(cannonS, false), new SpinDownShooterC(cannonS, false)));
    addRequirements(cannonS);
  }
}
