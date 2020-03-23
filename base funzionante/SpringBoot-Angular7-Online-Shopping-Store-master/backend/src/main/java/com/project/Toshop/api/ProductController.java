package com.project.Toshop.api;

import com.project.Toshop.entity.Client;
import com.project.Toshop.entity.ProductClient;
import com.project.Toshop.service.CategoryService;
import com.project.Toshop.service.ProductService;
import com.project.Toshop.entity.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@CrossOrigin
@RestController
public class ProductController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    /**
     * Show All Categories
     */

    @GetMapping("/product")
    public Page<ProductInfo> findAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                     @RequestParam(value = "size", defaultValue = "3") Integer size) {
        PageRequest request = PageRequest.of(page - 1, size);
        return productService.findAll(request);
    }

   /* @GetMapping("/product/Supplier")
    public Page<ProductInfo> findAllByIdUtente(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                     @RequestParam(value = "size", defaultValue = "3") Integer size, Long idUtente) {
        PageRequest request = PageRequest.of(page - 1, size);
        return productService.findByIdUtente(request, idUtente);
    }
*/
    @GetMapping("/product/{productId}")
    public ProductInfo showOne(@PathVariable("productId") String productId) {

        ProductInfo productInfo = productService.findOne(productId);

        return productInfo;
    }


    @PostMapping("/seller/producto/new")
    public ResponseEntity create(@Valid @RequestBody ProductInfo product/*, BindingResult bindingResult*/) {
        // System.out.println("salvo scemo");
        /*ProductInfo productIdExists = productService.findOne(product.getProductId());


        if (productIdExists != null) {
           bindingResult
                    .rejectValue("productId", "error.product",
                            "There is already a product with the code provided");
        }
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult);
        }*/
        return ResponseEntity.ok(productService.save(product));
    }


    @PostMapping("/client/producto/new")
    public ResponseEntity createBaratto(@Valid @RequestBody ProductClient product) {

       // ProductClient productClient = new ProductClient(product);
        return ResponseEntity.ok(productService.save(product));
    }

    @PutMapping("/seller/product/{id}/edit")
    public ResponseEntity edit(@PathVariable("id") String productId,
                               @Valid @RequestBody ProductInfo product,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult);
        }
        if (!productId.equals(product.getProductId())) {
            return ResponseEntity.badRequest().body("Id Not Matched");
        }

        return ResponseEntity.ok(productService.update(product));
    }

    @DeleteMapping("/seller/product/{id}/delete")
    public ResponseEntity delete(@PathVariable("id") String productId) {
        productService.delete(productId);
        return ResponseEntity.ok().build();
    }

}
