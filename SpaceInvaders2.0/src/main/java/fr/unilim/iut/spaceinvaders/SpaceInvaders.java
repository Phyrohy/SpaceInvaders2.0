package fr.unilim.iut.spaceinvaders;
import fr.unilim.iut.spaceinvaders.Vaisseau;

public class SpaceInvaders {

	private static final char CHAR_FIN_DE_LIGNE = '\n';
	private static final char CHAR_VIDE = '.';
	private static final char CHAR_VAISSEAU = 'V'; 
	private int largeur; 
	private int hauteur;
	
	public SpaceInvaders(int largeur, int hauteur) {
		this.largeur = largeur;
		this.hauteur = hauteur;
	}
}
