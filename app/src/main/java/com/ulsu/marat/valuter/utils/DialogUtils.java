package com.ulsu.marat.valuter.utils;

import android.app.AlertDialog;
import android.content.Context;

/**
 * Created by Marat on 09.05.2017.
 */

public class DialogUtils {
    public static void DialogMessage(Context context, int title, int message){
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle(context.getString(title))
                .setMessage(context.getString(message))
                .setPositiveButton("OK",null)
                .create();
        dialog.show();
    }
}
