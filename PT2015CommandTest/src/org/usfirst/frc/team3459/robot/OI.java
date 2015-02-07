package org.usfirst.frc.team3459.robot;

import org.usfirst.frc.team3459.robot.commands.LifterUp;
import org.usfirst.frc.team3459.robot.subsystems.Lifter;
import org.usfirst.frc.team3459.robot.triggers.MyButton;

import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	Joystick controlStick;
	MyButton goUpButton;
	Robot myRobot;
	
	public OI(Lifter lifter) {
		controlStick = new Joystick(Constants.J_CONTROL_ID);
		goUpButton = new MyButton(controlStick, Constants.B_LIFT_UP);
		myRobot = new Robot();
		
		goUpButton.whenPressed(new LifterUp(lifter));
	}
}