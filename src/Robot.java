import java.awt.*;
/**
 * Classe Robot representant le robot de chaque joueurs. La classe contient une case( la position du robot sur le Plateau), son orientation sur le plateau
 * et l'image qui est associe au robot
 * @author nico
 *
 */

public class Robot {
	/**
	 * Case representant la position du robot sur le plateau
	 * @see Case
	 * @see Plateau
	 */
	private Case c;
	/**
	 * Entier representant l'orientation du robot.
	 * 0 gauche 1 devant 2 droite 3 derriere
	 */
	private int orient; 
	/**
	 * Image representant le robot
	 */
	private Image Img;
	
	/**
	 * Constructeur de la classe en fonction du numero du joueur.
	 * @param num_joueur definit le robot du joueur a initialise
	 * @see Joueur
	 * @see Case
	 * @see Robot#c
	 * @see Robot#Img
	 * @see Robot#orient
	 */
	public Robot(int num_joueur){
		if(num_joueur == 1){
			c = new Case(0,7,0,1);
			Img = Toolkit.getDefaultToolkit().getImage("bb8.png");
		}
		else{
			c = new Case(2,7,0,1);
			Img = Toolkit.getDefaultToolkit().getImage("r2_d2.png");
		}
		orient = 1;
	}
	
	/**
	 * Accesseur pour la coordonnee x du robot
	 * @return la coordonnee x du robot
	 * @see Robot#c
	 * @see Case
	 * @see Case#Getx()
	 */
	public int Getx(){
		return c.Getx();
	}
	
	/**
	 * * Accesseur pour la coordonnee y du robot
	 * @return la coordonnee y du robot
	 * @see Robot#c
	 * @see Case
	 * @see Case#Gety()
	 */
	public int Gety(){
		return c.Gety();
	}
	
	/**
	 * Accesseur pour l'orientation du robot
	 * @return l'orientation du robot
	 * @see Robot#orient
	 */
	public int Getorient(){
		return orient;
	}
	
	/**
	 * Accesseur pour l'image du robot
	 * @return l'image du robot
	 * @see Robot#Img
	 */
	public Image GetImg(){
		return Img;
	}
	
	/**
	 * Deplace le robot de v case ou change l'orientation du robot
	 * @param v nombre de cases dont le robot est deplace
	 * @param orientation changement de l'orientation du robot
	 * @param r_j2 robot adverse en cas de collision
	 * @see Robot#c
	 * @see Robot#orient
	 * @see Robot#test_collision(int, int, int, int, Robot)
	 */
	public void deplacement(int v, int orientation, Robot r_j2){
		int x = this.Getx(), y = this.Gety();
		if(orient == 0)
			test_collision(x,-1,v,-1,r_j2);
		
		if(orient == 1)
			test_collision(-1,y,v,-1,r_j2);
		
		if(orient == 2)
			test_collision(x,-1,v,1,r_j2);
		
		if(orient == 3)
			test_collision(-1,y,v,1,r_j2);
		if(orientation == 0){
			if(orient < 2)
				orient += 2;
			else
				orient -= 2;
		}
		
		if(orientation == 2){
			if(orient == 3)
				orient = 0;
			else
				orient++;
		}
		
		if(orientation == 1){
			if(orient == 0)
				orient = 3;
			else
				orient--;
		}
		//System.out.println("Deplacement rob " + this.Getx() + " " + this.Gety());
	}
	
	/**
	 * En cas de collision pousse un robot vers la case a la position x y; lance un son d'accident
	 * @param x nouvelle coordonnee x du robot
	 * @param y nouvelle coordonnee y du robot
	 * @see AePlayWave
	 * @see Robot#c
	 * @see Case#Getx()
	 * @see Case#Gety()
	 * @see Case#change_case(int, int)
	 */
	public void bousculer(int x, int y){	//pousse le robot d'une case dans la direction souhaitée
		Thread playWave=new AePlayWave("accident.wav");
        playWave.start();
		c.change_case(this.Getx() + x, this.Gety() + y);
	}
	
	/**
	 * Fonction testant si il y a une collision avec un autre robot et le deplacant
	 * @param x direction dans laquelle se diriger si different de -1
	 * @param y direction dans laquelle se diriger si different de -1
	 * @param v nombre de case a se deplacer (-1 , 1 , 2 ou 3)
	 * @param increment 1: se deplacer vers la gauche ou l'avant et -1 se deplacer vers la droite ou l'arriere (represente l'orientation du robot)
	 * @param r_j2	robot adverse
	 * @see Robot#c
	 * @see Robot#deplacement(int, int, Robot)
	 * @see Robot#bousculer(int, int)
	 * @see Case#change_case(int, int)
	 */
	public void test_collision(int x, int y,int v,int increment,Robot r_j2){	//on regarde si les robots ne rentrent pas en contact
		if(v == -1){
			if(y == -1){
				x -= increment;
				if(x == r_j2.Getx() && this.Gety() == r_j2.Gety())
					r_j2.bousculer(-increment,0);
				this.c.change_case(x, this.c.Gety());
			}
			else{
				y -= increment;
				if(y == r_j2.Gety() && this.Getx() == r_j2.Getx())
					r_j2.bousculer(0,-increment);
				this.c.change_case(this.c.Getx(), y);
			}
		}
		else{
			if(y == -1){
				for(int i = 0 ; i < v ; i++){
					x += increment;
					if(x == r_j2.Getx() && this.Gety() == r_j2.Gety())
						r_j2.bousculer(increment,0);
				}
				this.c.change_case(x, this.c.Gety());
			}
			else{
				for(int i = 0 ; i < v ; i++){
					y += increment;
					if(y == r_j2.Gety() && this.Getx() == r_j2.Getx())
						r_j2.bousculer(0,increment);
				}
	
				this.c.change_case(this.c.Getx(), y);
			}
		}
		//System.out.println("Deplacement rob test " + this.Getx() + " " + this.Gety() + "orient " + orient);
	}
	
	/**
	 * Deplace le robot vers une position x y (utile lorsque le robot doit retourner vers une position de depart/drapeau) en remettant l'orientation
	 *  du robot vers l'avant
	 * @param c1 Case vers laquelle se deplacer
	 * @see Case#change_case(int, int)
	 * @see Joueur#retour_dernier_drapeau(Case)
	 * @see Robot#orient
	 * @see Robot#c
	 */
	public void position_brut(Case c1){
		c.change_case(c1.Getx(), c1.Gety());
		orient = 1;
	}
	
	/**
	 * Deplace le robot vers une position x y (utile lorsque le robot rencontre un obstacle) en gardant l'orientation
	 *  du robot vers l'avant
	 * @param c1 Case vers laquelle se deplacer
	 * @see Jeu#test_obs(Joueur, Joueur, int[])
	 * @see Case#change_case(int, int)
 	 * @see Robot#c 
	 */
	public void position_brut_or(Case c1){		//changement de position sans changer l'orientation
		c.change_case(c1.Getx(), c1.Gety());
	}
	
}
