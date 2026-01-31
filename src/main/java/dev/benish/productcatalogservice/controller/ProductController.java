package dev.benish.productcatalogservice.controller;

import dev.benish.productcatalogservice.dto.ProductRequestDto;
import dev.benish.productcatalogservice.dto.ProductResponseDto;
import dev.benish.productcatalogservice.dto.ResponseStatus;
import dev.benish.productcatalogservice.model.Product;
import dev.benish.productcatalogservice.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
     public ProductController(IProductService productService){
         this.productService = productService;
     }

    /*
     * Recieved json we say payload or requestBody
     */
    @PostMapping
    public ProductResponseDto createProduct(@RequestBody  ProductRequestDto request){

        return null;
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable("id") Long id){
       if(id < 1){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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
    public String getAllProducts(){
        return "helloWorld";
    }



}
