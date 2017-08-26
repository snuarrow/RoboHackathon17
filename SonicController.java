package org.lejos.example;

import lejos.nxt.*;

public class SonicController {
	
	private UltrasonicSensor us;
	
	public SonicController()
	{
		us = new UltrasonicSensor(SensorPort.S2);
		//us.setMode(1); // sets to mode "ping"
	}
	
	public int getDist() throws InterruptedException
	{
		//us.ping();
		int[] results = new int[8];
		
		for (int i = 0; i < 8; i++)
		{
			results[i] = us.getDistance();
		}
		int largest = 0;
		int smallest = 255;
		
		int largestIndex = 0;
		int smallestIndex = 0;
		
		for (int i = 0; i < 8; i++)
		{
			if (results[i] > largest)
			{
				largest = results[i];
				largestIndex = i;
			}
			
			if (results[i] < smallest)
			{
				smallest = results[i];
				smallestIndex = i;
			}
		}
		
		int sum = 0;
		for	(int i = 0; i < 8; i++)
		{
			if (i != smallestIndex && i != largestIndex)
			sum += results[i];
		}
		
		return sum / 6;
	}
}

