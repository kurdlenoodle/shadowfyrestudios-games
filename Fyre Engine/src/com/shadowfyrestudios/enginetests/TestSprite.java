package com.shadowfyrestudios.enginetests;

import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;

import com.shadowfyrestudios.fyreengine.rendering.Shader;
import com.shadowfyrestudios.fyreengine.rendering.Sprite;
import com.shadowfyrestudios.fyreengine.rendering.IMGUI.GUI;
import com.shadowfyrestudios.fyreengine.utils.Input;

public class TestSprite extends Sprite {

	public TestSprite() {
		super(new Vector2f(0, 0), new Vector2f(1,1), 0, "C:\\Users\\Gabykanoodle\\Desktop\\dungeon_tiles-1.png", 
				Shader.loadAndCompile("shaders/Standard.shader"));
	}

	public void update() {
				
		if(Input.getKey(GLFW.GLFW_KEY_W))
			position.y += 0.01f;
		if(Input.getKey(GLFW.GLFW_KEY_A))
			position.x -= 0.01f;
		if(Input.getKey(GLFW.GLFW_KEY_S))
			position.y -= 0.01f;
		if(Input.getKey(GLFW.GLFW_KEY_D))
			position.x += 0.01f;
		if(Input.getKey(GLFW.GLFW_KEY_Q))
			rotation += 0.1f;
		if(Input.getKey(GLFW.GLFW_KEY_E))
			rotation -= 0.1f;
	}

	public void onGUI(){
		GUI.Box(0, 0, 1, 1, "Test", texture);
	}
	
	public void setUniforms() {}
	
}
