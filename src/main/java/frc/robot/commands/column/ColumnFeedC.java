package frc.robot.commands.column;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ColumnS;

public class ColumnFeedC extends CommandBase {
  private ColumnS column;

  /** Creates a new ColumnFeedC. */
  public ColumnFeedC(ColumnS columnS) {
    this.column = columnS;
    addRequirements(column);
  }

  @Override
  public void initialize() {
    column.disableStopper();
    column.setAcceleratorSpeed(0.75);
    column.setColumnSpeed(0.75);
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {
    column.setAcceleratorSpeed(0);
    column.enableStopper();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
