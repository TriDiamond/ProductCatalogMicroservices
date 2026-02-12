package dev.benish.productcatalogservice.dto;

import dev.benish.productcatalogservice.model.BaseModel;
import dev.benish.productcatalogservice.model.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDto  {
    private Long id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private String imageUrl;
    private Category category;
}
