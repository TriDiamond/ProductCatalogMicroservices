package dev.benish.productcatalogservice.repository;

import dev.benish.productcatalogservice.model.Category;
import dev.benish.productcatalogservice.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @Transactional
    public void testCategoryJpa(){
       Optional<Category> optionalCategory = categoryRepository.findById(1L);
       if(optionalCategory.isPresent()){
            Category category = optionalCategory.get();
            System.out.println();
            for(Product product : category.getProducts()){
                System.out.println(product.getName());
            }
          // System.out.println();
       }
    }

}