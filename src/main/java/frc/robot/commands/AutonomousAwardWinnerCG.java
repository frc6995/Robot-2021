// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.cannon.SpinUpShooterC;
import frc.robot.commands.column.ColumnFeedC;
import frc.robot.commands.drivebase.DriveAutoC;
import frc.robot.commands.othercommands.ExpelBallsCG;
import frc.robot.commands.othercommands.StoreBallsCG;
import frc.robot.subsystems.AgitatorS;
import frc.robot.subsystems.ColumnS;
import frc.robot.subsystems.DrivebaseS;
import frc.robot.subsystems.IntakeS;
import frc.robot.subsystems.cannon.CannonS;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutonomousAwardWinnerCG extends SequentialCommandGroup {
  /** Creates a new AutonomousAwardWinnerCG. */
  public AutonomousAwardWinnerCG(DrivebaseS drivebase, CannonS cannon, AgitatorS agitator, ColumnS column, IntakeS intake) {
    addCommands(
      new SpinUpShooterC(cannon, false),
      new InstantCommand(()->column.disableStopper(), column),
      new WaitCommand(.5),
      //new ExpellBallsCG(intake, agitator, column).withTimeout(2),,
      new ColumnFeedC(column).withTimeout(2),
      new WaitCommand(.5), // spin shooter and launch 3  balls.
      new DriveAutoC(drivebase, 1, true).withTimeout(1.5).alongWith( //drive back, 
      new StoreBallsCG(intake, agitator, column).withTimeout(5)), // and load them into the column.
      new DriveAutoC(drivebase, 1, false).withTimeout(1.5),
      new WaitCommand(.5),
      new SpinUpShooterC(cannon, false), // spin up the shooter,
      new InstantCommand(()->column.setAcceleratorSpeed(0.75), column),
      new InstantCommand(()->column.disableStopper(), column),
      new WaitCommand(.5),
      new ExpelBallsCG(intake, agitator, column).withTimeout(5), // feed them to the shooter,
      new WaitCommand(.5)  
    );
  }
}
