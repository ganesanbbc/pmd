package com.cts.day1.dao;


import com.cts.day1.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "products", path = "products")
public interface ProductJPARepository extends JpaRepository<Product, Long>{


}
