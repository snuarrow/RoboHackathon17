package org.lejos.example;

import lejos.nxt.*;

public class LightSensorController {
	private LightSensor ls;
	private MovingPointAverageFilter filter;
	
	public LightSensorController()
	{
		ls = new LightSensor(SensorPort.S1);
	}
	
	public boolean isBlack()
	{
		if ( ls.getNormalizedLightValue() < 420) return true;
		return false;
	}
}
