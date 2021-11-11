package com.wenze.elasticsearch.document;

import org.apache.http.HttpHost;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

public class ESTestDocumentDelete {
  public static void main(String[] args) throws IOException {
    // 创建ES客户端
    RestHighLevelClient esClient = new RestHighLevelClient(
        RestClient.builder(new HttpHost("localhost", 9200, "http"))
    );
    // 删除数据
    DeleteRequest request = new DeleteRequest();
    request.index("user").id("1001");
    DeleteResponse response = esClient.delete(request, RequestOptions.DEFAULT);

    // 响应状态
    System.out.println(response.toString());

    // 关闭客户端
    esClient.close();
  }
}
