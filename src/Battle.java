public class Battle implements Runnable {
    private Character hero;
    private Character enemy;

    public Battle(Character hero, Character enemy){
        this.hero = hero;
        this.enemy = enemy;
    }

    @Override
    public void run() {
        while (hero.getHealth() > 0 && enemy.getHealth() > 0) {
            System.out.println(hero.getName() + ", Здоровье: " + hero.getHealth() + " | " + enemy.getName() + " Здоровье: " + enemy.getHealth());

            int heroAttack = hero.attack();
            if (heroAttack > 0){
                enemy.setHealth(enemy.getHealth() - heroAttack);
                System.out.println(hero.getName() + " наносит " + enemy.getName() + " " + heroAttack + " урона.");
            } else {
                System.out.println(hero.getName() + " промахивается.");
            }

            if (enemy.getHealth() <= 0){
                System.out.println(enemy.getName() + " побежден!");
                hero.setGold(hero.getGold() + enemy.getGold());
                hero.setExperience(hero.getExperience() + enemy.getExperience());
                break;
            }

            int enemyAttack = enemy.attack();
            if (enemyAttack > 0){
                hero.setHealth(hero.getHealth() - enemyAttack);
                System.out.println(enemy.getName() + " наносит " + hero.getName() + " " + enemyAttack + " урона.");
            } else {
                System.out.println(enemy.getName() + " промахивается.");
            }

            if (hero.getHealth() <= 0){
                System.out.println(hero.getName() + " побежден! Игра закончена.");
                break;
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Битва прерванаю");
            }
        }
    }
}
