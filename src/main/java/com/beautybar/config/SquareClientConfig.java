package com.beautybar.config;

import com.squareup.square.SquareClient;
import com.squareup.square.core.Environment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SquareClientConfig {

    /**
     * Alternatively, you can omit the token when constructing the client.
     * In this case, the SDK will automatically read the token from the SQUARE_TOKEN environment variable:
     */
    @Bean
    public SquareClient squareClient() {
        return SquareClient.builder()
                .environment(Environment.SANDBOX)
                .token("YOUR_SANDBOX_ACCESS_TOKEN")
                .build();
    }
}
