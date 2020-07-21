package com.unito.toshop.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.unito.toshop.Application;
import com.unito.toshop.R;
import com.unito.toshop.model.ProductClientResult;

import java.util.ArrayList;
import java.util.List;

public class AdapterListSHProducts extends BaseAdapter {

    private List<ProductClientResult> productsList;

    public AdapterListSHProducts() {
        productsList = new ArrayList<ProductClientResult>();
    }

    public AdapterListSHProducts(List<ProductClientResult> productsList) {
        this.productsList = productsList;
    }

    @Override
    public int getCount() {
        return this.productsList.size();
    }

    @Override
    public Object getItem(int position) {
        return productsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) Application.getInstance().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.row_products, null);
        }

        ImageView productImage = convertView.findViewById(R.id.productImage);
        TextView productName = convertView.findViewById(R.id.productName);
        TextView productPrice = convertView.findViewById(R.id.productPrice);

        ProductClientResult product = this.productsList.get(position);
        //productImage.setImageBitmap(product.getProductimage()); bisogna convertire da byte[] a Bitmap o da byte[] in qualcosaltro

        productImage.setImageResource(R.drawable.missing_image); //metto l'immagine vuota intanto

        productName.setText(product.getProductName());
        productPrice.setText("€ " + product.getProductPrice());

        return convertView;
    }
}
