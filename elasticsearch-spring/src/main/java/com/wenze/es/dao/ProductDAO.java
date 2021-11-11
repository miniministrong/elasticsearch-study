package com.wenze.es.dao;

import com.wenze.es.model.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDAO extends ElasticsearchRepository<Product, Long> {

}
