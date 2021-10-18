package com.example.demo.service;


import com.example.demo.dto.WordDto;

import java.util.List;

public interface WordService {

    List<WordDto> count(String url);

}
