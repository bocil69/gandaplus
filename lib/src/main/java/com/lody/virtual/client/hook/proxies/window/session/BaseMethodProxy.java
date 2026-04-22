package com.lody.virtual.client.hook.proxies.window.session;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.view.WindowInsets;
import android.view.WindowManager;

import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.hook.base.StaticMethodProxy;
import com.lody.virtual.helper.utils.ArrayUtils;
import com.xdja.zs.VAppPermissionManager;
import com.xdja.zs.VWaterMarkManager;
import com.xdja.zs.MobileInfoUtil;
import com.xdja.zs.WaterMarkInfo;


import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Lody
 */

class Relayout extends BaseMethodProxy{

    static final String TAG = Relayout.class.getName();
    private static final String VIEW_ROOT_IMPL_WINDOW_CLASS = "android.view.ViewRootImpl$W";
    private static final String VIEW_ROOT_IMPL_CLASS = "android.view.ViewRootImpl";
    private static volatile Field sViewAncestorField;
    private static volatile Field sViewField;

    private String mImei = "";
    private String mIconPath = "";
    private List<String> infos = new ArrayList<>();
    private int textColor = Color.parseColor("#AEAEAE");
    private float textSize = 72;  //20 24 26sp 60 72 78;
    private int textAlpha = 70;
    private float distance = 35;
    private int roate = -30;
    private boolean hasWaterMark;
    private boolean waterMarkApplied;

    public Relayout(String name) {
        super(name);
    }

    private void updateContent(){
        hasWaterMark = false;

//取消默认水印设置，完全依赖上层设置，包括默认值。不设置不显示。
//        mImei = MobileInfoUtil.getIMEI(VirtualCore.get().getContext());
//        infos.clear();
//        if(null==mImei||"".equals(mImei))
//            mImei = "安全模式";
//        infos.add(mImei);

        WaterMarkInfo waterMark = VWaterMarkManager.get().getWaterMark();
        if (waterMark == null || TextUtils.isEmpty(waterMark.getWaterMarkContent())) {
            infos.clear();
            return;
        }else if(waterMark.getTextSize()==0.0){
            infos.clear();
            return;
        }else if(TextUtils.isEmpty(waterMark.getTextColor())){
            infos.clear();
            return;
        }else if(waterMark.getTextAlpha()==0.0){
            infos.clear();
            return;
        }

        String waterInfo = waterMark.getWaterMarkContent();
        textColor = Color.parseColor(waterMark.getTextColor());
        textSize = waterMark.getTextSize();
        textAlpha = (int) (waterMark.getTextAlpha() * 255f);
        distance = waterMark.getDistance();
        roate = waterMark.getRotate();

        infos.clear();
        String content[] = waterInfo.split(",");
        for (String c : content){
            if("T".equals(c)){
                @SuppressLint("SimpleDateFormat")
                SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                infos.add(df.format(new Date()));
            } else {
                infos.add(c);
            }
        }
        hasWaterMark = !infos.isEmpty();
    }
    @Override
    public boolean beforeCall(Object who, Method method, Object... args) {
        updateContent();
        return super.beforeCall(who, method, args);
    }

    @SuppressLint("NewApi")
    @Override
    public Object call(Object who, Method method, Object... args) throws Throwable {

        boolean disableWaterMark = VAppPermissionManager.get().getAppPermissionEnable(getAppPkg(),VAppPermissionManager.PROHIBIT_WATER_MARK);
        if (!hasWaterMark || disableWaterMark) {
            if (!waterMarkApplied) {
                return super.call(who, method, args);
            }
        }

        //args[0] IWindow  ViewRootImpl.W extends IWindow.stub
        //args[2] WindowManager.LayoutParams attrs,

        Field viewAncestorField = findViewAncestorField(args[0]);
        if(viewAncestorField == null){
            return super.call(who, method, args);
        }
        WeakReference VRI = (WeakReference)viewAncestorField.get(args[0]);
        Object viewRootImpl = VRI != null ? VRI.get() : null;
        if (viewRootImpl == null) {
            return super.call(who, method, args);
        }
        Field viewField = getViewField();
        if (viewField == null) {
            return super.call(who, method, args);
        }
        View omView = (View)viewField.get(viewRootImpl);

        // 先清除水印，防止页面不刷新导致的水印残留
        if(omView!=null && omView.getClass().getName().equals("com.android.internal.policy.DecorView")){
            Drawable drawable = new ColorDrawable(Color.BLACK);
            drawable.setAlpha(0);
            omView.setForeground(drawable);
            waterMarkApplied = false;
        }

        if(disableWaterMark){
            Log.e(TAG,"禁止启用水印");
            return super.call(who, method, args);
        }
        if (!hasWaterMark) {
            return super.call(who, method, args);
        }
        if(omView!=null && omView.getClass().getName().equals("com.android.internal.policy.DecorView")){
            //横竖屏切换时会出现未绘制
            //omView.setForeground(null);

            int screenWidth = omView.getMeasuredWidth();
            int screenHeight = omView.getMeasuredHeight();

            if (screenWidth == 0 || screenHeight == 0) {
                return super.call(who, method, args);
            }
            //长宽比例适配，去除小窗口水印绘制
            Bitmap srcBitmap = Bitmap.createBitmap(screenWidth, screenHeight, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(srcBitmap);
            drawWaterMark(canvas,screenWidth,screenHeight);
//          Bitmap mDestBitmap = drawDestBitmap(mBackgroundBitmap,top,screenWidth,screenHeight);
            omView.setForeground(new BitmapDrawable(srcBitmap));
            waterMarkApplied = true;
        }

        return super.call(who, method, args);
    }

    /*
    适配水印纵向偏移
     */
    public Bitmap drawDestBitmap(Bitmap src,int top,int width,int height){
        Bitmap mDestBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(mDestBitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setAntiAlias(true);
        Rect mTopSrcRect = new Rect(0, 0, width, height);
        Rect mTopDestRect = new Rect(0, 0, width, height);
        canvas.drawBitmap(src, mTopSrcRect, mTopDestRect, paint);
        return mDestBitmap;
    }

    private void drawWaterMark(Canvas canvas, int width, int height) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeWidth(1);
        paint.setFakeBoldText(false);
        paint.setColor(textColor);
        paint.setAntiAlias(true);
        paint.setAlpha(textAlpha);
        paint.setTextSize(textSize);
        canvas.rotate(roate);
        float textWidth = 0;
        for (String s:infos) {
            if (TextUtils.isEmpty(s)) {
                continue;
            }
            float w = paint.measureText(s);
            textWidth = w>textWidth?w:textWidth;
        }
        Paint.FontMetrics fm = paint.getFontMetrics();
        //文字基准线的下部距离-文字基准线的上部距离 = 文字高度
        float textHeight =  fm.descent - fm.ascent;
        int index1 = 0;
        for (int positionY = 0; positionY <= height * 2; positionY += height / 4) {
            //旋转后每行会有空白开头;避免对齐
            float fromX = -(index1++ % 2)*(width/3);
            fromX -= (float) (Math.tan(roate)*positionY);
            for (float positionX = fromX; positionX < width*2; positionX += (textWidth+distance)) {
                int _positionY = positionY;
                for (String s:infos) {
                    if(TextUtils.isEmpty(s)){
                        continue;
                    }
                    canvas.drawText(s, positionX, _positionY, paint);
                    _positionY += textHeight+5;
                }
            }
        }
        canvas.save();
    }

    private Field findViewAncestorField(Object window) {
        if (window == null) {
            return null;
        }
        Field cachedField = sViewAncestorField;
        if (cachedField != null && cachedField.getDeclaringClass().isAssignableFrom(window.getClass())) {
            return cachedField;
        }
        Class<?> windowClass = window.getClass();
        if (VIEW_ROOT_IMPL_WINDOW_CLASS.equals(windowClass.getName())) {
            return cacheViewAncestorField(windowClass);
        }
        Class<?> parentClass = windowClass.getSuperclass();
        if (parentClass != null && VIEW_ROOT_IMPL_WINDOW_CLASS.equals(parentClass.getName())) {
            return cacheViewAncestorField(parentClass);
        }
        return null;
    }

    private Field cacheViewAncestorField(Class<?> windowClass) {
        try {
            Field field = windowClass.getDeclaredField("mViewAncestor");
            field.setAccessible(true);
            sViewAncestorField = field;
            return field;
        } catch (NoSuchFieldException e) {
            return null;
        }
    }

    @SuppressLint("PrivateApi")
    private Field getViewField() throws ClassNotFoundException, NoSuchFieldException {
        Field cachedField = sViewField;
        if (cachedField != null) {
            return cachedField;
        }
        Class<?> viewRootImplClass = Class.forName(VIEW_ROOT_IMPL_CLASS);
        Field field = viewRootImplClass.getDeclaredField("mView");
        field.setAccessible(true);
        sViewField = field;
        return field;
    }
}

/*package*/ class BaseMethodProxy extends StaticMethodProxy {

    public BaseMethodProxy(String name) {
        super(name);
    }

    private boolean mDrawOverlays = false;

    protected boolean isDrawOverlays(){
        return mDrawOverlays;
    }

    @SuppressLint("SwitchIntDef")
    @Override
    public boolean beforeCall(Object who, Method method, Object... args){
        mDrawOverlays = false;
        int index = ArrayUtils.indexOfFirst(args, WindowManager.LayoutParams.class);
        if (index != -1) {
            WindowManager.LayoutParams attrs = (WindowManager.LayoutParams) args[index];
            if (attrs != null) {
                attrs.packageName = getHostPkg();
                switch (attrs.type) {
                    case WindowManager.LayoutParams.TYPE_PHONE:
                    case WindowManager.LayoutParams.TYPE_PRIORITY_PHONE:
                    case WindowManager.LayoutParams.TYPE_SYSTEM_ALERT:
                    case WindowManager.LayoutParams.TYPE_SYSTEM_ERROR:
                    case WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY:
                    case WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY:
                        mDrawOverlays = true;
                        break;
                    default:
                        break;
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    if (VirtualCore.get().getTargetSdkVersion() >= Build.VERSION_CODES.O) {
                        //
                        if(mDrawOverlays){
                            attrs.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
                        }
                    }
                }
            }
        }
        return true;
    }
}
