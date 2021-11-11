package com.wenze.elasticsearch.document;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wenze.elasticsearch.model.User;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

public class ESTestDocumentInsert {
  public static void main(String[] args) throws IOException {
    // 创建ES客户端
    RestHighLevelClient esClient = new RestHighLevelClient(
        RestClient.builder(new HttpHost("localhost", 9200, "http"))
    );
    // 插入数据
    IndexRequest request = new IndexRequest();
    request.index("user").id("1001");

    User user = new User();
    user.setName("张三");
    user.setAge(30);
    user.setSex("男");

    // 向ES插入数据，必须将数据转换成json格式
    ObjectMapper mapper = new ObjectMapper();
    String userJson = mapper.writeValueAsString(user);

    request.source(userJson, XContentType.JSON);
    IndexResponse response = esClient.index(request, RequestOptions.DEFAULT);

    // 响应状态
    System.out.println(response.getResult());

    // 关闭客户端
    esClient.close();
  }
}