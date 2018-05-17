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
	
	public void initialiserJeu() {
		Position positionVaisseau = new Position(this.largeur/2,this.hauteur-1);
		Dimension dimensionVaisseau = new Dimension(Constante.VAISSEAU_largeur, Constante.VAISSEAU_HAUTEUR);
		positionnerUnNouveauVaisseau(dimensionVaisseau, positionVaisseau);
	}

	public String recupererEspaceJeuDansChaineASCII() {
		StringBuilder espaceDeJeu = new StringBuilder();
		for (int y = 0; y < hauteur; y++) {
			for (int x = 0; x < largeur; x++) {
				espaceDeJeu.append(recupererMarqueDeLaPosition(x, y));
			}
			espaceDeJeu.append(CHAR_FIN_DE_LIGNE);
		}
		return espaceDeJeu.toString();
	}

	private char recupererMarqueDeLaPosition(int x, int y) {
		char marque;
		if (this.aUnVaisseauQuiOccupeLaPosition(x, y))
			marque = CHAR_VAISSEAU;
		else
			marque = CHAR_VIDE;
		return marque;
	}

	private boolean aUnVaisseauQuiOccupeLaPosition(int x, int y) {
		return this.aUnVaisseau() && Vaisseau.occupeLaPosition(positionX, positionY);
	}

	public boolean aUnVaisseau() {
		return vaisseau != null;
	}

	public void positionnerUnNouveauVaisseau(Dimension dimension, Position position) {

		int x = position.abscisse();
		int y = position.ordonnee();

		if (!estDansEspaceJeu(x, y))
			throw new HorsEspaceJeuException("La position du vaisseau est en dehors de l'espace jeu");

		int largeurVaisseau = dimension.largeur(); 
		int hauteurVaisseau = dimension.hauteur();

		if (!estDansEspaceJeu(x + largeurVaisseau - 1, y))
			throw new DebordementEspaceJeuException(
					"Le vaisseau déborde de l'espace jeu vers la droite à cause de sa largeur");
		if (!estDansEspaceJeu(x, y - hauteurVaisseau + 1))
			throw new DebordementEspaceJeuException(
					"Le vaisseau déborde de l'espace jeu vers le bas à cause de sa hauteur");

		vaisseau = new Vaisseau(largeurVaisseau, hauteurVaisseau);
		vaisseau.positionner(x, y);
	}

	private boolean estDansEspaceJeu(int x, int y) {
		return ((x >= 0) && (x < largeur)) && ((y >= 0) && (y < hauteur));
	}

	public void deplacerVaisseauVersLaDroite() {
		if (vaisseau.abscisseLaPlusADroite() < (largeur - 1))
			vaisseau.seDeplacerVersLaDroite();
	}

	public void deplacerVaisseauVersLaGauche() {
		if (0 < vaisseau.abscisseLaPlusAGauche())
			vaisseau.seDeplacerVersLaGauche();

	}

	public Vaisseau recupererVaisseau() {
		return this.vaisseau;
	}

	@Override
	public void evoluer(Commande commandeUser) {

		if (commandeUser.gauche) {
			deplacerVaisseauVersLaGauche();
		}

		if (commandeUser.droite) {
			deplacerVaisseauVersLaDroite();
		}

	}

	@Override
	public boolean etreFini() {
		return false;

	}

}
