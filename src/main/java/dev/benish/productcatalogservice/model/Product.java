package dev.benish.productcatalogservice.model;

import dev.benish.productcatalogservice.dto.ProductResponseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseModel{
    private String name;
    private String description;
    private double price;
    private int quantity;
    private String imageUrl;
    private Category category;

    public ProductResponseDto convert(){
       ProductResponseDto responseDto = new ProductResponseDto();
       Category category = new Category();
       responseDto.setName(this.getName());
       responseDto.setPrice(this.price);
       responseDto.setDescription(this.description);
       responseDto.setCategory(this.getCategory());

       return responseDto;
    }
}
