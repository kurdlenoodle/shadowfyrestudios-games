package com.shadowfyrestudios.fyreengine.rendering;

import static org.lwjgl.opengl.GL20.*;

import java.nio.FloatBuffer;

import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;

import com.shadowfyrestudios.fyreengine.utils.FileManager;

public class Shader {

	public static Shader loadAndCompile(String path){
		
		String uncompiledShaderCode = FileManager.readFileToEnd(path);
		
		String[] uncompiledShaders = uncompiledShaderCode.split("#");
		
		String vertexCode = "";
		String fragmentCode = "";
		
		for(String code : uncompiledShaders){
			
			if(code.startsWith("vert")){
				//In future more will be needed here as features are added
				vertexCode = "//" + code;
			}else if(code.startsWith("frag")){
				//In future more will be needed here as features are added
				fragmentCode = "//" + code;
			}
			
		}
				
		return createShader(vertexCode, fragmentCode);
	}
	
	public static Shader loadAndCompile(String vertexPath, String fragmentPath){
		//Temporary. This will not work when more features are added to the Shader lang.
		return loadRaw(vertexPath, fragmentPath);
	}
	
	public static Shader loadRaw(String vertexPath, String fragmentPath)
	{
		return createShader(FileManager.readFileToEnd(vertexPath), FileManager.readFileToEnd(fragmentPath));
	}
	
	private static Shader createShader(String vertexCode, String fragmentCode)
	{
		Shader shader = new Shader();
		shader.programID = glCreateProgram();
		
		shader.vertexShaderID = glCreateShader(GL_VERTEX_SHADER);
		glShaderSource(shader.vertexShaderID, vertexCode);
		glCompileShader(shader.vertexShaderID);
		if(glGetShaderi(shader.vertexShaderID, GL_COMPILE_STATUS) != 1){
			System.err.println("OpenGL encountered an error trying to compile Shader:\n" + glGetShaderInfoLog(shader.vertexShaderID));
			return null;
		}
		
		shader.fragmentShaderID = glCreateShader(GL_FRAGMENT_SHADER);
		glShaderSource(shader.fragmentShaderID, fragmentCode);
		glCompileShader(shader.fragmentShaderID);
		if(glGetShaderi(shader.fragmentShaderID, GL_COMPILE_STATUS) != 1){
			System.err.println("OpenGL encountered an error trying to compile Shader:\n" + glGetShaderInfoLog(shader.vertexShaderID));
			return null;
		}
		
		glAttachShader(shader.programID, shader.vertexShaderID);
		glAttachShader(shader.programID, shader.fragmentShaderID);
		
		glBindAttribLocation(shader.programID, 0, "engine_verts");
		glBindAttribLocation(shader.programID, 1, "engine_uvs");
		
		glLinkProgram(shader.programID);
		if(glGetProgrami(shader.programID, GL_LINK_STATUS) != 1){
			System.err.println("OpenGL encountered an error trying to link a Shader:\n" + glGetProgramInfoLog(shader.programID));
			return null;
		}
		
		glValidateProgram(shader.programID);
		if(glGetProgrami(shader.programID, GL_VALIDATE_STATUS) != 1){
			System.err.println("OpenGL encountered an error trying to link a Shader:\n" + glGetProgramInfoLog(shader.programID));
			return null;
		}
		
		return shader;
	}
	
	private int programID, vertexShaderID, fragmentShaderID;
	
	public void bind()
	{
		glUseProgram(programID);
	}
	
	public void setUniform(String name, int value)
	{
		int location = glGetUniformLocation(programID, name);
		if(location == -1){
			return;
		}
		
		glUniform1i(location, value);
		
	}
	
	public void setUniform(String name, Matrix4f value)
	{
		int location = glGetUniformLocation(programID, name);
		if(location == -1){
			return;
		}
		
		FloatBuffer buffer = BufferUtils.createFloatBuffer(16);
		value.get(buffer);
		glUniformMatrix4fv(location, false, buffer);
		
	}
	
}
