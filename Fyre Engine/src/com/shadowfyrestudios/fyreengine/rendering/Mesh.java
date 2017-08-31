package com.shadowfyrestudios.fyreengine.rendering;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;

public class Mesh {

	private int drawCount, vertexBufferID, uvBufferID, indicesBufferID;
	
	public Mesh(float[] vertices, float[] uvs, int[] indices){
		drawCount = indices.length;
		
		vertexBufferID = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vertexBufferID);
		glBufferData(GL_ARRAY_BUFFER, createFloatBuffer(vertices), GL_STATIC_DRAW);
		
		uvBufferID = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, uvBufferID);
		glBufferData(GL_ARRAY_BUFFER, createFloatBuffer(uvs), GL_STATIC_DRAW);
		
		indicesBufferID = glGenBuffers();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indicesBufferID);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, createIntBuffer(indices), GL_STATIC_DRAW);
		
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
	}
	
	private FloatBuffer createFloatBuffer(float[] buffer)
	{
		FloatBuffer fbuffer = BufferUtils.createFloatBuffer(buffer.length);
		fbuffer.put(buffer);
		fbuffer.flip();
		return fbuffer;
	}
	
	private IntBuffer createIntBuffer(int[] buffer){
		IntBuffer ibuffer = BufferUtils.createIntBuffer(buffer.length);
		ibuffer.put(buffer);
		ibuffer.flip();
		return ibuffer;
	}
	
	public void render()
	{
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		
		glBindBuffer(GL_ARRAY_BUFFER, vertexBufferID);
		glVertexAttribPointer(0, 2, GL_FLOAT, false, 0, 0);
		
		glBindBuffer(GL_ARRAY_BUFFER, uvBufferID);
		glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indicesBufferID);
		
		glDrawElements(GL_TRIANGLES, drawCount, GL_UNSIGNED_INT, 0);
		
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		
		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(1);
	
	}
	
}
