package org.usfirst.frc.team3459.robot.commands;

import org.usfirst.frc.team3459.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class DriveWithJoysticks extends Command {

	Joystick left;
	Joystick right;
	
	DriveTrain drive;
	
	public DriveWithJoysticks(DriveTrain dT, Joystick l, Joystick r) {
		left = l;
		right = r;
		drive = dT;
	}
	
	@Override
	protected void initialize() {
		setTimeout(1);
	}

	@Override
	protected void execute() {
		drive.driveFromJS(left, right);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		drive.stop();
		
	}

	@Override
	protected void interrupted() {
		end();
	}

}
