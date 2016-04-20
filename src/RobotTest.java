import static org.junit.Assert.*;
import org.junit.Test;


public class RobotTest {

	@Test
	public void testDeplacement() {
		//Creation de deux robots
		Robot r1 = new Robot(1);	//coordonnee (0,7) oriente sur l'axe y negatif(vers le haut)
		Robot r2 = new Robot(2);	//coordonnee (2,7) oriente sur l'axe y negatif(vers le haut)
		//On fait se deplacer r1 de 1 case vers le haut puis on lui fait faire une rotation de 90 vers la gauche
		r1.deplacement(1, -1, r2);	//deplace le robot d'une case en avant
		assertEquals("Erreur",r1.Getx(),0);	//ne doit pas bouger en x
		assertEquals("Erreur",r1.Gety(),6);	//doit se deplacer d'une case en y
		assertEquals("Erreur",r1.Getorient(),1);	//ne doit pas bouger en orientation
		r1.deplacement(0, 1, r2);	//tourne le robot vers la gauche
		assertEquals("Erreur",r1.Getx(),0);	//ne doit pas bouger en x
		assertEquals("Erreur",r1.Gety(),6);	// ne doit pas bouger en y
		assertEquals("Erreur",r1.Getorient(),0);	//doit tourner a gauche en orientation
		
	}

	@Test
	public void testBousculer() {
		Robot r1 = new Robot(1);	//coordonnee (0,7) oriente sur l'axe y negatif(vers le haut)
		r1.bousculer_test(1, 0); // Le robot se fait pousser d'une case vers la droite
		assertEquals("Erreur",r1.Getx(),1);
		assertEquals("Erreur",r1.Gety(),7);
		r1.bousculer_test(0, -1); // Le robot se fait bousculer d'une case vers le haut
		assertEquals("Erreur",r1.Getx(),1);
		assertEquals("Erreur",r1.Gety(),6);
	}
	@Test
		public void testPosition_brut() {
			Robot r1 = new Robot(1);	//coordonnee (0,7) oriente sur l'axe y negatif(vers le haut)
			Robot r2 = new Robot(2);	//coordonnee (2,7) oriente sur l'axe y negatif(vers le haut)
			r1.deplacement(0, 1, r2);	//On tourne le robot a gauche
			r1.position_brut(new Case(5,5,0,0)); //Deplacement du robot sur la case (5,5)
			assertEquals("Erreur",r1.Getx(),5);		//Verification de la coordonnee x
			assertEquals("Erreur",r1.Gety(),5);		//Verification de la coordonnee y
			assertEquals("Erreur",r1.Getorient(),1);	//Verification de l'orientation (on revient a l'orientation par defaut)
		}
	
	@Test
	public void testPosition_brut_or() {
		Robot r1 = new Robot(1);	//coordonnee (0,7) oriente sur l'axe y negatif(vers le haut)
		Robot r2 = new Robot(2);	//coordonnee (2,7) oriente sur l'axe y negatif(vers le haut)
		r1.deplacement(0, 1, r2);	//On tourne le robot a gauche
		r1.position_brut_or(new Case(5,5,0,0)); //Deplacement du robot sur la case (5,5)
		assertEquals("Erreur",r1.Getx(),5);		//Verification de la coordonnee x
		assertEquals("Erreur",r1.Gety(),5);		//Verification de la coordonnee y
		assertEquals("Erreur",r1.Getorient(),0);	//Verification de l'orientation (on garde l'orientation du robot)
	}
	
	
	@Test
	public void testTest_collision() {
		//Creation de deux robots
		Robot r1 = new Robot(1);	//coordonnee (0,7) oriente sur l'axe y negatif(vers le haut)
		Robot r2 = new Robot(2);	//coordonnee (2,7) oriente sur l'axe y negatif(vers le haut)
		r1.position_brut(new Case(2,2,0,0)); // On place le robot 1 a la position (2,2)
		r2.position_brut(new Case(2,3,0,0)); //On place le robot 2 a la position (2,3)
		r1.test_collisiontest(-1, 1, 3, 1, r2);	//r1 se deplace en y, de deux cases et vers le haut
		assertEquals("Erreur",r2.Getx(),2);
		assertEquals("Erreur",r2.Gety(),5);
		assertEquals("Erreur",r1.Getx(),2);
		assertEquals("Erreur",r1.Gety(),4);
		assertEquals("Erreur",r1.Getorient(),1);
		assertEquals("Erreur",r2.Getorient(),1);
	}



}
