package spacewar;

public class Spaceship extends SpaceObject {

	private static final double SPACESHIP_SPEED = 0.6;
	private static final double SPACESHIP_BRAKES = 0.90;
	private static final double SPACESHIP_ROTATION_SPEED = 3.00;
	private static final int SPACESHIP_COLLISION_FACTOR = 400;
	private static final double SPACE_FRICTION = 0.95;	//La fricción hace que frene un 5%

	class LastMovement {	//almaceno el último movimiento de mi jugador
		boolean thrust = false;	//W: acelerar
		boolean brake = false;	//S: frenar
		boolean rotLeft = false;	//A
		boolean rotRight = false;	//D
	}

	private LastMovement lastMovement;	

	public Spaceship() {
		this.setCollisionFactor(SPACESHIP_COLLISION_FACTOR);
		// Randomize
		this.initSpaceship(Math.random() * 1000, Math.random() * 600, Math.random() * 360);	//spawn (generación) de naves
	}

	public void initSpaceship(double coordX, double coordY, double facingAngle) {
		this.setPosition(coordX, coordY);
		this.setVelocity(0, 0);
		this.setFacingAngle(facingAngle);
		lastMovement = new LastMovement();
	}

	public void loadMovement(boolean thrust, boolean brake, boolean rotLeft, boolean rotRight) {
		this.lastMovement.thrust = thrust;
		this.lastMovement.brake = brake;
		this.lastMovement.rotLeft = rotLeft;
		this.lastMovement.rotRight = rotRight;
	}

	public void calculateMovement() {
		this.multVelocity(SPACE_FRICTION);

		if (this.lastMovement.thrust) {
			this.incVelocity(Math.cos(this.getFacingAngle() * Math.PI / 180) * SPACESHIP_SPEED,
					Math.sin(this.getFacingAngle() * Math.PI / 180) * SPACESHIP_SPEED);
		}

		if (this.lastMovement.brake) {
			this.multVelocity(SPACESHIP_BRAKES);
		}

		if (this.lastMovement.rotLeft) {
			this.incFacingAngle(-SPACESHIP_ROTATION_SPEED);
		}

		if (this.lastMovement.rotRight) {
			this.incFacingAngle(SPACESHIP_ROTATION_SPEED);
		}

		this.applyVelocity2Position();

		lastMovement = new LastMovement();	//Comentar o no esta línea para temas de LAG
	}
}
