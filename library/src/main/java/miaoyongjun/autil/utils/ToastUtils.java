package miaoyongjun.autil.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Author：miaoyongjun
 * Time: 2016/11/29
 * Module:
 */
public class ToastUtils {
    public static void show(Context context,String showStr){
        Toast.makeText(context,showStr,Toast.LENGTH_SHORT).show();
    }
}
