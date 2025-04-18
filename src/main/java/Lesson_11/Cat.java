package Lesson_11;

class Cat extends Animal {
    private static int catCount = 0;
    private static final int MAX_RUN_DISTANCE = 200;
    private boolean isFed = false;

    public Cat(String name) {
        super(name);
        catCount++;
    }

    @Override
    public void run(int distance) {
        if (distance <= MAX_RUN_DISTANCE) {
            System.out.println(getName() + " пробежал " + distance + " м.");
        } else {
            System.out.println(getName() + " не может пробежать " + distance + " м. (максимум " + MAX_RUN_DISTANCE + " м.)");
        }
    }

    @Override
    public void swim(int distance) {
        System.out.println(getName() + " не умеет плавать.");
    }

    public void eat(Bowl bowl, int amount) {
        if (bowl.decreaseFood(amount)) {
            isFed = true;
            System.out.println(getName() + " поел из миски и теперь сыт.");
        } else {
            System.out.println(getName() + " не смог поесть - в миске недостаточно еды.");
        }
    }

    public boolean isFed() {
        return isFed;
    }

    public static int getCatCount() {
        return catCount;
    }
}

