package org.example.llv2.wiseSaying.controller;

import org.example.llv2.Container;
import org.example.llv2.Rq;
import org.example.llv2.wiseSaying.entity.WiseSaying;

import java.util.ArrayList;
import java.util.List;

public class WiseSayingController {
    private long lastWiseSayingId;
    private final List<WiseSaying> wiseSayings;

    public WiseSayingController(){
        lastWiseSayingId = 0;
        wiseSayings = new ArrayList<>();
    }

    private WiseSaying findById(int id) {
        for(WiseSaying wiseSaying : wiseSayings){
            if(wiseSaying.getId() == id){
                return wiseSaying;
            }
        }
        return  null;
    }

    public void write() {
        long id = lastWiseSayingId + 1;
        System.out.printf("명언 : ");
        String content = Container.getScanner().nextLine().trim();
        System.out.printf("작가 : ");
        String authorName = Container.getScanner().nextLine().trim();

        WiseSaying wiseSaying = new WiseSaying(id, content, authorName);
        wiseSayings.add(wiseSaying);

        System.out.printf("%d번 명언이 등록되었습니다. \n", id);
        lastWiseSayingId = id;
    }

    public void list() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("-".repeat(30));

        for (int i = wiseSayings.size()-1; i>=0; i--){
            WiseSaying wiseSaying = wiseSayings.get(i);

            System.out.printf("%d / %s / %s\n" , wiseSaying.getId(), wiseSaying.getAuthorName(), wiseSaying.getContent());
        }
    }

    public void remove(Rq rq){
        int id = rq.getIntParam("id", -1);

        if(id == -1){
            System.out.println("id(정수)를 입력해주세요.");
            return;
        }

        // 입력된 id와 일치하는 명언객체 찾기
        WiseSaying wiseSaying = findById(id);

        if(wiseSaying == null){
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
            return;
        }

        // 찾은 명언객체를 리스트에서 제거
        wiseSayings.remove(wiseSaying);

        System.out.printf("%d번 명언이 삭제되었습니다.\n", id);
    }

    public void modify(Rq rq) {
        int id = rq.getIntParam("id", -1);

        if(id == -1){
            System.out.println("id(정수)를 입력해주세요.");
            return;
        }

        // 입력된 id와 일치하는 명언객체 찾기
        WiseSaying wiseSaying = findById(id);

        if(wiseSaying == null){
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
            return;
        }

        System.out.printf("명언(기존) : %s\n", wiseSaying.getContent());
        System.out.printf("명언 : ");
        String content = Container.getScanner().nextLine().trim();
        System.out.printf("작가(기존) : %s\n", wiseSaying.getAuthorName());
        System.out.printf("작가 : ");
        String authorName = Container.getScanner().nextLine().trim();

        wiseSaying.setAuthorName(authorName);
        wiseSaying.setContent(content);



        System.out.printf("%d번 명언이 수정되었습니다.\n", id);
    }
}
