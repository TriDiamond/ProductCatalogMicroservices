package dev.benish.productcatalogservice.service;

import dev.benish.productcatalogservice.dto.SortParam;
import dev.benish.productcatalogservice.dto.SortType;
import dev.benish.productcatalogservice.model.Product;
import dev.benish.productcatalogservice.repository.ProductRepository;
import lombok.NonNull;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService implements ISearchService{
    @Autowired
    private ProductRepository productRepo;

    @Override
    public Page<Product> search(String query,
                                int pageSize,
                                int pageNo,
                                SortParam sortParameter) {
        Sort sort = null;
        if (sortParameter != null && sortParameter.getType() != null) {
            if (sortParameter.getType().equals(SortType.DESC)) {
                sort = Sort.by(sortParameter.getParameter()).descending();
            } else {
                sort = Sort.by(sortParameter.getParameter()).ascending();
            }
        }
       return productRepo.findProductsByName(query, PageRequest.of(pageNo, pageSize, sort));
       //return productRepo.findProductsByName(productName);

    }
}
