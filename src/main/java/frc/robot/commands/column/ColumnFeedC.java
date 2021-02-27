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
    column.setColumnBeltsSpeed(column.getConstants().getColumnFeedSpeed());
    column.spinAccel(0.8);
    //column.spinToRPM(3000);
  }

  @Override
  public void execute() {
  }

  @Override
  public void end(boolean interrupted) {
    column.setColumnBeltsSpeed(0);
    column.stopAccelerator();
    column.enableStopper();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
