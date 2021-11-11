package com.wenze.elasticsearch.index;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;

import java.io.IOException;

public class ESTestIndexSearch {
  public static void main(String[] args) throws IOException {
    // 创建ES客户端
    RestHighLevelClient esClient = new RestHighLevelClient(
        RestClient.builder(new HttpHost("localhost", 9200, "http"))
    );
    // 查询索引
    GetIndexRequest user = new GetIndexRequest("user");
    GetIndexResponse response = esClient.indices().get(user, RequestOptions.DEFAULT);

    // 响应状态
    System.out.println(response.getAliases());
    System.out.println(response.getMappings());
    System.out.println(response.getSettings());

    //关闭ES客户端
    esClient.close();
  }
}
