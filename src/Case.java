/**
 * Case est la classe representant chaque case du plateau de jeu
 * Une case est caracterise par sa position x et y, son type(ce qu'elle contient) et un entier indiquant si la case est occupe ou non
 * 
 * @author nico
 *
 */
public class Case {
	/**
	 * Coordonnees x et y de la case sur le tableau, x et y sont tous les deux compris entre 0 et 7
	 * @see Case#Getx()
	 * @see Case#Gety()
	 * @see Case#change_case(int, int)
	 * @see Case#Case(int, int, int, int)
	 */
	private int x,y;
	/**
	 * Type de la case , 0 = rien, 1 = trou, 2 = drapeau1, 3 = drapeau2, 4 = drapeau3, 5 = drapeau4, 6 = obstacle
	 * @see Case#change_type(int)
	 * @see Case#Gettype()
	 * @see Case#Case(int, int, int, int)
	 */
	private int type;	//0 rien 1 trou 2 drapeau1 3 drapeau2 4 drapeau3 5 drapeau4 6 obstacle
	/**
	 * Entier indiquant si la case est occupe ou non
	 * @see Case#Getoccupe()
	 * @see Case#Case(int, int, int, int)
	 */
	private int occupe; //0 rien 1 occupe
	
	/**
	 * Constructeur d'une case, appele lors de l'initialisation du plateau
	 * @param x_
	 * @param y_
	 * @param type_
	 * @param occupe_
	 * @see Plateau
	 * @see Case#x
	 * @see Case#y
	 * @see Case#occupe
	 * @see Case#type
	 */
	public Case(int x_, int y_, int type_, int occupe_){
		x = x_;
		y = y_;
		type = type_;
		occupe = occupe_;
	}
	/**
	 * Accesseur pour le type de la case
	 * @see Case#type
	 * @return type d'une case
	 */
	public int Gettype(){
		return type;
	}
	
	/**
	 * Change le type d'une case en type_
	 * @param type_
	 * @see Case#type
	 */
	public void change_type(int type_){
		type = type_;
		if(type != 0)
			occupe = 1;
	}
	
	/**
	 * Accesseur pour l'entier donnant l'etat de la case
	 * @return etat de la case
	 * @see Case#occupe
	 */
	
	public int Getoccupe(){
		return occupe;
	}
	/**
	 * Accesseur pour la coordonnee x de la case
	 * @return la position x de la case
	 * @see Case#x
	 */
	public int Getx(){
		return x;
	}
	/**
	 * Accesseur pour la coordonnee y de la case
	 * @return la position y de la case
	 *@see Case#y
	 */
	public int Gety(){
		return y;
	}
	
	/**
	 * Fonction changeant la valeur de la case vers une case ayant pour coordonnees (x_, y_)
	 * @param x_ nouvelle coordonnee x
	 * @param y_ nouvelle coordonnee y
 	 * @see Case#x
	 * @see Case#y
	 */
	
	public void change_case(int x_, int y_){	
		this.x = x_;
		this.y = y_;
	}
}
