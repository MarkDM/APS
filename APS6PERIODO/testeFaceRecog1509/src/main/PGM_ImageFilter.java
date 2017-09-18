package main;

import java.lang.*;
import java.io.*;
import java.lang.Math.*;

public class PGM_ImageFilter
{
	String inFilePath,outFilePath;
	boolean printStatus=false;

	//constructor
	public PGM_ImageFilter()
	{
		inFilePath="";
		outFilePath="";
	}

	//get functions
	public String get_inFilePath()
	{
		return(inFilePath);
	}
	
	public String get_outFilePath()
	{
		return(outFilePath);
	}
	
	//set functions
	public void set_inFilePath(String tFilePath)
	{
		inFilePath=tFilePath;
	}
	
	public void set_outFilePath(String tFilePath)
	{
		outFilePath=tFilePath;
	}

	//methods
	public void resize(int wout,int hout)
	{
		PGM imgin=new PGM();
		PGM imgout=new PGM();
	
		if(printStatus==true)
		{
			System.out.print("\nResizing...");
		}
		int r,c,inval,outval;
	
		//read input image
		imgin.setFilePath(inFilePath);
		imgin.readImage();
	
		//set output-image header
		imgout.setFilePath(outFilePath);
		imgout.setType("P5");
		imgout.setComment("#resized image");
		imgout.setDimension(wout,hout);
		imgout.setMaxGray(imgin.getMaxGray());
	
		//resize algorithm (linear)
		double win,hin;
		int xi,ci,yi,ri;
	
		win=imgin.getCols();
		hin=imgin.getRows();
	
		for(r=0;r<imgout.getRows();r++)
		{
			for(c=0;c<imgout.getCols();c++)
			{
				xi=c;
				yi=r;
	
				ci=(int)(xi*((double)win/(double)wout));
				ri=(int)(yi*((double)hin/(double)hout));
				
				inval=imgin.getPixel(ri,ci);
				outval=inval;
	
				imgout.setPixel(yi,xi,outval);
			}
		}
	
		if(printStatus==true)
		{
			System.out.println("done.");
		}
	
		//write output image
		imgout.writeImage();
	}
	
	public void resize_per(double xper,double yper)
	{
		PGM timgin=new PGM();
		timgin.setFilePath(inFilePath);
		timgin.readImage();
	
		int twout=(int)((xper/100.0)*(double)timgin.getCols());
		int thout=(int)((yper/100.0)*(double)timgin.getRows());
		resize(twout,thout);
	}
	
	//edge finding
	public void edge(int tIntensityDifference)
	{
		PGM imgin=new PGM();
		PGM imgout=new PGM();
	
		if(printStatus==true)
		{
			System.out.print("Edgefinding...");
		}
		int r,c,inval,outval;
	
		//read input image
		imgin.setFilePath(inFilePath);
		imgin.readImage();
	
		//set output-image header
		imgout.setFilePath(outFilePath);
		imgout.setType("P5");
		imgout.setComment("#edge image");
		imgout.setDimension(imgin.getCols(),imgin.getRows());
		imgout.setMaxGray(imgin.getMaxGray());
	
		//edgefinding algorithm
		for(r=0;r<imgout.getRows();r++)
		{
			for(c=0;c<imgout.getCols();c++)
			{
				inval=imgin.getPixel(r,c); //get current pixel
	
				int tinval[]=new int[2];
				boolean fval;
				boolean flag1,flag2;
	
				tinval[0]=imgin.getPixel(r,c+1); //get right pixel
				tinval[1]=imgin.getPixel(r+1,c); //get down pixel
	
				flag1=java.lang.Math.abs(inval-tinval[0])>tIntensityDifference?true:false;
				flag2=java.lang.Math.abs(inval-tinval[1])>tIntensityDifference?true:false;
				fval=flag1||flag2;
	
				outval=fval==true?0:255;
	
				imgout.setPixel(r,c,outval);
			}
		}
		if(printStatus==true)
		{
			System.out.println("done.");
		}
	
		//write output image
		imgout.writeImage();
	}
	
	//smoothing (mean filter)
	public void smooth_mean(int tIterations)
	{
		PGM imgin=new PGM();
		PGM imgout=new PGM();
	
		if(printStatus==true)
		{
			System.out.print("Smoothing...");
		}
		int r,c,inval,outval;
	
		//read input image
		imgin.setFilePath(inFilePath);
		imgin.readImage();
	
		//set output-image header
		imgout.setFilePath(outFilePath);
		imgout.setType("P5");
		imgout.setComment("#smoothed image");
		imgout.setDimension(imgin.getCols(),imgin.getRows());
		imgout.setMaxGray(imgin.getMaxGray());
	
		//smoothing algorithm (mean)
		for(int t=1;t<=tIterations;t++)
		{
			for(r=0;r<imgout.getRows();r++)
			{
				for(c=0;c<imgout.getCols();c++)
				{
					inval=imgin.getPixel(r,c); //get current pixel
	
					//get neighbourhood pixel intensity values
					int neighbour[]=new int[8];
					neighbour[0]=imgin.getNeighbor(r,c,Globals.NW);
					neighbour[1]=imgin.getNeighbor(r,c,Globals.W );
					neighbour[2]=imgin.getNeighbor(r,c,Globals.SW);
					neighbour[3]=imgin.getNeighbor(r,c,Globals.S );
					neighbour[4]=imgin.getNeighbor(r,c,Globals.SE);
					neighbour[5]=imgin.getNeighbor(r,c,Globals.E);
					neighbour[6]=imgin.getNeighbor(r,c,Globals.NE);
					neighbour[7]=imgin.getNeighbor(r,c,Globals.N );
	
					//calculate new intensity value
					int tsum=inval;
					for(int k=0;k<8;k++) tsum=tsum+neighbour[k]; //calc sum
					outval=tsum/9; //calc mean
	
					imgout.setPixel(r,c,outval);
				}
			}
	
			//set outputimage as input to next iteration (if not last iteration)
			if(t!=tIterations)
			{
				for(int r1=0;r1<imgin.getRows();r1++)
					for(c=0;c<imgin.getCols();c++)
						imgin.setPixel(r1,c,imgout.getPixel(r1,c));
			}
		}
		if(printStatus==true)
		{
			System.out.println("done.");
		}
	
		//write output image
		imgout.writeImage();
	}
	
	//thresholding
	public void threshold(int tIntensityThreshold)
	{
		PGM imgin=new PGM();
		PGM imgout=new PGM();
	
		if(printStatus==true)
		{
			System.out.print("Thresholding...");
		}
		int r,c,inval,outval;
	
		//read input image
		imgin.setFilePath(inFilePath);
		imgin.readImage();

		//set output-image header
		imgout.setFilePath(outFilePath);
		imgout.setType("P5");
		imgout.setComment("#thresholded image");
		imgout.setDimension(imgin.getCols(),imgin.getRows());
		imgout.setMaxGray(imgin.getMaxGray());
	
		//binary thresholding algorithm
		for(r=0;r<imgout.getRows();r++)
		{
			for(c=0;c<imgout.getCols();c++)
			{
				inval=imgin.getPixel(r,c); //get current pixel
	
				outval=inval>=tIntensityThreshold?0:255;
	
				imgout.setPixel(r,c,outval);
			}
		}
		if(printStatus==true)
		{
			System.out.println("done.");
		}
	
		//write output image
		imgout.writeImage();
	}
	
	//brightness adjustment
	public void brightness(double tBrightnessPercentage)
	{
		PGM imgin=new PGM();
		PGM imgout=new PGM();
	
		if(printStatus==true)
		{
			System.out.print("Adjusting brightness...");
		}
		int r,c,inval,outval;
	
		//read input image
		imgin.setFilePath(inFilePath);
		imgin.readImage();
	
		//set output-image header
		imgout.setFilePath(outFilePath);
		imgout.setType("P5");
		imgout.setComment("#brightness-adjusted image");
		imgout.setDimension(imgin.getCols(),imgin.getRows());
		imgout.setMaxGray(imgin.getMaxGray());
	
		//build brightness-transform look-up table
		double tPercentage=tBrightnessPercentage/100.0;
		int tBrightnessIntensity=(int)(tPercentage*imgin.getMaxGray());
		int btransform[]=new int[256];
		for(int i=0;i<256;i++)
		{
			btransform[i]=i+tBrightnessIntensity;
			if(btransform[i]>255) btransform[i]=255;
			if(btransform[i]<0) btransform[i]=0;
		}
	
		//brightness adjustment algorithm
		for(r=0;r<imgout.getRows();r++)
		{
			for(c=0;c<imgout.getCols();c++)
			{
				inval=imgin.getPixel(r,c); //get current pixel
	
				outval=btransform[inval];
	
				imgout.setPixel(r,c,outval);
			}
		}
		if(printStatus==true)
		{
			System.out.println("done.");
		}
	
		//write output image
		imgout.writeImage();
	}
	
	//contrast adjustment
	public void contrast(double tContrastPercentage)
	{
		PGM imgin=new PGM();
		PGM imgout=new PGM();
	
		if(printStatus==true)
		{
			System.out.print("Adjusting contrast...");
		}
		int r,c,inval,outval;
	
		//read input image
		imgin.setFilePath(inFilePath);
		imgin.readImage();
	
		//set output-image header
		imgout.setFilePath(outFilePath);
		imgout.setType("P5");
		imgout.setComment("#contrast-adjusted image");
		imgout.setDimension(imgin.getCols(),imgin.getRows());
		imgout.setMaxGray(imgin.getMaxGray());
	
		//build contrast-transform look-up table
		double tPercentage=tContrastPercentage/100.0;
		int tContrastIntensity=(int)(tPercentage*imgin.getMaxGray());
		int ctransform[]=new int[256];
		double contrast=1.0-((double)tContrastPercentage/100.0);
		for(int i=0;i<256;i++)
		{
			int tan1=(int)(128.0f+128.0f*java.lang.Math.tan(contrast));
			int tan2=(int)(128.0f-128.0f*java.lang.Math.tan(contrast));
	
			if(i<tan1&&i>tan2)
				ctransform[i]=(int)((i-128)/java.lang.Math.tan(contrast)+128);
			else if(i>=tan1)
				ctransform[i]=255;
			else
				ctransform[i]=0;
		}
	
		//contrast enhancement algorithm
		for(r=0;r<imgout.getRows();r++)
		{
			for(c=0;c<imgout.getCols();c++)
			{
				inval=imgin.getPixel(r,c); //get current pixel
	
				outval=ctransform[inval];
	
				imgout.setPixel(r,c,outval);
			}
		}
		if(printStatus==true)
		{
			System.out.println("done.");
		}
	
		//write output image
		imgout.writeImage();
	}
	
	//image rotation
	public void rotate(double tRotationAngle)
	{
		PGM imgin=new PGM();
		PGM imgout=new PGM();
	
		if(printStatus==true)
		{
			System.out.print("\nRotating...");
		}
		int r,c,inval,outval;
	
		//read input image
		imgin.setFilePath(inFilePath);
		imgin.readImage();
	
		//set output-image header
		imgout.setFilePath(outFilePath);
		imgout.setType("P5");
		imgout.setComment("#rotated image");
		imgout.setDimension(imgin.getCols(),imgin.getRows());
		imgout.setMaxGray(imgin.getMaxGray());
	
		//rotating algorithm
		double tcosval=java.lang.Math.cos(tRotationAngle),tsinval=java.lang.Math.sin(tRotationAngle);
		int x0=imgin.getCols()/2,y0=imgin.getRows()/2;
		for(r=0;r<imgout.getRows();r++)
		{
			for(c=0;c<imgout.getCols();c++)
			{
				inval=imgin.getPixel(r,c); //get current pixel
	
				int a=c-x0,b=r-y0;
				int tx=(int)(a*tcosval-b*tsinval+x0);
				int ty=(int)(a*tsinval+b*tcosval+y0);
				outval=imgin.getPixel(ty,tx);
				
				imgout.setPixel(r,c,outval);
			}
		}
		if(printStatus==true)
		{
			System.out.println("done.");
		}
	
		//write output image
		imgout.writeImage();
	}
	
	void rotate_deg(double tdegree)
	{
		double tradians;
		tradians=(tdegree/360)*(2*Globals.PI);
		rotate(tradians);
	}
}
