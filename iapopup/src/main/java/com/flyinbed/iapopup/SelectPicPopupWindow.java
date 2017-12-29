package com.flyinbed.iapopup;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;


/**
 * 作者：Administrator on 2017/4/23 13:58
 * 邮箱：zhanghuaiha@gmail.com
 */

public class SelectPicPopupWindow extends PopupWindow {
    private TextView item_popupwindows_cancel,  //取消
            item_popupwindows_top,              //顶部
            item_popupwindows_bottom,           //底部
            item_popupwindows_frist,            //第一个item
            item_popupwindows_second,           //第二个item
            item_popupwindows_third;            //第三个item
    private LinearLayout linearLayout;
    private View menuview,
            view_popupwindows_frist,
            view_popupwindows_second,
            view_popupwindows_third;
    private Context context;
    private int height;



    @SuppressLint("WrongViewCast")
    public SelectPicPopupWindow(Activity context){
        super(context);
        this.context = context;
        //设置弹窗位置
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        menuview = inflater.inflate(R.layout.popup_item_ios,null);
        linearLayout = menuview.findViewById(R.id.ll_popup);
        item_popupwindows_cancel =  menuview.findViewById(R.id.popup_ios_cancel);
        item_popupwindows_top =  menuview.findViewById(R.id.popup_ios_top);
        item_popupwindows_bottom =  menuview.findViewById(R.id.popup_ios_bottom);


        item_popupwindows_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hint();
            }
        });
        //设置SelectPicPopupWindow的View
        initData();
    }

    private void initData() {
        this.setContentView(menuview);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.FILL_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        //修改高度显示，解决被手机底部虚拟键挡住的问题
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
//        this.setAnimationStyle(R.style.AnimHead);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(Color.parseColor("#11000000"));
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        //menuview添加ontouchlistener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        menuview.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int height = menuview.findViewById(R.id.ll_popup).getTop();
                int y = (int) motionEvent.getY();
                if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if (y<height){
                        hint();
                    }
                }
                return true;
            }
        });
    }

    //显示popup
    public void show(View view){
        showAtLocation(view, Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
        //显示动画
        linearLayout.setVisibility(View.VISIBLE);
        height=ViewUtils.getViewMeasuredHeight(linearLayout);
        this.setTrans(linearLayout, true);
    }

    //关闭popup
    public void hint(){
        //隐藏动画
        setTrans(linearLayout, false);
    }

    /**
     * @return 获取顶部Textview
     */
    public TextView getTopView(){
        return item_popupwindows_top;
    }

    /**
     * @return 获取底部Textview
     */
    public TextView getBottomView(){
        return item_popupwindows_bottom;
    }

    /**
     * @return 获取取消Textview
     */
    public TextView getCancelView(){
        return item_popupwindows_cancel;
    }

    /**
     * @return 获取中间第一个Textview
     */
    public TextView getFristView(){
        item_popupwindows_frist =  menuview.findViewById(R.id.popup_ios_first);
        view_popupwindows_frist =  menuview.findViewById(R.id.popup_view_first);
        item_popupwindows_frist.setVisibility(View.VISIBLE);
        view_popupwindows_frist.setVisibility(View.VISIBLE);
        return item_popupwindows_frist;
    }

    /**
     * @return 获取中间第二个Textview
     */
    public TextView getSecondView(){
        item_popupwindows_second =  menuview.findViewById(R.id.popup_ios_second);
        view_popupwindows_second =  menuview.findViewById(R.id.popup_view_second);
        item_popupwindows_second.setVisibility(View.VISIBLE);
        view_popupwindows_second.setVisibility(View.VISIBLE);
        return item_popupwindows_second;
    }

    /**
     * @return 获取中间第三个Textview
     */
    public TextView getThirdView(){
        item_popupwindows_third =  menuview.findViewById(R.id.popup_ios_third);
        view_popupwindows_third =  menuview.findViewById(R.id.popup_view_third);
        item_popupwindows_third.setVisibility(View.VISIBLE);
        view_popupwindows_third.setVisibility(View.VISIBLE);
        return item_popupwindows_third;
    }




    //动画
    ValueAnimator va = null;
    private void setTrans(final View view, final boolean show){
        if (show){
            va = ValueAnimator.ofInt(0,height);
        }else{
            va = ValueAnimator.ofInt(height,0);
        }
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = animatedValue;
                view.requestLayout();

            }
        });

        va.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (show) {
                }else{
                    view.setVisibility(View.INVISIBLE);
                    dismiss();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        va.setDuration(200);
        va.start();

    }

}
