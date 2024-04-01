import java.util.Scanner;
import java.util.Random;

public class Game {
    private static Character hero;

    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();

    private static void createHero(){
        System.out.println("Создание персонажа. Введите имя вашего героя:");
        String name = scanner.next();
        hero = new Hero(name, 120, 20, 25, 1, 0, 0);
        System.out.println("Персонаж создан: " + hero.getName());
    }

    public void start(){
        createHero();
        while (true){
            System.out.println("Куда вы хотите пойти?\n1. К торговцу\n2. В тёмный лес\n3. На выход");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    goToMerch();    
                    break;
                case 2:
                    goToForest();
                    break;
                case 3:
                    System.out.println("Выход из игры...");
                    return;
                default:
                    System.out.println("Неверный ввод.");
                    break;
            }
        }
    }

    private static void goToMerch(){
        System.out.println("Торговец еще не вышел на работу.");
    }

    private static void goToForest(){
        while (true){
            Character enemy = random.nextBoolean() ? 
            new Goblin("Гоблин", 70, 10, 20, 1, 200, 50) :
            new Skeleton("Скелет", 50, 15, 10, 1, 150, 30);
            //гоблин будет посильнее скелета, такая вот несправедливость игрового мира
            System.out.println("На вас нападает дикий " + enemy.getName() + "!");

            Battle battle = new Battle(hero, enemy);
            Thread thread = new Thread(battle);

            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Бой был прерван.");
            }

            if (enemy.getHealth() <= 0){
                System.out.println("Вы победили " + enemy.getName());
                System.out.println("Получено " + enemy.getGold() + " золота и " + enemy.getExperience() + " опыта.");
                System.out.println("1. Продолжить бой\n2. Вернуться в город");
                int choice = scanner.nextInt();
                if (choice == 2){
                    break;
                }
            } else if (hero.getHealth() <= 0){
                System.out.println(hero.getName() + " был побежден!");
                createHero();
                //после поражения можно сразу же начать новую игру
                break;
            }
        }
    }
}
