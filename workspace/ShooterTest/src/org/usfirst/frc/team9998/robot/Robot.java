package org.usfirst.frc.team9998.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class Robot extends SampleRobot {
	public static final int SHOOTER_M1 = 10;
	public static final int SHOOTER_M2 = 11;
	public static final int SHOOTER_S1 = 3;

	Shooter shooter;
	Joystick shootStick;
	JoystickButton shootIncreaseVButton;
	JoystickButton shootDecreaseVButton;
	JoystickButton shootFireButton;
	JoystickButton shootDisableButton;
	JoystickButton shootEnableButton;

	RobotDrive drive;
	Joystick leftStick;
	Joystick rightStick;

	DoubleSolenoid tilt;
	JoystickButton tiltUpButton;
	JoystickButton tiltDownButton;
	JoystickButton tiltOffButton;

	public Robot() {
		shooter = new Shooter(SHOOTER_M1, SHOOTER_M2, SHOOTER_S1);
		shootStick = new Joystick(3);

		shootIncreaseVButton = new JoystickButton(shootStick, 5);
		shootDecreaseVButton = new JoystickButton(shootStick, 6);
		shootFireButton = new JoystickButton(shootStick, 1);
		shootDisableButton = new JoystickButton(shootStick, 4);
		shootEnableButton = new JoystickButton(shootStick, 3);

		drive = new RobotDrive(0, 1);
		leftStick = new Joystick(1);
		rightStick = new Joystick(2);

		tilt = new DoubleSolenoid(0, 1);	//solenoid calibration
		tiltUpButton = new JoystickButton(shootStick, 8);
		tiltDownButton = new JoystickButton(shootStick, 7);
		tiltOffButton = new JoystickButton(shootStick, 12);
	}

	public void autonomous() {
	}

	public void operatorControl() {
		while (isOperatorControl() && isEnabled()) {
			updateShooter();
			updateDrive();
			updateTilt();

			Timer.delay(0.005); // wait for a motor update time
		}
	}

	public void test() {
	}

	public void updateShooter() {
		// Voltage Control
		if (shootIncreaseVButton.get()) {
			if (shootDecreaseVButton.get()) {
				System.out.println("Shooter: decreaseV/increaseV simultaneous press");
			} else {
				shooter.increaseSpeed();
			}
		} else if (shootDecreaseVButton.get()) {
			shooter.decreaseSpeed();
		}

		// State Control
		if (shootEnableButton.get()) {
			if (shootDisableButton.get()) {
				System.out.println("Shooter: disable/enable simultaneous press");
			} else {
				shooter.enableShoot();
			}
		} else if (shootDisableButton.get()) {
			shooter.disableShoot();
		}

		// Firing
		if (shootFireButton.get())
			shooter.fire();

		shooter.update();
	}

	public void updateDrive() {
		drive.tankDrive(-leftStick.getY(), -rightStick.getY());
	}

	public void updateTilt() {
		boolean tiltUp = tiltUpButton.get();
		boolean tiltDown = tiltDownButton.get();
		boolean tiltOff = tiltOffButton.get();
		int buttonsPressed = (tiltUp ? 1 : 0) + (tiltDown ? 1 : 0)
				+ (tiltOff ? 1 : 0);
		
		if (buttonsPressed > 1) {
			return;
		}
		
		if (tiltUp) {
			if(tilt.get() != DoubleSolenoid.Value.kForward)
				tilt.set(DoubleSolenoid.Value.kForward);
			return;
		}
		if (tiltDown) {
			if(tilt.get() != DoubleSolenoid.Value.kReverse)
				tilt.set(DoubleSolenoid.Value.kReverse);
			return;
		}
		if (tiltOff) {
			if(tilt.get() != DoubleSolenoid.Value.kOff)
				tilt.set(DoubleSolenoid.Value.kOff);
			return;
		}
	}
}