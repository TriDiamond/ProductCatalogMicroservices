package dev.benish.productcatalogservice.controller;

import dev.benish.productcatalogservice.dto.SearchRequest;
import dev.benish.productcatalogservice.model.Product;
import dev.benish.productcatalogservice.service.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private ISearchService searchService;

    @PostMapping
    public Page<Product> search(@RequestBody SearchRequest searchRequest){
            return searchService.search(searchRequest.getProductName(),
                    searchRequest.getPageSize(),
                    searchRequest.getPageNo(),
                    searchRequest.getSortParameter());
    }
}
