package miuyongjun.utillibrary;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;
import android.widget.Toast;

/**
 * Created by miaoyongjun on 16/6/9.
 * 　　　　    　┃┫┫　┃┫┫
 * 　　　　    　┗┻┛　┗┻┛
 */
public class DoubleClickExitHelper {
    private int timeInterval = 2000;
    private static final String DEFAULT_TOAST_STRING = "再按一次返回键退出应用";
    private final Activity mActivity;
    private boolean isOnKeyBacking;
    private Handler mHandler;
    private Toast mBackToast;


    public DoubleClickExitHelper(Activity activity) {
        this(activity, null);
    }

    public DoubleClickExitHelper(Activity activity, String toast) {
        String toastStr = DEFAULT_TOAST_STRING;
        if (toast != null) toastStr = toast;
        mActivity = activity;
        mHandler = new Handler(Looper.getMainLooper());
        mBackToast = Toast.makeText(mActivity, toastStr,
                Toast.LENGTH_LONG);
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode != KeyEvent.KEYCODE_BACK) {
            return false;
        }
        if (isOnKeyBacking) {
            mHandler.removeCallbacks(onBackTimeRunnable);
            if (mBackToast != null) {
                mBackToast.cancel();
            }
            mActivity.finish();
            return true;
        } else {
            isOnKeyBacking = true;
            mBackToast.show();
            mHandler.postDelayed(onBackTimeRunnable, timeInterval);
            return true;
        }
    }

    public boolean onKeyDownWithCallBack(int keyCode, KeyEvent event, MyCallBack callBack) {
        if (keyCode != KeyEvent.KEYCODE_BACK) {
            return false;
        }
        if (isOnKeyBacking) {
            mHandler.removeCallbacks(onBackTimeRunnable);
            if (mBackToast != null) {
                mBackToast.cancel();
            }
            callBack.call();
            return true;
        } else {
            isOnKeyBacking = true;
            mBackToast.show();
            mHandler.postDelayed(onBackTimeRunnable, timeInterval);
            return true;
        }
    }


    private Runnable onBackTimeRunnable = new Runnable() {

        @Override
        public void run() {
            isOnKeyBacking = false;
            if (mBackToast != null) {
                mBackToast.cancel();
            }
        }
    };

    interface MyCallBack {
        void call();
    }

}
