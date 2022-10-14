package works.weave.socks.cart;

import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.featurehub.client.ClientContext;
import io.featurehub.client.EdgeFeatureHubConfig;
import io.featurehub.client.FeatureHubConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
@EnablePrometheusEndpoint
public class CartApplication {
    public static void main(String[] args) {
        SpringApplication.run(CartApplication.class, args);
    }

  @Bean
  public FeatureHubConfig featureHubConfig() {
    String host = System.getenv("FEATUREHUB_EDGE_URL");

    if (host == null) {
      throw new RuntimeException("Unable to determine the host for FeatureHub");
    }

    String apiKey = System.getenv("FEATUREHUB_API_KEY");

    if (apiKey == null) {
      throw new RuntimeException("Unable to determine the API key for FeatureHub");
    }

    FeatureHubConfig config = new EdgeFeatureHubConfig(host, apiKey);
    config.init();

    return config;
  }
}
