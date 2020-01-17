package com.example.appstore.Utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

public class MyToast  {

    public  static  Toast toast;
    public static void showToast(Context context,String message){
        if (toast==null){
            toast=Toast.makeText(context,message,Toast.LENGTH_SHORT);
        }else {
            toast.setText(message);
        }
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }
}

