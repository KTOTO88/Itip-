abstract class Monster {
    private String name;
    private int health;
    private int attackDamage;
    private static int count = 0;


    public Monster() {
        this.name = "Unknown";
        this.health = 100;
        this.attackDamage = 10;
        count++;
    }


    public Monster(String name, int health, int attackDamage) {
        this.name = name;
        this.health = health;
        this.attackDamage = attackDamage;
        count++;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }


    public static int getCount(){
        return count;
    }


    public void attack() {
        System.out.println("Выполняется атака: " + attackDamage + " единиц урона.");
    }


    public abstract void useAbility();
}


class Goblin extends Monster {
    private int loot;

    public Goblin() {
        super();
        this.loot = 0;
    }

    public Goblin(String name, int health, int attackDamage, int loot) {
        super(name, health, attackDamage);
        this.loot = loot;
    }

    public int getLoot() {
        return loot;
    }

    public void setLoot(int loot) {
        this.loot = loot;
    }

    @Override
    public void useAbility() {
        System.out.println("Гоблин применяет особую способность: украдено " + loot + " золота.");
    }
}


class Mermaid extends Monster {
    private boolean canSwim;

    public Mermaid() {
        super();
        this.canSwim = true;
    }

    public Mermaid(String name, int health, int attackDamage, boolean canSwim) {
        super(name, health, attackDamage);
        this.canSwim = canSwim;
    }

    public boolean isCanSwim() {
        return canSwim;
    }

    public void setCanSwim(boolean canSwim) {
        this.canSwim = canSwim;
    }

    @Override
    public void useAbility() {
        System.out.println("Русалка использует особую способность: плавает грациозно.");
    }
}


class Dragon extends Monster {
    private int fireBreathDamage;

    public Dragon() {
        super();
        this.fireBreathDamage = 50;
    }

    public Dragon(String name, int health, int attackDamage, int fireBreathDamage) {
        super(name, health, attackDamage);
        this.fireBreathDamage = fireBreathDamage;
    }

    public int getFireBreathDamage() {
        return fireBreathDamage;
    }

    public void setFireBreathDamage(int fireBreathDamage) {
        this.fireBreathDamage = fireBreathDamage;
    }

    @Override
    public void useAbility() {
        System.out.println("Дракон выпускает огненное дыхание и наносит " + fireBreathDamage + " единиц урона.");
    }
}


public class Main {
    public static void main(String[] args) {

        Goblin goblin = new Goblin("Гоблин", 50, 5, 10);
        Mermaid mermaid = new Mermaid("Русалка", 80, 15, true);
        Dragon dragon = new Dragon("Дракон", 200, 30, 100);


        System.out.println("Информация о гоблине:");
        System.out.println("Имя: " + goblin.getName());
        System.out.println("Здоровье: " + goblin.getHealth());
        System.out.println("Урон атаки: " + goblin.getAttackDamage());
        System.out.println("Добыча: " + goblin.getLoot());
        System.out.println("Количество созданных объектов: " + Monster.getCount());

        System.out.println();

        System.out.println("Информация о русалке:");
        System.out.println("Имя: " + mermaid.getName());
        System.out.println("Здоровье: " + mermaid.getHealth());
        System.out.println("Урон атаки: " + mermaid.getAttackDamage());
        System.out.println("Может плавать: " + mermaid.isCanSwim());
        System.out.println("Количество созданных объектов: " + Monster.getCount());

        System.out.println();

        System.out.println("Информация о драконе:");
        System.out.println("Имя: " + dragon.getName());
        System.out.println("Здоровье: " + dragon.getHealth());
        System.out.println("Урон атаки: " + dragon.getAttackDamage());
        System.out.println("Урон огненного дыхания: " + dragon.getFireBreathDamage());
        System.out.println("Количество созданных объектов: " + Monster.getCount());

        System.out.println();


        goblin.attack();
        goblin.useAbility();

        mermaid.attack();
        mermaid.useAbility();

        dragon.attack();
        dragon.useAbility();
    }
}