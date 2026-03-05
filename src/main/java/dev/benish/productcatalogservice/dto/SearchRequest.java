package dev.benish.productcatalogservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchRequest {
    private String productName;
    private int pageNo;
    private int pageSize;
    private SortParam sortParameter;
}
