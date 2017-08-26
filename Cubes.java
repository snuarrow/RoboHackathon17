package org.lejos.example;

public class Cubes {

	private MotorController mc;
	private LightSensorController lsc;
	private SonicController sc;
	
	private int criticalDistance = 10;
	private int driveSpeed = 100;
	
	public Cubes()
	{
		mc = new MotorController();
		lsc = new LightSensorController();
		sc = new SonicController();
	}
	
	public void followRightWall() throws InterruptedException
	{
		mc.forward(driveSpeed);
		int frontD = 255;
		int rightD = 255;
		
		//mc.centerEye();
		//frontD = sc.getDist();
		
		mc.rotateTo(-80);
		rightD = sc.getDist();
		
		//if (frontD < 20) 
		//{
		//	mc.reverse(100);
		//	mc.turnLeft(30, 50);
		//	return;
		//}
		
		
		
		int d = pidAngle(rightD);
		
		System.out.println(rightD);
		
		mc.turnSmooth(d); 
		
	}
	
	public int pidAngle(int distance)
	{
		int d = criticalDistance - distance;
		
		if (d > 30) return 30;
		else return d;
	}
	
	public void scan() throws InterruptedException
	{
		
		if (mc.eyePos() < 80)
		{
			int j = 0;
			for	(int i = -90; i <= 90; i += 20)
			{
				
				
				mc.rotateTo(i);
				if (sc.getDist() < criticalDistance)
				{
					if (i < 5) mc.turnLeft(15, 50);
					else if (i > 5) mc.turnRight(15, 50);
					j++;
				}
			}
		} else
		{
			int j = 9;
			for	(int i = 90; i >= -90; i -= 20)
			{
				mc.rotateTo(i);
				
				if (sc.getDist() < criticalDistance)
				{
					if (i < 5) mc.turnLeft(15, 50);
					else if (i > 5) mc.turnRight(15, 50);
					j--;
				}
			}
		}
		
		//return a;
	}
	
	public void avoidWalls2() throws InterruptedException
	{
		mc.stop();
	    scan();
		mc.forward(100);
		
	}
	
	public void avoidWalls() throws InterruptedException
	{
		boolean frontBlock = false;
		boolean leftBlock = false;
		boolean rightBlock = false;
		int leftD = 255;
		int rightD = 255;
		int frontD = 255;
		
		mc.stop();
		
		mc.centerEye();
		if ((frontD = sc.getDist()) < 20) frontBlock = true;
		mc.rotateEyeRight(90);
		if ((rightD = sc.getDist()) < 20) rightBlock = true;
		mc.centerEye();
		mc.rotateEyeLeft(90);
		if ((leftD = sc.getDist()) < 20) leftBlock = true;
		mc.centerEye();
		
		if (leftBlock && frontBlock && rightBlock)
		{
			for (int i = 0; i < 5; i++) mc.reverse(50);
			return;
		}
		
		if (leftBlock && frontBlock)
		{
			mc.turnRight(30, 50);
			return;
		}
		
		if (rightBlock && frontBlock)
		{
			mc.turnLeft(30, 50);
			return;
		}
		
		if (leftBlock)
		{
			mc.turnRight(15, 50);
			return;
		}
		
		if (rightBlock)
		{
			mc.turnLeft(15, 50);
			return;
		}
		
		if (frontBlock)
		{
			if (leftD < rightD) mc.turnRight(30, 50);
			else mc.turnLeft(30, 50);
		}
		
		mc.forward(100);
		
	}
}

