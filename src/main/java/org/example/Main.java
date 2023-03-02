package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int count = 0;
        List<Wise> write = new ArrayList<>();
        System.out.println("== 명언 앱 ==");
        while(true){
            System.out.printf("명령) ");
            String i = sc.nextLine().trim();
            if(i.equals("종료")){
                break;
            }else if(i.equals("등록")){
                System.out.printf("명언 : ");
                String i2 = sc.nextLine();
                System.out.printf("작가 : ");
                String i3 = sc.nextLine();
                count++;
                write.add(new Wise(count,i2,i3));
                System.out.println(count+"번 명언이 등록되었습니다.");
            }else if(i.equals("목록")){
                System.out.println("번호 / 작가 / 명언");
                System.out.println("--------------------------");
                Collections.reverse(write);
                for (Wise w :write){
                    System.out.println(w.toString());
                }
            }else if((i.contains("삭제?id="))==true){
                int remove_id = Integer.parseInt(i.substring(6));
                int get_remove_id = -1;
                for (Wise w : write){
                    if(w.getId() == remove_id){
                        get_remove_id = w.getId();
                    }
                }
                try{
                    write.remove(write.get(get_remove_id));
                    System.out.println(remove_id+"번 명언이 삭제되었습니다.");
                }catch(IndexOutOfBoundsException e){
                    System.out.println(remove_id+"번 명언은 존재하지 않습니다.");
                }
            }else if((i.contains("수정?id="))==true){
                int correction_id = Integer.parseInt(i.substring(6));
                Wise get_correction_id = null;
                for (Wise w : write){
                    if(w.getId() == correction_id){
                        get_correction_id = w;
                    }
                }
                try{
                    System.out.println("명언(기존) : "+ get_correction_id.getWise());
                    System.out.printf("명언 : ");
                    String i4 = sc.nextLine();
                    get_correction_id.setWise(i4);
                    System.out.println("작가(기존) : "+ get_correction_id.getWriter());
                    System.out.printf("작가 : ");
                    String i5 = sc.nextLine();
                    get_correction_id.setWriter(i5);
                }catch(IndexOutOfBoundsException e){
                    System.out.println(correction_id+"번 명언은 존재하지 않습니다.");
                }
            }
        }
        sc.close();
    }
}


class Wise{
    private int id;
    private String wise;
    private String writer;

    public Wise(int id, String wise, String writer){
        this.id = id;
        this.wise = wise;
        this.writer = writer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWise() {
        return wise;
    }

    public void setWise(String wise) {
        this.wise = wise;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    @Override
    public String toString() {
        return this.id+ " / "+this.writer+" / "+this.wise;
    }
}