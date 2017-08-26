package org.lejos.example;

import lejos.nxt.*;

public class MotorController {
	private NXTRegulatedMotor left;
	private NXTRegulatedMotor right;
	private NXTRegulatedMotor eye;
	
	private int currentEyeAngle = 0;
	
	public MotorController()
	{
		left = new NXTRegulatedMotor(MotorPort.A);
		right = new NXTRegulatedMotor(MotorPort.B);
		eye = new NXTRegulatedMotor(MotorPort.C);
	}
	
	private int counter = 0;
	public void printEye()
	{
		System.out.println("eye: "+eye.getPosition()+" :: "+counter++);
	}
	
	public int eyePos()
	{
		return eye.getPosition();
	}
	
	public void rotateTo(int input)
	{
		eye.rotateTo(input);
	}
	
	public void centerEye()
	{
		eye.rotateTo(0);
	}
	
	public void rotateEyeLeft(int angle)
	{
		eye.rotate(angle);
	}
	
	public void rotateEyeRight(int angle)
	{
		eye.rotate(-angle);
	}
	
	public void reverse(int speed) throws InterruptedException
	{
		left.setSpeed(speed);
		right.setSpeed(speed);
		
		left.backward();
		right.backward();
		
		Thread.sleep(1000);
	}
	
	public void forward(int speed) throws InterruptedException
	{
		left.setSpeed((int) ((double) speed * 1.0));
		right.setSpeed(speed);
		
		left.forward();
		right.forward();
		
		Thread.sleep(500);
	}
	
	public void turnSmooth(int degrees)
	{
		if (degrees < 0)  // right turn;
		{
			if (degrees < -30) right.setSpeed(0);
			else right.setSpeed(60+2*degrees);
			
			left.setSpeed(80);
		}
		else 
		{
			if (degrees > 30) left.setSpeed(0);
			else left.setSpeed(60-2*degrees);
			right.setSpeed(80);
		}
	}
	
	public void turnLeft(int degrees, int speed) throws InterruptedException
	{
		left.setSpeed(50);
		right.setSpeed(50);
		
		left.backward();
		right.forward();
		
		// 3500 == 90 astetta
		// 40 == 1 aste
		
		Thread.sleep(40*degrees);
		stop();
	}
	
	public void turnRight(int degrees, int speed) throws InterruptedException
	{
		left.setSpeed(50);
		right.setSpeed(50);
		
		left.forward();
		right.backward();
		
		Thread.sleep(40*degrees);
		stop();
	}
	
	public void stop()
	{
		left.setSpeed(0);
		right.setSpeed(0);
	}
	
}

