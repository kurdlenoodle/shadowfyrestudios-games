package com.shadowfyrestudios.fyreengine.main;

import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import com.shadowfyrestudios.fyreengine.rendering.Sprite;
import com.shadowfyrestudios.fyreengine.utils.Input;

import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;

import org.joml.Matrix4f;
import org.lwjgl.glfw.Callbacks;

public class FyreEngine {
		
	public static ArrayList<Sprite> level = new ArrayList<Sprite>();
	private static long windowID = 0;
	private static InitializationArea initArea = null;
	
	public static void initializeEngine(String windowTitle, int windowWidth, int windowHeight, boolean fullscreen, 
			InitializationArea initializationArea){
		System.out.println("Initializing Engine...");
		initArea = initializationArea;
	 
		GLFWErrorCallback.createPrint(System.err).set();
	 
	 	if(!glfwInit())
	 	{
		 	throw new IllegalStateException("OH SHIT! GLFW fucked up! Aborting...");
	 	}

	 	System.out.println("GLFW is now running.");
	 	
	 	Display.createDisplay(windowTitle, windowWidth, windowHeight, fullscreen);
	 	
	 	System.out.println("Done.");
	 	
	 	run(windowWidth, windowHeight);
	 	
	}
	
	private static void run(int winWidth, int winHeight)
	{
		GL.createCapabilities();
		glClearColor(1, 1, 1, 1);
		glEnable(GL_TEXTURE_2D);
	 	glEnable(GL_BLEND);
	 	glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		initArea.doneInit();
		
		Matrix4f projectionMatrix = new Matrix4f().ortho2D(-winWidth / 2, winWidth / 2, -winHeight / 2, winHeight / 2).scale(64);
		
		while(!glfwWindowShouldClose(windowID)){
			
			update();
			
			Renderer.render(windowID, projectionMatrix);
			
		}
		
		shutdownEngine();
	}
	
	private static void update()
	{
		for(Sprite sprite : level){
			sprite.update();
		}
	}
	
	public static void shutdownEngine()
	{	
		System.out.println("Shutting Down.");
		
		Callbacks.glfwFreeCallbacks(windowID);
		
		Display.closeDisplay();
		
		glfwSetErrorCallback(null).free();
		glfwTerminate();
		
	}
	
	public static void tossWindowID(long id)
	{
		if(windowID == 0){
			windowID = id;
			Input.windowID = id;
		}
	}
}
