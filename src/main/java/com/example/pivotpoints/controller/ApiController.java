package com.example.pivotpoints.controller;

import com.example.pivotpoints.model.PivotResponse;
import com.example.pivotpoints.service.PivotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {

    private PivotService pivotService;

    @Autowired
    public ApiController(PivotService pivotService) {
        this.pivotService = pivotService;
    }

    @GetMapping("/points")
    public PivotResponse getPoints(@RequestParam(name = "date") String date, @RequestParam(name = "days") Integer days) {
        return pivotService.getPivotPoints(date, days);
    }

}
