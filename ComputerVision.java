package org.lejos.example;

import lejos.nxt.*;

public class ComputerVision {

		private int[] image;
		private MotorController mc;
		private SonicController sc;
		private int imageSize = 100;
		LCD lcd;
		private double correctionFact = (64/255.0);
		
		public ComputerVision(MotorController mc, SonicController sc)
		{
			this.sc = sc;
			this.mc = mc;
			image = new int[imageSize];
		}
		
		public void scan() throws InterruptedException
		{
			int angle = -100;
			for (int i = 0; i < 100; i++)
			{
				mc.rotateTo(angle);
				angle += 2;
				image[i] = sc.getDist();
				drawColumn((int) (image[i]*correctionFact), i);
			}
			mc.centerEye();
		}
		
		public void printImage()
		{
			// width   ::   height
			
			lcd.clearDisplay();
			//lcd.setPixel(10, 50, 1);
			//lcd.setPixel(11, 51, 1);
			
			for	(int i = 0; i < 100; i++)
			{
				//double correctionFact = 64/255.0;
				//correctionFact *= 1.5;
				double pixelPos = image[i] * correctionFact;
				drawColumn((int)pixelPos, i);
			}
			
		}
		
		private void drawColumn(int height, int pos)
		{
			for	(int i = 0; i < height; i++)
			{
				lcd.setPixel(pos, i, 1);
			}
			//lcd.setPixel(x, y, color)
		}
}
