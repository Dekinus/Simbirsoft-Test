package com.example.demo.controller;

import com.example.demo.dto.WordDto;
import com.example.demo.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test")

public class WordController {

    private final WordService wordService;

    @Autowired
    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @GetMapping("/count")
    public List<WordDto> countWords(@RequestParam String url) {
        return wordService.count(url);
    }
}
