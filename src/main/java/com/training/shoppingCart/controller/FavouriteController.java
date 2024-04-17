package com.training.shoppingCart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.training.shoppingCart.DTO.FavouriteDto;
import com.training.shoppingCart.model.Product;
import com.training.shoppingCart.service.FavouriteService;

import java.util.List;

@RestController
@RequestMapping("/api/favourites")
public class FavouriteController {

    @Autowired
    private  FavouriteService favouriteService;





    @PostMapping
    public ResponseEntity<Void> addToFavourite(@RequestBody Product product) {
        favouriteService.addToFavourite(product);
        return ResponseEntity.noContent().build();
    }


    @GetMapping
    public ResponseEntity<List<FavouriteDto>> getFavouriteList() {
        List<FavouriteDto> favouriteDtos = favouriteService.getfavouriteList();
        return ResponseEntity.ok(favouriteDtos);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeItem(@PathVariable Integer id) {
        favouriteService.removeitem(id);
        return ResponseEntity.noContent().build();
    }
}
