package com.elyes.ecommerce.product;


import com.elyes.ecommerce.exception.BuisnessException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductClient {
    @Value("${application.config.product-url}")
    private String productUrl ;
    private final RestTemplate restTemplate;

    public List<PurchaseResponse> purchaseProducts(List<PurchaseRequest> requestBody) {

        //create header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

// melange of requestBody and headers
        HttpEntity<List<PurchaseRequest>> request = new HttpEntity<>(requestBody, headers);

        ParameterizedTypeReference<List<PurchaseResponse>> responseType =
                new ParameterizedTypeReference<>() {}; //manage the responseType


        ResponseEntity<List<PurchaseResponse>> responseEntity =
                restTemplate.exchange(
                        productUrl + "/purchase",
                        HttpMethod.POST,
                        request,
                        responseType
                ); //using for send method : url ,method type , request, reponse type

        if (responseEntity.getStatusCode().isError()) {
            throw new BuisnessException("An error occurred while processing the request");
        }

        return responseEntity.getBody();
    }
}
