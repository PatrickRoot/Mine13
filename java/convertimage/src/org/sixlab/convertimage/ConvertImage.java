package org.sixlab.convertimage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

public class ConvertImage
{
	public static void main(String[] args) throws Exception
	{
		long beginTime = System.currentTimeMillis();
		
		// 要转换的RGB图像位于rgb目录
		File rgbroot = new File("rgb");
		File[] subfile = rgbroot.listFiles();
		
		for (File file : subfile)
		{
			// 转换后的灰度图像和原来图像同名，位于gray目录下
			String filename = file.getName();
			File newFile = new File("gray/" + filename);
			
			// 读取图像
			BufferedImage inImage = ImageIO.read(file);
			
			// 遍历图片每一个点，对其进行转换
			for (int h = 0; h < inImage.getHeight(); h++)
			{
				for (int w = 0; w < inImage.getWidth(); w++)
				{
					// 获得当前点的RGB值
					int rgb = inImage.getRGB(w, h);
					
					// 获得RGB三个分量的值，并将其乘以灰度转换的系数
					int newr = (int) (((rgb & 0xff0000) >> 16) * 0.299f);
					int newg = (int) (((rgb & 0xff00) >> 8) * 0.587f);
					int newb = (int) ((rgb & 0xff) * 0.114f);
					
					// 得到灰度值
					int newrgb = (int) ((newr + newg + newb));
					
					// 计算RGB三个分量的新的值
					newr = (newrgb) << 16;
					newg = (newrgb) << 8;
					newb = (newrgb);
					
					// setRGB要用到的rgb值
					int finalrgb = newg + newr + newb;
					
					// setRGB设置新的灰度图像的值
					inImage.setRGB(w, h, finalrgb);
				}
			}
			
			// 将修改后的文件保存
			ImageIO.write(inImage, "jpg", newFile);
		}
		
		long endTime = System.currentTimeMillis();
		long cenTime = endTime - beginTime;
		System.out.println("用时："+cenTime+"ms");
	}
}
