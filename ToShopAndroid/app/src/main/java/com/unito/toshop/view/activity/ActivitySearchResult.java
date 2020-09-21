package com.unito.toshop.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.unito.toshop.Application;
import com.unito.toshop.Constants;
import com.unito.toshop.R;
import com.unito.toshop.model.ProductInfoResult;
import com.unito.toshop.view.adapter.AdapterListFHProducts;

import java.util.HashMap;
import java.util.List;

public class ActivitySearchResult extends AppCompatActivity {

    private ListView searchResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_product_result);
        this.initComponents();
        this.setActions();
    }

    private void initComponents() {
        searchResult = findViewById(R.id.searchResult);
        HashMap<String, Integer> images = (HashMap<String, Integer>) Application.getInstance().getModel().getBean(Constants.IMAGES);
        searchResult.setAdapter(new AdapterListFHProducts((List<ProductInfoResult>) Application.getInstance().getModel().getBean(Constants.PRODUCT_SEARCH_RESULT), images));
    }

    private void setActions() {
        searchResult.setOnItemClickListener(Application.getInstance().getHomeControl().getActionGoToProductDetail());
    }

    public void showProductDetailsActivity() {
        Intent intent = new Intent(this, ActivityProductDetails.class);
        startActivity(intent);
        //finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, ActivityHome.class);
        startActivity(intent);
        finish();
    }
}
