class Animal {
    public int age;
    public String gender;

    public boolean isMammal() {
        //default implementation (can be overridden if needed)
        return this instanceof Zebra;
    }

    public void mate() {
        
    }
}

class Duck extends Animal {
    public String beakColor = "yellow";

    public void swim() {
        
    }

    public void quack() {
        
    }
}

class Fish extends Animal {
    private int sizeInFt;
    private boolean canEat;

    //constructor
    public Fish(int sizeInFt, boolean canEat) {
        this.sizeInFt=sizeInFt;
        this.canEat=canEat;
    }

    public void swim() {
        
    }

    //getters
    public int getSizeInFt() {
        return sizeInFt;
    }

    public boolean canEat() {
        return canEat;
    }
}

class Zebra extends Animal {
    public boolean is_wild;

    //constructor
    public Zebra(boolean is_wild) {
        this.is_wild = is_wild;
    }

    public void run() {
        
    }
}

public class UmlDiagram2 {
    public static void main(String[] args) {
        Duck duck = new Duck();
        duck.age = 2;
        duck.swim();
        duck.mate();

        Fish fish = new Fish(3, true);
        fish.age = 1;
        fish.gender = "Male";
        fish.mate();

        Zebra zebra = new Zebra(true);
        zebra.age = 5;
        zebra.gender = "Male";
        System.out.println("Is Zebra wild? " + zebra.is_wild);
        zebra.run();

    }
}
