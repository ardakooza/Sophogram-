package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.RequiresApi;

public class GlassFrameLayout extends FrameLayout {

    private boolean blurEnabled = true;
    private float blurRadius = 24f;

    public GlassFrameLayout(Context context) {
        super(context);
        init();
    }

    public GlassFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GlassFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setBackground(new GlassDrawable());
        if (blurEnabled && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            applyRenderEffect();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.S)
    private void applyRenderEffect() {
        setRenderEffect(RenderEffect.createBlurEffect(
                blurRadius, blurRadius, Shader.TileMode.MIRROR));
    }

    public void setBlurEnabled(boolean blurEnabled) {
        this.blurEnabled = blurEnabled;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            setRenderEffect(blurEnabled ? RenderEffect.createBlurEffect(
                    blurRadius, blurRadius, Shader.TileMode.MIRROR) : null);
        }
    }

    public void setBlurRadius(float blurRadius) {
        this.blurRadius = blurRadius;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && blurEnabled) {
            applyRenderEffect();
        }
    }
}
