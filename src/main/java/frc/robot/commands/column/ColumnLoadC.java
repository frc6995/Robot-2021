package frc.robot.commands.column;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ColumnS;

/**A command that closes the hatch between the Column and Shooter and loads the balls into position to be fired. */
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
    column.setAcceleratorSpeed(column.getConstants().getColumnSpeed());
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {
    column.setAcceleratorSpeed(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
