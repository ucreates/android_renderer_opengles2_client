varying mediump vec4 v_color;
varying mediump vec2 v_uv;
uniform mediump vec4 v_randomColor;
uniform sampler2D u_texture;
void main(void) {
    mediump vec4 ret = v_color;
    if (0.0 != length(v_randomColor)) {
        ret = v_randomColor;
    }
    if (0.0 != length(v_uv)) {
        ret *= texture2D(u_texture, v_uv);
    }
    gl_FragColor = ret;
}
