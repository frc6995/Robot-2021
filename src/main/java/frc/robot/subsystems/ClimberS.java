package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.wrappers.motorcontrollers.NomadSparkMax;
import frc.robot.constants.ClimberConstants2021;

public class ClimberS extends SubsystemBase {

	private NomadSparkMax climbRatchet;
	private Servo ratchetRelease;
	private Solenoid climberDeploy;
	private CANEncoder encoder;
	private ClimberConstants2021 constants;

	/** Creates a new ClimberS. */
	public ClimberS(ClimberConstants2021 constants, NomadSparkMax climbRatchet, Servo ratchetRelease,
			Solenoid climberDeploy) {
		this.climbRatchet = climbRatchet;
		this.ratchetRelease = ratchetRelease;
		this.climberDeploy = climberDeploy;
		this.encoder = climbRatchet.getEncoder();
		this.constants = constants;

		climbRatchet.getPIDController().setP(constants.getKP());
		climbRatchet.getPIDController().setI(constants.getKI());
		climbRatchet.getPIDController().setD(constants.getKD());
		climbRatchet.getPIDController().setFF(constants.getKF());
		climbRatchet.getPIDController().setIZone(constants.getIZone());

		engageRatchet();
		retractClimber();
	}

	public void disengageRatchet() {
		ratchetRelease.setAngle(constants.servoDisengagedAngle());
		climbRatchet.setIdleMode(IdleMode.kCoast);
	}

	public void engageRatchet() {
		ratchetRelease.setAngle(constants.servoEngagedAngle());
		climbRatchet.setIdleMode(IdleMode.kBrake);
	}

	public void deployClimber() {
		climberDeploy.set(true);
	}

	private void retractClimber() {
		climberDeploy.set(false);
	}

	public void resetEncoder() {
		encoder.setPosition(0);
	}

	public void runPID() {
		climbRatchet.getPIDController().setReference(constants.getPullupSetpoint(), ControlType.kPosition);
	}

	public boolean isAtSetpoint() {
		return (Math.abs(encoder.getPosition() - constants.getPullupSetpoint()) < constants
				.getAllowableError());
	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}
}
