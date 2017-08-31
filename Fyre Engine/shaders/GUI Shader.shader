#vert

attribute vec2 engine_verts;
attribute vec2 engine_uvs;

uniform mat4 engine_transMatrix;

varying vec2 uvs;

void main()
{
	
	uvs = engine_uvs;
	gl_Position = engine_transMatrix * vec4(engine_verts, 0, 1);
	
}

#frag

uniform sampler2D engine_texture;

varying vec2 uvs;

void main()
{

	vec4 colour = texture2D(engine_texture, uvs);

	gl_FragColor = colour;

}