package com.shadowfyrestudios.fyreengine.rendering;

import org.joml.Vector2f;

public abstract class Sprite {

	public Vector2f position = new Vector2f(0, 0), scale = new Vector2f(1,1);
	public float rotation = 0f;
	public Texture texture = null;
	public Shader shader;
	
	public Sprite(Vector2f position, Vector2f scale, float rotation, String texture, Shader shader) {
		
		this.position = position;
		this.scale = scale;
		this.rotation = rotation;
		this.texture = Texture.createTexture(texture);
		this.shader = shader;
		
	}

	public abstract void update();
	public abstract void setUniforms();
	public abstract void onGUI();
	
}
