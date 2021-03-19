attribute mediump vec4 a_position;
attribute mediump vec4 a_color;
attribute mediump vec4 a_normal;
attribute mediump vec2 a_uv;
varying mediump vec4 v_color;
varying mediump vec2 v_uv;
uniform highp mat4 u_projectionMatrix;
uniform highp mat4 u_viewMatrix;
uniform highp mat4 u_modelMatrix;
uniform highp mat4 u_transformMatrix;
void main() {
    v_color = a_color;
    v_uv = a_uv;
    vec4 ret = u_projectionMatrix * u_viewMatrix * u_modelMatrix * a_position;
    gl_Position = ret;
}
