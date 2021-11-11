package com.wenze.elasticsearch.index;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;

import java.io.IOException;

public class ESTestIndexDelete {
  public static void main(String[] args) throws IOException {
    // 创建ES客户端
    RestHighLevelClient esClient = new RestHighLevelClient(
        RestClient.builder(new HttpHost("localhost", 9200, "http"))
    );
    // 删除索引
    DeleteIndexRequest user = new DeleteIndexRequest("user");
    AcknowledgedResponse response = esClient.indices().delete(user, RequestOptions.DEFAULT);

    // 响应状态
    System.out.println(response.isAcknowledged());

    //关闭ES客户端
    esClient.close();
  }
}
