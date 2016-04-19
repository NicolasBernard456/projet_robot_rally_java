import java.util.ArrayList;
import javax.swing.JFrame;

/**
 * Classe jeu gerant l'ensemble du programme et contenant la fonction "main".
 * 
 * @author nico
 *
 */
public class Jeu {
   
	/**
	 * Joueur 1
	 * @see Joueur
	 */
	private Joueur j1_;
	/**
	 * Joueur 2
	 * @see Joueur
	 */
	private Joueur j2_;
	/**
	 * Paquet pour la pioche de chaque joueur, partage avec la pioche de la classe Plateau
	 * @see Paquet
	 */
	private Paquet Pioche;
	/**
	 * Plateau de jeu pour la partie graphique, lancee dans le main
	 * @see Plateau
	 */
	private Plateau plateau_jeu;

	/**
	 * Constructeur de la classe jeu, on initialise un Paquet pour la pioche, 2 joueurs et le plateau
	 * @see Plateau
	 * @see Joueur
	 * @see Paquet
	 * @see Jeu#j1_
	 * @see Jeu#j2_
	 * @see Jeu#plateau_jeu
	 * @see Jeu#Pioche
	 */
    public Jeu() {
    	Pioche = new Paquet();
    	j1_ = new Joueur("joueur 1",Pioche.piocher_cartes(),1);
    	j2_ = new Joueur("joueur 2",Pioche.piocher_cartes(),2);
    	plateau_jeu = new Plateau(j1_,j2_);
    }
    
    /**
     * Fonction permettant de deplacer les deux robots a partir des cartes tirees par le joueur, on effectue les tests en cas d'obstacle, sortie de plateau
     *  drapeau, et trou. En cas de deplacement sur plusieurs cases, on appelle une fonction intermediaire qui deplace le robot case par case et fait
     *  les tests pour chaque deplacements
     * @param j1 Joueur 1
     * @param j2 Joueur 2
     * @see Jeu#ordre_deplacement(Joueur, Joueur, Carte, Carte)
     * @see Jeu#test_drapeau(Joueur, Joueur)
     * @see Jeu#test_obs(Joueur, Joueur, int[])
     * @see Jeu#test_plateau(Joueur, Joueur)
     * @see Jeu#test_trou(Joueur, Joueur)
     * @see Joueur
     * @see Carte
     */
    public void deplacement_robot(Joueur j1, Joueur j2){
    	for(int i = 0 ; i < 5 ; i ++){
    		//System.out.println("deplacement");
    		Carte c_j1 = j1.Getp().Tirer_carte();
    		Carte c_j2 = j2.Getp().Tirer_carte();
    		int[] ancienne_pos_j = new int [4];
    		ancienne_pos_j[0] = (j1.getr().Getx());
    		ancienne_pos_j[1] = (j1.getr().Gety());
    		ancienne_pos_j[2] = (j2.getr().Getx());
    		ancienne_pos_j[3] = (j2.getr().Gety());
    		if(c_j1.gettype_fleche() < 5 && c_j2.gettype_fleche() < 5)
    			j1.deplacement(j2,c_j1,c_j2);
    		else{
    			ordre_deplacement(j1,j2,c_j1,c_j2);
    		}
    		test_obs(j1, j2,ancienne_pos_j);	//On regarde si on est pas sur un obstacle
    		test_plateau(j1, j2); // On regarde si les robots sont toujours sur le plateau
    		test_drapeau(j1, j2); //On regarde si on a atteint un drapeau
    		test_trou(j1, j2); // On regarde si on est pas tombé dans un trou
    	}
    	
    }
    
    /**
     * Fonction appelle en cas de deplacement sur plusieurs cases du robot, les tests effectues sont les meme que dans la fonction {@link Jeu#deplacement_robot(Joueur, Joueur)}
     * 
     * @param j1 Joueur 1
     * @param j2 Joueur 2
     * @param c_j1 Carte jouee par le joueur 1
     * @param c_j2 Carte jouee par le joueur 2
	 * @see Jeu#deplacement_robot(Joueur, Joueur)
     * @see Jeu#test_drapeau(Joueur, Joueur)
     * @see Jeu#test_obs(Joueur, Joueur, int[])
     * @see Jeu#test_plateau(Joueur, Joueur)
     * @see Jeu#test_trou(Joueur, Joueur)
     * @see Joueur
     * @see Carte
     */
    public void ordre_deplacement(Joueur j1, Joueur j2, Carte c_j1, Carte c_j2){
		int choix = 0;
		int[] ancienne_pos_j = new int [4];
		//Choix du joueur qui commence
		if (c_j1.Comparer(c_j2) == 0)
			choix = 1;			    
		else
			choix = 2;
		//Robot le plus rapide se déplace en premier
		if(choix == 1){
			if(c_j1.gettype_fleche() >= 5)
				for(int j = 0 ; j < c_j1.gettype_fleche() - 3 ; j++){
					ancienne_pos_j[0] = (j1.getr().Getx());
					ancienne_pos_j[1] = (j1.getr().Gety());
					ancienne_pos_j[2] = (j2.getr().Getx());
					ancienne_pos_j[3] = (j2.getr().Gety());
					j1.deplacement_carte2_3(j2);
					test_obs(j1, j2,ancienne_pos_j);	//On regarde si on est pas sur un obstacle
		    		test_plateau(j1,j2); // On regarde si les robots sont toujours sur le plateau
		    		test_drapeau(j1,j2); //On regarde si on a atteint un drapeau
		    		test_trou(j1 , j2); // On regarde si on est pas tombé dans un trou
				}
			else
				j1.deplacement_rob_gene(c_j1.gettype_fleche(), j2);
			if(c_j2.gettype_fleche() >= 5)
				for(int j = 0 ; j < c_j2.gettype_fleche() - 3 ; j++){
					ancienne_pos_j[0] = (j1.getr().Getx());
					ancienne_pos_j[1] = (j1.getr().Gety());
					ancienne_pos_j[2] = (j2.getr().Getx());
					ancienne_pos_j[3] = (j2.getr().Gety());
					j2.deplacement_carte2_3(j1);
					test_obs(j1, j2,ancienne_pos_j);	//On regarde si on est pas sur un obstacle
		    		test_plateau(j1,j2); // On regarde si les robots sont toujours sur le plateau
		    		test_drapeau(j1,j2); //On regarde si on a atteint un drapeau
		    		test_trou(j1 , j2); // On regarde si on est pas tombé dans un trou
				}
			else
				j2.deplacement_rob_gene(c_j2.gettype_fleche(), j1);
		}
		else{
			if(c_j2.gettype_fleche() >= 5)
				for(int j = 0 ; j < c_j2.gettype_fleche() - 3 ; j++){
					ancienne_pos_j[0] = (j1.getr().Getx());
					ancienne_pos_j[1] = (j1.getr().Gety());
					ancienne_pos_j[2] = (j2.getr().Getx());
					ancienne_pos_j[3] = (j2.getr().Gety());
					j2.deplacement_carte2_3(j1);
					test_obs(j1, j2,ancienne_pos_j);	//On regarde si on est pas sur un obstacle
		    		test_plateau(j1,j2); // On regarde si les robots sont toujours sur le plateau
		    		test_drapeau(j1,j2); //On regarde si on a atteint un drapeau
		    		test_trou(j1 , j2); // On regarde si on est pas tombé dans un trou
				}
			else
				j2.deplacement_rob_gene(c_j2.gettype_fleche(), j1);
			if(c_j1.gettype_fleche() >= 5)
				for(int j = 0 ; j < c_j1.gettype_fleche() - 3 ; j++){
					ancienne_pos_j[0] = (j1.getr().Getx());
					ancienne_pos_j[1] = (j1.getr().Gety());
					ancienne_pos_j[2] = (j2.getr().Getx());
					ancienne_pos_j[3] = (j2.getr().Gety());
					j1.deplacement_carte2_3(j2);
					test_obs(j1, j2,ancienne_pos_j);	//On regarde si on est pas sur un obstacle
		    		test_plateau(j1,j2); // On regarde si les robots sont toujours sur le plateau
		    		test_drapeau(j1,j2); //On regarde si on a atteint un drapeau
		    		test_trou(j1 , j2); // On regarde si on est pas tombé dans un trou
				}
			else
				j1.deplacement_rob_gene(c_j1.gettype_fleche(), j2);
		}
    }
    
    /**
     * Fonction testant si un des robots n'est pas en dehors de plateau et fais perdre une vie au joueur si c'est le cas. Le robot est ensuite renvoye
     * vers son dernier drapeau ou son point de depart
     * @param j1 Joueur 1
     * @param j2 Joueur 2
     * @see Plateau
     * @see Jeu#plateau_jeu
     * @see Joueur
     * @see Jeu#perdre_vie(Joueur)
     */
    public void test_plateau(Joueur j1, Joueur j2){	/*AJOUTER LES CAS TOMBER DANS UN TROU*/
    	if(j1.getr().Getx() < 0 || j1.getr().Getx() > 7 || j1.getr().Gety() < 0 || j1.getr().Gety() > 7){
    		plateau_jeu.explosion(j1.getr().Getx(),j1.getr().Gety());
    		perdre_vie(j1);
    		j1.retour_dernier_drapeau(plateau_jeu.Getdrapeau(4-j1.Getcpt_drapeaux(),j1.getnum()));
    	}
    	if(j2.getr().Getx() < 0 || j2.getr().Getx() > 7 || j2.getr().Gety() < 0 || j2.getr().Gety() > 7){
    		plateau_jeu.explosion(j2.getr().Getx(),j2.getr().Gety());
    		perdre_vie(j2);
    		j2.retour_dernier_drapeau(plateau_jeu.Getdrapeau(4-j2.Getcpt_drapeaux(),j2.getnum()));
    	}
    }
    
    /**
     * Fonction testant si un des robots n'est pas tombe dans un trou et fais perdre une vie au joueur si c'est le cas. Le robot est ensuite renvoye
     * vers son dernier drapeau ou son point de depart
     * @param j1 Joueur 1
     * @param j2 Joueur 2
     * @see Plateau
     * @see Jeu#plateau_jeu
     * @see Joueur
     * @see Jeu#perdre_vie(Joueur)
     * @see Plateau#Get_pose_trou()
     */
    public void test_trou(Joueur j1, Joueur j2){
    	ArrayList<Case> c = plateau_jeu.Get_pose_trou();
    	for(int i = 0 ; i < c.size() ; i++ ){
    		if(j1.getr().Getx() == c.get(i).Getx() && j1.getr().Gety() == c.get(i).Gety()){
    			perdre_vie(j1);
        		j1.retour_dernier_drapeau(plateau_jeu.Getdrapeau(4-j1.Getcpt_drapeaux(),j1.getnum()));
    		}
    		if(j2.getr().Getx() == c.get(i).Getx() && j2.getr().Gety() == c.get(i).Gety()){
    			perdre_vie(j2);
        		j2.retour_dernier_drapeau(plateau_jeu.Getdrapeau(4-j2.Getcpt_drapeaux(),j2.getnum()));
    		}
    	}
    }
    
    /**
     * Fonction testant si un des robot a atteint un drapeau. Si c'est le cas on appelle la fonction gagner un drapeau.
     * @param j1 Joueur 1
     * @param j2 Joueur 2     
     * @see Plateau
     * @see Jeu#plateau_jeu
     * @see Joueur
     * @see Joueur#gagner_drapeau()
     */
    public void test_drapeau(Joueur j1, Joueur j2){
    	System.out.println("rob x y : " + j2.getr().Getx() + " " + j2.getr().Gety());
    	System.out.println("drapeau x y : " + plateau_jeu.Getdrapeau(3 - j2.Getcpt_drapeaux(),j2.getnum()).Getx() + " " + plateau_jeu.Getdrapeau(3 -j2.Getcpt_drapeaux(),j1.getnum()).Gety());
    	
    	if(j1.getr().Getx() == plateau_jeu.Getdrapeau(3 - j1.Getcpt_drapeaux(),j1.getnum()).Getx() && j1.getr().Gety() == plateau_jeu.Getdrapeau(3 - j1.Getcpt_drapeaux(),j1.getnum()).Gety()){
    		j1.gagner_drapeau();
    		System.out.println("nb drapeau = " + j1.Getcpt_drapeaux());
    	}
    	if(j2.getr().Getx() == plateau_jeu.Getdrapeau(3 - j2.Getcpt_drapeaux(),j2.getnum()).Getx() && j2.getr().Gety() == plateau_jeu.Getdrapeau(3 - j2.Getcpt_drapeaux(),j2.getnum()).Gety()){
    		j2.gagner_drapeau();
    		System.out.println("nb drapeau = " + j2.Getcpt_drapeaux());
    	}
    	
    }
    
    /**
     * Fonction testant si on est sur un obstacle, si c'est le cas on replace le robot a sa position precedente
     * @param j1 Joueur 1
     * @param j2 Joueur 2
     * @param ancienne_pos Ancienne position du robot
     * @see Plateau
     * @see Jeu#plateau_jeu
     * @see Joueur
     * @see Robot#position_brut_or(Case)
     */
    public void test_obs(Joueur j1, Joueur j2, int[] ancienne_pos){
    	ArrayList<Case> tab_obs = plateau_jeu.Get_pose_obs();
    	int i = 0;
    	while(i < tab_obs.size()){
    		System.out.println("xj1 = " + j1.getr().Getx() + "yj1 = " + j1.getr().Gety() + "obsx = " + tab_obs.get(i).Getx() + "obsy = " + tab_obs.get(i).Gety());
    		if(tab_obs.get(i).Getx() == j1.getr().Getx() && tab_obs.get(i).Gety() == j1.getr().Gety() ){
    			if(j1.getr().Getx() == ancienne_pos[0])
    				if(j1.getr().Gety() - ancienne_pos[1] < 0)
    					j1.getr().position_brut_or(new Case(j1.getr().Getx(),j1.getr().Gety() + 1,0,0));
    				else
    					j1.getr().position_brut_or(new Case(j1.getr().Getx(),j1.getr().Gety() - 1,0,0));
    			else{
    				if(j1.getr().Getx() - ancienne_pos[0] < 0)
    					j1.getr().position_brut_or(new Case(j1.getr().Getx() + 1,j1.getr().Gety(),0,0));
    				else
    					j1.getr().position_brut_or(new Case(j1.getr().Getx() - 1,j1.getr().Gety(),0,0));
    			}
    		}
    		if(tab_obs.get(i).Getx() == j2.getr().Getx() && tab_obs.get(i).Gety() == j2.getr().Gety() ){
    			if(j2.getr().Getx() == ancienne_pos[2])
    				if(j2.getr().Gety() - ancienne_pos[3] < 0)
    					j2.getr().position_brut_or(new Case(j2.getr().Getx(),j2.getr().Gety() + 1,0,0));
    				else
    					j2.getr().position_brut_or(new Case(j2.getr().Getx(),j2.getr().Gety() - 1,0,0));
    			else{
    				if(j2.getr().Getx() - ancienne_pos[2] < 0)
    					j2.getr().position_brut_or(new Case(j2.getr().Getx() + 1,j2.getr().Gety(),0,0));
    				else
    					j2.getr().position_brut_or(new Case(j2.getr().Getx() - 1,j2.getr().Gety(),0,0));
    			}
    		}
    		i++;
    	}
    }
    
    /**
     * Fonction appelant la methode perdre_vie d'un joueur
     * @param j Joueur perdant une vie
     * @see Joueur
     * @see Joueur#perdre_vie()
     */
    public void perdre_vie(Joueur j){
    	j.perdre_vie();
    	System.out.println("nb _vie = " + j.Getnb_vie());
    }
    
    /**
     * Accesseur pour le plateau de jeu
     * @return le plateau de jeu
     * @see Plateau
     * @see Jeu#plateau_jeu
     */
    public Plateau get_plateau(){
    	return plateau_jeu;
    }
    
    /**
     * Accesseur pour le joueur
     * @param j entier representant le joueur 1 ou 2
     * @return le joueur desire
     * @see Joueur
     * @see Jeu#j1_
     * @see Jeu#j2_
     */
    public Joueur get_joueur(int j){
    	if (j == 1)
    		return j1_;
    	else
    		return j2_;
    }
    
    /**
     * Fonction testant s'il y a assez de cartes dans le paquet de carte pour piocher les 9 cartes d'un joueur. S'il n'y en a pas assez on renvoie 0 et 1 sinon.
     * @param p	Paquet a tester
     * @return 1 si il y assez de cartes 0 sinon
     * @see Paquet
     */
    public int test_paquet(Paquet p){
		if(p.get_size()-9 < 0){
			return 0;
		}
		return 1;
	}
    
    /**
     * Fonction testant si le jeu est termine ou non en regardant le nombre de vie de chaque joueurs et le nombre de drapeaux de chaques joueurs.
     * @param j1 Joueur 1
     * @param j2 Joueur 2
     * @return faux si le jeux est fini et vrai sinon
     */
    public boolean test_fin_jeu(Joueur j1, Joueur j2){
    	if(j2.Getnb_vie() == 0 || j1.Getcpt_drapeaux() == 4){
    		System.out.println("VICTOIRE J1");
    		return false;
    	}
    	else
    		if(j1.Getnb_vie() == 0 || j2.Getcpt_drapeaux() == 4){
        			System.out.println("VICTOIRE J2");
        			return false;
        	}
    		else
    			return true;
    }
    
    /**
     * Fonction main.
     * On construit la classe Jeu, paquet et plateau. Puis on lance dans une boucle while avec pour condition la fonction test_fin_jeu.
     * Dans la boucle on gere la relation entre la partie graphique et non graphique du jeu. On fait jouer chaque joueur chacun leur tour en commencant par
     * le joueur 1. Lorsque les deux joueurs ont termine leur tour, on ajoute un sleep pour laisser le temps a la partie graphique de
     * s'executer lors des deplacements. 
     * @param args Non utilise
     * @see Jeu#deplacement_robot(Joueur, Joueur)
     * @see Jeu#test_fin_jeu(Joueur, Joueur)
     */
    public static void main(String[] args) {
    	//Paquet p = new Paquet();	//pioche
    	Paquet p = new Paquet();
    	int tour = 1;
    	Jeu j = new Jeu();
        JFrame frame = new JFrame("Plateau de jeu");
        // Dimensions of the window in pixels:
        frame.setSize(1280, 720);
        // Quit the program when the window is closed:
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // The window will contain only our panel:

        frame.add(j.plateau_jeu);
        
        // Make the window visible:
        frame.setVisible(true);
        j.get_plateau().change_jcour(j.get_joueur(1));
        j.get_plateau().change_pioche(p.piocher_cartes());
        while(j.test_fin_jeu(j.get_joueur(1),j.get_joueur(2))){
        	if(j.get_plateau().get_cpt_main() >= 6 && tour == 2){
        		try {
    				Thread.sleep(10);
    			}
    			catch (InterruptedException x) {
    				Thread.currentThread().interrupt();
    			}
        		j.deplacement_robot(j.get_plateau().get_j(),j.get_joueur(1));
        		tour = 0;
        	}
        	if(j.get_plateau().get_cpt_main() >= 7){
        		tour ++;
        		if(j.get_plateau().get_j().getnum() == j.get_joueur(1).getnum())
        			j.get_plateau().change_jcour(j.get_joueur(2));
        		else
        			j.get_plateau().change_jcour(j.get_joueur(1));
        		if(j.test_paquet(p) == 0)
        			p = new Paquet();
        		j.get_plateau().change_pioche(p.piocher_cartes());
        	}
        	else
        		System.out.print("");
        }
        j.get_plateau().game_over();
        try {
			Thread.sleep(8000);
		}
		catch (InterruptedException x) {
			Thread.currentThread().interrupt();
		}
        frame.dispose();
    }
}
