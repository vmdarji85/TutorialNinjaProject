package experiments;

import java.util.Date;

public class Demo {
    public static void main(String[] args) {
        Date date = new Date();
        long epochMills = date.getTime();
        System.out.println(epochMills);
    }
}