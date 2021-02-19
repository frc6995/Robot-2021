package frc.robot.commands.column;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ColumnS;

public class ColumnLoadC extends CommandBase {
  private ColumnS column;
  
  /** Creates a new ColumnLoadC. */
  public ColumnLoadC(ColumnS column) {
    this.column = column;
    addRequirements(column);
  }

  @Override
  public void initialize() {
    column.enableStopper();
    //column.setFrontSpeed(column.getConstants().getColumnSpeed());
    column.setBackSpeed(0.75);
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {
    column.setFrontSpeed(0);
    column.setBackSpeed(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
