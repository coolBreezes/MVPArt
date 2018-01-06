//package com.qing.mvpart.base;
//
//import android.graphics.Color;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.ImageButton;
//import android.widget.TextView;
//
//import com.qing.mvpart.R;
//import com.qing.mvpart.mvp.IPresenter;
//import com.wuhenzhizao.titlebar.widget.CommonTitleBar;
//
//import butterknife.BindView;
//
///**
// * 带有标题栏的 Activity 基类
// * Created by QING on 2017/12/22.
// * todo think 放在哪里 这个类
// */
//public abstract class TitlebarBaseActivity<P extends IPresenter> extends QActivity<P> {
//
//    @BindView(R.id.publico_titlebar)
//    public CommonTitleBar mTitlebar;
//
//
//    protected void setTitlebar(String title) {
//        setTitlebar(title, Color.WHITE, true);  //默认白色背景色
//    }
//
//    protected void setTitlebar(String title, int colorId) {
//        setTitlebar(title, colorId, true);
//    }
//
//    protected void setTitlebar(String title, boolean isFinish) {
//        setTitlebar(title, Color.WHITE, isFinish);
//    }
//
//    /**
//     * 设置标题栏
//     *
//     * @param title    标题
//     * @param colorId  标题栏背景色
//     * @param isFinish true表示左侧显示返回按钮
//     */
//    protected void setTitlebar(String title, int colorId, boolean isFinish) {
//
//        setTitleName(title);
//        setTitlebarBg(colorId);
//        if (isFinish) {
//            ImageButton btnLeft = mTitlebar.getLeftImageButton();
//            if (colorId == Color.WHITE) {
//                btnLeft.setImageResource(R.drawable.publico_title_back_arrow);
//            } else {
//                btnLeft.setImageResource(R.drawable.publico_title_back_arrow_white);
//            }
//            mTitlebar.setListener(new CommonTitleBar.OnTitleBarListener() {
//                @Override
//                public void onClicked(View v, int action, String extra) {
//                    if (action == CommonTitleBar.ACTION_LEFT_BUTTON) {
//                        finish();
//                    }
//                }
//            });
//        }
//    }
//
//    protected void setTitlebar(String title, int resId, View.OnClickListener listener) {
//        setTitlebar(title, Color.WHITE, resId, listener);
//    }
//
//    protected void setTitlebar(String title, int colorId, int resId, View.OnClickListener listener) {
//        setTitleName(title);
//        setTitlebarBg(colorId);
//        ImageButton btnLeft = mTitlebar.getLeftImageButton();
//        btnLeft.setImageResource(resId);
//        btnLeft.setOnClickListener(listener);
//    }
//
//    /**
//     * 设置标题
//     */
//    public void setTitleName(String title) {
//        if (!TextUtils.isEmpty(title)) {
//            TextView tvCenter = mTitlebar.getCenterTextView();
//            tvCenter.setVisibility(View.VISIBLE);
//            tvCenter.setText(title);
//        }
//    }
//
//    /**
//     * 设置标题栏背景色
//     */
//    public void setTitlebarBg(int colorId) {
//        if (colorId > 0) {
//            mTitlebar.setBackgroundColor(getResources().getColor(colorId));
//            //如果背景是白色，修改字体颜色
//            TextView tvCenter = mTitlebar.getCenterTextView();
//            if (colorId == Color.WHITE) {
//                tvCenter.setTextColor(Color.parseColor("#333333"));
//            } else {
//                tvCenter.setTextColor(Color.WHITE);
//            }
//        }
//    }
//
//    //////////  右侧按钮  /////////////////
//
//
//    public void setRightTv(String text, View.OnClickListener listener) {
//        setRightTv(text, Color.parseColor("#666666"), listener);
//    }
//
//    public void setRightTv(String text, int colorId, View.OnClickListener listener) {
//        TextView tvRight = mTitlebar.getRightTextView();
//        if (tvRight == null) {
//            return;
//        }
//        tvRight.setText(text);
//        tvRight.setTextColor(colorId);
//        tvRight.setOnClickListener(listener);
//    }
//
//    /**
//     * CommonTitleBar 设置默认rightType，调用此函数需要将
//     * rightType 设置为 ImageButton
//     * @param resId
//     * @param listener
//     */
//    public void setRightBtn(int resId, View.OnClickListener listener) {
//        ImageButton btnRight = mTitlebar.getRightImageButton();
//        if (btnRight == null) {
//            return;
//        }
//        btnRight.setImageResource(resId);
//        btnRight.setOnClickListener(listener);
//    }
//}
