package dev.benish.productcatalogservice.controller;

import dev.benish.productcatalogservice.dto.ProductRequestDto;
import dev.benish.productcatalogservice.dto.ProductResponseDto;
import dev.benish.productcatalogservice.dto.ResponseStatus;
import dev.benish.productcatalogservice.exception.ControllerException;
import dev.benish.productcatalogservice.model.Product;
import dev.benish.productcatalogservice.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/*
 * Request recieve to the controller is json so we using restcontroller
 * rest controller recieve json and send json object not looking html data
 * not going to render(no view resolver)
 */

    /*Real-world analogy 🏗️

    @Controller → Reception desk (handles requests)

    @Service → Manager (decides what to do)

    @Component → Helper staff

    @Bean → External consultant you hired

    @Configuration → Office setup blueprint
    */

@RestController
public class ProductController {
    /*
     * 1) create Product
     * 2) get product by Id
     * 3) get all products
     */
     private IProductService productService;

     @Autowired
     public ProductController(@Qualifier("productService") IProductService productService){
         this.productService = productService;
     }

    /*
     * Recieved json we say payload or requestBody
     */
    @PostMapping("/products")
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody  ProductRequestDto request){
        ProductResponseDto response = new ProductResponseDto();
        Product product = productService.createProduct(request.convertToProduct());

        if(product == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ProductResponseDto productResponse = product.convert();
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable("id") Long id){
       if(id < 0){
            throw new IllegalArgumentException("Product Id not found");
        }
        if(id == 0 ){
            throw new IllegalArgumentException("Product Id starts with positive");
        }


        Product product  = productService.getProductById(id);
       if(product == null){
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }

       ProductResponseDto productResponse = product.convert();
      return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    /*@GetMapping("/products")
    public List<ProductResponseDto> getAllProducts(){
        List<ProductResponseDto> response = null;
        return response;
    }*/

    @GetMapping("/products")
    public List<ProductResponseDto> getAllProducts(){
        List<ProductResponseDto> productResponseDto = new ArrayList<>();
        List<Product> productList = productService.getProduct();
        for(Product product : productList){
            productResponseDto.add(product.convert());
        }
        return productResponseDto;
    }

    @PutMapping("/products/{id}")
    public ProductResponseDto updateProduct(@RequestBody  ProductRequestDto productRequestDto, @PathVariable("id") Long id){
        Product product = productService.replaceProduct(productRequestDto, id);
        return product.convert();
    }
}
