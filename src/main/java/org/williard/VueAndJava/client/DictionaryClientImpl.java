package org.williard.VueAndJava.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.williard.VueAndJava.dto.DictionaryDto;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class DictionaryClientImpl {

    @Value("${dictionary.apiUrl}")
    private String baseUrl;

    @Autowired
    private RestTemplate restTemplate;

    public DictionaryDto getDefinition(String word){
        DictionaryDto response = null;
        String restUrl = this.baseUrl + word;

        try{
            ResponseEntity<List<DictionaryDto>> restResponse = restTemplate.exchange(restUrl, HttpMethod.GET, this.getRequestEntity() ,new ParameterizedTypeReference<List<DictionaryDto>>() {});
            if(restResponse != null && restResponse.getBody() != null){
               response = restResponse.getBody().get(0);
            }
        } catch (Exception e){
            log.error(e.getMessage());
        }
        return response;
    }

    private HttpEntity getRequestEntity(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        return entity;
    }

}
