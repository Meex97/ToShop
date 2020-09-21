package com.unito.toshop;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import com.unito.toshop.control.HomeControl;
import com.unito.toshop.control.LoginControl;
import com.unito.toshop.control.ProductDetailsControl;
import com.unito.toshop.control.servercalls.ProductManager;
import com.unito.toshop.model.Model;
import com.unito.toshop.model.PersistentModel;

public class Application extends android.app.Application {

    public static final String TAG = Application.class.getSimpleName();
    private static Application singleton;

    public static Application getInstance() {
        return singleton;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        singleton = (Application) getApplicationContext();
        singleton.registerActivityLifecycleCallbacks(new ActivityManager());
    }

    //------------- Model -------------
    private Model model = new Model();
    private PersistentModel persistentModel = new PersistentModel();

    public Model getModel() {
        return model;
    }
    public PersistentModel getPersistentModel() {
        return persistentModel;
    }

    //------------- Activity -------------
    private Activity currentActivity = null;

    public Activity getCurrentActivity() {
        return this.currentActivity;
    }


    //------------- Control -------------
    private HomeControl homeControl = new HomeControl();
    private LoginControl loginControl = new LoginControl();
    private ProductDetailsControl productDetailsControl = new ProductDetailsControl();

    public HomeControl getHomeControl() {
        return homeControl;
    }
    public LoginControl getLoginControl() {
        return this.loginControl;
    }
    public ProductDetailsControl getProductDetailsControl() {
        return this.productDetailsControl;
    }

    //------------- ActivityManager -------------
    private class ActivityManager implements ActivityLifecycleCallbacks {

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            Log.v(TAG, "onActivityCreated: " + activity);
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            Log.v(TAG, "onActivityDestroyed: " + activity);
        }

        @Override
        public void onActivityStarted(Activity activity) {
            Log.v(TAG, "onActivityStarted: " + activity);
        }

        @Override
        public void onActivityResumed(Activity activity) {
            Log.v(TAG, "currentActivity initialized: " + activity);
            currentActivity = activity;
        }

        @Override
        public void onActivityPaused(Activity activity) {
            Log.v(TAG, "onActivityPaused: " + activity);
        }

        @Override
        public void onActivityStopped(Activity activity) {
            if (currentActivity == activity) {
                Log.v(TAG, "currentActivity stopped: " + activity);
                currentActivity = null;
            }
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            Log.v(TAG, "onActivitySaveInstanceState: " + activity);
        }

    }

}
