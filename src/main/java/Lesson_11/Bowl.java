package Lesson_11;

class Bowl {
    private int foodAmount;

    public Bowl(int initialFood) {
        this.foodAmount = Math.max(initialFood, 0); // Гарантируем, что количество еды не отрицательное
    }

    public boolean decreaseFood(int amount) {
        if (amount <= 0) return false;
        if (foodAmount >= amount) {
            foodAmount -= amount;
            return true;
        }
        return false;
    }

    public void addFood(int amount) {
        if (amount > 0) {
            foodAmount += amount;
            System.out.println("В миску добавлено " + amount + " еды. Теперь в миске " + foodAmount + " еды.");
        }
    }

    public int getFoodAmount() {
        return foodAmount;
    }
}
