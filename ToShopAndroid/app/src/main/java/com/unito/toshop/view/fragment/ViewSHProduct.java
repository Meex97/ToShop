package com.unito.toshop.view.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.unito.toshop.Application;
import com.unito.toshop.Constants;
import com.unito.toshop.R;
import com.unito.toshop.control.servercalls.ProductManager;
import com.unito.toshop.model.ProductClientResult;
import com.unito.toshop.view.adapter.AdapterListSHProducts;

import java.util.List;

public class ViewSHProduct extends Fragment {

    private ListView listSHProducts;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sh_products,container,false);
        listSHProducts = view.findViewById(R.id.listSHProducts);
        swipeRefreshLayout = view.findViewById(R.id.swipeContainer);
        swipeRefreshLayout.setColorSchemeColors(
                getResources().getColor(android.R.color.holo_blue_bright),
                getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_orange_light),
                getResources().getColor(android.R.color.holo_red_light)
        );
        List<ProductClientResult> list = (List<ProductClientResult>) Application.getInstance().getModel().getBean(Constants.PRODUCT_LIST_SH);
        listSHProducts.setAdapter(new AdapterListSHProducts(list));
        this.setActions();
        return view;
    }

    private void setActions() {
        listSHProducts.setOnItemClickListener(Application.getInstance().getHomeControl().getActionGoToProductDetail());
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ProductManager.getInstance().getProductsListFH();
                ProductManager.getInstance().getProductsListSH();
                // To keep animation for 4 seconds
                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        // Stop animation (This will be after 3 seconds)
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 4000); // Delay in millis
            }
        });
    }

}
