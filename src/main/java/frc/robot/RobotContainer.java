package frc.robot;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.cannon.AimHoodC;
import frc.robot.commands.cannon.AimTurretC;
import frc.robot.commands.cannon.FindTargetC;
import frc.robot.commands.cannon.SpinDownShooterC;
import frc.robot.commands.cannon.SpinUpShooterC;
import frc.robot.subsystems.cannon.LaunchBallC;


public class RobotContainer {

  public RobotContainer() {
    configureButtonBindings();

    // AimCannon Command Group - Update nulls with hardware once created
    /**A command group that finds the target, aims the hood, aims the turret, and preps the shooter for launch. */
    ParallelCommandGroup aimCannonCG = new FindTargetC(null).andThen(new AimHoodC(null, null)).alongWith(new AimTurretC(null, null).alongWith(new SpinUpShooterC(null)));

    // Shoot Cannon: 
    SequentialCommandGroup shootCannonCG = new SpinUpShooterC(null).andThen(new LaunchBallC(null, null)).andThen(new SpinDownShooterC(null));
  }

  private void configureButtonBindings() {}
}
