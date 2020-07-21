package com.unito.toshop.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import com.unito.toshop.Application;
import com.unito.toshop.Constants;
import com.unito.toshop.R;
import com.unito.toshop.control.servercalls.ProductManager;
import com.unito.toshop.model.LoginResult;
import com.unito.toshop.view.adapter.HomeTabAdapter;
import com.unito.toshop.view.fragment.ViewFHProduct;

public class ActivityHome extends AppCompatActivity {

    private static final String TAG = ActivityHome.class.getSimpleName();

    private SearchView search;
    private ImageView login;
    private TextView welcomeUser;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.initComponents();
        this.setActions();
    }

    private void initComponents() {
        search = findViewById(R.id.searchProducts);
        login = findViewById(R.id.imageViewLogin);
        welcomeUser = findViewById(R.id.textWelcomeUser);
        LoginResult loginResult = (LoginResult) Application.getInstance().getPersistentModel().getPersistentBean(Constants.LOGIN_RESULT, LoginResult.class);
        if (loginResult != null) {
            welcomeUser.setVisibility(TextView.VISIBLE);
            welcomeUser.setText("Welcome " + loginResult.getName() + "!");
        }
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPagerHome);
        viewPager.setAdapter(new HomeTabAdapter(this.getSupportFragmentManager(),2));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("First Hand");
        tabLayout.getTabAt(1).setText("Second Hand");
    }

    private void setActions() {
        search.setOnQueryTextListener(Application.getInstance().getHomeControl().getActionSearchProducts());
        login.setOnClickListener(Application.getInstance().getHomeControl().getActionGoToLogin());
        //todo aggiungere azioni?
//        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
//        tabLayout.addOnTabSelectedListener(null);
    }

    private void refreshDataFromServer() {
        ProductManager.getInstance().getProductsListFH();
        ProductManager.getInstance().getProductsListSH();
    }

    public void showLoginActivity() {
        this.refreshDataFromServer();
        Intent intent = new Intent(this, ActivityLogin.class);
        startActivity(intent);
        finish();
    }

    public void showProductDetailActivity() {
        this.refreshDataFromServer();
        Intent intent = new Intent(this, ActivityProductDetails.class);
        startActivity(intent);
        //finish();
    }

    public void showActivitySearchResult() {
        this.refreshDataFromServer();
        Intent intent = new Intent(this, ActivitySearchResult.class);
        startActivity(intent);
        finish();
    }

}
