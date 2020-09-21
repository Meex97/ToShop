package com.unito.toshop.control;

import android.util.Log;
import android.view.View;
import com.unito.toshop.Application;
import com.unito.toshop.Constants;
import com.unito.toshop.model.LoginCredential;
import com.unito.toshop.model.LoginResult;
import com.unito.toshop.control.servercalls.LoginManager;
import com.unito.toshop.view.activity.ActivityLogin;

import java.util.regex.Pattern;

public class LoginControl {

    private static final String TAG = LoginControl.class.getSimpleName();

    private ActionLogin actionLogin = new ActionLogin();
    private ActionLogout actionLogout = new ActionLogout();

    public ActionLogin getActionLogin() {
        return actionLogin;
    }
    public ActionLogout getActionLogout() {
        return actionLogout;
    }

    private class ActionLogin implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            ActivityLogin activityLogin = (ActivityLogin) Application.getInstance().getCurrentActivity();
            String email = activityLogin.getEmail();
            String password = activityLogin.getPassword();
            if (this.checkDatas(activityLogin, email, password)) {
                LoginCredential loginCredential = new LoginCredential(email,password);
                Log.e(TAG,"email da loginCredential: " + loginCredential.getUsername());
                Log.e(TAG,"password da loginCredential: " + loginCredential.getPassword());
                LoginManager.getInstance().login(loginCredential);
            }
        }

        private boolean checkDatas(ActivityLogin activityLogin, String email, String password) {
            String PATTERN_EMAIL = "[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}";
            if (!Pattern.matches(PATTERN_EMAIL, email)){
                activityLogin.setErrorEmail();
                return false;
            }
            if (password.length() < 3) {
                activityLogin.setErrorPassword();
                return false;
            }
            return true;
        }
    }

    private class ActionLogout implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            ActivityLogin activityLogin = (ActivityLogin) Application.getInstance().getCurrentActivity();
            if (Application.getInstance().getPersistentModel().getPersistentBean(Constants.LOGIN_RESULT, LoginResult.class) != null) {
                Application.getInstance().getPersistentModel().saveBean(Constants.LOGIN_RESULT, null);
                activityLogin.makeToast("Logged out!");
            }
            activityLogin.showActivityHome();
        }
    }
}
