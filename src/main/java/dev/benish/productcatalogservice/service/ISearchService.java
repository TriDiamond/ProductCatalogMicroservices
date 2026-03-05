package dev.benish.productcatalogservice.service;

import dev.benish.productcatalogservice.dto.SortParam;
import dev.benish.productcatalogservice.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ISearchService {
    Page<Product> search(String productName,
                         int pageSize,
                         int pageNo,
                         SortParam sortParameter);
}
