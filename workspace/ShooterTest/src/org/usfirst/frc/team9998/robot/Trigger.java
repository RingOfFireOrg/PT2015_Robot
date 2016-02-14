package org.usfirst.frc.team9998.robot;

import edu.wpi.first.wpilibj.Servo;

public class Trigger {
	private Servo a;
	private long fireTime = 0;
	private double idleAngle = 0;
	private double fireAngle = 0;
	private boolean firing = false;
	private long lastFire = 0;
	
	public Trigger(int n, double idleAngle, double fireAngle, long fireTime) {
		a = new Servo(n);
		this.fireTime = fireTime;
		this.idleAngle = idleAngle;
		this.fireAngle = fireAngle;
	}
	
	public void fire() {
		if(!firing) {
			lastFire = System.currentTimeMillis();
			firing = true;
			a.set(fireAngle);
		}
	}
	
	public void update() {
		if(firing && System.currentTimeMillis()-lastFire > fireTime) {
			firing = false;
			a.set(idleAngle);
		}
	}
}
