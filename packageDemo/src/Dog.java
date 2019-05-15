public class Dog extends Animal {
    String name;
//    Dog(){
//        super("AFDAS");
//    }
    Dog(String name){
        super(name);
        System.out.println("Dog no args constructor");
    }
    void eat(Pork p){
        System.out.println("dog eats pork");
    }
    void eat(Flesh f){
        System.out.println("dog eats flesh");
    }
    void eat(Water w){
        System.out.println("dog eats water");
    }
    void sleep(){
        System.out.println("dog sleeps");
    }
    static void out(){
        System.out.println("dog out");
    }

    @Override
    public String toString() {
        return "Dog";
    }
}
