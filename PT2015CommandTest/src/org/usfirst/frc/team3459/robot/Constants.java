package org.usfirst.frc.team3459.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class Constants {
	//PWM values
	public static final int PWM_LIFT = 0;
	public static final int PWM_GRABBER = 1;
	public static final int PWM_DRIVE_LEFT = 2;
	public static final int PWM_DRIVE_RIGHT = 3;
	
	//Digital IO
	public static final int IO_LIFTER_TOP = 0;
	public static final int IO_LIFTER_BOTTOM = 1;
	
	
	//Control Things
	//Joysticks
	public static final int J_CONTROL_ID = 3;
	
	//Buttons
	public static final int B_LIFT_UP = 33;
}
