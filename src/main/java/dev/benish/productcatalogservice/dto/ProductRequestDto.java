package dev.benish.productcatalogservice.dto;

import dev.benish.productcatalogservice.model.Category;
import dev.benish.productcatalogservice.model.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDto {
    private Long id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private String imageUrl;  //link og s3bucket
    private Category category;

    //convert to fakeProductDto

    public FakeStoreProductDto convertToFakeProductDto(){
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(this.getId());
        fakeStoreProductDto.setCategory(this.getCategory().getName());
        fakeStoreProductDto.setTitle(this.getName());
        fakeStoreProductDto.setDescription(this.getDescription());
        fakeStoreProductDto.setPrice((float)this.getPrice());
        fakeStoreProductDto.setImageUrl(this.getImageUrl());

        return fakeStoreProductDto;
    }


    public Product convertToProduct(){
        Product product = new Product();
        Category category = new Category();
        product.setId(this.getId());
        product.setName(this.getName());
        product.setPrice(this.getPrice());
        category.setName(this.getCategory().getName());
        product.setCategory(category);
        product.setDescription(this.getDescription());
        product.setImageUrl(this.imageUrl);

        return product;
    }

}


