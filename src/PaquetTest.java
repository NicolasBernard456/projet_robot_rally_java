import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;
import java.awt.*;

public class PaquetTest {

	@Test
	public void testAjouter_carte() {
		Image img = Toolkit.getDefaultToolkit().getImage("robot_uturn.png");;
		Carte c = new Carte(1,1,img);
		Paquet p = new Paquet(c);
		Carte c2 = new Carte(2,2,img);
		p.Ajouter_carte(c2);
		assertEquals("Erreur",p.Getcartes().get(1),c2);
		assertEquals("Erreur",p.Getcartes().get(0),c);
		assertEquals("Erreur",p.get_size(),2);
	}

	@Test
	public void testTirer_carte() {
		Image img = Toolkit.getDefaultToolkit().getImage("robot_uturn.png");;
		Carte c = new Carte(1,1,img);
		Paquet p = new Paquet(c);
		Carte c2 = new Carte(2,2,img);
		p.Ajouter_carte(c2);
		p.Tirer_carte();
		assertEquals("Erreur",p.Getcartes().get(0),c2);
		assertEquals("Erreur",p.get_size(),1);
		
	}

	@Test
	public void testMelanger() {
		Paquet p = new Paquet();
		p.Melanger();
		assertEquals("Erreur",p.get_size(),84);
	}

	@Test
	public void testRecuperer_paquet() {
		Paquet p = new Paquet();
		ArrayList<Carte> c = new ArrayList<Carte>();
		p.recuperer_paquet(c);
		assertEquals("Erreur",p.Getcartes(),c);
	}

	@Test
	public void testPiocher_cartes() {
		Paquet pioche = new Paquet();
		Paquet p = pioche.piocher_cartes();
		assertEquals("Erreur",pioche.get_size(),84-9);	//On retire 9 cartes du paquet
		assertEquals("Erreur",p.get_size(),9);	//On a 9 cartes dans le paquet
	}

}
