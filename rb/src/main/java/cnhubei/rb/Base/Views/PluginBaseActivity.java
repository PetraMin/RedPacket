package cnhubei.rb.Base.Views;

import android.os.Bundle;

import com.cnhubei.gaf.mvp.bijection.RequiresPresenter;
import com.cnhubei.gaf.mvp.expansion.BeamBaseActivity;
import com.cnhubei.gaf.mvp.expansion.data.BeamDataActivityPresenter;

@RequiresPresenter(PluginBasePresenter.class)
public class PluginBaseActivity<P extends BeamDataActivityPresenter> extends BeamBaseActivity<PluginBasePresenter> {
    float startX = 0;
    float startY = 0;
    float endX = 0;
    float endY = 0;
    float disX = 0;
    float disY = 0;

    // 默认允许滑动退出，如果不需要将该值改为false
    private boolean mTouchToExit = true;
    //默认允许左滑事件，如果不需要将该值改为false
    private boolean mTouchLeft = true;

    public PluginBaseActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(getActivityLayoutResID());
    }


//    protected void addFragment(Fragment fragment) {
//        //步骤一：添加一个FragmentTransaction的实例
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        //步骤二：用add()方法加上Fragment的对象
//        transaction.replace(R.id.fragment, fragment);
//        //步骤三：调用commit()方法使得FragmentTransaction实例的改变生效
//        transaction.commit();
//    }

//    /**
//     * 设置布局文件
//     *
//     * @return
//     */
//    public int getActivityLayoutResID() {
//        return R.layout.a_base;
//    }
//
//    @Override
//    protected void initToolbar(Toolbar toolbar) {
////        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
//    }
//
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent event) {
//
//        // start of ime event dispatch
//        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//            startX = event.getX();
//            startY = event.getY();
//
//            View v = getCurrentFocus();
//            if (isShouldHideKeyboard(v, event)) {
//                v.clearFocus();
//                hideKeyboard(v.getWindowToken());
//                return interceptTouchevent(event);
//            }
//        }
//        // end of ime event dispatch
//        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//            startX = event.getX();
//            startY = event.getY();
//            //如果不支持手势就直接将其实位置和结束位置都赋值为0 就不会响应手势了
//            if((!mTouchToExit && !mTouchLeft)){
//                startX = 0;
//            }
//        }
//        if (event.getAction() == MotionEvent.ACTION_UP) {
//            endX = event.getX();
//            endY = event.getY();
//            //如果不支持手势就直接将其实位置和结束位置都赋值为0 就不会响应手势了
//            if((!mTouchToExit && !mTouchLeft)) {
//                endX = 0;
//            }
//            disX = endX - startX;//正值为从左向右滑，负值为从右向左滑
//            if(startX<=0||startY<=0){
//                return touchEx(event);
//            }
//            disY = Math.abs(endY - startY);
//            if ((disX > 120) && disX / disY > 2 && mTouchToExit) {
//                doFinish();
//                return true;
//            }
//            if ((-disX > 120) && -disX / disY > 2 && mTouchLeft) {
//                onLeftSlide();
//                return touchEx(event);
//            }
//        }
//        return touchEx(event);
//    }
//
//    /**
//     * 为防止崩溃
//     * @param event
//     * @return
//     */
//    private boolean touchEx(MotionEvent event){
//        try {
//            return super.dispatchTouchEvent(event);
//        } catch (IllegalArgumentException ex) {
//            ex.printStackTrace();
//            return false;
//        }
//    }
//
//    protected  void doFinish(){
//        finish();
//    }
//
//    /**
//     * 是否屏蔽关闭键盘同时触发的其他事件
//     *
//     * @return true 表示屏蔽 默认不屏蔽
//     */
//    protected boolean interceptTouchevent(MotionEvent event) {
//        return touchEx(event);
//    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        if (null != this.getCurrentFocus()) {
//            /**
//             * 点击空白位置 隐藏软键盘
//             */
//            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
//            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
//        }
//        return super.onTouchEvent(event);
//    }
//
//    /**
//     * 当左滑的时候触发的动作（可用于详情页左滑出评论列表）
//     */
//    protected void onLeftSlide() {
//
//    }
//
//
//    /**
//     * 向外层透出设置滑动退出的接口
//     *
//     * @param touchToExit true时可以滑动退出activity， false 不行
//     */
//    public void setTouchToExit(boolean touchToExit) {
//        mTouchToExit = touchToExit;
//    }
//
//    /**
//     * 向外层透出设置滑动退出的接口
//     *
//     * @param mTouchLeft true时可以触发左滑事件， false 不行
//     */
//    public void setmTouchLeft(boolean mTouchLeft) {
//        this.mTouchLeft = mTouchLeft;
//    }
//
//    @Override
//    public void finish() {
//        super.finish();
////        if (isCanFinishAnim) {
////            overridePendingTransition(R.anim.no_anim, R.anim.push_right_out);
////        }
//        if (null != this.getCurrentFocus()) {
//            /**
//             * 滑动退出隐藏软键盘
//             */
//            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
//            mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
//        }
//    }
//
//    @Override
//    public void startActivity(Intent intent) {
//        super.startActivity(intent);
////        this.overridePendingTransition(R.anim.push_right_in, R.anim.no_anim);
//    }
//
//
//    @Override
//    public void startActivityForResult(Intent intent, int requestCode) {
//        super.startActivityForResult(intent, requestCode);
////        this.overridePendingTransition(R.anim.push_right_in, R.anim.no_anim);
//    }
//
//    private boolean isCanFinishAnim = true;
//
//    /**
//     * 向外层透出设置是否需要退出动画的接口
//     *
//     * @param isCanFinishAnim true时有滑动退出动画，false时没有
//     */
//    public void setIsCanFinishAnim(boolean isCanFinishAnim) {
//        this.isCanFinishAnim = isCanFinishAnim;
//    }
//
//    /**
//     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏
//     *
//     * @param v
//     * @param event
//     * @return
//     */
//    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
//        if (v != null && (v instanceof EditText)) {
//            int[] l = {0, 0};
//            v.getLocationInWindow(l);
////            int left = l[0];
//            int top = l[1];
//            int bottom = top + v.getHeight();
////            int right = left + v.getWidth();
////            if (event.getX() > left && event.getX() < right
////                    && event.getY() > top && event.getY() < bottom) {
//            return !(event.getY() > top && event.getY() < bottom);
//        }
//        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
//        return false;
//    }
//
//    /**
//     * 获取InputMethodManager，隐藏软键盘
//     *
//     * @param token
//     */
//    private void hideKeyboard(IBinder token) {
//        if (token != null) {
//            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
//        }
//    }
//
//    @Override
//    protected void onDestroy() {
////        setContentView(R.layout.null_layout);
//        super.onDestroy();
//    }
}
