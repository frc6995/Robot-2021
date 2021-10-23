package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.cannon.SpinUpAndAimC;
import frc.robot.commands.column.ColumnFeedC;
import frc.robot.commands.drivebase.DriveAutoC;
import frc.robot.commands.intake.IntakeSpinWhileHeldC;
import frc.robot.commands.othercommands.AgitatorSpinC;
import frc.robot.subsystems.AgitatorS;
import frc.robot.subsystems.ColumnS;
import frc.robot.subsystems.DrivebaseS;
import frc.robot.subsystems.IntakeS;
import frc.robot.subsystems.LimelightS;
import frc.robot.subsystems.cannon.CannonS;

public class SixBallAutoCG extends ParallelCommandGroup {
  /** Creates a new SixBallAutoCG. */
  public SixBallAutoCG(CannonS cannon, LimelightS limelight, IntakeS intake, AgitatorS agitator, ColumnS column, DrivebaseS drivebase) {
    addCommands(new SpinUpAndAimC(cannon, limelight, true),
    new IntakeSpinWhileHeldC(intake),
    new AgitatorSpinC(agitator),
    new SequentialCommandGroup(
        new WaitCommand(2.5),
      new InstantCommand(()->column.disableStopper(), column),
      new WaitCommand(.5),
      new ColumnFeedC(column, 0.85, 0.25, false)),
    new DriveAutoC(drivebase, 4, true, 0.15).withTimeout(4)
      );
  }
}
