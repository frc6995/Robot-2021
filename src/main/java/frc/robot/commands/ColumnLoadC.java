package frc.robot.commands;

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

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    column.enableStopper();
    column.setFrontSpeed(column.getConstants().getColumnSpeed());
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    column.setFrontSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
