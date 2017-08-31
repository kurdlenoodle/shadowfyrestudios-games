package com.shadowfyrestudios.fyreengine.utils;

import static org.lwjgl.glfw.GLFW.GLFW_TRUE;
import static org.lwjgl.glfw.GLFW.glfwGetKey;

public class Input {

	public static long windowID = 0;
	
	public static boolean getKey(int keyCode) //Use GLFW Key Codes
	{
		return glfwGetKey(windowID, keyCode) == GLFW_TRUE;
	}
	
	
}
