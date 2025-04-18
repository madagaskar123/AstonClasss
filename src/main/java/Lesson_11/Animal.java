package Lesson_11;

abstract class Animal {
    private static int animalCount = 0;
    private final String name;

    public Animal(String name) {
        this.name = name;
        animalCount++;
    }

    public abstract void run(int distance);
    public abstract void swim(int distance);

    public String getName() {
        return name;
    }

    public static int getAnimalCount() {
        return animalCount;
    }
}
