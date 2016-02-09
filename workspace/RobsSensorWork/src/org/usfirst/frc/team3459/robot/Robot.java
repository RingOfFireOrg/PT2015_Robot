package org.usfirst.frc.team3459.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Counter;



// this is a comment
public class Robot extends SampleRobot {
	// Joysticks

	double cRange;
	
	RobotDrive myRobot;
	Counter pulseC;
	double pulseLength;
	DigitalOutput triggerPing;
	DigitalOutput indicator;

	public Robot() {

		myRobot = new RobotDrive(0, 1);
		myRobot.setExpiration(0.1);
		pulseC = new Counter(1);
		pulseC.setSemiPeriodMode(true);
		triggerPing = new DigitalOutput(0);
		indicator = new DigitalOutput(9);
	}

	public void autonomous() {
	}

	public void operatorControl() {

		myRobot.setSafetyEnabled(true);

		while (isOperatorControl() && isEnabled()) {
			myRobot.tankDrive(0,0);
	
			cRange = this.getRange();
			
			if (cRange > 75) {
				indicator.set(true);
			} else {
			    indicator.set(false);	
			}
                    
			Timer.delay(0.005); // wait for a motor update time
		}
	}
	private long getRange() {
		double pulseLength = 0;
		
		triggerPing.set(true);
		Timer.delay(0.000015);
		triggerPing.set(false);
		Timer.delay(0.001);
		
		pulseLength = pulseC.getPeriod();
		return (long)(pulseLength*1000000/59);
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