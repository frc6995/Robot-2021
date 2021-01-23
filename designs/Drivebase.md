# DrivebaseS

## Description
The DrivebaseS subsystem is a differential 6-wheel, 4-NEO drivebase with autonomous path following and teleoperated driving capabilites. 

## Inputs
- REV throughbore encoder on each side, direct-driven by the wheel axle. 
- NavX gyro mounted to the RoboRIO MXP port. 

## Outputs
### Hardware Ouputs

- A row of linked wheels on each side, each side driven by a 2-NEO gearbox. One NEO on each side is leader, and the other follows it.

### Software Outputs
- The current encoder values (velocity, position) for each side
- The current DifferentialDriveWheelSpeeds object, containing m/s velocity for each side.
- The current gyro yaw heading.
- The current pose of the robot.

## Actions
- Drive using open-loop speed control, given a percentage value for left and right sides
    - Use `NomadBaseMotor.set()` on the leader motors on each side.
- Convert arcade-style fwd/back/turn control to percentage for each side.
    - Exact conversion algorithm TBD, up to the drivers.
- Drive using given voltage to each side.
    - Use `NomadBaseMotor.setVoltage()` on each leader.
- Track the pose of the robot given encoder and gyro values.
    - Use `DifferentialDriveOdometry`.
- Stop all motors with a single method.
    - Call `NomadBaseMotor.stopMotor()` on each leader motor.

## Constraints
- Amperage constraint on each NEO, 40 A Continuous.
- Ramp rate limits on the NEOs for open-loop driving, value TBD, start with 1 sec to full velocity.
- Drivebase should not command motors past their maximum power of 100%

## Design (Commands)
- ArcadeDriveC
    - Inputs: double fwdBack, double turn
    - Algorithm: call arcadeDriveController in DrivebaseS (actual algorithm TBD)
    - Output: call `DrivebaseS.drivePercentages()` with the output of the algorithm.
- PathFollowing Command
    - Generate a Ramsete Command to follow a trajectory.
- VisionAlignC
    - Inputs: Limelight offset values.
    - Algorithm:  PID on fwd/back/turn to reduce vision offsets to 0.
    - Output: Motor outputs in a feedback loop.
- (Stretch, Related) TargetLockC
    - Uses the turret, shooter, and hood to maintain a lock on the target based on the drivebase's velocity and/or position
