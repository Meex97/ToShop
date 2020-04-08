package com.project.Toshop.api;


import com.project.Toshop.entity.Cart;
import com.project.Toshop.entity.Client;
import com.project.Toshop.entity.ProductInOrder;
import com.project.Toshop.entity.User;
import com.project.Toshop.form.ItemForm;
import com.project.Toshop.service.CartService;
import com.project.Toshop.service.ProductInOrderService;
import com.project.Toshop.service.ProductService;
import com.project.Toshop.service.UserService;
import com.project.Toshop.repository.ProductInOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;
import java.util.Collections;

/**
 * Created By Zhu Lin on 3/11/2018.
 */
@CrossOrigin
@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;
    @Autowired
    ProductInOrderService productInOrderService;
    @Autowired
    ProductInOrderRepository productInOrderRepository;

    @PostMapping("")
    public ResponseEntity<Cart> mergeCart(@RequestBody Collection<ProductInOrder> productInOrders, Principal principal) {
        User user = userService.findOne(principal.getName());
        try {
            cartService.mergeLocalCart(productInOrders, user);
        } catch (Exception e) {
            ResponseEntity.badRequest().body("Merge Cart Failed");
        }
        return ResponseEntity.ok(cartService.getCart(user));
    }

    @GetMapping("")
    public Cart getCart(Principal principal) {
        User user = userService.findOne(principal.getName());
        return cartService.getCart(user);
    }


    @PostMapping("/add")
    public boolean addToCart(@RequestBody ItemForm form, Principal principal) {
        var productInfo = productService.findOne(form.getProductId());
        try {
            mergeCart(Collections.singleton(new ProductInOrder(productInfo, form.getQuantity())), principal);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @PutMapping("/{itemId}")
    public ProductInOrder modifyItem(@PathVariable("itemId") String itemId, @RequestBody Integer quantity, Principal principal) {
        User user = userService.findOne(principal.getName());
         productInOrderService.update(itemId, quantity, user);
        return productInOrderService.findOne(itemId, user);
    }

    @DeleteMapping("/{itemId}")
    public void deleteItem(@PathVariable("itemId") String itemId, Principal principal) {
        User user = userService.findOne(principal.getName());
         cartService.delete(itemId, user);
         // flush memory into DB
    }


    @PostMapping("/checkout/{email}")
    public ResponseEntity checkout(@PathVariable("email") String email,Principal principal) {
        //User user = userService.findOne(principal.getName());// Email as username
        Client client = userService.findOneClient(email);
        // System.out.println(client);
        cartService.checkout(client);
        return ResponseEntity.ok(null);
    }


}
