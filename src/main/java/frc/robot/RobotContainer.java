package frc.robot;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.super_shooter.HomeSuperShooterC;
import frc.robot.commands.super_shooter.AimHoodC;
import frc.robot.commands.super_shooter.AimShooterC;
import frc.robot.commands.super_shooter.AimTurretC;


public class RobotContainer {

  public RobotContainer() {
    configureButtonBindings();
    ParallelCommandGroup aimSuperShooterCG = new AimHoodC().alongWith(new AimShooterC()).alongWith(new AimTurretC());
  }

  private void configureButtonBindings() {}
}
