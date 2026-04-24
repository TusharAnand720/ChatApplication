package chatapp.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig {

    @Bean
    public MongoClient mongoClient() {
        System.out.println("Connecting to MongoDB!");
        String connectionString = "mongodb+srv://ChatApplicationService:7a0Vsb2PoMZ2Z73Q@clusterchatapplication.2jijjyi.mongodb.net/?appName=ClusterChatApplication";
//
        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();
//
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();

        return MongoClients.create(settings);
    }

    @Bean  // ← required for Spring Data repositories to work
    public MongoTemplate mongoTemplate(MongoClient mongoClient) {
        return new MongoTemplate(mongoClient, "ChatApplication");
    }
}
