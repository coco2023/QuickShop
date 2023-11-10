package com.umistore.imsys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.HashMap;
import java.util.Map;

@RestController
// http://localhost:9001/api/mappings
public class MappingsController {

    private final RequestMappingHandlerMapping handlerMapping;

    @Autowired
    public MappingsController(RequestMappingHandlerMapping handlerMapping) {
        this.handlerMapping = handlerMapping;
    }

    @GetMapping("/api/mappings")
    public Map<String, String> getMappings() {
        Map<String, String> mappings = new HashMap<>();
        this.handlerMapping.getHandlerMethods().forEach((key, value) -> mappings.put(key.toString(), value.toString()));
        return mappings;
    }

}
