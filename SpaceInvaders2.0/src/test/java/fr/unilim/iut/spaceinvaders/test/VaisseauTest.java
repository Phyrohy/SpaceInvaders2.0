package fr.unilim.iut.spaceinvaders.test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import static org.junit.Assert.fail;
import org.junit.Before;

import fr.unilim.iut.spaceinvaders.model.Dimension;
import fr.unilim.iut.spaceinvaders.model.Position;
import fr.unilim.iut.spaceinvaders.model.SpaceInvaders;
import fr.unilim.iut.spaceinvaders.model.Vaisseau;
import fr.unilim.fr.spaceinvaders.utils.DebordementEspaceJeuException;
import fr.unilim.fr.spaceinvaders.utils.HorsEspaceJeuException;
import fr.unilim.fr.spaceinvaders.utils.MissileException;

public class VaisseauTest {
	 @Test(expected = MissileException.class)
		public void test_LongueurMissileSuperieureALongueurVaisseau_UneExceptionEstLevee() throws Exception {
			Vaisseau vaisseau = new Vaisseau(new Dimension(5,2),new Position(5,9), 1);
			vaisseau.tirerUnMissile(new Dimension(7,2),1);
		}
}
