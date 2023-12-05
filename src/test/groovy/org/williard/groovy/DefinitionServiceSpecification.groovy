package org.williard.groovy

import org.jeasy.random.EasyRandom
import org.jeasy.random.EasyRandomParameters
import org.springframework.http.ResponseEntity
import org.williard.VueAndJava.client.DictionaryClientImpl
import org.williard.VueAndJava.dto.DictionaryDto
import org.williard.VueAndJava.dto.Meaning
import org.williard.VueAndJava.service.DefinitionService
import spock.lang.Specification

class DefinitionServiceSpecification extends Specification{

    EasyRandom generator;
    DictionaryClientImpl dictionaryClient;
    DefinitionService definitionService;

    void setup(){
        generator = new EasyRandom(new EasyRandomParameters().collectionSizeRange(1,2));
        dictionaryClient = Mock();
        definitionService = new DefinitionService(dictionaryClient);
    };

    def "should be able to mock a dictionary definition api"() {
        given:
        //Use j-easy easyRandom to mock up our Pojo response
        DictionaryDto mockedDto = generator.nextObject(DictionaryDto.class);

        when:
        //Tell Spock to ultimately run this method which will be tested
        ResponseEntity<DictionaryDto> actualResponse = definitionService.getDefinition("aWord")

        then:
        //This method within the aboe definitionService.getDefinition("String"), so we are going to ask it to return our mocked DTO
        //1 * says the method should be called only once. It a) tests that it is called only once and b) tells the method to return the mockedDto
        1 * dictionaryClient.getDefinition("aWord") >> mockedDto
        actualResponse != null
        actualResponse.getBody() != null

    }

}
