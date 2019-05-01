package spacewar;

public class Projectile extends SpaceObject {

	private static final int LIFESPAN = 2000;	//Tiempo que va a durar la bala (2seg)
	private static final double PROJECTILE_SPEED = 25;
	private static final int PROJECTILE_COLLISION_FACTOR = 200;

	private final Player owner;	//Tengo que saber de quién es la bala, por si una bala mía me da a mi mismo, que la ignore
	private final long firingInstant;
	private final int id;

	private boolean isHit = false;	//Cuando isHit es true, es que ha golpeado a alguien

	public Projectile(Player owner, int id) {
		this.setCollisionFactor(PROJECTILE_COLLISION_FACTOR);
		this.owner = owner;
		this.firingInstant = System.currentTimeMillis();
		this.initProjectile();
		this.id = id % 800; // 800 = maxNumProjectiles	//Para reutilizar el pool de balas
	}

	public Player getOwner() {
		return this.owner;
	}

	public static int getLifespan() {
		return LIFESPAN;
	}

	public int getId() {
		return id;
	}

	public boolean isAlive(long thisInstant) {
		return (thisInstant < (this.firingInstant + LIFESPAN));
	}

	public boolean isHit() {
		return isHit;
	}

	public void setHit(boolean isHit) {
		this.isHit = isHit;
	}

	public void initProjectile() {
		this.setPosition(this.owner.getPosX(), this.owner.getPosY());
		this.setFacingAngle(owner.getFacingAngle());
		this.setVelocity(Math.cos(this.getFacingAngle() * Math.PI / 180) * PROJECTILE_SPEED,
				Math.sin(this.getFacingAngle() * Math.PI / 180) * PROJECTILE_SPEED);
	}

}
