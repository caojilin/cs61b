import java.util.function.Function;

public class Animal {
    String name;

//    Animal(){
//        System.out.println("animal no args constructor");
//    }
    Animal(String name){
        this.name = name;
    }
    void eat(Flesh f) {
        System.out.println("animal eats flesh");
    }

    void sleep() {
        System.out.println("animal sleeps");
    }

    static void out() {
        System.out.println("animal out");
    }

//    void eat(Pork p){
//        System.out.println("animal eats pork");
//    }

    @Override
    public String toString() {
        return "Animal";
    }

    public static void main(String[] args) {
        Animal d = new Dog("");
//        Animal a = new Animal();
//        Water w = new Water();
//        Pork p = new Pork();
//        Flesh f = new Flesh();
//        Dog g = (Dog) d;
        Dog z = new Dog("");
//        ((Animal)z).eat(f);

        System.out.println(z.name);
    }
}
