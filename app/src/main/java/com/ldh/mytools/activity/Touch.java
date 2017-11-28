package com.ldh.mytools.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.FloatMath;
import android.util.FloatProperty;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.common.primitives.Floats;
import com.ldh.barlibrary.abstracts.BaseActivity;
import com.ldh.mytools.R;
import com.ldh.mytools.view.MyScrollView;

public class Touch extends BaseActivity implements View.OnTouchListener, View.OnClickListener {
    private static final String TAG = "Touch";

    // These matrices will be used to move and zoom image   
    Matrix matrix = new Matrix();
    Matrix savedMatrix = new Matrix();
    PointF start = new PointF();
    PointF mid = new PointF();
    float oldDist;
    private ImageView view;
    private MyScrollView scrollView;
    private Button zoomIn, zoomOut;
    //button zoom   
    private float scaleWidth = 1;
    private float scaleHeight = 1;
    private Bitmap bmp, zoomedBMP;

    private static final double ZOOM_IN_SCALE = 1.25;//放大系数   
    private static final double ZOOM_OUT_SCALE = 0.8;//缩小系数   
    // We can be in one of these 3 states   
    static final int NONE = 0;
    static final int DRAG = 1;
    static final int ZOOM = 2;
    int mode = NONE;

    int bmpWidth, bmpHeight;
    int imageHeight, imageWidth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main1);
        view = (ImageView) findViewById(R.id.imageView);
        view.setOnTouchListener(this);
        scrollView = (MyScrollView) findViewById(R.id.scrollview);

        zoomIn = (Button) findViewById(R.id.btn_zoom_in);
        zoomOut = (Button) findViewById(R.id.btn_zoom_out);
        zoomOut.setOnClickListener(this);
        zoomIn.setOnClickListener(this);
        //取得drawable中图片，放大，缩小，多点触摸的作用对象   
        bmp = BitmapFactory.decodeResource(Touch.this.getResources(), R.drawable.img_head_default);
        bmpWidth = bmp.getWidth();
        bmpHeight = bmp.getHeight();
    }


    public boolean onTouch(View v, MotionEvent event) {
        // Handle touch events here...   
        ImageView view = (ImageView) v;

//      imageWidth = v.getWidth();//屏幕宽度（像素数）  
//      imageHeight = v.getHeight();  

        // Handle touch events here...   
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            //设置拖拉模式
            case MotionEvent.ACTION_DOWN:
                savedMatrix.set(matrix);
                start.set(event.getX(), event.getY());
                Log.d(TAG, "mode=DRAG");
                mode = DRAG;
                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                mode = NONE;
                Log.d(TAG, "mode=NONE");
                break;
            //设置多点触摸模式   
            case MotionEvent.ACTION_POINTER_DOWN:
                oldDist = spacing(event);
                //          originalDist = oldDist;
                Log.d(TAG, "oldDist=" + oldDist);
                if (oldDist > 10f) {
                    savedMatrix.set(matrix);
                    midPoint(mid, event);
                    mode = ZOOM;
                    Log.d(TAG, "mode=ZOOM");
                }
                break;
            //若为DRAG模式，则点击移动图片   
            case MotionEvent.ACTION_MOVE:
                if (mode == DRAG) {
                    matrix.set(savedMatrix);
                    // 设置位移
                    matrix.postTranslate(event.getX() - start.x,
                            event.getY() - start.y);
                }
                //若为ZOOM模式，则多点触摸缩放
                else if (mode == ZOOM) {
                    float newDist = spacing(event);
                    Log.d(TAG, "newDist=" + newDist);
                    if (newDist > 10f) {
                        matrix.set(savedMatrix);
                        float scale = newDist / oldDist;
                        //设置缩放比例和图片中点位置
                        matrix.postScale(scale, scale, mid.x, mid.y);
                    }
                }
                break;
        }

        // Perform the transformation   
        view.setImageMatrix(matrix);

        return true; // indicate event was handled   
    }


    //计算移动距离   
    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return x * x + y * y;
    }

    //计算中点位置   
    private void midPoint(PointF point, MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2, y / 2);
    }

    //放大，缩小按钮点击事件   
    @Override
    public void onClick(View v) {
        if (v == zoomIn) {
            enlarge();
        } else if (v == zoomOut) {
            small();
        }
    }

    //按钮点击缩小函数   
    private void small() {
        int bmpWidth = bmp.getWidth();
        int bmpHeight = bmp.getHeight();

        scaleWidth = (float) (scaleWidth * ZOOM_OUT_SCALE);
        scaleHeight = (float) (scaleHeight * ZOOM_OUT_SCALE);

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        zoomedBMP = Bitmap.createBitmap(bmp, 0, 0, bmpWidth, bmpHeight, matrix,
                true);
        view.setImageBitmap(zoomedBMP);
    }

    //按钮点击放大函数   
    private void enlarge() {
        try {
            int bmpWidth = bmp.getWidth();
            int bmpHeight = bmp.getHeight();

            scaleWidth = (float) (scaleWidth * ZOOM_IN_SCALE);
            scaleHeight = (float) (scaleHeight * ZOOM_IN_SCALE);

            Matrix matrix = new Matrix();
            matrix.postScale(scaleWidth, scaleHeight);
            zoomedBMP = Bitmap.createBitmap(bmp, 0, 0, bmpWidth, bmpHeight, matrix,
                    true);
            view.setImageBitmap(zoomedBMP); //存在内存溢出问题  

        } catch (Exception e) {
            //can't zoom because of memory issue, just ignore, no big deal   
        }
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub  
        if (null != bmp && !bmp.isRecycled()) {
            bmp.recycle();
        }
        System.gc();
        super.onDestroy();
    }


}  