import java.util.ArrayList;

/**
 * Classe Joueur representant un joueur du jeu.
 * La classe contient un nom, le nom du joueur, un paquet p correspondant aux cartes tirees par le joueur, le robot du joueur
 * , le nombre de vie du joueur, le nombre de drapeau collecte par le joueur, le numero du joueur et les cartes jouees par le joueur
 * @author nico
 *
 */
public class Joueur {
	/**
	 * Nom du joueur sous la forme d'un string
	 * @see Joueur#Joueur(String, Paquet, int)
	 */
	private String nom;
	/**
	 * Paquet correspondant aux cartes tirees par le joueur
	 * @see Paquet#piocher_cartes()
	 * @see Paquet
	 * @see Joueur#Joueur(String, Paquet, int)
	 */
	private Paquet p;
	/**
	 * Robot du joueur
	 * @see Robot
	 * @see Joueur#Joueur(String, Paquet, int)
	 */
	private Robot r;
	/**
	 * Nombre de vie du joueur initialise a 3 par defaut
	 * @see Joueur#Joueur(String, Paquet, int)
	 * @see Joueur#perdre_vie()
	 * 
	 */
	private int nb_vie;
	/**
	 * Nombre de drapeaux collecte par le joueur initialise a 0 par defaut
	 * @see Joueur#Joueur(String, Paquet, int)
	 * @see Joueur#gagner_drapeau()
	 */
	private int cpt_drapeaux;
	/**
	 * Numero representant le joueur (joueur 1 ou 2)
	 * @see Joueur#Joueur(String, Paquet, int)
	 */
	private int numero_joueur;
	/**
	 * Tableau de cartes representant les cartes selectionne par le joueur
	 * @see Joueur#clear_carte_joue()
	 * @see Joueur#nouvelle_cartes(Paquet)
	 */
	private ArrayList<Carte> c_joue;
	
	/**
	 * Constructeur du joueur par defaut, nombre de vie initialise a 3, nombre de drapeau initialise a 0
	 * @param nom_	Nom du joueur
	 * @param p_	Paquet utilise pour piocher les cartes du joueur
	 * @param numero_joueur_	Numero du joueur
	 * @see Joueur#nb_vie
	 * @see Joueur#c_joue
	 * @see Joueur#cpt_drapeaux
	 * @see Joueur#nb_vie
	 * @see Joueur#p
	 * @see Joueur#r
	 * @see Joueur#nom
	 * @see Joueur#numero_joueur
	 * @see Carte
	 */
	public Joueur(String nom_, Paquet p_, int numero_joueur_){
		nom = nom_;
		nb_vie = 3; 
		p = p_.piocher_cartes();
		numero_joueur = numero_joueur_;
		r = new Robot(numero_joueur_);
		cpt_drapeaux = 0;
		ArrayList<Carte> c = new ArrayList<Carte>();
		c_joue = c;
	}
	
	/**
	 * Accesseur pour le nombre de drapeau
	 * @return le nombre de drapeau du joueur
	 * @see Joueur#cpt_drapeaux
	 */
	public int Getcpt_drapeaux(){
		return cpt_drapeaux;
	}
	/**
	 * Accesseur pour le nom du joueur
	 * @return nom du joueur
	 * @see Joueur#nom
	 */
	public String Getnom(){
		return nom;
	}
	
	/**
	 * Accesseur pour le paquet p du joueur
	 * @return le paquet de carte du joueur
	 * @see Joueur#p
	 * @see Paquet
	 */
	public Paquet Getp(){
		return p;
	}
	
	/**
	 * Accesseur pour le robot du jouer
	 * @return le robot du joueur
	 * @see Joueur#r
	 * @see Robot
	 */
	public Robot getr(){
		return r;
	}
	
	/**
	 * Accesseur pour le nombre de vie du joueur
	 * @return le nombre de vie du joueur
	 * @see Joueur#nb_vie
	 */
	public int Getnb_vie(){
		return nb_vie;
	}
	
	/**
	 * Fonction faisant perdre une vie au joueur
	 * @see Joueur#nb_vie
	 */
	public void perdre_vie(){
		nb_vie--;
	}
	
	/**
	 * Accesseur pour le numero du joueur
	 * @return le numero du joueur
	 * @see Joueur#numero_joueur
	 */
	public int getnum(){
		return numero_joueur;
	}
	
	/**
	 * Fonction permettant de piocher 8 cartes et les stocker dans p
	 * @param p_ paquet dans lequel piocher
	 * @see Joueur#p
	 * @see Paquet#piocher_cartes()
	 */
	public void nouvelle_cartes(Paquet p_){	//Piocher 8 cartes
		p = p_.piocher_cartes();
	}
	
	/**
	 * Fonction utilisant les cartes des deux joueurs et mettant a jour l'interface graphique. Les deplacements sont ensuite envoye vers la fonction
	 * deplacement_rob_gene pour deplacer les robots en fonction du deplacement desire.
	 * @param j2 2eme joueur
	 * @param c_j1 Carte jouee par le joueur 1
	 * @param c_j2 Carte jouee par le joueur 2
	 * @see Plateau
	 * @see Joueur#r
	 * @see Joueur#deplacement_rob_gene(int, Joueur)
	 * @see Joueur#init_carte_joue(Carte, Carte)
	 * @see Joueur#clear_carte_joue()
	 * @see Carte#Comparer(Carte)
	 */
	public void deplacement(Joueur j2, Carte c_j1, Carte c_j2){	//Deplacement du robot avec une carte tirée
		
		int choix = 0;
		//Choix du joueur qui commence
		if (c_j1.Comparer(c_j2) == 0)
			choix = 1;			    
		else
			choix = 2;
		//Robot le plus rapide se déplace en premier
		for(int j = 0 ; j < 2 ; j++){
			if(choix == 1){
				this.deplacement_rob_gene(c_j1.gettype_fleche(),j2);
			}
			else{
				//Le second robot se deplace
				j2.deplacement_rob_gene(c_j2.gettype_fleche(),this);
			}
			choix--;
		}
		if(numero_joueur == 1)
			init_carte_joue(c_j1, c_j2);
		else

		init_carte_joue(c_j2, c_j1);
		try{
			Thread.sleep(1000);
		}
		catch (InterruptedException x) {
			Thread.currentThread().interrupt();
		}
		clear_carte_joue();
	}
	
	/**
	 * Fonction de deplacement des robots cas des deplacements d'une case ou des rotations
	 * @param type	type de la carte 
	 * @param j2	2eme joueur
	 * @see Joueur#r
	 * @see Robot#deplacement(int, int, Robot)
	 */
	public void deplacement_rob_gene(int type, Joueur j2){
		if(type < 3){
			r.deplacement(0, type,j2.getr());
		}
		else
			if(type == 3)
				r.deplacement(-1, 3,j2.getr());
			else
				r.deplacement(type - 3, 3, j2.getr());
	}
	
	/**
	 * Fonction de deplacement des robots cas des deplacements de plusieurs cases
	 * @param j2	2eme joueur
	 * @see Joueur#r
	 * @see Robot#deplacement(int, int, Robot)
	 */
	public void deplacement_carte2_3(Joueur j2){	//Deplacement du robot avec une carte tirée (cas des deplacement 2 et 3 en avant
		
		r.deplacement(1, 3, j2.getr());
		try{
			Thread.sleep(1000);
		}
		catch (InterruptedException x) {
			Thread.currentThread().interrupt();
		}
	}
	
	/**
	 * Fonction initialisant les cartes selectionnes par les joueurs
	 * @param c1 carte du joueur 1
	 * @param c2 carte du joueur 2
	 * @see Joueur#c_joue
	 */
	public void init_carte_joue(Carte c1, Carte c2){
		c_joue.add(c1);
		c_joue.add(c2);
	}
	/**
	 * Fonction effacant les cartes selectionnes par les joueurs
	 * @see Joueur#c_joue
	 */
	public void clear_carte_joue(){
		c_joue.remove(0);
		c_joue.remove(0);
	}
	
	/**
	 * Fonction placant le robot a la case du dernier drapeau (en argument de la fonction)
	 * @param c case du drapeau
	 * @see Joueur#r
	 * @see Robot#position_brut(Case)
	 * @see Case
	 */
	public void retour_dernier_drapeau(Case c){
		r.position_brut(c);
	}
	
	/**
	 * Fonction faisant gagner un drapeau au joueur
	 * @see Joueur#cpt_drapeaux
	 */
	public void gagner_drapeau(){
		cpt_drapeaux++;
	}
	
	/**
	 * Accesseur pour les cartes selectionne par le joueur
	 * @return les cartes selectionnes par le joueur
	 * @see Joueur#c_joue
	 */
	public ArrayList<Carte> get_carte_joue(){
		return c_joue;
	}
}
