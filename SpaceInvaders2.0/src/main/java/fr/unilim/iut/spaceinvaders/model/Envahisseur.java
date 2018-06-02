package fr.unilim.iut.spaceinvaders.model;

public class Envahisseur extends Sprite {
	
	private static Direction sens = Direction.GAUCHE;
	
	public Envahisseur(Dimension dimension, Position positionOrigine, int vitesse) {
		super(dimension, positionOrigine, vitesse);
	}
	
	public static Direction getSens() {
		return sens;
	}

	public static void setSens(Direction sens) {
		Envahisseur.sens = sens;
	}
}
