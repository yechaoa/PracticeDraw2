package com.hencoder.hencoderpracticedraw2.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ComposePathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.SumPathEffect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice12PathEffectView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Path path = new Path();

    public Practice12PathEffectView(Context context) {
        super(context);
    }

    public Practice12PathEffectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice12PathEffectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint.setStyle(Paint.Style.STROKE);

        path.moveTo(50, 100);
        path.rLineTo(50, 100);
        path.rLineTo(80, -150);
        path.rLineTo(100, 100);
        path.rLineTo(70, -120);
        path.rLineTo(150, 80);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 使用 Paint.setPathEffect() 来设置不同的 PathEffect , 给图形的轮廓设置效果

        // 第一处：CornerPathEffect
        canvas.drawPath(path, paint);

        canvas.save();
        canvas.translate(500, 0);
        // 第二处：DiscretePathEffect 数值越大 越圆滑
        DiscretePathEffect discretePathEffect = new DiscretePathEffect(30, 10);
        paint.setPathEffect(discretePathEffect);
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(0, 200);
        // 第三处：DashPathEffect 画30 空10 画20 空10
        DashPathEffect dashPathEffect = new DashPathEffect(new float[]{30, 10, 20, 10}, 0);
        paint.setPathEffect(dashPathEffect);
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(500, 200);
        // 第四处：PathDashPathEffect

        //三角形
        Path dashPath = new Path();
        dashPath.lineTo(30,0);
        dashPath.rLineTo(-15,20);
        dashPath.close();

        PathDashPathEffect pathDashPathEffect = new PathDashPathEffect(dashPath, 30, 0, PathDashPathEffect.Style.MORPH);
        paint.setPathEffect(pathDashPathEffect);
        canvas.drawPath(this.path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(0, 400);
        // 第五处：SumPathEffect 多个结合使用 效果并行都显示
        DiscretePathEffect discretePathEffect1 = new DiscretePathEffect(30, 10);
        DashPathEffect dashPathEffect1 = new DashPathEffect(new float[]{30, 10, 20, 10}, 0);
        SumPathEffect sumPathEffect = new SumPathEffect(discretePathEffect1, dashPathEffect1);
        paint.setPathEffect(sumPathEffect);
        canvas.drawPath(this.path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(500, 400);
        // 第六处：ComposePathEffect 多个结合使用 效果合并显示
        DiscretePathEffect discretePathEffect2 = new DiscretePathEffect(30, 10);
        DashPathEffect dashPathEffect2 = new DashPathEffect(new float[]{30, 10, 20, 10}, 0);
        ComposePathEffect composePathEffect = new ComposePathEffect(dashPathEffect2, discretePathEffect2);
        paint.setPathEffect(composePathEffect);
        canvas.drawPath(this.path, paint);
        canvas.restore();
    }
}
