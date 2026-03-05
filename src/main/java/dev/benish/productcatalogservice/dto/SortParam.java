package dev.benish.productcatalogservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SortParam {
    private String parameter;
    private SortType type;
}
