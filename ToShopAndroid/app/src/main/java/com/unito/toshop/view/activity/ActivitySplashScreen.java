package com.unito.toshop.view.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.unito.toshop.Application;
import com.unito.toshop.Constants;
import com.unito.toshop.R;
import com.unito.toshop.control.servercalls.ProductManager;
import com.unito.toshop.model.ProductClientResult;
import com.unito.toshop.model.ProductInfoResult;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.util.*;

public class ActivitySplashScreen extends AppCompatActivity {

    private static final String TAG = ActivitySplashScreen.class.getSimpleName();

    private final int splashTime = 3000;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        if (!isOnline()) {
            new AlertDialog.Builder(this)
                    .setTitle("CONNECTION ERROR")
                    .setMessage("No internet connection, you must connect to access the store.")
                    .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setCancelable(false)
                    .show();
        } else {
            Log.e(TAG, "Connection found");
            this.initData();

            final Handler handler = new Handler();
            Timer timer = new Timer();
            TimerTask doAsynchronousTask = new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        public void run() {
                            showActivityHome();
                        }
                    });
                }
            };
            timer.schedule(doAsynchronousTask, splashTime);

        }
    }

    public boolean isOnline() {
        ConnectivityManager connectivityManager = (ConnectivityManager) Application.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            return true;
        }
        return false;
    }

    public boolean isOnline2() { //method to check server aviability
        try {
            InetAddress ipAddr = InetAddress.getByName(""); //put server host here
            //You can replace it with your name
            return !ipAddr.equals("");
        } catch (Exception e) {
            Log.e(TAG, "No connection");
            return false;
        }
    }

    private void showActivityHome() {
        Intent intent = new Intent(this, ActivityHome.class);
        this.startActivity(intent);
        Log.e(TAG, "fine splash screen");
        finish();
    }

    private void initData() {
        ProductManager.getInstance().getProductsListFH();
        ProductManager.getInstance().getProductsListSH();

        HashMap<String, Integer> images = new HashMap<>();
        images.put("../../../assets/img/kebab.jpeg", R.drawable.kebab);
        images.put("../../../assets/img/falafel.jpeg", R.drawable.falafel);
        images.put("../../../assets/img/piatti.jpg", R.drawable.piatti);
        images.put("../../../assets/img/urna.jpg", R.drawable.urna);
        images.put("../../../assets/img/stereo.jpg", R.drawable.stereo);
        images.put("../../../assets/img/calze1.jpg", R.drawable.calze1);
        images.put("../../../assets/img/calze2.jpeg", R.drawable.calze2);
        images.put("../../../assets/img/aspira.jpg", R.drawable.aspira);
        images.put("../../../assets/img/cassetta.jpg", R.drawable.cassetta);

        Application.getInstance().getModel().putBean(Constants.IMAGES, images);
    }

}
