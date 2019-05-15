package jilin.cao.animal;

import jilin.cao.animal.Dog;
public class Doglauncher extends Dog{

    public void get(){
        System.out.println("get");
        }
    public void set(){
        System.out.println("set");
    }
    /*@Override
    public void print(){
        System.out.println("overwrite");
    }*/
    /** a superclass static type cannot use subclass method*/
    public void print1(){
        System.out.println("subclass method");
    }

    public static void main(String[] args) {
        Dog d = new Doglauncher();
        d.get();
        d.print();
        Doglauncher b = (Doglauncher) d;
        b.print1();
    }
}
