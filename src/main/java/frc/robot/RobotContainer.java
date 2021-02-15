package frc.robot;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.cannon.AimHoodC;
import frc.robot.commands.cannon.AimTurretC;
import frc.robot.commands.cannon.FindTargetC;
import frc.robot.commands.cannon.SpinUpShooterC;


public class RobotContainer {

  public RobotContainer() {
    configureButtonBindings();

    // AimCannon Command Group - Update nulls with hardware once created
    /**A command group that finds the target, aims the hood, aims the turret, and preps the shooter for launch. */
    ParallelCommandGroup aimCannonCG = new FindTargetC(null).andThen(new AimHoodC(null, null)).alongWith(new AimTurretC(null, null).alongWith(new SpinUpShooterC(null)));
  }

  private void configureButtonBindings() {}
}
