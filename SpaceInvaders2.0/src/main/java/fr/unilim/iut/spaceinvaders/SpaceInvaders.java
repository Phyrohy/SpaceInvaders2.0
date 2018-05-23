package fr.unilim.iut.spaceinvaders;
import fr.unilim.iut.spaceinvaders.Vaisseau;

public class SpaceInvaders {

	private static final char MARQUE_FIN_DE_LIGNE = '\n';
	private static final char MARQUE_VIDE = '.';
	private static final char MARQUE_VAISSEAU = 'V'; 
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
		for (int y = 0; y < hauteur; y++) {
			for (int x = 0; x < longueur; x++) {
				char marque = recupererMarqueDeLaPosition(y, x);
				
			    espaceDeJeu.append(marque);
			}
			espaceDeJeu.append(MARQUE_FIN_DE_LIGNE);
		}
		return espaceDeJeu.toString();
	}

	private char recupererMarqueDeLaPosition(int y, int x) {
		char marque;
		if (this.aUnVaisseauQuiOccupeLaPosition(x, y))
		      marque=MARQUE_VAISSEAU;
		else
		      marque=MARQUE_VIDE;
		return marque;
	}

	public void positionnerUnNouveauVaisseau(int x, int y) {
		
		if (  !estDansEspaceJeu(x, y) )
			throw new HorsEspaceJeuException("Vous êtes en dehors de l'espace jeu");
	
		vaisseau = new Vaisseau(x, y); 
	}

	private boolean estDansEspaceJeu(int x, int y) {
		return ((x >= 0) && (x < longueur)) && ((y >= 0) && (y < hauteur));
	}
	
	private boolean aUnVaisseauQuiOccupeLaPosition(int x, int y) {
		return aUnVaisseauQuiOccupeLaPosition(x, y);
	}

	public void deplacerVaisseauVersLaDroite() {
        if (vaisseau.abscisse()< (longueur-1)) vaisseau.seDeplacerVersLaDroite();
	}
	
	public void deplacerVaisseauVersLaGauche() {
        if (vaisseau.abscisse()< (longueur-1)) vaisseau.seDeplacerVersLaGauche();
	}
	
}
