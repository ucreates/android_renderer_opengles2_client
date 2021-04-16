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
import com.ucreates.renderer.asset.mesh.GLES2CircleAsset1;
import com.ucreates.renderer.asset.mesh.GLES2CircleAsset2;
import com.ucreates.renderer.asset.polygon.GLES2RectangleAsset1;
import com.ucreates.renderer.entity.GLES2Color;
import com.ucreates.renderer.shader.GLES2ProgramObject;
import java.util.Random;
public class CircleTextureBehaviour1 extends BaseBehaviour {
    public GLES2BaseAsset asset;
    public CircleTextureBehaviour1(Context context) {
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
        programObject.setUVName("a_uv");
        programObject.link();
        Random rnd = new Random();
        int primitiveType = rnd.nextInt(2);
        if (0 == primitiveType) {
            this.asset = new GLES2CircleAsset1(0.5f, 100, GLES2Color.white);
        } else {
            this.asset = new GLES2CircleAsset2(0.5f, 100, GLES2Color.white);
        }
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
        this.asset.transform.setRotation(0.0f, 0.0f, 0.0f);
        return;
    }
}