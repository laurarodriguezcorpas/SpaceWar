package spacewar;

import spacewar.Spaceship.LastMovement;

public class Meteorite extends SpaceObject{
	private static final double METEORITE_SPEED = 0;	//No se mueve
	private static final double SPACESHIP_ROTATION_SPEED = .00;	//Pero gira sobre sí mismo
	private static final int SPACESHIP_COLLISION_FACTOR = 400;	//??
	
	public Meteorite () {
		this.initMeteorite(Math.random() * 1000, Math.random() * 600, Math.random() * 360);
	}
	
	public void initMeteorite(double coordX, double coordY, double facingAngle) {	//Spawn
		this.setPosition(coordX, coordY);
		this.setVelocity(0, 0);
		this.setFacingAngle(facingAngle);
	}
}
