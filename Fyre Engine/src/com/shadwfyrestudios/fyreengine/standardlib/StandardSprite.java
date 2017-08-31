package com.shadwfyrestudios.fyreengine.standardlib;

import org.joml.Vector2f;

import com.shadowfyrestudios.fyreengine.rendering.Shader;
import com.shadowfyrestudios.fyreengine.rendering.Sprite;

public class StandardSprite extends Sprite {

	public StandardSprite(Vector2f position, Vector2f scale, float rotation, String texture, Shader shader) {
		super(position, scale, rotation, texture, shader);
	}

	public void update() {}
	public void setUniforms() {}
	public void onGUI(){}

}
