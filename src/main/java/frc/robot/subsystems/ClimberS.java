package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMax.SoftLimitDirection;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.wrappers.motorcontrollers.NomadSparkMax;
import frc.robot.constants.ClimberConstants2021;

public class ClimberS extends SubsystemBase {

	private NomadSparkMax climbRatchet;
	private NomadSparkMax translator;
	private Servo ratchetRelease;
	private DoubleSolenoid climberDeploy;
	private CANEncoder encoder;
	private ClimberConstants2021 constants;

	/** Creates a new ClimberS. */
	public ClimberS(ClimberConstants2021 constants, NomadSparkMax climbRatchet, Servo ratchetRelease,
			DoubleSolenoid climberDeploy, NomadSparkMax translator) {
		this.climbRatchet = climbRatchet;
		this.translator = translator;
		this.ratchetRelease = ratchetRelease;
		this.climberDeploy = climberDeploy;
		this.encoder = climbRatchet.getEncoder();
		this.constants = constants;

		climbRatchet.getPIDController().setP(constants.getKP());
		climbRatchet.getPIDController().setI(constants.getKI());
		climbRatchet.getPIDController().setD(constants.getKD());
		climbRatchet.getPIDController().setFF(constants.getKF());
		climbRatchet.getPIDController().setIZone(constants.getIZone());
		climbRatchet.setSoftLimit(SoftLimitDirection.kReverse, -35);
		climbRatchet.setSoftLimit(SoftLimitDirection.kForward, 0);

		engageRatchet();
		retractClimber();
	}

	public void setSpeed(double speed) {
		climbRatchet.set(speed);
	}

	public void setTranslatorSpeed(double speed) {
		translator.set(speed);
	}

	public void disengageRatchet() {
		ratchetRelease.setAngle(constants.servoDisengagedAngle());
		climbRatchet.setIdleMode(IdleMode.kCoast);
	}

	public void engageRatchet() {
		ratchetRelease.setAngle(constants.servoEngagedAngle());
		climbRatchet.setIdleMode(IdleMode.kBrake);
	}

	public boolean isDeployed() {
		return (climberDeploy.get() == Value.kForward ? true : false);
	}

	public void deployClimber() {
		climberDeploy.set(Value.kForward);
	}

	public void retractClimber() {
		climberDeploy.set(Value.kReverse);
	}

	public void resetEncoder() {
		encoder.setPosition(0);
	}

	public void runPID() {
		climbRatchet.getPIDController().setReference(constants.getPullupSetpoint(), ControlType.kPosition);
	}

	public boolean isAtSetpoint() {
		return !(Math.abs(encoder.getPosition()) > constants.getPullupSetpoint());
	}

	public boolean isAtUpSetpoint() {
		return (Math.abs(encoder.getPosition()) > constants.getExtendSetPoint());
	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
		SmartDashboard.putNumber("Climber Encoder", encoder.getPosition());
		SmartDashboard.putBoolean("C Is At Setpoint", isAtSetpoint());
		SmartDashboard.putBoolean("C Is At Up Setpoint", isAtUpSetpoint());
		SmartDashboard.putNumber("Ratchet Value", ratchetRelease.getAngle());
	}
}
