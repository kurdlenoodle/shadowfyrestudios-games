package com.shadowfyrestudios.fyreengine.rendering.IMGUI;

import org.joml.Matrix4f;

import com.shadowfyrestudios.fyreengine.rendering.Mesh;
import com.shadowfyrestudios.fyreengine.rendering.Shader;
import com.shadowfyrestudios.fyreengine.rendering.Texture;

public class GUI {
	
	private static final Mesh QUAD = new Mesh(
			
			new float[] {
					
					0, 0,
					1, 0,
					1, 1,
					0, 1
					
			},
			new float[] {
					
					0, 0,
					1, 0,
					1, 1,
					0, 1
					
			},
			new int[] {
					
					2, 3, 0,
					0, 1, 2
					
			}
			
			);
	
	private static final Shader GUI_SHADER = Shader.loadAndCompile("shaders/GUI Shader.shader");
	
	public static void startGUIRender(){
		GUI_SHADER.bind();
	}
	
	public static void Box(float rX, float rY, float rW, float rH, String text, Texture texture){
		
		Matrix4f transform = new Matrix4f().translate(-1, 1, 0).translate(rX, rY, 0).scale(rW, -rH, 0);
		GUI_SHADER.setUniform("engine_transMatrix", transform);
		GUI_SHADER.setUniform("engine_texture", 0);
		texture.bind(0);
		QUAD.render();
		
	}
	
}
