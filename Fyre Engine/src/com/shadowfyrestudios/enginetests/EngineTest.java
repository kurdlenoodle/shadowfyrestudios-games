package com.shadowfyrestudios.enginetests;

import org.joml.Vector2f;

import com.shadowfyrestudios.fyreengine.main.FyreEngine;
import com.shadowfyrestudios.fyreengine.main.InitializationArea;
import com.shadowfyrestudios.fyreengine.rendering.Shader;
import com.shadwfyrestudios.fyreengine.standardlib.StandardSprite;

public class EngineTest implements Runnable, InitializationArea{
	
	public static void main(String[] args) {
		(new Thread(new EngineTest())).start();
	}

	public void run() {
		FyreEngine.initializeEngine("Engine Test", 720, 480, false, this);
	}

	public void doneInit() {
		FyreEngine.level.add(new TestSprite());
		FyreEngine.level.add(new StandardSprite(new Vector2f(0, 0), new Vector2f(1,1), 0, 
				"C:\\Users\\Gabykanoodle\\Desktop\\dungeon_tiles-1.png", Shader.loadAndCompile("shaders/Standard.shader")));
	}

}
