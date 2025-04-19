package com.crud_example.app.client;

import com.crud_example.app.client.dto.response.PurchasesHistoryResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductClient {

    private static final String BASE_URL = "http://localhost:9001";
    private static final String GET_CUSTOMER_PURCHASES_HISTORY_BY_ID = "/purchases/{customerId}";
    private final WebClient.Builder webClientBuilder;

    @PostConstruct
    private void configureWebClient() {
        webClientBuilder.baseUrl(BASE_URL);
    }

    public PurchasesHistoryResponseDto getPurchasesHistory(UUID customerId) {
        return webClientBuilder
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(GET_CUSTOMER_PURCHASES_HISTORY_BY_ID)
                        .build(customerId))
                .retrieve()
                .bodyToMono(PurchasesHistoryResponseDto.class)
                .block();
    }

    //TODO: Hacer método como el de arriba pero que sea un post que crea un object y no devuelva nada(buscar como
    // declarar que una llamada no tiene cuerpo de respuesta con WEBCLIENT de spring webflux)

    public void createProductForCustomer(UUID customerId, ObjectRequestDto requestDto) {
        webClientBuilder
                .build()
                .post()
                .uri("/products/{customerId}", customerId)
                .bodyValue(requestDto)
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}
