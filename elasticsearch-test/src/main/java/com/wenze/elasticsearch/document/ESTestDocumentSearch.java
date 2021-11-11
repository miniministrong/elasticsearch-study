package com.wenze.elasticsearch.document;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

public class ESTestDocumentSearch {
  public static void main(String[] args) throws IOException {
    // 创建ES客户端
    RestHighLevelClient esClient = new RestHighLevelClient(
        RestClient.builder(new HttpHost("localhost", 9200, "http"))
    );
    // 查询数据
    GetRequest request = new GetRequest();
    request.index("user").id("1001");
    GetResponse response = esClient.get(request, RequestOptions.DEFAULT);

    // 响应状态
    System.out.println(response.getSourceAsString());

    // 关闭客户端
    esClient.close();
  }
}
