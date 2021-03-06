package com.project.nycschools.data;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.project.nycschools.R;


public class AppUtils {


    public static void snackbar(View view, String text, Context context) {
        Snackbar.make(view, text, Snackbar.LENGTH_SHORT);

        Snackbar snackbar = Snackbar
                .make(view, text, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        snackbar.show();
    }
}
