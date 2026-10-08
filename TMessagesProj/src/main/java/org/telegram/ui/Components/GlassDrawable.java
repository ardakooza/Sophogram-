package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

public class GlassDrawable extends Drawable {

    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int color = 0x30FFFFFF;

    public GlassDrawable() {
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawPaint(paint);
    }

    @Override
    public void setAlpha(int alpha) {
        paint.setAlpha(alpha);
        invalidateSelf();
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        paint.setColorFilter(colorFilter);
        invalidateSelf();
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    public void setColor(int color) {
        this.color = color;
        paint.setColor(color);
        invalidateSelf();
    }

    public int getColor() {
        return color;
    }
}
