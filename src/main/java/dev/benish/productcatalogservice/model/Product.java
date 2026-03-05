package dev.benish.productcatalogservice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import dev.benish.productcatalogservice.dto.ProductResponseDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product extends BaseModel{
    private String name;
    private String description;
    private double price;
    private int quantity;
    private String imageUrl;
    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.ALL) //composition if category table updated product table also modified
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
