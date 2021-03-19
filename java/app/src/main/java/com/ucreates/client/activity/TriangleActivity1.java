// ======================================================================
// Project Name    : android_renderer_openGLES2_client
//
// Copyright © 2020 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.ucreates.client.activity;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.ucreates.client.R;
import com.ucreates.client.behaviour.TriangleBehaviour1;
import com.ucreates.renderer.entity.GLES2Color;
import com.ucreates.renderer.renderer.GLES2Renderer;
import com.ucreates.renderer.timer.GLES2TimeInterval;
import java.util.ArrayList;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
public class TriangleActivity1 extends AppCompatActivity implements GLSurfaceView.Renderer {
    private GLES2Renderer renderer;
    private ArrayList<TriangleBehaviour1> behaviours;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GLSurfaceView view = (GLSurfaceView) this.findViewById(R.id.view);
        view.setEGLContextClientVersion(2);
        view.setPreserveEGLContextOnPause(true);
        view.setRenderer(this);
        return;
    }
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        Context context = this.getApplicationContext();
        this.behaviours = new ArrayList<TriangleBehaviour1>();
        for (int i = 0; i < 1; i++) {
            this.behaviours.add(new TriangleBehaviour1(context));
        }
        this.renderer = new GLES2Renderer();
        this.renderer.setModelMatrixAttributeName("u_projectionMatrix");
        this.renderer.setProjectionMatrixAttributeName("u_modelMatrix");
        this.renderer.setViewMatrixAttributeName("u_viewMatrix");
        this.renderer.create();
        this.renderer.camera.setClear(GLES2Color.black);
        this.renderer.camera.setClippingPlane(-1.0f, 1.0f, GLES2Renderer.DIMENSION2D);
        return;
    }
    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        this.renderer.rebind(this.getApplicationContext(), width, height);
        return;
    }
    @Override
    public void onDrawFrame(GL10 gl) {
        GLES2TimeInterval timer = GLES2TimeInterval.getInstance();
        timer.update();
        float delta = timer.getDelta();
        this.renderer.clear();
        this.renderer.transform(GLES2Renderer.DIMENSION2D);
        for (int i = 0; i < this.behaviours.size(); i++) {
            TriangleBehaviour1 behaviour = this.behaviours.get(i);
            behaviour.onUpdate(timer.getDelta());
            this.renderer.render(delta, behaviour.asset);
        }
        return;
    }
}
