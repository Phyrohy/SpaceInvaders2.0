package fr.unilim.iut.spaceinvaders.model;
import fr.unilim.fr.spaceinvaders.utils.DebordementEspaceJeuException;
import fr.unilim.fr.spaceinvaders.utils.HorsEspaceJeuException;
import fr.unilim.fr.spaceinvaders.utils.MissileException;
import fr.unilim.iut.spaceinvaders.model.Vaisseau;
import fr.unilim.iut.spaceinvaders.moteurjeu.Commande;
import fr.unilim.iut.spaceinvaders.moteurjeu.Jeu;


public class SpaceInvaders implements Jeu{

	private int longueur; 
	private int hauteur;
	Vaisseau vaisseau;
	Missile missile;
	Envahisseur envahisseur;
	Collision collision = new Collision ();
	boolean etreFini = false;
	
	public SpaceInvaders(int longueur, int hauteur) {
		this.longueur = longueur;
		this.hauteur = hauteur;
	}
	
	  public void initialiserJeu() {
		    Position positionVaisseau = new Position(this.longueur/2,this.hauteur-1);
		    Dimension dimensionVaisseau = new Dimension(Constante.VAISSEAU_LONGUEUR, Constante.VAISSEAU_HAUTEUR);
		    positionnerUnNouveauVaisseau(dimensionVaisseau, positionVaisseau, Constante.VAISSEAU_VITESSE);
		    Position positionEnvahisseur = new Position(this.longueur - Constante.ENVAHISSEUR_LONGUEUR * 2,
					Constante.ESPACEJEU_HAUTEUR / 10 + Constante.ENVAHISSEUR_HAUTEUR);
			Dimension dimensionEnvahisseur = new Dimension(Constante.ENVAHISSEUR_LONGUEUR,
					Constante.ENVAHISSEUR_HAUTEUR);
			positionnerUnNouvelEnvahisseur(dimensionEnvahisseur, positionEnvahisseur, Constante.ENVAHISSEUR_VITESSE);
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
	
	public boolean aUnEnvahisseur() {
		return envahisseur != null;
	}
	
	public Envahisseur recupererEnvahisseur() {
		return this.envahisseur;
	}
	
	private boolean aUnVaisseauQuiOccupeLaPosition(int x, int y) {
		return aUnVaisseauQuiOccupeLaPosition(x, y);
	}

	private boolean aUnMissileQuiOccupeLaPosition(int x, int y) {
		return aUnMissileQuiOccupeLaPosition(x, y);
	}
	
	private boolean aUnEnvahisseurQuiOccupeLaPosition(int x, int y) {
		return aUnEnvahisseurQuiOccupeLaPosition(x, y);
	}


	public void positionnerUnNouveauVaisseau(Dimension dimension, Position position, int vitesse) {
		
		int x = position.abscisse();
		int y = position.ordonnee();
		
		if (!estDansEspaceJeu(x, y))
			throw new HorsEspaceJeuException("La position du vaisseau est en dehors de l'espace jeu");

		int longueurVaisseau = dimension.longueur();
		int hauteurVaisseau = dimension.hauteur();
		
		if (!estDansEspaceJeu(x + longueurVaisseau - 1, y))
			throw new DebordementEspaceJeuException("Le vaisseau déborde de l'espace jeu vers la droite à cause de sa longueur");
		if (!estDansEspaceJeu(x, y - hauteurVaisseau + 1))
			throw new DebordementEspaceJeuException("Le vaisseau déborde de l'espace jeu vers le bas à cause de sa hauteur");

		vaisseau = new Vaisseau(dimension,position,vitesse);
	}
	
public void positionnerUnNouvelEnvahisseur(Dimension dimension, Position position, int vitesse) {
		
		int x = position.abscisse();
		int y = position.ordonnee();
		
		if (!estDansEspaceJeu(x, y))
			throw new HorsEspaceJeuException("La position de l'envahisseur est en dehors de l'espace jeu");

		int longueurEnvahisseur = dimension.longueur();
		int hauteurEnvahisseur = dimension.hauteur();
		
		if (!estDansEspaceJeu(x + longueurEnvahisseur - 1, y))
			throw new DebordementEspaceJeuException("l'envahisseur déborde de l'espace jeu vers la droite à cause de sa longueur");
		if (!estDansEspaceJeu(x, y - hauteurEnvahisseur + 1))
			throw new DebordementEspaceJeuException("l'envahisseur déborde de l'espace jeu vers le bas à cause de sa hauteur");

		envahisseur = new Envahisseur(dimension,position,vitesse);
	}

	private boolean estDansEspaceJeu(int x, int y) {
		return ((x >= 0) && (x < longueur)) && ((y >= 0) && (y < hauteur));
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

		if (aUnEnvahisseur()) {
			deplacerEnvahisseurDansLeSens();
		}
	}

	public boolean etreFini() {
		return etreFini;
	}
	
	public void deplacerMissile(){
		if(0<missile.ordonneeLaPlusHaute()) {
			missile.deplacerVerticalementVers(Direction.HAUT_ECRAN);
		}
		
		if (aUnEnvahisseur()) {
			if(detectionCollisionMissilesEnvahisseurs(envahisseur, missile)) {
				System.out.println("Fin de partie");
				System.exit(0);
			}
		}
		
		if(!estDansEspaceJeu(missile.abscisseLaPlusAGauche(), missile.ordonneeLaPlusBasse())) {
			supprimerMissile(); 
		}
	}

	private void supprimerMissile() {
		this.missile = null;
	}
	
	private void supprimerEnvahisseur() {
		this.envahisseur = null;
	}
	
	private boolean detectionCollisionMissilesEnvahisseurs(Envahisseur envahisseur, Missile missile) {
			if (collision.detecterCollision(missile, envahisseur)) {
				supprimerMissile();
				supprimerEnvahisseur();
				return true; 
			}else { return false; }
	}
	
	public void deplacerEnvahisseurVersLaDroite() {
			if (envahisseur.abscisseLaPlusADroite() < (longueur - 1)) {
				envahisseur.deplacerHorizontalementVers(Direction.DROITE);
				if (!estDansEspaceJeu(envahisseur.abscisseLaPlusADroite(), envahisseur.ordonneeLaPlusHaute())) {
					envahisseur.positionner(longueur - envahisseur.longueur(), envahisseur.ordonneeLaPlusHaute());
				}
			}
	}

	public void deplacerEnvahisseurVersLaGauche() {
			if (0 < envahisseur.abscisseLaPlusAGauche())
				envahisseur.deplacerHorizontalementVers(Direction.GAUCHE);
			if (!estDansEspaceJeu(envahisseur.abscisseLaPlusAGauche(), envahisseur.ordonneeLaPlusHaute())) {
				envahisseur.positionner(0, envahisseur.ordonneeLaPlusHaute());
			}
	}

	public void deplacerEnvahisseurDansLeSens() {
			if (envahisseur.abscisseLaPlusADroite() == Constante.ESPACEJEU_LONGUEUR - 1) {
				Envahisseur.setSens(Direction.GAUCHE);
			}
			if (envahisseur.abscisseLaPlusAGauche() == 0) {
				Envahisseur.setSens(Direction.DROITE);
		}
		if (Envahisseur.getSens() == Direction.GAUCHE) {
			this.deplacerEnvahisseurVersLaGauche();
		} else {
			this.deplacerEnvahisseurVersLaDroite();
		}
	}
}
