import static org.junit.Assert.*;

import org.junit.Test;


public class PlateauTest {

	@Test
	public void testChange_pioche() {
		Paquet pioche = new Paquet(4);
		Paquet pioche2 = new Paquet(5);
		Paquet p = pioche.piocher_cartes();
		Paquet p2 = pioche2.piocher_cartes();
		Joueur j1 = new Joueur("j_test",p,1);
		Joueur j2 = new Joueur("j_test2",p2,2);
		Plateau pl = new Plateau(j1,j2);
		pl.change_pioche(p2);	//On change la pioche du plateau en la pioche p2
		assertEquals("Erreur",pl.getpioche(),p2);	//On verifie que la nouvelle pioche est egale a p2
	}

	@Test
	public void testChange_jcour() {
		Paquet pioche = new Paquet(4);
		Paquet pioche2 = new Paquet(5);
		Paquet p = pioche.piocher_cartes();
		Paquet p2 = pioche2.piocher_cartes();
		Joueur j1 = new Joueur("j_test",p,1);
		Joueur j2 = new Joueur("j_test2",p2,2);
		Plateau pl = new Plateau(j1,j2);
		pl.change_jcour(j2);	//On change le joueur courant a j2
		assertEquals("Erreur",pl.get_j(),j2);	//On verifie que le joueur courant est j2
		pl.change_jcour(j1);	//On change le joueur courant a j1
		assertEquals("Erreur",pl.get_j(),j1);	//On verifie que le joueur courant est j1
	}

	@Test
	public void testIncr() {
		Paquet pioche = new Paquet(4);
		Paquet pioche2 = new Paquet(5);
		Paquet p = pioche.piocher_cartes();
		Paquet p2 = pioche2.piocher_cartes();
		Joueur j1 = new Joueur("j_test",p,1);
		Joueur j2 = new Joueur("j_test2",p2,2);
		Plateau pl = new Plateau(j1,j2);
		pl.incr();
		assertEquals("Erreur",pl.get_cpt_main(),2);	//cpt_main est initialise a 1 on l'a incremente une fois on regade donc s'il est egale a 2
	}

	@Test
	public void testExplosion() {
		Paquet pioche = new Paquet(4);
		Paquet pioche2 = new Paquet(5);
		Paquet p = pioche.piocher_cartes();
		Paquet p2 = pioche2.piocher_cartes();
		Joueur j1 = new Joueur("j_test",p,1);
		Joueur j2 = new Joueur("j_test2",p2,2);
		Plateau pl = new Plateau(j1,j2);
		pl.explosion(2, 5);
		assertEquals("Erreur",pl.get_explosion()[0],1);
		assertEquals("Erreur",pl.get_explosion()[1],2);
		assertEquals("Erreur",pl.get_explosion()[2],5);
	}

	@Test
	public void testGame_over() {
		Paquet pioche = new Paquet(4);
		Paquet pioche2 = new Paquet(5);
		Paquet p = pioche.piocher_cartes();
		Paquet p2 = pioche2.piocher_cartes();
		Joueur j1 = new Joueur("j_test",p,1);
		Joueur j2 = new Joueur("j_test2",p2,2);
		Plateau pl = new Plateau(j1,j2);
		pl.game_over();
		assertEquals("Erreur",pl.getgame_over(),1);
	}

}
