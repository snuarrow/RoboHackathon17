package org.lejos.example;

import lejos.nxt.*;

public class HelloWorld {

	//private static NXTRegulatedMotor m1;
	//private static NXTRegulatedMotor m2;
	//private static LightSensorController lsc;
	private static MotorController mc;
	//private static Labyrinth l;
	private static SonicController sc;
	private static ComputerVision cv;
	
	
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Hello World");
		
		sc = new SonicController();
		mc = new MotorController();
		cv = new ComputerVision(mc, sc);
		//Cubes c = new Cubes();
		
		cv.scan();
		//cv.printImage();
		//while (true)
		//{
			//mc.forward(50);
			//System.out.println(sc.getDist());
			//Thread.sleep(500);
		//}
		Button.waitForPress();
		
		//mc.stop();
	}
}

