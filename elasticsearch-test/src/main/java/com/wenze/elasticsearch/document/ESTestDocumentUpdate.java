package com.wenze.elasticsearch.document;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wenze.elasticsearch.model.User;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

public class ESTestDocumentUpdate {
  public static void main(String[] args) throws IOException {
    // 创建ES客户端
    RestHighLevelClient esClient = new RestHighLevelClient(
        RestClient.builder(new HttpHost("localhost", 9200, "http"))
    );
    // 修改数据
    UpdateRequest request = new UpdateRequest();
    request.index("user").id("1001");
    // 局部修改
    request.doc(XContentType.JSON, "sex", "女");

    UpdateResponse response = esClient.update(request, RequestOptions.DEFAULT);

    // 响应状态
    System.out.println(response.getResult());

    // 关闭客户端
    esClient.close();
  }
}
