package com.example.jxmall.controller;

import com.example.jxmall.entity.Product;
import com.example.jxmall.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    /* 创建新商品 */
    @PostMapping
    public ResponseEntity<Void> addProduct(@RequestBody Product product) {
        productRepository.save(product);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    /* 修改商品信息 */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updataProduct(@PathVariable long id, @RequestBody Product product) {
        if (productRepository.existsById(id)){
            int inventory = productRepository.findById(id).get().getInventory();
            product.setId(id);
            product.setInventory(inventory);
            productRepository.save(product);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Void>(HttpStatus.200);
    }

    /* 查询 - 根据商品id查找商品 */
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable long id) {
        return productRepository.findById(id).get();
    }

    /*  查询 - 查找所有商品 */
    //@RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    /*  查询 - 按照name查找商品 */
    @GetMapping(params = "name")
    public List<Product> getProductsByName(@RequestParam String name) {
        return productRepository.findByName(name);
    }

    /*  查询 - 根据name和描述模糊查询 */
    @GetMapping(params = {"name","description"})
    public List<Product> getProductsByNameAndDescription(@RequestParam String name, @RequestParam String description) {
        return productRepository.findByNameContainingAndDescriptionContaining(name, description);
    }

}