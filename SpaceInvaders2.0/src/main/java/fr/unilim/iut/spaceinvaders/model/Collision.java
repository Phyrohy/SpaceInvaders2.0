package fr.unilim.iut.spaceinvaders.model;

public class Collision {
	
	private boolean missileToucheOrdonneeEnvahisseur(Sprite envahisseur, Sprite missile) {
		return elementToucheParOrdonnee(envahisseur, missile.ordonneeLaPlusBasse())
				|| elementToucheParOrdonnee(envahisseur, missile.ordonneeLaPlusHaute());
	}

	private boolean missileToucheAbscisseEnvahisseur(Sprite envahisseur, Sprite missile) {
		return elementToucheParAbscisse(envahisseur, missile.abscisseLaPlusAGauche())
				|| elementToucheParAbscisse(envahisseur, missile.abscisseLaPlusADroite());
	}
	
	public boolean detecterCollision(Sprite envahisseur, Sprite missile) {
		if (missileToucheAbscisseEnvahisseur(missile, envahisseur)
				&& missileToucheOrdonneeEnvahisseur(missile, envahisseur)) {
			return true;
		}
		if (missileToucheAbscisseEnvahisseur(envahisseur, missile)
				&& missileToucheOrdonneeEnvahisseur(envahisseur, missile)) {
			return true;
		}
		return false;
	}

	public boolean elementToucheParAbscisse(Sprite envahisseur, int x) {
		if (envahisseur.estAbscisseCouverte(x)) {
			return true;
		}
		return false;
	}

	public boolean elementToucheParOrdonnee(Sprite envahisseur, int y) {
		if (envahisseur.estOrdonneeCouverte(y)) {
			return true;
		}
		return false;
	}
}