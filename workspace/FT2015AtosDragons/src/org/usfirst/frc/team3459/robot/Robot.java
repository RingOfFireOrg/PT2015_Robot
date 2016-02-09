package org.usfirst.frc.team3459.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

// this is a comment
public class Robot extends SampleRobot {
	// Joysticks
	Joystick leftStick;
	Joystick rightStick;
	Joystick controlStick;
	Joystick potentiometers;

	// Buttons
	JoystickButton grabber1;
	JoystickButton grabber2;
	JoystickButton elevator1;
	JoystickButton elevator2;

	RobotDrive myRobot; // robot drive

	// Grabber*******************************
	// + Switches
	DigitalInput closeSwitch;
	DigitalInput openSwitch;
	DigitalInput pressureSwitch;

	// + Objects
	Victor grabberMotor;
	Grabber grabber;

	// Elevator******************************
	// + Switches
	DigitalInput topSwitch;
	DigitalInput bottomSwitch;

	// + Objects
	Victor elevatorMotor;
	Elevator elevator;

	public Robot() {
		leftStick = new Joystick(0);
		rightStick = new Joystick(1);
		controlStick = new Joystick(2);
		potentiometers = new Joystick(3);

		grabber1 = new JoystickButton(controlStick, 12);
		grabber2 = new JoystickButton(controlStick, 11);
		elevator1 = new JoystickButton(controlStick, 3);
		elevator2 = new JoystickButton(controlStick, 4);

		myRobot = new RobotDrive(0, 1);
		myRobot.setExpiration(0.1);

		topSwitch = new DigitalInput(6);
		bottomSwitch = new DigitalInput(1);
		elevatorMotor = new Victor(2);
		elevator = new Elevator(elevatorMotor, elevator1, elevator2,
				bottomSwitch, topSwitch);

		openSwitch = new DigitalInput(7);
		closeSwitch = new DigitalInput(8);
		pressureSwitch = new DigitalInput(4);
		grabberMotor = new Victor(3);
		grabber = new Grabber(grabberMotor, grabber1, grabber2, openSwitch,
				closeSwitch, pressureSwitch);

	}

	public void autonomous() {
		long start = System.currentTimeMillis();
		long elapsed;
		
		long forwardTime = 1000;
		long turnTime = 2000;
		
		while (isAutonomous() && isEnabled()) { // while in auton
			elapsed = System.currentTimeMillis() - start;
			
			if (elapsed < forwardTime) {
				myRobot.tankDrive(0.5, 0.5);
			} else if (elapsed < forwardTime + turnTime) {
				myRobot.tankDrive(-0.15, 0.15);
			} 
		}
		myRobot.tankDrive(0, 0);
	}

	public void operatorControl() {
		myRobot.setSafetyEnabled(true);

		while (isOperatorControl() && isEnabled()) {
			myRobot.tankDrive(leftStick.getY() * -1, rightStick.getY() * -1); // set
																				// the
																				// tank
																				// drive

			grabber.update();
			elevator.update();

			Timer.delay(0.005); // wait for a motor update time
		}
	}
}

//long start = System.currentTimeMillis();
//long elapsed;
//while (isAutonomous() && isEnabled()) { // while in auton
//	elapsed = System.currentTimeMillis() - start;
//	
//	if (elapsed < 500) {
//		myRobot.tankDrive(0.5, 0.5); // go
//	} else if (elapsed < 2000) { // if first 1 seconds
//		grabber.runAutonomous();
//	} else if (elapsed < 4000) {
//		grabber.stopAutonomous();
//		elevator.runAutonomous();
//	} else if (elapsed < 6000) {
//		elevator.stopAutonomous();
//		myRobot.tankDrive(-0.15, 0.15);
//	} else if (elapsed < 8000) {
//		myRobot.tankDrive(0.5, 0.5); // go
//	} else { // if after 2 seconds
//		myRobot.tankDrive(0, 0); // stop
//	}
//}
//myRobot.tankDrive(0, 0);