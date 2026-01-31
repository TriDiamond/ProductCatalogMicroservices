package dev.benish.productcatalogservice.dto;

import dev.benish.productcatalogservice.model.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDto {
    private String name;
    private String description;
    private double price;
    private int quantity;
    private String imageUrl;  //link og s3bucket
    private Category category;
}
