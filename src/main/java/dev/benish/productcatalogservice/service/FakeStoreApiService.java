package dev.benish.productcatalogservice.service;

import dev.benish.productcatalogservice.dto.FakeStoreProductDto;
import dev.benish.productcatalogservice.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class FakeStoreApiService implements IProductService{

    private RestTemplate restTemplate;

    @Autowired
    public FakeStoreApiService(RestTemplate restTemplate){
     this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductById(Long id) {
        /*FakeStoreProductDto fakeStoreProduct = restTemplate.getForObject("https://fakestoreapi.com/products/{id}",
                FakeStoreProductDto.class,
                id
                );
        if(fakeStoreProduct == null){
            throw new RuntimeException("Null PointerException");
        }*/

        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.getForEntity(
                "https://fakestoreapi.com/products/{id}",
                FakeStoreProductDto.class,
                id
         );

        if(responseEntity.hasBody()){
            return responseEntity.getBody().convertFakeStoreProductToProduct();
        }
      return null;
    }

    @Override
    public List<Product> getProduct() {
        return List.of();
    }

    @Override
    public void createProduct() {

    }
}
