package com.anfinogenov.springbootcrud;

public class Test {
    public static void main(String[] args) {
        String[] arrStr = "ubuntu      2222    2221 76 15:39 ?        00:00:00 java -jar /home/ubuntu/app/SpringBootCRUD-0.0.1-SNAPSHOT.jar".split("      ");
        String result = arrStr[1].substring(0,4);
        System.out.println(result);
    }
}
