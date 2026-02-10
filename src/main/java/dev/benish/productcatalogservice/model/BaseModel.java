package dev.benish.productcatalogservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseModel {
    @Id
    private Long id;
    private Date createdAt;  //store date in epoc format
    private Date lastUpdatedDate;
    private State state;  //rather than actually deleting the record mark it as inactive
}
