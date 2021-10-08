package frc.robot.commands.column;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ColumnS;

public class ColumnFeedC extends CommandBase {
  private ColumnS column;
  private double accelSpeed = 0.85;
  private double colSpeed = 0.4;

  /** Creates a new ColumnFeedC. */
  public ColumnFeedC(ColumnS columnS) {
    this.column = columnS;
    addRequirements(column);
  }

  /**
   * Creates a new ColumnFeedC
   * @param columnS The subsystem to require
   * @param accelSpeed The speed of the accelerator wheels
   * @param colSpeed The speed of the column belts
   */
  public ColumnFeedC(ColumnS columnS, double accelSpeed, double colSpeed) {
    this(columnS);
    this.accelSpeed = accelSpeed;
    this.colSpeed = colSpeed;
  }

  @Override
  public void initialize() {
    column.disableStopper();
    column.setAcceleratorSpeed(accelSpeed);
    column.setColumnSpeed(colSpeed);
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {
    column.setAcceleratorSpeed(0);
    column.setColumnSpeed(0);
    column.enableStopper();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
