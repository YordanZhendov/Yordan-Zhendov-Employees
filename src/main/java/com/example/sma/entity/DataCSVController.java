package com.example.sma.entity;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DataCSVController {

    private final ServiceLogic serviceLogic;

    @GetMapping("/csv-data")
    public ResponseEntity<String> getCSVData(){
        return  ResponseEntity.ok(serviceLogic.getOrderedEmployees());
    }
}
