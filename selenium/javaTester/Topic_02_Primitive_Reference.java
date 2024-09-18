package javaTester;

public class Topic_02_Primitive_Reference{
    int x;
    public static void main(String[] args){
        int x = 15;
        int y = x;
        System.out.println("x = " + x);
        System.out.println("y = " + y);
        x = 30;
        System.out.println("x = " + x);
        System.out.println("y = " + y);
        // Class
        // Instance 1
        Topic_02_Primitive_Reference c = new Topic_02_Primitive_Reference();
        c.x = 43;
        System.out.println(c.x);
        // Instance 2
        Topic_02_Primitive_Reference d = new Topic_02_Primitive_Reference();
        System.out.println(d.x);
        c.x = 16;
        d = c;
        System.out.println(c.x);
        System.out.println(d.x);
    }
}
