package org.williard.VueAndJava.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.williard.VueAndJava.service.DefinitionService;

@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:8080")
@RequiredArgsConstructor
@RestController
@Log4j2
public class DefinitionController {

//    private static final Logger LOGGER = LogManager.getLogger(DefinitionController.class);

    private final DefinitionService definitionService;

    @Operation(summary = "Returns the definition of the provided word")
    @ApiResponse(responseCode = "200", description = "Returned Definition")
    @ApiResponse(responseCode = "404", description = "Word not found")
    @ApiResponse(responseCode = "500", description = "Unexpected Error")
    @GetMapping("/define/{word}")

    ResponseEntity<Object> getInvoices(@PathVariable String word) {
        log.trace("Trace level log message");
        log.debug("Debug level log message");
        log.info("Info level log message");
        log.warn("Warn level log message");
        log.error("Error level log message");
        log.fatal("Fatal level log message");
        return definitionService.getDefinition(word);
    }
}
