package org.usfirst.frc.team3459.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {

	RobotDrive drive;
	
	public DriveTrain(int driveLeftPWM, int driveRightPWM) {
		drive = new RobotDrive(driveLeftPWM, driveRightPWM);
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	public void driveFromJS(Joystick left, Joystick right) {
		drive.tankDrive(left, right);
	}
	
	public void stop() {
		drive.tankDrive(0, 0);
	}
}
