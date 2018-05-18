package fr.unilim.iut.spaceinvaders;
import fr.unilim.iut.spaceinvaders.Vaisseau;

public class SpaceInvaders {

	private static final char CHAR_FIN_DE_LIGNE = '\n';
	private static final char CHAR_VIDE = '.';
	private static final char CHAR_VAISSEAU = 'V'; 
	private int longueur; 
	private int hauteur;
	Vaisseau vaisseau;
	
	public SpaceInvaders(int longueur, int hauteur) {
		this.longueur = longueur;
		this.hauteur = hauteur;
	}
	
	@Override
	public String toString() {
		StringBuilder espaceDeJeu = new StringBuilder();
		for (int x = 0; x < hauteur; x++) {
			for (int y = 0; y < longueur; y++) {
				if (vaisseau.occupeLaPosition(x, y))
				     espaceDeJeu.append('V');
				else
					espaceDeJeu.append('.');
			}
			espaceDeJeu.append('\n');
		}
		return espaceDeJeu.toString();
	}

	public void positionnerUnNouveauVaisseau(int x, int y) {
		this.vaisseau = new Vaisseau(x,y);
	}
	
	
}
