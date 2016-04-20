import static org.junit.Assert.*;
import java.awt.*;

import java.awt.Toolkit;

import org.junit.Test;


public class JoueurTest {

	@Test
	public void testPerdre_vie() {
		Paquet p = new Paquet();
		Joueur j1 = new Joueur("j_test",p,1);
		j1.perdre_vie();
		assertEquals("Erreur",j1.Getnb_vie(),2);
	}

	@Test
	public void testNouvelle_cartes() {
		Paquet pioche = new Paquet(5);
		Paquet p = pioche.piocher_cartes();
		Image image = Toolkit.getDefaultToolkit().getImage("robot_fwd2.png");
		Carte c;
		Joueur j1 = new Joueur("j_test",p,1);
		j1.nouvelle_cartes(pioche);
		assertEquals("Erreur",j1.Getp().get_size(),9);
		for(int i = 0 ; i < 9 ; i++){
			c = new Carte(i + 9,5,image);
			assertEquals("Erreur",j1.Getp().Getcartes().get(i).getVitesse(),c.getVitesse());
			assertEquals("Erreur",j1.Getp().Getcartes().get(i).gettype_fleche(),c.gettype_fleche());
		}
	}

	@Test
	public void testDeplacement() {
		Paquet pioche = new Paquet(4);
		Paquet pioche2 = new Paquet(5);
		Paquet p = pioche.piocher_cartes();
		Paquet p2 = pioche2.piocher_cartes();
		Image image = Toolkit.getDefaultToolkit().getImage("robot_fwd1.png");
		Carte c_j1 = new Carte(10,4,image);
		Carte c_j2 = new Carte(20,0,image);
		Joueur j1 = new Joueur("j_test",p,1);
		Joueur j2 = new Joueur("j_test2",p2,2);
		j1.deplacement(j2, c_j1, c_j2);
		assertEquals("Erreur",j1.getr().Getx(),0);	//Le robot ne doit pas bouger en x
		assertEquals("Erreur",j1.getr().Gety(),6);	//Le robot doit se deplacer d'une case en y
		assertEquals("Erreur",j1.getr().Getorient(),1); //Le robot ne doit pas bouger en orientation
		assertEquals("Erreur",j2.getr().Getx(),2);	//Le robot ne doit pas bouger en x
		assertEquals("Erreur",j2.getr().Gety(),7);	//Le robot ne doit pas bouger en y
		assertEquals("Erreur",j2.getr().Getorient(),3); // Le robot doit faire demi tour
		
	}

	@Test
	public void testDeplacement_rob_gene() {
		Paquet pioche = new Paquet(4);
		Paquet pioche2 = new Paquet(5);
		Paquet p = pioche.piocher_cartes();
		Paquet p2 = pioche2.piocher_cartes();
		Joueur j1 = new Joueur("j_test",p,1);
		Joueur j2 = new Joueur("j_test2",p2,2);
		j1.deplacement_rob_gene(4, j2);
		j2.deplacement_rob_gene(0, j1);
		assertEquals("Erreur",j1.getr().Getx(),0);	//Le robot ne doit pas bouger en x
		assertEquals("Erreur",j1.getr().Gety(),6);	//Le robot doit se deplacer d'une case en y
		assertEquals("Erreur",j1.getr().Getorient(),1); //Le robot ne doit pas bouger en orientation
		assertEquals("Erreur",j2.getr().Getx(),2);	//Le robot ne doit pas bouger en x
		assertEquals("Erreur",j2.getr().Gety(),7);	//Le robot ne doit pas bouger en y
		assertEquals("Erreur",j2.getr().Getorient(),3); // Le robot doit faire demi tour
		
	}

	@Test
	public void testDeplacement_carte2_3() {
		Paquet pioche = new Paquet(4);
		Paquet pioche2 = new Paquet(5);
		Paquet p = pioche.piocher_cartes();
		Paquet p2 = pioche2.piocher_cartes();
		Joueur j1 = new Joueur("j_test",p,1);
		Joueur j2 = new Joueur("j_test2",p2,2);
		for(int i = 0 ; i < 2 ; i ++) //deplacement de 2 cases
			j1.deplacement_carte2_3(j2);
		assertEquals("Erreur",j1.getr().Getx(),0);	//Le robot ne doit pas bouger en x
		assertEquals("Erreur",j1.getr().Gety(),5);	//Le robot doit se deplacer de deux cases en y
		assertEquals("Erreur",j1.getr().Getorient(),1); //Le robot ne doit pas bouger en orientation
	}

	@Test
	public void testInit_carte_joue() {
		Paquet pioche = new Paquet(4);
		Paquet p = pioche.piocher_cartes();
		Joueur j1 = new Joueur("j_test",p,1);
		Image image = Toolkit.getDefaultToolkit().getImage("robot_fwd1.png");
		Carte c_j1 = new Carte(10,4,image);
		Carte c_j2 = new Carte(20,0,image);
		j1.init_carte_joue(c_j1, c_j2);
		//On regarde si les carte ajoutee sont les memes que celle qu'on voulait ajouter
		assertEquals("Erreur",j1.get_carte_joue().get(0),c_j1);
		assertEquals("Erreur",j1.get_carte_joue().get(1),c_j2);
	}

	@Test
	public void testClear_carte_joue() {
		Paquet pioche = new Paquet(4);
		Paquet p = pioche.piocher_cartes();
		Joueur j1 = new Joueur("j_test",p,1);
		Image image = Toolkit.getDefaultToolkit().getImage("robot_fwd1.png");
		Carte c_j1 = new Carte(10,4,image);
		Carte c_j2 = new Carte(20,0,image);
		j1.init_carte_joue(c_j1, c_j2);
		// On regarde si le tableau est vide
		j1.clear_carte_joue();
		assertEquals("Erreur",j1.get_carte_joue().size(),0);
	}

	@Test
	public void testRetour_dernier_drapeau() {
		Paquet pioche = new Paquet(4);
		Paquet p = pioche.piocher_cartes();
		Joueur j1 = new Joueur("j_test",p,1);
		Case c = new Case(0,0,0,0);
		j1.retour_dernier_drapeau(c);
		assertEquals("Erreur",j1.getr().Getx(),0);
		assertEquals("Erreur",j1.getr().Gety(),0);
		assertEquals("Erreur",j1.getr().Getorient(),1);
		
	}

	@Test
	public void testGagner_drapeau() {
		Paquet pioche = new Paquet(4);
		Paquet p = pioche.piocher_cartes();
		Joueur j1 = new Joueur("j_test",p,1);
		j1.gagner_drapeau();
		assertEquals("Erreur",j1.Getcpt_drapeaux(),1);
	}

}
