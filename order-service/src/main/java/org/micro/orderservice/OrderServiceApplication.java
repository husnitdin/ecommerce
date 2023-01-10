package org.micro.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

//    @Bean
//    public Customizer<Resilience4JCircuitBreakerFactory> defaultCustomizer() {
//        return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
//                .circuitBreakerConfig(CircuitBreakerConfig.custom()
//                        .slidingWindowSize(5)
//                        .failureRateThreshold(2)
//                        .permittedNumberOfCallsInHalfOpenState(5)
//                        .slowCallDurationThreshold(Duration.ofMillis(200))
//                        .slowCallRateThreshold(50.0F)
//                        .waitDurationInOpenState(Duration.ofSeconds(30))
//                        .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
//                        .build())
//                .timeLimiterConfig(TimeLimiterConfig.custom()
//                        .timeoutDuration(Duration.ofSeconds(3000))
//                        .cancelRunningFuture(true)
//                        .build())
//                .build());
//    }
//
//    @Autowired
//    private ClientRegistrationRepository clientRegistrationRepository;
//    @Autowired
//    private OAuth2AuthorizedClientRepository oAuth2AuthorizedClientRepositor;
//
//    @Bean
//    @LoadBalanced
//    public RestTemplate restTemplate() {
//
//        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.setInterceptors(Arrays.asList(new RestTemplateInterceptor(
//                clientManager(clientRegistrationRepository, oAuth2AuthorizedClientRepositor)
//        )));
//        return restTemplate;
//    }
//
//    @Bean
//    public OAuth2AuthorizedClientManager clientManager(
//            ClientRegistrationRepository clientRegistrationRepository,
//            OAuth2AuthorizedClientRepository oAuth2AuthorizedClientRepository
//    ) {
//
//        OAuth2AuthorizedClientProvider oAuth2AuthorizedClientProvider =
//                OAuth2AuthorizedClientProviderBuilder.builder()
//                        .clientCredentials()
//                        .build();
//
//        DefaultOAuth2AuthorizedClientManager oAuth2AuthorizedClientManager =
//                new DefaultOAuth2AuthorizedClientManager(
//                        clientRegistrationRepository, oAuth2AuthorizedClientRepository);
//
//        oAuth2AuthorizedClientManager.setAuthorizedClientProvider(oAuth2AuthorizedClientProvider);
//
//        return oAuth2AuthorizedClientManager;
//
//    }
}