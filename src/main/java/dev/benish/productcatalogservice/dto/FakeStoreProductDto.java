package dev.benish.productcatalogservice.dto;


import dev.benish.productcatalogservice.model.Category;
import dev.benish.productcatalogservice.model.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private float price;
    private String description;
    private String category;
    private String imageUrl;

    public Product convertFakeStoreProductToProduct(){
        Product product = new Product();
        Category category = new Category();
        product.setId(this.getId());
        product.setName(this.getTitle());
        product.setPrice(this.getPrice());
        category.setName(this.getCategory());
        product.setCategory(category);
        product.setDescription(this.getDescription());
        product.setImageUrl(this.imageUrl);

        return product;
    }
}

