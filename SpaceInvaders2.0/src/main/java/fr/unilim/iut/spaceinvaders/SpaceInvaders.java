package fr.unilim.iut.spaceinvaders;
import fr.unilim.iut.spaceinvaders.Vaisseau;
import fr.unilim.fr.spaceinvaders.utils.DebordementEspaceJeuException;
import fr.unilim.fr.spaceinvaders.utils.HorsEspaceJeuException;
import fr.unilim.fr.spaceinvaders.utils.MissileException;
import fr.unilim.iut.spaceinvaders.Constante;

import fr.unilim.iut.spaceinvaders.moteurjeu.Commande;
import fr.unilim.iut.spaceinvaders.moteurjeu.Jeu;


public class SpaceInvaders implements Jeu{

	private int longueur; 
	private int hauteur;
	Vaisseau vaisseau;
	Missile missile;
	
	public SpaceInvaders(int longueur, int hauteur) {
		this.longueur = longueur;
		this.hauteur = hauteur;
	}
	
	  public void initialiserJeu() {
		    Position positionVaisseau = new Position(this.longueur/2,this.hauteur-1);
		    Dimension dimensionVaisseau = new Dimension(Constante.VAISSEAU_LONGUEUR, Constante.VAISSEAU_HAUTEUR);
		    positionnerUnNouveauVaisseau(dimensionVaisseau, positionVaisseau, Constante.VAISSEAU_VITESSE);
	    }
	
	@Override
	public String toString() {
		StringBuilder espaceDeJeu = new StringBuilder();
		for (int y = 0; y < hauteur; y++) {
			for (int x = 0; x < longueur; x++) {
				char marque = recupererMarqueDeLaPosition(y, x);
				
			    espaceDeJeu.append(marque);
			}
			espaceDeJeu.append(Constante.MARQUE_FIN_LIGNE);
		}
		return espaceDeJeu.toString();
	}

	private char recupererMarqueDeLaPosition(int x, int y) {
		char marque;
		if (this.aUnVaisseauQuiOccupeLaPosition(x, y))
			marque = Constante.MARQUE_VAISSEAU;
		else if (this.aUnMissileQuiOccupeLaPosition(x, y))
				marque = Constante.MARQUE_MISSILE;
		else marque = Constante.MARQUE_VIDE;
		return marque;
	}
	
	public boolean aUnVaisseau() {
		return vaisseau != null;
	}
	
	public Vaisseau recupererVaisseau() {
		return this.vaisseau;
	}
	
	public boolean aUnMissile() {
		return missile != null;
	}
	
	public Missile recupererMissile() {
		return this.missile;
	}

	private boolean aUnMissileQuiOccupeLaPosition(int x, int y) {
		return aUnMissileQuiOccupeLaPosition(x, y);
	}

	public void positionnerUnNouveauVaisseau(Dimension dimension, Position position, int vitesse) {
		
		int x = position.abscisse();
		int y = position.ordonnee();
		
		if (!estDansEspaceJeu(x, y))
			throw new HorsEspaceJeuException("La position du vaisseau est en dehors de l'espace jeu");

		int longueurVaisseau = dimension.longueur();
		int hauteurVaisseau = dimension.hauteur();
		
		if (!estDansEspaceJeu(x + longueurVaisseau - 1, y))
			throw new DebordementEspaceJeuException("Le vaisseau d�borde de l'espace jeu vers la droite � cause de sa longueur");
		if (!estDansEspaceJeu(x, y - hauteurVaisseau + 1))
			throw new DebordementEspaceJeuException("Le vaisseau d�borde de l'espace jeu vers le bas � cause de sa hauteur");

		vaisseau = new Vaisseau(dimension,position,vitesse);
	}

	private boolean estDansEspaceJeu(int x, int y) {
		return ((x >= 0) && (x < longueur)) && ((y >= 0) && (y < hauteur));
	}
	
	private boolean aUnVaisseauQuiOccupeLaPosition(int x, int y) {
		return aUnVaisseauQuiOccupeLaPosition(x, y);
	}

	public void deplacerVaisseauVersLaDroite() {
		if (vaisseau.abscisseLaPlusADroite() < (longueur - 1)) {
			vaisseau.deplacerHorizontalementVers(Direction.DROITE);
			if (!estDansEspaceJeu(vaisseau.abscisseLaPlusADroite(), vaisseau.ordonneeLaPlusHaute())) {
				vaisseau.positionner(longueur - vaisseau.longueur(), vaisseau.ordonneeLaPlusHaute());
			}
		}
	}

	public void deplacerVaisseauVersLaGauche() {
		if (0 < vaisseau.abscisseLaPlusAGauche())
			vaisseau.deplacerHorizontalementVers(Direction.GAUCHE);
		if (!estDansEspaceJeu(vaisseau.abscisseLaPlusAGauche(), vaisseau.ordonneeLaPlusHaute())) {
			vaisseau.positionner(0, vaisseau.ordonneeLaPlusHaute());
		}
	}

	public String recupererEspaceJeuDansChaineASCII() {
		StringBuilder espaceDeJeu = new StringBuilder();
		for (int y = 0; y < hauteur; y++) {
			for (int x = 0; x < longueur; x++) {
				espaceDeJeu.append(recupererMarqueDeLaPosition(x, y));
			}
			espaceDeJeu.append(Constante.MARQUE_FIN_LIGNE);
		}
		return espaceDeJeu.toString();
	}
	
	public void tirerUnMissile(Dimension dimensionMissile, int vitesseMissile) {
		
		   if ((vaisseau.hauteur()+ dimensionMissile.hauteur()) > this.hauteur )
			   throw new MissileException("Pas assez de hauteur libre entre le vaisseau et le haut de l'espace jeu pour tirer le missile");
							
		   this.missile =this.vaisseau.tirerUnMissile(dimensionMissile, vitesseMissile);
    }

	public void evoluer(Commande commandeUser) {

		if (commandeUser.gauche) {
			deplacerVaisseauVersLaGauche();
		}

		if (commandeUser.droite) {
			deplacerVaisseauVersLaDroite();
		}

		if (commandeUser.tir && !this.aUnMissile()){
			tirerUnMissile(new Dimension(Constante.MISSILE_LONGUEUR, Constante.MISSILE_HAUTEUR),
					Constante.MISSILE_VITESSE);
		}
		if (aUnMissile()) {
			deplacerMissile();
		}


	}

	public boolean etreFini() {
		return false;

	}

	public void deplacerMissile() {
	if(0<missile.ordonneeLaPlusHaute()) {
		missile.deplacerVerticalementVers(Direction.HAUT_ECRAN);
	}
	if(!estDansEspaceJeu(missile.abscisseLaPlusAGauche(), missile.ordonneeLaPlusBasse())) {
		this.missile = null; 
	}
		
	}
}
