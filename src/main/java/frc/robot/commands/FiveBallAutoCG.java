package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.cannon.SpinUpAndAimC;
import frc.robot.commands.cannon.SpinUpShooterDistanceC;
import frc.robot.commands.column.ColumnFeedC;
import frc.robot.commands.drivebase.DriveAutoC;
import frc.robot.commands.drivebase.DriveAutoCurvatureC;
import frc.robot.commands.intake.IntakeSpinWhileHeldC;
import frc.robot.commands.othercommands.AgitatorSpinC;
import frc.robot.subsystems.AgitatorS;
import frc.robot.subsystems.ColumnS;
import frc.robot.subsystems.DrivebaseS;
import frc.robot.subsystems.IntakeS;
import frc.robot.subsystems.LimelightS;
import frc.robot.subsystems.cannon.CannonS;

public class FiveBallAutoCG extends ParallelCommandGroup {
	/** Creates a new SixBallAutoCG. */
	public FiveBallAutoCG(CannonS cannon, LimelightS limelight, IntakeS intake, AgitatorS agitator, ColumnS column,
			DrivebaseS drivebase) {
		addCommands(new WaitCommand(5).andThen(new SpinUpShooterDistanceC(cannon, limelight, true)),
				new IntakeSpinWhileHeldC(intake),
				new AgitatorSpinC(agitator),
				new SequentialCommandGroup(new WaitCommand(6.5),
						new InstantCommand(() -> column.disableStopper(), column), new WaitCommand(0.5), //0.5
						new ColumnFeedC(column, 0.85, 0.4, false).withTimeout(15)),
				new DriveAutoC(drivebase, 2, true, 0.25).withTimeout(3.8)
						.andThen(new DriveAutoC(drivebase, 2, false, 0.25).withTimeout(1.5))
						.andThen(new DriveAutoCurvatureC(drivebase, 2, true, -0.18, -0.18).withTimeout(2.5)));
	}
}