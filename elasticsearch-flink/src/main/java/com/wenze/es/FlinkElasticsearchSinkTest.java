package com.wenze.es;

import org.apache.flink.api.common.functions.RuntimeContext;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.elasticsearch.ElasticsearchSinkFunction;
import org.apache.flink.streaming.connectors.elasticsearch.RequestIndexer;
import org.apache.flink.streaming.connectors.elasticsearch7.ElasticsearchSink;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.Requests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlinkElasticsearchSinkTest {
  public static void main(String[] args) throws Exception {
    // 构建flink的环境对象
    StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();
    // source：数据的输入
    DataStreamSource<String> source = executionEnvironment.socketTextStream("localhost", 9999);
    // 使用ESBuilder构建输出
    List<HttpHost> hosts = new ArrayList<>();
    hosts.add(new HttpHost("localhost", 9200, "http"));
    ElasticsearchSink.Builder<String> esBuilder = new ElasticsearchSink.Builder<>(hosts, new ElasticsearchSinkFunction<String>() {
      @Override
      public void process(String s, RuntimeContext runtimeContext, RequestIndexer requestIndexer) {
        Map<String, String> jsonMap = new HashMap<>();
        jsonMap.put("data", s);

        IndexRequest indexRequest = Requests.indexRequest();
        indexRequest.index("flink-index");
        indexRequest.id("9001");
        indexRequest.source(jsonMap);

        requestIndexer.add(indexRequest);
      }
    });
    // sink：数据的输出
    esBuilder.setBulkFlushMaxActions(1);
    source.addSink(esBuilder.build());
    // 执行操作
    executionEnvironment.execute("flink-es");
  }
}
