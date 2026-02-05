package dev.benish.productcatalogservice.service;

import dev.benish.productcatalogservice.client.FakeStoreAPIClient;
import dev.benish.productcatalogservice.dto.FakeStoreProductDto;
import dev.benish.productcatalogservice.dto.ProductRequestDto;
import dev.benish.productcatalogservice.model.Product;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;


@Service
public class FakeStoreApiService implements IProductService{

    private FakeStoreAPIClient fakeStoreAPIClient;

    public FakeStoreApiService(FakeStoreAPIClient fakeStoreAPIClient){
        this.fakeStoreAPIClient = fakeStoreAPIClient;
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

        ResponseEntity<FakeStoreProductDto> responseEntity = fakeStoreAPIClient.requestForEntity(
                HttpMethod.GET,
                "https://fakestoreapi.com/products/{id}",
                null,
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
        /*
          List<FakeStoreProductDto>.class // runtime doesn't which going to be add
          Type erasure come in to the picture
          In runtime inside generics its going to erase
          while compiling check type safety but in runtime its erase
         */
        List<Product> productList = new ArrayList<>();
        ResponseEntity<FakeStoreProductDto[]> responseEntity = fakeStoreAPIClient.requestForEntity(
                HttpMethod.GET,
                "https://fakestoreapi.com/products",
                null,
                FakeStoreProductDto[].class);

        FakeStoreProductDto[] fakeStoreProduct = null;
        if(fakeStoreAPIClient.validateResponseForArray(responseEntity)){
            fakeStoreProduct = responseEntity.getBody();
        }
        for(FakeStoreProductDto itrProduct : fakeStoreProduct){
            productList.add(itrProduct.convertFakeStoreProductToProduct());
        }
        return productList;
    }



    @Override
    public void createProduct() {

    }

    @Override
    public Product replaceProduct(ProductRequestDto productRequestDto, Long id) {
        FakeStoreProductDto fakeStoreProductDto = productRequestDto.convertToFakeProductDto();
        ResponseEntity<FakeStoreProductDto> responseEntity =
                fakeStoreAPIClient.requestForEntity(
                        HttpMethod.PUT,
                        "https://fakestoreapi.com/products/{id}",
                        FakeStoreProductDto.class, FakeStoreProductDto.class,
                        id);
        if(fakeStoreAPIClient.validateResponse(responseEntity)){
           FakeStoreProductDto responseFakeStoreProductDto =  responseEntity.getBody();
           return responseFakeStoreProductDto.convertFakeStoreProductToProduct();
        }
        return null;
    }
}
