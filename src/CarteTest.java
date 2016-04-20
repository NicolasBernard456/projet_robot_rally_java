import java.awt.Image;
import java.awt.Toolkit;
import static org.junit.Assert.*;
import org.junit.Test;


public class CarteTest {

	@Test
	public void testComparer() {
		Image image = Toolkit.getDefaultToolkit().getImage("robot_uturn.png");
		Carte c1 = new Carte(100,0,image);
		Carte c2 = new Carte(200,0,image);
		assertEquals("Erreur",c1.Comparer(c2),1);
	}

}
