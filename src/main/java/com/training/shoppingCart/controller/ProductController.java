package com.training.shoppingCart.controller;

import com.training.shoppingCart.DTO.ProductDto;
import com.training.shoppingCart.constant.WebConstants;
import com.training.shoppingCart.model.Product;
import com.training.shoppingCart.requestWrapper.ProductRequestWrapper;
import com.training.shoppingCart.response.JSONObject;
import com.training.shoppingCart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/product/api")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {
    @Autowired
    private  ProductService productService;
    @Autowired
    private MessageSource messageSource;
    @PostMapping
    public ResponseEntity<JSONObject> addProduct(@RequestBody ProductRequestWrapper request,@RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization) {
         productService.addProduct(request);
        String message = messageSource.getMessage("productAddSuccess", null, Locale.ENGLISH);
        JSONObject data = new JSONObject(WebConstants.KEY_STATUS_SUCCESS, 200, message );
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    // Endpoint to update an existing product
    @PutMapping("/{id}")
    public ResponseEntity<JSONObject> updateProduct(@RequestBody ProductRequestWrapper request,@RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization, @PathVariable Integer id) {
      productService.updateProduct(request,id);
        String message = messageSource.getMessage("productUpdateSuccess", null, Locale.ENGLISH);
        JSONObject data = new JSONObject(WebConstants.KEY_STATUS_SUCCESS, 200, message );
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        // Call the service method to get all products
        List<ProductDto> productDtos = productService.getAllProduct();

        // Return the list of products in the response
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }

    // Endpoint to delete a product by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id,@RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization) {
        try {
            productService.deleteProduct(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{id}/statistics")
    public ResponseEntity<ProductDto> getProductStatistics(@PathVariable Integer id,@RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization) {

        ProductDto productDto = productService.getStatistics(id);


        return ResponseEntity.ok(productDto);
    }
}
