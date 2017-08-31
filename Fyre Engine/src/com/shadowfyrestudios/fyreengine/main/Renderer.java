package com.shadowfyrestudios.fyreengine.main;

import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

import org.joml.Matrix4f;
import org.joml.Vector2f;

import com.shadowfyrestudios.fyreengine.rendering.Camera;
import com.shadowfyrestudios.fyreengine.rendering.Mesh;
import com.shadowfyrestudios.fyreengine.rendering.Sprite;
import com.shadowfyrestudios.fyreengine.rendering.IMGUI.GUI;

public class Renderer {

	private static Mesh quad = new Mesh(
			new float[]{
					-0.5f, 0.5f,
					0.5f, 0.5f,
					0.5f, -0.5f,
					-0.5f, -0.5f
			},
			
			new float[]{
					
					0, 0,
					1, 0,
					1, 1,
					0, 1					
			},
			
			new int[]{
					
					0, 1, 2,
					2, 3, 0
					
			}
	);

	
	public static void render(long windowID, Matrix4f projectionMatrix)
	{
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glfwPollEvents();
		
		for(Sprite sprite : FyreEngine.level){
			sprite.shader.bind();
			sprite.setUniforms();
			sprite.shader.setUniform("engine_transMatrix", createTransformationMatrix(sprite.position, sprite.scale, sprite.rotation));
			sprite.shader.setUniform("engine_projMatrix", projectionMatrix);
			sprite.shader.setUniform("engine_texture", 0);
			sprite.texture.bind(0);
			quad.render();
		}
		
		renderGUI();
		
		glfwSwapBuffers(windowID);
	}

	private static void renderGUI(){
		GUI.startGUIRender();
		
		for(Sprite sprite : FyreEngine.level){
			sprite.onGUI();
		}
		
	}
	
	public static Matrix4f createTransformationMatrix(Vector2f position, Vector2f scale, float rotation){
		Matrix4f matrix = new Matrix4f().translate(position.x - Camera.position.x, position.y - Camera.position.y, 0)
				.scale(scale.x / Camera.zoom, scale.y / Camera.zoom, 1).rotate(rotation, 0, 0, 1);
		return matrix;
	}
	
}
