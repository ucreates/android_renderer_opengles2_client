// ======================================================================
// Project Name    : android_renderer_openGLES2_client
//
// Copyright © 2020 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.ucreates.client.behaviour;
import android.content.Context;
import com.frontend.behaviour.BaseBehaviour;
import com.ucreates.renderer.asset.GLES2BaseAsset;
import com.ucreates.renderer.asset.mesh.GLES2CubeAsset1;
import com.ucreates.renderer.asset.mesh.GLES2SphereAsset1;
import com.ucreates.renderer.entity.GLES2Color;
import com.ucreates.renderer.shader.GLES2ProgramObject;
public class SphereTextureBehaviour1 extends BaseBehaviour {
    private float rotate;
    public GLES2BaseAsset asset;
    public SphereTextureBehaviour1(Context context) {
        super(context);
        GLES2BaseAsset.BindCallback cb = new GLES2BaseAsset.BindCallback() {
            @Override
            public void OnBind(double delta) {
                asset.vertex.setRandomColor("v_randomColor", asset.programObject);
            }
        };
        String vertexShaderPath = "vertex.glsl";
        String fragmentShaderPath = "fragment.glsl";
        GLES2ProgramObject programObject = new GLES2ProgramObject();
        programObject.setVertexShader(vertexShaderPath, context);
        programObject.setFragmentShader(fragmentShaderPath, context);
        programObject.setPositionName("a_position");
        programObject.setColorName("a_color");
        programObject.setNormalName("a_normal");
        programObject.setUVName("a_uv");
        programObject.link();
        this.asset = new GLES2SphereAsset1(0.5f, 100, GLES2Color.white);
        this.asset.setProgramObject(programObject);
        this.asset.setBindCallback(cb);
        this.asset.create("texture01.jpg", "u_texture", context);
        return;
    }
    @Override
    public void onCreate() {
        return;
    }
    @Override
    public void onUpdate(double delta) {
        this.asset.transform.setPosition(0.0f, 0.0f, 0.0f);
        this.asset.transform.setScale(1.0f, 1.0f, 1.0f);
        this.asset.transform.setRotation(rotate, rotate, rotate);
        this.rotate += 1;
        return;
    }
}