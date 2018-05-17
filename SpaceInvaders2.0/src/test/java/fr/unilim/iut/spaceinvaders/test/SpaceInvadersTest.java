package fr.unilim.iut.spaceinvaders.test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import fr.unilim.iut.spaceinvaders.SpaceInvaders;

public class SpaceInvadersTest {

   @Test
   public void test_AuDebut_JeuSpaceInvaderEstVide() {
	    SpaceInvaders spaceinvaders = new SpaceInvaders(15, 10);
	    assertEquals("" + 
	    "...............\n" + 
	    "...............\n" +
	    "...............\n" + 
	    "...............\n" + 
	    "...............\n" + 
	    "...............\n" + 
	    "...............\n" + 
	    "...............\n" + 
	    "...............\n" + 
	    "...............\n" , spaceinvaders.toString());
        }

   }