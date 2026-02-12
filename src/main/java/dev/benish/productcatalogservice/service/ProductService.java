package dev.benish.productcatalogservice.service;

import dev.benish.productcatalogservice.dto.ProductRequestDto;
import dev.benish.productcatalogservice.model.Product;
import dev.benish.productcatalogservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Qualifier("productService")
@Primary
public class ProductService implements IProductService{

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    @Override
    public Product getProductById(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        return productOptional.orElse(null);
    }

    @Override
    public List<Product> getProduct() {
        List<Product> products = productRepository.findAll();
        return products;
    }

    @Override
    public Product createProduct(Product product) {
        Optional<Product> productOptional = productRepository.findById(product.getId());

        if(productOptional.isEmpty()){
           return productRepository.save(product);
        }else{
            throw new RuntimeException("Already Given Id exists in the database !");
        }
    }

    @Override
    public Product replaceProduct(ProductRequestDto productRequest, Long id) {
        Optional<Product> productOptional = productRepository.findById(productRequest.getId());

        if(productOptional.isPresent()){
           return  productRepository.save(productRequest.convertToProduct());
        }else{
            throw new RuntimeException("No product exists in the given Id");
        }
    }
}
