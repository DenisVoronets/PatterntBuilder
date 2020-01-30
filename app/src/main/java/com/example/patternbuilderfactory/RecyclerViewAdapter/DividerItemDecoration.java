package com.example.patternbuilderfactory.RecyclerViewAdapter;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class DividerItemDecoration extends RecyclerView.ItemDecoration {
    private int mPadding;
    private Paint mBluePaint;
    private Paint mRedPaint;
    private int mOffset;

    public DividerItemDecoration(int padding) {
        this.mPadding = padding;
        mOffset = 15;
        mBluePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBluePaint.setColor(Color.BLUE);
        mBluePaint.setStyle(Paint.Style.STROKE);
        mBluePaint.setStrokeWidth(3);

        mRedPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRedPaint.setColor(Color.BLACK);
        mRedPaint.setStyle(Paint.Style.STROKE);
        mRedPaint.setStrokeWidth(3);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        outRect.bottom += mPadding;
        outRect.top += mPadding;
    }
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);

        final RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();

        for (int i = 0; i < parent.getChildCount(); i++) {
            final View child = parent.getChildAt(i);

            c.drawRect(
                    layoutManager.getDecoratedLeft(child) + mOffset - mOffset,
                    layoutManager.getDecoratedTop(child) + mOffset,
                    layoutManager.getDecoratedRight(child) - mOffset,
                    layoutManager.getDecoratedBottom(child) - mOffset,
                    mRedPaint);

        }
    }
}
