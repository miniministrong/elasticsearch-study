package com.wenze.es;

import com.wenze.es.dao.ProductDAO;
import com.wenze.es.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ElasticsearchProductDAOTest {
  @Autowired
  private ProductDAO productDAO;

  /**
   * 新增数据
   */
  @Test
  void save(){
    Product product = new Product();
    product.setId(2L);
    product.setTitle("华为手机");
    product.setCategory("手机");
    product.setPrice(2999.00);
    product.setImages("https://www.wenze.com/hw.jpg");
    productDAO.save(product);
  }

  /**
   * 修改
   */
  @Test
  void update(){
    Product product = new Product();
    product.setId(1L);
    product.setTitle("小米2手机");
    product.setCategory("手机");
    product.setPrice(9999.00);
    product.setImages("https://www.wenze.com/xm.jpg");
    productDAO.save(product);
  }

  /**
   * 根据id查询
   */
  @Test
  void findById(){
    Product product = productDAO.findById(1L).get();
    System.out.println(product);
  }

  /**
   * 查询所有
   */
  @Test
  void findAll(){
    Iterable<Product> products = productDAO.findAll();
    for (Product product : products) {
      System.out.println(product);
    }
  }

  /**
   * 删除
   */
  @Test
  void delete(){
    Product product = new Product();
    product.setId(2L);
    productDAO.delete(product);
  }

  /**
   * 删除全部
   */
  @Test
  void deleteAll(){
    productDAO.deleteAll();
  }

  /**
   * 批量新增
   */
  @Test
  void saveAll(){
    List<Product> productList = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      Product product = new Product();
      product.setId(Long.valueOf(i));
      product.setTitle("第" + i + "个小米手机");
      product.setCategory("手机");
      product.setPrice(1999.00 + i);
      product.setImages("https://www.wenze.com/xm.jpg");
      productList.add(product);
    }
    productDAO.saveAll(productList);
  }

  /**
   * 分页查询
   */
  @Test
  void findByPageable(){
    // 设置排序（排序方式，正序还是倒序，排序的id）
    Sort sort = Sort.by(Sort.Direction.DESC, "id");
    // 当前页，第一页从0开始，1表示第二页
    int pageNum = 0;
    // 每页显示多少条
    int pageSize = 5;
    // 设置查询分页
    PageRequest pageRequest = PageRequest.of(pageNum, pageSize, sort);
    // 分页查询
    Page<Product> productPage = productDAO.findAll(pageRequest);
    for (Product product : productPage.getContent()) {
      System.out.println(product);
    }
  }

}