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
import java.util.List;

public class AdapterListFHProducts extends BaseAdapter {

    private static final String TAG = AdapterListFHProducts.class.getSimpleName();

    private List<ProductInfoResult> productsList;

    public  AdapterListFHProducts(List<ProductInfoResult> productsList) {
        this.productsList = productsList;
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
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) Application.getInstance().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.row_products, null);
        }

        ImageView productImage = convertView.findViewById(R.id.productImage);
        TextView productName = convertView.findViewById(R.id.productName);
        TextView productPrice = convertView.findViewById(R.id.productPrice);

        ProductInfoResult product = this.productsList.get(position);

        productImage.setImageResource(R.drawable.missing_image); //metto l'immagine vuota intanto

        //String path = (String) product.getProductIcon().subSequence(15,product.getProductIcon().length());
        //ImageModel im = ImageManager.getInstance().getImage(path);
        Icon icon = Icon.createWithFilePath("src/main/res/img/aspira.jpg");
        productImage.setImageIcon(icon);

/*
        String path = (String) product.getProductIcon().subSequence(8,product.getProductIcon().length());
        String path2 = "src/main/res/drawable/" + path;

        File imgFile = new File(path2);
        Log.e(TAG,"Image path: " + path2);

        if(imgFile.exists()){
            System.out.println("Image exists *********************************");
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            productImage.setImageBitmap(myBitmap);
        }
*/
        productName.setText(product.getProductName());
        productPrice.setText("€ " + product.getProductPrice());

        return convertView;
    }

    //TODO creare async task qui
    // https://www.tutorialspoint.com/android-asynctask-example-and-explanation

}
