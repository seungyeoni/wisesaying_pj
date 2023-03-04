package org.example.llv2.wiseSaying.service;

import org.example.llv2.Util;
import org.example.llv2.wiseSaying.entity.WiseSaying;
import org.example.llv2.wiseSaying.repository.WiseSayingRepository;

import java.util.List;
import java.util.stream.Collectors;

public class WiseSayingService {
    private final WiseSayingRepository wiseSayingRepository;

    public List<WiseSaying> findAll() {
        return wiseSayingRepository.findAll();
    }

    public WiseSaying findById(long id) {
        return wiseSayingRepository.findById(id);
    }

    public WiseSayingService() {
        wiseSayingRepository = new WiseSayingRepository();
    }

    public long write(String content, String authorName) {
        return wiseSayingRepository.write(content, authorName);

    }

    public void remove(WiseSaying wiseSaying) {
        wiseSayingRepository.remove(wiseSaying);
    }

    public void modify(WiseSaying wiseSaying, String content, String authorName) {
        wiseSayingRepository.modify(wiseSaying, content, authorName);
    }

    public void build() {
        List<WiseSaying> wiseSayings = wiseSayingRepository.findAll();

        Util.file.mkdir("prodBuild");

        String json = "[" + wiseSayings
                .stream()
                .map(wiseSaying -> wiseSaying.toJson())
                .collect(Collectors.joining(",\n")) + "]";

        Util.file.saveToFile("prodBuild/data.json", json);
    }
}
