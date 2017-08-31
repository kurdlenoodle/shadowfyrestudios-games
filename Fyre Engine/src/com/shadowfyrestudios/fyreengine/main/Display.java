package com.shadowfyrestudios.fyreengine.main;

import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.system.MemoryUtil;

public class Display {

	private static long windowID;
	
	public static void createDisplay(String title, int width, int height, boolean isFullScreen)
	{
		glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
		long fullscreen = 0;
		if(isFullScreen)
			fullscreen = glfwGetPrimaryMonitor();
		windowID = glfwCreateWindow(width, height, title, fullscreen, 0);
		
		if(windowID == MemoryUtil.NULL){
			throw new IllegalStateException("OpenGL is unintelligent and couldn't create a window. Congrats.");
		}
		
		glfwMakeContextCurrent(windowID);
		glfwSwapInterval(1);
		glfwShowWindow(windowID);
		FyreEngine.tossWindowID(windowID);
		
	}
	
	public static void closeDisplay()
	{
		glfwDestroyWindow(windowID);
	}
	
}
