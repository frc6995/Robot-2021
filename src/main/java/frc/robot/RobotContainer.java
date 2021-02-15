package frc.robot;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.cannon.AimHoodC;
import frc.robot.commands.cannon.AimTurretC;
import frc.robot.commands.cannon.FindTargetC;
import frc.robot.commands.cannon.HomeSuperShooterC;


public class RobotContainer {

  public RobotContainer() {
    configureButtonBindings();
   // ParallelCommandGroup aimSuperShooterCG = new FindTargetC().andThen(new AimHoodC()).alongWith(new AimTurretC());
  }

  private void configureButtonBindings() {}
}
