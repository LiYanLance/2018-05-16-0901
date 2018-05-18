package com.example.jxmall.controller;

import com.example.jxmall.entity.Product;
import com.example.jxmall.repository.ProductRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductRepositry productRepositry;

    /* 添加 */
    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productRepositry.save(product);
    }

    /* 查询 - 对商品的名称和描述进行模糊查询 */
    @GetMapping("/{characters}")
    public List<Product> getProducts(@PathVariable String characters) {
        return productRepositry.findByNameContainingOrDescriptionContaining(characters, characters);
    }

    /* 修改 - 输入商品的名称，描述，价格*/
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void updataProduct(@PathVariable long id, @RequestBody Product product) {
        if (productRepositry.existsById(id)){
            int inventory = productRepositry.findById(id).get().getInventory();
            product.setId(id);
            product.setInventory(inventory);
            productRepositry.save(product);
        }
    }
}
