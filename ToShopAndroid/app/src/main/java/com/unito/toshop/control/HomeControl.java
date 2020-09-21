package com.unito.toshop.control;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SearchView;
import android.widget.Toast;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.unito.toshop.Application;
import com.unito.toshop.Constants;
import com.unito.toshop.control.servercalls.ProductManager;
import com.unito.toshop.model.ProductClientResult;
import com.unito.toshop.model.ProductInfoResult;
import com.unito.toshop.view.activity.ActivityHome;
import com.unito.toshop.view.activity.ActivitySearchResult;
import com.unito.toshop.view.fragment.ViewFHProduct;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class HomeControl {

    private static final String TAG = HomeControl.class.getSimpleName();

    private ActionSearchProducts actionSearchProducts = new ActionSearchProducts();
    private ActionGoToLogin actionGoToLogin = new ActionGoToLogin();
    private ActionGoToProductDetail actionGoToProductDetail = new ActionGoToProductDetail();

    public ActionSearchProducts getActionSearchProducts() {
        return actionSearchProducts;
    }
    public ActionGoToLogin getActionGoToLogin() {
        return actionGoToLogin;
    }
    public ActionGoToProductDetail getActionGoToProductDetail() {
        return actionGoToProductDetail;
    }

    private class ActionSearchProducts implements SearchView.OnQueryTextListener {
        @Override
        public boolean onQueryTextSubmit(String query) {
            HashSet<ProductInfoResult> result = new HashSet<>();
            List<ProductInfoResult> listProductInfoResult = (List<ProductInfoResult>) Application.getInstance().getModel().getBean(Constants.PRODUCT_LIST_FH);
            List<ProductClientResult> listProductClientResult = (List<ProductClientResult>) Application.getInstance().getModel().getBean(Constants.PRODUCT_LIST_SH);
            //listProductInfoResult.addAll(listProductClientResult);
            String[] querySplit = query.split(" ");
            for (ProductInfoResult p : listProductInfoResult) {
                for (String s : querySplit) {
                    if (p.getProductName().contains(s)) {
                        result.add(p);
                    }
                }
            }
            if (result.isEmpty()) {
                Toast.makeText(Application.getInstance().getCurrentActivity(), "No products found, try with differents words", Toast.LENGTH_LONG).show();
                return false;
            }
            Log.e(TAG, "result size: " + result.size());
            List<ProductInfoResult> resultList = new ArrayList<>(result); //forse non serve metterlo come List
            Application.getInstance().getModel().putBean(Constants.PRODUCT_SEARCH_RESULT, resultList);
            try {
                ActivityHome activityHome = (ActivityHome) Application.getInstance().getCurrentActivity();
                activityHome.showActivitySearchResult();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(Application.getInstance().getCurrentActivity(), "An error occured", Toast.LENGTH_LONG).show();
            }
            return true;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }

    }

    private class ActionGoToLogin implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            ActivityHome activityHome = (ActivityHome) Application.getInstance().getCurrentActivity();
            activityHome.showLoginActivity();
        }
    }

    private class ActionGoToProductDetail implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            ProductInfoResult selectedProduct = (ProductInfoResult) parent.getItemAtPosition(position);
            Application.getInstance().getModel().putBean(Constants.SELECTED_PRODUCT, selectedProduct);
            try {
                ActivityHome activityHome = (ActivityHome) Application.getInstance().getCurrentActivity();
                activityHome.showProductDetailActivity();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                ActivitySearchResult activitySearchResult = (ActivitySearchResult) Application.getInstance().getCurrentActivity();
                activitySearchResult.showProductDetailsActivity();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
