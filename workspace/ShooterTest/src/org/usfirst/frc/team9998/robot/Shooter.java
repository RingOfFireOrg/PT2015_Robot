package org.usfirst.frc.team9998.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DriverStation;

public class Shooter {
	public static final long TIME_THRESHOLD = 250;
	public static final double VOLT_INCREMENT = 1;
	
	public static final double ANGLE_IDLE = 0;
	public static final double ANGLE_FIRE = 1;
	public static final long TIME_FIRE = 1;

	public enum Mode {SHOOT,PICKUP,DISABLE}
	//***************************************************************************************
	
	CANTalon motor1, motor2;
	Trigger trigger;
	
	long lastIncrease;
	long lastDecrease;
	
	double voltage = 9;
	
	Mode state = Mode.DISABLE;
	
	public Shooter(int m1, int m2, int s1) {
		motor1 = new CANTalon(m1);
		motor2 = new CANTalon(m2);
		trigger = new Trigger(s1,ANGLE_IDLE,ANGLE_FIRE,TIME_FIRE);
		
//		motor1.changeControlMode(CANTalon.ControlMode.Voltage);
//		motor2.changeControlMode(CANTalon.ControlMode.Voltage);
		
		lastIncrease = System.currentTimeMillis();
		lastDecrease = lastIncrease;
	}
	
	public void increaseVoltage() {
		if(System.currentTimeMillis() - lastIncrease > TIME_THRESHOLD) {
			voltage += VOLT_INCREMENT;
			lastIncrease = System.currentTimeMillis();
		}
	}
	
	public void decreaseVoltage() {
		if(System.currentTimeMillis() - lastDecrease > TIME_THRESHOLD) {
			voltage -= VOLT_INCREMENT;
			lastDecrease = System.currentTimeMillis();
		}
	}
	
	public void update() {
		trigger.update();
		switch(state) {
		case DISABLE:
			setWheels(0);
			break;
		case PICKUP:
			setWheels(0);
			break;
		case SHOOT:
			setWheels(voltage);
			DriverStation.reportError("Process Shoot " + voltage + "\n", false);
			break;
		}
	}
	
	public void setWheels(double val) {
		motor1.set(val);
		motor2.set(val);
	}
	
	public void setShoot() {
		state = Mode.SHOOT;
		DriverStation.reportError("Enter Shoot\n", false);
	}
	
	public void setPickup() {
		state = Mode.PICKUP;
	}
	
	public void setDisable() {
		state = Mode.DISABLE;
	}
	
	public void fire() {
		if(state == Mode.SHOOT)
			trigger.fire();
	}
}