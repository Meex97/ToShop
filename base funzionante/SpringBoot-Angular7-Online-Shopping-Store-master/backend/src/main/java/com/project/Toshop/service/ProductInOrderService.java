package com.project.Toshop.service;

import com.project.Toshop.entity.ProductInOrder;
import com.project.Toshop.entity.User;

/**
 * Created By Zhu Lin on 1/3/2019.
 */
public interface ProductInOrderService {
    void update(String itemId, Integer quantity, User user);
    ProductInOrder findOne(String itemId, User user);
}
