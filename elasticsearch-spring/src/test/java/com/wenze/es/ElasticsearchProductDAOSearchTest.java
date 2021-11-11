package com.wenze.es;

import com.wenze.es.dao.ProductDAO;
import com.wenze.es.model.Product;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

@SpringBootTest
public class ElasticsearchProductDAOSearchTest {
  @Autowired
  private ProductDAO productDAO;

  /**
   * term查询
   * search（termQueryBuilder）调用搜索方法，参数查询构建器对象
   */
  @Test
  void termQuery(){
    TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("category", "手机");
    Iterable<Product> products = productDAO.search(termQueryBuilder);
    for (Product product : products) {
      System.out.println(product);
    }
  }

  /**
   * term查询加分页
   */
  @Test
  void termQueryByPage(){
    int pageNum = 0;
    int pageSize = 5;
    // 设置查询分页
    PageRequest pageRequest = PageRequest.of(pageNum, pageSize);
    TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("category", "手机");
    Iterable<Product> products = productDAO.search(termQueryBuilder, pageRequest);
    for (Product product : products) {
      System.out.println(product);
    }
  }
}
