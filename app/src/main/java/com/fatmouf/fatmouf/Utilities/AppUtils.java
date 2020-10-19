package com.fatmouf.fatmouf.Utilities;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.app.ActivityCompat;

import com.fatmouf.fatmouf.R;


public class AppUtils {

    public static int[] getScreenResolution(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        int[] ints = {width, height};
        return ints;
    }

    public static boolean isStoragePermissionGranted(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                MyLog.LogI("TAG", "Permission is granted");
                return true;
            } else {

                MyLog.LogI("TAG", "Permission is revoked");
                ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            MyLog.LogI("TAG", "Permission is granted");
            return true;
        }
    }

    public static boolean ValidateText(String txt, String err, AppCompatEditText editText) {
        if (txt.isEmpty()) {
            editText.setError(err);
            return false;
        } else {
            return true;
        }

    }

    public static void share(Context context, String title, String msg) {
        String msgs = title + "\n\n" + msg + "\n\n\n" + context.getResources().getString(R.string.app_name);
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,
                msgs);
        sendIntent.setType("text/plain");
        context.startActivity(sendIntent);
    }

    public static void hideKeyboard(Activity activity) {
        try {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            //Find the currently focused view, so we can grab the correct window token from it.
            View view = activity.getCurrentFocus();
            //If no view currently has focus, create a new one, just so we can grab a window token from it
            if (view == null) {
                view = new View(activity);
            }
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        } catch (Exception e) {
        }
    }

    public static boolean TextValidation(String text, AppCompatEditText et, String err) {
        if (text.isEmpty()) {
            et.setError(err);
            return false;
        } else {
            return true;
        }
    }

}

