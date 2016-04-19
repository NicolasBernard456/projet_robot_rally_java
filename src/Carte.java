import java.awt.*;
/**
 * Carte est la classe representant une carte du jeux Robot rally.
 * Une carte est caracterisé par sa vitesse(unique à chaque carte), son type: demi tour, tourner a droite, tourner a gauche, reculer, avancer de 1,2 et 3 cases
 * et par son image
 * 
 * @author nico
 *
 */
public class Carte {
	/**
	 * La vitesse d'une carte determine le joueur se deplacant le premier pendant un tour.
	 * Les Carte#vitesses vont de 10 a 840.
	 * @see Carte#getVitesse()
	 * @see Carte#Carte(int vitese_, int type_fleche_, Image img_)
	 */
	private int vitesse;
	/**
	 * Le type d'une carte, demi tour, tourner a droite, tourner a gauche, reculer, avancer de 1,2 et 3 cases.
	 * Le type est ecrit sous la forme d'un entier avec :0 = demi tour; 1 = tourner a droite; 2 = tourner a gauche; 3 = reculer;4 = avancer de 1; 5 = avancer de 2;6 = avancer de 3 
	 *@see Carte#getVitesse()
	 *@see Carte#Carte(int, int, Image)
	 */
	private int type_fleche;
	/**
	 * L'image d'une carte est determine en fonction de son type
	 * @see Carte#getImg()
	 *@see Carte#Carte(int, int, Image)
	 */
	
	private Image Img;
	
	
	/**
	 * Constructeur de la classe Carte utilise dans la classe Paquet
	 * @param vitesse_	Vitesse a laquelle la carte est initialisé
	 * @param type_fleche_	Type de carte a laquelle la carte est initialisé
	 * @param img_	Image a laquelle la carte est initialisé
	 * @see Carte#vitesse
	 * @see Carte#type_fleche
	 * @see Carte#Img
	 * @see Paquet
	 */
	
	public Carte(int vitesse_, int type_fleche_, Image img_) {
		vitesse = vitesse_;
		type_fleche = type_fleche_;
		Img = img_;
	}

	
	/**
	 * Accesseur pour le type d'une carte
	 * @return le type de fleche de la carte sous la forme d'un int 
	 * @see Carte#type_fleche
	 */
	public int gettype_fleche(){
		return type_fleche;
	}
	
	/**
	 * Accesseur de l'image de la carte
	 * @return l'image correspondant a la carte
	 * @see Carte#Img
	 */
	public Image getImg(){
		return Img;
	}
	
	/**
	 * Accesseur de la vitesse de la carte
	 * @return la vitesse de la carte
	 * @see Carte#vitesse
	 */
	public int getVitesse() {
		return vitesse;
	}
	/**
	 * Fonction de comparaison entre deux cartes
	 * @param c1, la carte a comparer
	 * @return la carte avec la vitesse la plus elevee
	 * @see Carte#vitesse
	 * @see Carte#getVitesse()
	 */
	public int Comparer(Carte c1){
		if(vitesse > c1.getVitesse())
			return 0;
		else
			return 1;
	}
	/**
	 * Fonction d'affichage de la carte (utile pour le debuggage)
	 * 
	 */
	public void affiche_carte(){
		System.out.println("v = " + vitesse + "type = " + type_fleche);
	}
}
