package fr.unilim.iut.spaceinvaders.test;

import static org.junit.Assert.*;

import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import fr.unilim.iut.spaceinvaders.model.Collision;
import fr.unilim.iut.spaceinvaders.model.Dimension;
import fr.unilim.iut.spaceinvaders.model.Envahisseur;
import fr.unilim.iut.spaceinvaders.model.Missile;
import fr.unilim.iut.spaceinvaders.model.Position;

@SuppressWarnings("unused")
public class CollisionTest {
	private Collision collision = new Collision();
	
	@Test
	public void collisionParLaDroite(){
		Envahisseur envahisseur = new Envahisseur(new Dimension(3,2), new Position(3,2), 1);
		Missile missile = new Missile(new Dimension(1,2), new Position(4,2), 1);
		assertTrue(collision.detecterCollision(envahisseur,missile));
	}
	
	@Test
	public void collisionParLaGauche(){
		Envahisseur envahisseur = new Envahisseur(new Dimension(3,2), new Position(3,2), 1);
		Missile missile = new Missile(new Dimension(1,2), new Position(4,2), 1);
		assertTrue(collision.detecterCollision(missile,envahisseur));
	}
	
	@Test
	public void collisionParLeBas(){
		Envahisseur envahisseur = new Envahisseur(new Dimension(2,2), new Position(1,3), 1);
		Missile missile = new Missile(new Dimension(1,2), new Position(1,4), 1);
		assertTrue(collision.detecterCollision(envahisseur, missile));
	}
}