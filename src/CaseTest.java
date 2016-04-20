import org.junit.Test;
import static org.junit.Assert.*;

public class CaseTest {

	@Test
	public void testChange_type() {
		Case c1 = new Case(0,0,0,0);
		c1.change_type(1);
		assertEquals("Erreur",c1.Gettype(), 1);
		assertEquals("Erreur",c1.Getoccupe(), 1);
	}

	@Test
	public void testChange_case() {
		Case c1 = new Case(0,0,0,0);
		c1.change_case(1, 1);
		assertEquals("Erreur",c1.Getx(),1);
		assertEquals("Erreur",c1.Gety(),1);
	}

}
