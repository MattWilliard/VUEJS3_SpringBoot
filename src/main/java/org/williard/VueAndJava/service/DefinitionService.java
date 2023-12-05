package org.williard.VueAndJava.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.williard.VueAndJava.client.DictionaryClientImpl;
import org.williard.VueAndJava.dto.DictionaryDto;

@Service
@RequiredArgsConstructor
public class DefinitionService {

    private final DictionaryClientImpl dictionaryClient;

    public ResponseEntity<Object> getDefinition(String word){
        DictionaryDto dictionaryDto = dictionaryClient.getDefinition(word);
        if(dictionaryDto != null ){
            return new ResponseEntity<>(dictionaryDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}
