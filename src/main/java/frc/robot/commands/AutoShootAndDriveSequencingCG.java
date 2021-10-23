package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.cannon.SpinUpAndAimC;
import frc.robot.commands.cannon.SpinUpShooterC;
import frc.robot.commands.column.ColumnFeedC;
import frc.robot.commands.drivebase.DriveAutoC;
import frc.robot.subsystems.AgitatorS;
import frc.robot.subsystems.ColumnS;
import frc.robot.subsystems.DrivebaseS;
import frc.robot.subsystems.IntakeS;
import frc.robot.subsystems.LimelightS;
import frc.robot.subsystems.cannon.CannonS;


public class AutoShootAndDriveSequencingCG extends SequentialCommandGroup {
  /** Creates a new AutoShootAndDriveCG. */
  public AutoShootAndDriveSequencingCG(DrivebaseS drivebase, CannonS cannon, AgitatorS agitator, ColumnS column, IntakeS intake, LimelightS limelight, boolean forwards) {
    addCommands(
      new ParallelCommandGroup(new SpinUpAndAimC(cannon, limelight, false),
      new SequentialCommandGroup(
        new WaitCommand(3),
      //new SpinUpShooterC(cannon, false),
      new InstantCommand(()->column.disableStopper(), column),
      new WaitCommand(.5),
      new ColumnFeedC(column, 0.85, 0.25).withTimeout(1),
      new WaitCommand(0.5),
      new ColumnFeedC(column, 0.85, 0.25).withTimeout(1),
      new WaitCommand(0.5),
      new ColumnFeedC(column, 0.85, 0.25).withTimeout(1),
      new WaitCommand(.5), // spin shooter and launch 3  balls.
      new DriveAutoC(drivebase, 3, forwards, 0.25).withTimeout(1.5)))
    );
  }
}
