package org.example.llv2;

import org.example.llv2.system.controller.SystemController;
import org.example.llv2.wiseSaying.controller.WiseSayingController;

public class App {
    public void run() {
        System.out.println("== 명언 앱 ==");

        SystemController systemController = new SystemController();
        WiseSayingController wiseSayingController = new WiseSayingController();

        while(true){
            System.out.printf("명령) ");
            // trim() : 혹시 있을지 모르는 좌우공백제거
            String command = Container.getScanner().nextLine().trim();
            Rq rq = new Rq(command);

            switch (rq.getActionCode()){
                case "종료":
                    systemController.exit();
                    return;
                case "등록":
                    wiseSayingController.write();
                    break;
                case "목록":
                    wiseSayingController.list();
                    break;
                case "삭제":
                    wiseSayingController.remove(rq);
                    break;
                case "수정":
                    wiseSayingController.modify(rq);
                    break;
                case "빌드":
                    wiseSayingController.build();
                    break;
            }
        }


    }
}
