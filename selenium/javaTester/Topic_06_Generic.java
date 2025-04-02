package javaTester;

import java.util.ArrayList;

public class Topic_06_Generic {
    public static void main(String[] args) {
        ArrayList students = new ArrayList();
        students.add("John");
        students.add("Jane");
        students.add("Bob");
        students.add(18);
        students.add(22);
        students.add(true);

        //Generic
        ArrayList<String> students1 = new ArrayList<>();
        students1.add("John");
        students1.add("Jane");
        students1.add("Bob");
    }
}
