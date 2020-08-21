package com.unito.toshop.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.unito.toshop.Application;
import com.unito.toshop.R;
import com.unito.toshop.model.ProductClientResult;
import com.unito.toshop.model.ProductInfoResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdapterListSHProducts extends BaseAdapter {

    private static final String TAG = AdapterListSHProducts.class.getSimpleName();

    private List<ProductClientResult> productsList;
    private HashMap<String, Integer> images = new HashMap<>();

    public AdapterListSHProducts() {
        productsList = new ArrayList<ProductClientResult>();
    }

    public AdapterListSHProducts(List<ProductClientResult> productsList, HashMap<String, Integer> images) {
        this.productsList = productsList;
        this.images = images;
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


        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) Application.getInstance().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.row_products, null);

            viewHolder.productImage = convertView.findViewById(R.id.productImage);
            viewHolder.productName = convertView.findViewById(R.id.productName);
            viewHolder.productPrice = convertView.findViewById(R.id.productPrice);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ProductClientResult product = this.productsList.get(position);
        String path = (String) product.getProductIcon();
        Log.e(TAG,"Path: " + path);

        viewHolder.productName.setText(product.getProductName());
        viewHolder.productPrice.setText("€ " + product.getProductPrice());
        try{
            viewHolder.productImage.setImageResource(images.get(path));
        } catch (Exception e) {
            Log.e(TAG,"Errore, settata immagine di default");
            viewHolder.productImage.setImageResource(R.drawable.missing_image);
        }
        return convertView;

/*
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
 */
    }

    private static class ViewHolder {
        ImageView productImage;
        TextView productName;
        TextView productPrice;
    }

}
