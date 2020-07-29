package com.unito.toshop.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.unito.toshop.Application;
import com.unito.toshop.Constants;
import com.unito.toshop.R;
import com.unito.toshop.model.ProductInfoResult;

public class ActivityProductDetails extends AppCompatActivity {

    private ImageView productImage, addToCart;
    private TextView productName, productQuantity, productDescription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produt_details);
        this.initComponents();
        this.setActions();
    }

    private void initComponents() {
        ProductInfoResult product = (ProductInfoResult) Application.getInstance().getModel().getBean(Constants.SELECTED_PRODUCT);

        productImage = findViewById(R.id.imageViewProductImage);
        productName = findViewById(R.id.textName);
        productQuantity = findViewById(R.id.textQuantity);
        productDescription = findViewById(R.id.textDescription);
        addToCart = findViewById(R.id.imageViewAddToCart);

        //TODO productImage.set
        productName.setText(product.getProductName());
        productQuantity.setText(product.getProductStock().toString());
        productDescription.setText(product.getProductDescription());
    }

    private void setActions() {
        addToCart.setOnClickListener(Application.getInstance().getProductDetailsControl().getActionAddToCart());
    }

    public void showActivityLogin() {
        Intent intent = new Intent(this, ActivityLogin.class);
        startActivity(intent);
        this.finish();
    }
}
