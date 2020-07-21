package com.unito.toshop.control;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Toast;
import com.unito.toshop.Application;
import com.unito.toshop.Constants;
import com.unito.toshop.model.LoginResult;
import com.unito.toshop.model.ProductInfoResult;
import com.unito.toshop.view.activity.ActivityProductDetails;

public class ProductDetailsControl {

    private ActionAddToCart actionAddToCart = new ActionAddToCart();

    public ActionAddToCart getActionAddToCart() {
        return actionAddToCart;
    }

    private static class ActionAddToCart implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            ProductInfoResult product = (ProductInfoResult) Application.getInstance().getModel().getBean(Constants.SELECTED_PRODUCT);
            LoginResult user = (LoginResult) Application.getInstance().getPersistentModel().getPersistentBean(Constants.LOGIN_RESULT,LoginResult.class);
            if (user == null) {
                new AlertDialog.Builder(Application.getInstance().getCurrentActivity())
                        .setTitle("ERROR")
                        .setMessage("You're not logged in! Do you want to log in now?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityProductDetails activityProductDetails = (ActivityProductDetails) Application.getInstance().getCurrentActivity();
                                activityProductDetails.showActivityLogin();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
            } else {
                Toast.makeText(Application.getInstance().getCurrentActivity(), "Product added to cart!", Toast.LENGTH_LONG).show();
            }
        }
    }
}
