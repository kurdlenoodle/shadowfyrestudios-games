package com.shadowfyrestudios.fyreengine.rendering;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

public class Texture {

	private static HashMap<String, Texture> textureBank = new HashMap<String, Texture>();
	
	private int id, width, height;
	
	public static Texture createTexture(String filename)
	{
		if(textureBank.containsKey(filename))
		{
			return textureBank.get(filename); 
		}
		
		BufferedImage buff;
		Texture newTex = new Texture();
		try{
			buff = ImageIO.read(new File(filename));
			newTex.width = buff.getWidth();
			newTex.height = buff.getHeight();
			
			int[] rawPixels = new int[newTex.width * newTex.height];
			rawPixels = buff.getRGB(0, 0, newTex.width, newTex.height, null, 0, newTex.width);
			
			ByteBuffer pixelsBuffer = BufferUtils.createByteBuffer(rawPixels.length * 4);
			
			for(int y = 0; y < newTex.height; y++){
				for(int x = 0; x < newTex.width; x++)
				{
					int pixel = rawPixels[y*newTex.width + x];
					pixelsBuffer.put((byte)((pixel >> 16) & 0xFF));
					pixelsBuffer.put((byte)((pixel >> 8) & 0xFF));
					pixelsBuffer.put((byte)(pixel & 0xFF));
					pixelsBuffer.put((byte)((pixel >> 24) & 0xFF));
				}
			}
			
			pixelsBuffer.flip();
			
			newTex.id = GL11.glGenTextures();
			
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, newTex.id);
			GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
			GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
			
			GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, newTex.width, newTex.height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, pixelsBuffer);
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
		textureBank.put(filename, newTex);
		return newTex;
		
	}
	
	public void bind(int sampler)
	{
		if(sampler >= 0 && sampler < 32)
			GL13.glActiveTexture(GL13.GL_TEXTURE0 + sampler);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, id);
	}
	
}
