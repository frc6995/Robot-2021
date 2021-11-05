// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.stream.Collectors;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.simulation.ADXRS450_GyroSim;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;
import edu.wpi.first.wpilibj.simulation.EncoderSim;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.lib.subsystems.DifferentialDrivebaseS;
import frc.lib.utility.drivebase.DrivebaseWheelPercentages;
import frc.lib.wrappers.motorcontrollers.NomadPWMMotor;
import frc.lib.wrappers.motorcontrollers.NomadSparkMax;
import frc.lib.wrappers.motorcontrollers.NomadTalonSRX;
import frc.robot.auto.Trajectories;
import frc.lib.constants.AutoConstants;
import frc.lib.constants.DriveConstants;

public class DrivebaseS extends DifferentialDrivebaseS {
	private NomadSparkMax leftLeader;
	private NomadSparkMax rightLeader;
	private NomadSparkMax leftFollower;
	private NomadSparkMax rightFollower;

	// private DifferentialDrive m_drive;

	private AHRS navx = new AHRS(Port.kMXP);

	public DifferentialDrive m_drive;
	// Create the simulation model of our drivetrain.
	private DifferentialDrivetrainSim m_driveSim;

	private DifferentialDriveOdometry m_odometry = new DifferentialDriveOdometry(new Rotation2d());

	private Encoder m_leftEncoder;
	private EncoderSim m_leftEncoderSim;
	private Encoder m_rightEncoder;
	private EncoderSim m_rightEncoderSim;
	private Rotation2d m_gyroSim;

	private Field2d m_field = new Field2d();

	private RamseteController controller = new RamseteController(autoConstants.getkRamseteB(),
			autoConstants.getkRamseteZeta());
	private PIDController leftPidController = new PIDController(driveConstants.getkPDriveVel(), 0, 0);
	private PIDController rightPidController = new PIDController(driveConstants.getkPDriveVel(), 0, 0);

	/** Creates a new AutonomousDrivebaseS. */
	public DrivebaseS(DriveConstants driveConstants, AutoConstants autoConstants) {
		super(driveConstants, autoConstants);

		leftLeader = new NomadSparkMax(driveConstants.getCanIDLeftDriveMaster(), MotorType.kBrushless,
				driveConstants.getLeftDriveLeaderInverted());

		rightLeader = new NomadSparkMax(driveConstants.getCanIDRightDriveMaster(), MotorType.kBrushless,
				driveConstants.getRightDriveLeaderInverted());

		leftFollower = new NomadSparkMax(driveConstants.getCanIDLeftDriveFollower(), MotorType.kBrushless,
				driveConstants.getLeftDriveFollowerInverted(), leftLeader);

		rightFollower = new NomadSparkMax(driveConstants.getCanIDRightDriveFollower(), MotorType.kBrushless,
				driveConstants.getRightDriveFollowerInverted(), rightLeader);

		leftLeader.getEncoder().setPositionConversionFactor(42);
		rightLeader.getEncoder().setPositionConversionFactor(42);
		leftLeader.getEncoder().setVelocityConversionFactor(42 / 60.0);
		rightLeader.getEncoder().setVelocityConversionFactor(42 / 60.0);

		setIdleMode(IdleMode.kCoast);
		leftLeader.setOpenLoopRampRate(1.5);
		rightLeader.setOpenLoopRampRate(1.5);

		m_drive = new DifferentialDrive(leftLeader, rightLeader);
		m_drive.setRightSideInverted(false);
		if (RobotBase.isSimulation()) {
			m_leftEncoder = new Encoder(driveConstants.getLeftEncoderPorts()[0],
					driveConstants.getLeftEncoderPorts()[1],
					driveConstants.getLeftEncoderReversed());

			m_rightEncoder = new Encoder(driveConstants.getRightEncoderPorts()[0],
					driveConstants.getRightEncoderPorts()[1],
					driveConstants.getRightEncoderReversed());

			m_leftEncoder.setDistancePerPulse(driveConstants.getEncoderDistancePerPulse());
			m_rightEncoder.setDistancePerPulse(driveConstants.getEncoderDistancePerPulse());

			m_leftEncoderSim = new EncoderSim(m_leftEncoder);
			m_rightEncoderSim = new EncoderSim(m_rightEncoder);
			m_gyroSim = new Rotation2d();
		}

		m_driveSim = new DifferentialDrivetrainSim(driveConstants.getDrivetrainPlant(),
				driveConstants.getDriveGearbox(),
				driveConstants.getEncoderRevolutionsPerWheelRevolution(),
				driveConstants.getkTrackWidthMeters(), driveConstants.getkWheelDiameter() / 2.0,
				driveConstants.getSimEncoderStdDev());

		// m_gyroSim = new ADXRS450_GyroSim(gyro);
		m_field.getObject("trajectory").setPoses(Trajectories.trenchToLineRevTrajectory.getStates().stream()
				.map(state -> state.poseMeters).collect(Collectors.toList()));

		// SmartDashboard.putData("Field", m_field);

		resetOdometry(new Pose2d());
	}

	public PIDController getLeftPidController() {
		return leftPidController;
	}

	public PIDController getRightPidController() {
		return rightPidController;
	}

	public RamseteController getRamseteController() {
		return controller;
	}

	@Override
	public void periodic() {
		if (RobotBase.isReal()) {
			m_odometry.update(navx.getRotation2d(),
					leftLeader.getEncoder().getPosition()
							* driveConstants.getEncoderDistancePerPulse(),
					rightLeader.getEncoder().getPosition()
							* driveConstants.getEncoderDistancePerPulse());
		} else {
			m_odometry.update(m_gyroSim, m_leftEncoder.getDistance(), m_rightEncoder.getDistance());
		}
		// SmartDashboard.putData(m_drive);
		/*
		 * SmartDashboard.putNumber("PoseX", m_odometry.getPoseMeters().getX());
		 * SmartDashboard.putNumber("PoseY", m_odometry.getPoseMeters().getY());
		 * SmartDashboard.putNumber("DriveLeftCounts",
		 * leftLeader.getEncoder().getPosition());
		 * SmartDashboard.putNumber("DriveLeftVel", getLeftVelocity());
		 * SmartDashboard.putNumber("DriveRightVel", getRightVelocity());
		 * SmartDashboard.putNumber("DriveRightCounts",
		 * rightLeader.getEncoder().getPosition()); SmartDashboard.putNumber("Gyro",
		 * navx.getAngle());
		 */
		m_field.setRobotPose(m_odometry.getPoseMeters());
	}

	public void simulationPeriodic() {
		// Set the inputs to the system. Note that we need to convert
		// the [-1, 1] PWM signal to voltage by multiplying it by the
		// robot controller voltage.
		m_driveSim.setInputs(leftLeader.get() * RobotController.getInputVoltage(),
				(driveConstants.getDrivebaseRightSideInverted() ? -1 : 1) * rightLeader.get()
						* RobotController.getInputVoltage());

		// Advance the model by 20 ms. Note that if you are running this
		// subsystem in a separate thread or have changed the nominal timestep
		// of TimedRobot, this value needs to match it.
		m_driveSim.update(0.02);

		// Update all of our sensors.
		m_leftEncoderSim.setDistance(m_driveSim.getLeftPositionMeters());
		m_leftEncoderSim.setRate(m_driveSim.getLeftVelocityMetersPerSecond());
		m_rightEncoderSim.setDistance(m_driveSim.getRightPositionMeters());
		m_rightEncoderSim.setRate(m_driveSim.getRightVelocityMetersPerSecond());

		m_gyroSim = m_driveSim.getHeading();

	}

	@Override
	public double getYaw() {
		return Math.IEEEremainder(navx.getAngle(), 360) * (driveConstants.getGyroReversed() ? -1.0 : 1.0);
	}

	public double getRotationSpeed() {
		return navx.getRate() * (driveConstants.getGyroReversed() ? -1.0 : 1.0);
	}

	@Override
	public DifferentialDriveWheelSpeeds getWheelSpeeds() {
		return new DifferentialDriveWheelSpeeds(getLeftVelocity(), getRightVelocity());
	}

	public Pose2d getPose() {
		return m_odometry.getPoseMeters();
	}

	@Override
	public double getLeftVelocity() {
		return leftLeader.getEncoder().getVelocity();
	}

	@Override
	public double getRightVelocity() {
		return rightLeader.getEncoder().getVelocity();
	}

	@Override
	public DrivebaseWheelPercentages arcadeDriveController(double fwdBack, double leftRight) {
		double leftPercent = fwdBack + leftRight;
		double rightPercent = fwdBack - leftRight;
		return new DrivebaseWheelPercentages().setLeftPercentage(leftPercent).setRightPercentage(rightPercent);
	}

	@Override
	public void drivePercentages(DrivebaseWheelPercentages percentages) {
		leftLeader.set(percentages.getLeftPercentage());
		rightLeader.set((driveConstants.getDrivebaseRightSideInverted() ? -1 : 1)
				* percentages.getRightPercentage());
	}

	public void curvatureDrive(double xSpeed, double zRotation, boolean isQuickTurn) {
		// SmartDashboard.putNumber("xSpeed", xSpeed);
		m_drive.curvatureDrive(xSpeed, zRotation * (isQuickTurn ? 0.75 : 1), isQuickTurn);
	}

	@Override
	public void stopMotor() {
		leftLeader.stopMotor();
		rightLeader.stopMotor();
	}

	public void resetEncoders() {
		leftLeader.getEncoder().setPosition(0);
		rightLeader.getEncoder().setPosition(0);
	}

	public void resetOdometry(Pose2d pose) {
		resetEncoders();
		navx.reset();
		m_driveSim.setPose(pose);
		m_odometry.resetPosition(pose, navx.getRotation2d());
	}

	@Override
	public void updateTelemetry() {

		// SmartDashboard.putData("Drivebase", this);
		// SmartDashboard.putNumber("fwdbackaxis",
		// driveConstants.getDriveControllerFwdBackAxis());
	}

	@Override
	public void initSendable(SendableBuilder builder) {
		builder.setSmartDashboardType("DifferentialDrive");
		builder.setActuator(false);
		builder.setSafeState(this::stopMotor);
		builder.addDoubleProperty("Left Motor Speed", this::getLeftVelocity, null);
		builder.addDoubleProperty("Right Motor Speed", this::getRightVelocity, null);
		builder.addDoubleProperty("Left Motor % Speed", this::getLeftSetSpeed, null);
		builder.addDoubleProperty("Right Motor % Speed", this::getRightSetSpeed, null);
	}

	public void tankDriveVolts(double leftVolts, double rightVolts) {
		var batteryVoltage = RobotController.getBatteryVoltage();
		if (Math.max(Math.abs(leftVolts), Math.abs(rightVolts)) > batteryVoltage) {
			leftVolts *= batteryVoltage / 12.0;
			rightVolts *= batteryVoltage / 12.0;
		}
		leftLeader.setOutputVoltage(leftVolts);
		rightLeader.setOutputVoltage((driveConstants.getDrivebaseRightSideInverted() ? -1 : 1) * rightVolts);
		// m_drive.feed();
	}

	@Override
	public double getLeftSetSpeed() {
		// TODO Auto-generated method stub
		return leftLeader.getActualOutputPercent();
	}

	@Override
	public double getRightSetSpeed() {
		// TODO Auto-generated method stub
		return rightLeader.getActualOutputPercent();
	}

	public void setIdleMode(IdleMode mode) {
		leftLeader.setIdleMode(mode);
		rightLeader.setIdleMode(mode);
	}

}
