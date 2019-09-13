package com.riztech.dragdropfinal;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Paint.Style;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.DragShadowBuilder;

import androidx.annotation.NonNull;

public  class MyDragShadowBuilder extends View.DragShadowBuilder {
    private Drawable _shadow;

    public MyDragShadowBuilder(View v, Drawable shadow) {
        super(v);
        _shadow = shadow;
    }

    @Override
    public void onProvideShadowMetrics(@NonNull Point size, @NonNull Point touch) {
        int width, height;
        width = getView().getResources().getDimensionPixelSize(R.dimen.drag_icon_enlarged);
        height = getView().getResources().getDimensionPixelSize(R.dimen.drag_icon_enlarged);
        int intrinsicHeight = _shadow.getIntrinsicHeight();
        int intrinsicWidth = _shadow.getIntrinsicWidth();
        if (intrinsicHeight > 0 && intrinsicWidth > 0) {
            if (width > height) {
                height = width * intrinsicHeight / intrinsicWidth;
            } else {
                width = height * intrinsicWidth / intrinsicHeight;
            }
            _shadow.setBounds(0, 0, width, height);
            size.set(width, height);
            touch.set(width / 2, height / 2);
        } else {
            _shadow.setBounds(0, 0, width, height);
            size.set(width, height);
            touch.set(width / 2, height / 2);
        }
    }

    @Override
    public void onDrawShadow(@NonNull Canvas canvas) {
        _shadow.draw(canvas);
    }
}