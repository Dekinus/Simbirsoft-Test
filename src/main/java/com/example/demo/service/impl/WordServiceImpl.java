package com.example.demo.service.impl;

import com.example.demo.dto.WordDto;
import com.example.demo.entity.Word;
import com.example.demo.repository.WordRepository;
import com.example.demo.service.WordService;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class WordServiceImpl implements WordService {

    private final WordRepository wordRepository;

    private final String REG = "[—«»/&–\\s,.!?\";:\\[\\]()\\n\\r\\t]+";

    @Override
    public List<WordDto> count(String url) {
        Document test = null;
        try {
            test = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] data = new String[0];
        if (test != null) {
            data = test.body().text().toLowerCase().split(REG);
        }
        Map<String, Integer> wordsAndCount = new HashMap<>();
        for (String datum : data) {
            if (wordsAndCount.containsKey(datum)) {
                wordsAndCount.put(datum, wordsAndCount.get(datum) + 1);
            } else {
                wordsAndCount.put(datum, 1);
            }
        }

        wordRepository.deleteAll();
        List<WordDto> wordDtoList = new ArrayList<>();
        wordsAndCount.forEach((word, count) ->
        {
            Word temp = new Word();
            temp.setWord(word);
            temp.setCounter(count);
            wordRepository.save(temp);
            wordDtoList.add(convertWordToWordDto(temp));
        });

        return wordDtoList;
    }

    private WordDto convertWordToWordDto(Word word) {
        WordDto dto = new WordDto();
        dto.setWord(word.getWord());
        dto.setCount(word.getCounter());
        return dto;
    }
}
