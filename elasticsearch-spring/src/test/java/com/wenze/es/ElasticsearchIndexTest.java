package com.wenze.es;

import com.wenze.es.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

@SpringBootTest
class ElasticsearchIndexTest {

  @Autowired
  private ElasticsearchRestTemplate elasticsearchRestTemplate;

  /**
   * 创建索引
   */
  @Test
  void createIndex() {
    // 创建索引，系统初始化会自动创建索引
    System.out.println("创建索引");
  }

  /**
   * 删除索引
   */
  @Test
  void deleteIndex(){
    // 创建索引，系统初始化会自动创建索引
    boolean flag = elasticsearchRestTemplate.deleteIndex(Product.class);
    System.out.println("删除索引：" + flag);
  }

}
