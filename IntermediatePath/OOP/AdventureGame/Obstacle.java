import java.util.Random;
public class Obstacle {
	private String name;
	private int damage, award, health, maxNumber;

	public Obstacle(String name, int damage, int health, int award, int maxNumber) {
		this.name = name;
		this.damage = damage;
		this.award = award;
		this.health = health;
		this.maxNumber = maxNumber;


		if (this.name.equals("Snake")) {
			Random random = new Random();
			this.damage = random.nextInt(3) + 3;
		}
	}
	
	public int count() {
		Random r = new Random();
		return r.nextInt(this.maxNumber) + 1;
	}

	public String getName() {
		return name;
	}

	public int getDamage() {
		return damage;
	}

	public int getAward() {
		return award;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

}
