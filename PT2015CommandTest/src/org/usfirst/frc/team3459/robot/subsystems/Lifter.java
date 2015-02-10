package org.usfirst.frc.team3459.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Lifter extends Subsystem{

	Victor motor;
	DigitalInput switch_top;
	DigitalInput switch_bottom;
	
	public Lifter(int motorPwm, int switch1IO, int switch2IO) {
		motor = new Victor(motorPwm);
		switch_top = new DigitalInput(switch1IO);
		switch_bottom = new DigitalInput(switch2IO);
	}

	@Override
	protected void initDefaultCommand() {}
	
	public void setUp() {
		motor.set(0.7);
	}
	
	public void setDown() {
		motor.set(-0.7);
	}
	
	public void setStop() {
		motor.set(0);
	}
	
	public boolean atTop() {
		return switch_top.get();
	}
	
	public boolean atBottom() {
		return switch_bottom.get();
	}
}
