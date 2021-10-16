package frc.robot.commands.cannon;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.lib.wrappers.limelight.Limelight;
import frc.robot.subsystems.DrivebaseS;
import frc.robot.subsystems.LimelightS;
import frc.robot.subsystems.cannon.CannonS;

/**
 * A command that preps the shooter for launching power cells.
 * 
 * @author JoeyFabel, sammcdo
 */
public class SpinUpAndAimDbC extends CommandBase {
	private CannonS cannon;
	private LimelightS limelight;
	private DrivebaseS drivebase;
	private double rpm = 2250;
	private double offset;

	/** Creates a new SpinUpShooterC. */
	public SpinUpAndAimDbC(CannonS cannon, LimelightS limelight, DrivebaseS drivebase, boolean requireCannon) {
		this.cannon = cannon;
		this.limelight = limelight;
		this.drivebase = drivebase;

		if (requireCannon) {
			addRequirements(cannon);
		}

	}

	public SpinUpAndAimDbC(CannonS cannon, LimelightS limelight, DrivebaseS drivebase, boolean requireCannon,
			double rpm) {
		this(cannon, limelight, drivebase, requireCannon);
		this.rpm = rpm;
	}

	public SpinUpAndAimDbC(CannonS cannon, LimelightS limelight, DrivebaseS drivebase, boolean requireCannon,
			DoubleSupplier rpm) {
		this(cannon, limelight, drivebase, requireCannon);
		this.rpm = rpm.getAsDouble();
	}

	@Override
	public void initialize() {
		cannon.pidShooterToTargetSpeed(rpm);
		limelight.register();
		offset = cannon.turret.getTurretEncoderPosition()
				- (limelight.getFilteredXOffset() * (limelight.isTargetFound() ? 1 : 0));
		cannon.turret.setSetpoint(offset);
		cannon.turret.runPID();
	}

	@Override
	public void execute() {
		offset = -drivebase.getRotationSpeed() * 0.02;
		offset += cannon.turret.getTurretEncoderPosition()
				- (limelight.getFilteredXOffset() * (limelight.isTargetFound() ? 1 : 0));
		cannon.turret.setSetpoint(offset);
		cannon.turret.runPID();
	}

	@Override
	public void end(boolean interrupted) {
		cannon.stopShooter();
		limelight.deregister();
		super.end(interrupted);
	}

	@Override
	public boolean isFinished() {
		return false;
	}
}