package org.example;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.ArrayList;
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
                try (
                        FileWriter fw = new FileWriter("test.txt", true);
                        BufferedWriter bw = new BufferedWriter(fw);
                ){
                    for (int k=write.size()-1;k>=0;k--){
                        bw.write(write.get(k).toString());
                        bw.newLine();
                    }
                    bw.flush();
                }catch (IOException e) {
                    throw new RuntimeException(e);
                }
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

                File f = new File("test.txt");
                if(f.isFile()){
                    try(
                        FileReader rw = new FileReader("test.txt");
                        BufferedReader br = new BufferedReader(rw);
                    ){
                        String readLine = null;
                        while((readLine = br.readLine()) != null){
                            System.out.println(readLine);
                        }
                    }catch (IOException e){
                        System.out.println(e);
                    }

                }
                for (int k=write.size()-1;k>=0;k--){
                    System.out.println(write.get(k));
                }
            }else if((i.contains("삭제?id="))==true){
                int remove_id = Integer.parseInt(i.substring(6));
                int get_remove_id = -1;
                for (int i_remove=0;i_remove<write.size();i_remove++){
                    if(write.get(i_remove).getId() == remove_id){
                        get_remove_id = i_remove;
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
                int get_correction_id=-1;
                for (int i_correction = 0; i_correction<write.size();i_correction++){
                    if(write.get(i_correction).getId() == correction_id){
                        get_correction_id = i_correction;
                    }
                }
                try{
                    System.out.println("명언(기존) : "+ write.get(get_correction_id).getWise());
                    System.out.printf("명언 : ");
                    write.get(get_correction_id).setWise(sc.nextLine());

                    System.out.println("작가(기존) : "+ write.get(get_correction_id).getWriter());
                    System.out.printf("작가 : ");
                    write.get(get_correction_id).setWriter(sc.nextLine());
                }catch(Exception e){
                    System.out.println(correction_id+"번 명언은 존재하지 않습니다.");
                }
            }else if(i.equals("빌드")){
                try{
                    JSONArray jsonArray = new JSONArray();
                    for(int k=0;k<write.size();k++){
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("id", write.get(k).getId());
                        jsonObject.put("content", write.get(k).getWise());
                        jsonObject.put("author", write.get(k).getWriter());
                        jsonArray.add(jsonObject);
                    }
                    try{
                        FileWriter fileWriter = new FileWriter("data.json");
                        fileWriter.write(jsonArray.toString());
                        fileWriter.flush();
                        fileWriter.close();
                        System.out.println("data.json 파일의 내용이 갱신되었습니다.");
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                }catch(Exception e){
                    e.printStackTrace();
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