package com.wenze.elasticsearch.document;

import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

public class ESTestDocumentBatchDelete {
  public static void main(String[] args) throws IOException {
    // 创建ES客户端
    RestHighLevelClient esClient = new RestHighLevelClient(
        RestClient.builder(new HttpHost("localhost", 9200, "http"))
    );
    // 批量删除数据
    BulkRequest request = new BulkRequest();
    request.add(new DeleteRequest().index("user").id("1001"));
    request.add(new DeleteRequest().index("user").id("1002"));
    request.add(new DeleteRequest().index("user").id("1003"));
    request.add(new DeleteRequest().index("user").id("1004"));
    request.add(new DeleteRequest().index("user").id("1005"));
    request.add(new DeleteRequest().index("user").id("1006"));
    request.add(new DeleteRequest().index("user").id("1007"));
    request.add(new DeleteRequest().index("user").id("1008"));
    BulkResponse response = esClient.bulk(request, RequestOptions.DEFAULT);

    // 响应状态
    System.out.println(response.toString());

    // 关闭客户端
    esClient.close();
  }
}
