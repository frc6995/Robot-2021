package frc.robot;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.super_shooter.HomeSuperShooterC;
import frc.robot.commands.super_shooter.AimHoodC;
import frc.robot.commands.super_shooter.AimTurretC;
import frc.robot.commands.super_shooter.FindTargetC;


public class RobotContainer {

  public RobotContainer() {
    configureButtonBindings();
   // ParallelCommandGroup aimSuperShooterCG = new FindTargetC().andThen(new AimHoodC()).alongWith(new AimTurretC());
  }

  private void configureButtonBindings() {}
}
