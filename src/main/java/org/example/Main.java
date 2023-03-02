package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int count = 0;
        System.out.println("== 명언 앱 ==");
        while(true){
            System.out.printf("명령) ");
            String i = sc.nextLine();
            if(i.equals("종료")){
                break;
            }else if(i.equals("등록")){
                System.out.printf("명언 : ");
                String i2 = sc.nextLine();
                System.out.printf("작가 : ");
                String i3 = sc.nextLine();
                count++;
                System.out.println(count+"번 명언이 등록되었습니다.");
            }
        }
        sc.close();
    }
}