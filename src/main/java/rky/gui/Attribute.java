package rky.gui;

import java.awt.Color;

import rky.simpleGamePlatform.RigidRectPiece;

public class Attribute extends RigidRectPiece 
{
	Material material = new Material();
	
	public Material getMaterial(){
		return material; 
	}
	
	public int getValue()
	{
		int returnValue = 0;
		returnValue = (int) ((material.red*100/255));
		return returnValue;
	}
		
	public class Material
	{
		
		Color colorScheme;
		int red = 0 , blue= 255,green = 255;
		int step = 8;
		
		public Material(){
			colorScheme = new Color(red,blue,green);
			Attribute.this.setColor(colorScheme);
		}

		public  void setDefaultcolor(Color c){
			colorScheme = c;
		}

		public  void setStep(int newStep){
			step = newStep;
		}
		
		public void setValue(double colorValue)
		{
			if(colorValue > 0 && colorValue < 256){
				red = (int) colorValue;
				blue = (int)(255 - colorValue);
				green = (int)(255 - colorValue);
				colorScheme = new Color(red,blue,green);
				Attribute.this.setColor(colorScheme);
			}
		}

		public  void increaseGradient(){
			if(red + step < 256){
				red = red + step;
				blue = blue - step;
				green = green - step;
				colorScheme = new Color(red,blue,green);
				Attribute.this.setColor(colorScheme);
			}
		}

		public  void reduceGradient(){
			
			if(red > 0){
				red = red - step;
				blue = blue + step;
				green = green + step;
				colorScheme = new Color(red,blue,green);
				Attribute.this.setColor(colorScheme);
			}
		}
	}
}
