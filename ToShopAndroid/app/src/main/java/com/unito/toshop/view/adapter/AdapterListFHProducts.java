package com.unito.toshop.view.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.unito.toshop.Application;
import com.unito.toshop.Constants;
import com.unito.toshop.R;
import com.unito.toshop.control.servercalls.ImageManager;
import com.unito.toshop.model.ImageModel;
import com.unito.toshop.model.ProductInfoResult;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdapterListFHProducts extends BaseAdapter {

    private static final String TAG = AdapterListFHProducts.class.getSimpleName();

    private List<ProductInfoResult> productsList;
    private HashMap<String, Integer> images = new HashMap<>();

    public  AdapterListFHProducts(List<ProductInfoResult> productsList, HashMap<String, Integer> images) {
        this.productsList = productsList;
        this.images = images;
    }

    public AdapterListFHProducts() {
        this.productsList = new ArrayList<ProductInfoResult>();
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

        ProductInfoResult product = this.productsList.get(position);
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
    }

    private static class ViewHolder {
        ImageView productImage;
        TextView productName;
        TextView productPrice;
    }

    //TODO creare async task qui
    // https://www.tutorialspoint.com/android-asynctask-example-and-explanation

}
