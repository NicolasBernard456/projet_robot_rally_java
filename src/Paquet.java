import java.util.*;
import java.awt.*;

/**
 * Classe Paquet representant un paquet de Carte
 * Un paquet est compose d'un tableau de cartes. 
 * @author nico
 *
 */
public class Paquet {
	/**
	 * Tableau contenant les cartes d'un paquet.
	 * @see Carte
	 * @see Paquet#recuperer_paquet(ArrayList)
	 */
	private ArrayList<Carte> cartes;
	
	
	/**
	 * Constructeur initialisant toutes les cartes d'un paquet. Chaque paquet est initialise de la meme maniere puis il est melange.
	 * On initialise un tableau d'image pour que chaque carte soit initialise avec la bonne image.
	 * @see Paquet#Init_image(ArrayList)
	 * @see Paquet#Melanger()
	 * @see Paquet#cartes
	 * @see Paquet#modifier_compteur(int)
	 */
	public Paquet(){
		cartes = new ArrayList<Carte>();
		ArrayList<Image> Img = new ArrayList<Image>();
		Init_image(Img);
		Carte c;
		int i, j, debut = 10, fin = 60, pas = 10;
		for(i = 0 ; i < 7 ; i++){
			for(j = debut ; j <= fin ; j = j + pas){
				c = new Carte(j,i,Img.get(i));
				cartes.add(c);
			}
			int res[] = modifier_compteur(i);
			debut = res[0];
			fin = res[1];
			pas = res[2];
		}
		Melanger();
	}
	
	/**
	 * Constructeur permettant de construire un paquet test contenant un seul type de carte avecla meme vitesse, utile pour les tests. L'entier en parametre 
	 * permet de differencier le constructeur par defaut de celui ci.
	 * @param test permet de choisir le type de carte pour remplir le paquet
	 * @see Paquet#cartes
	 * @see Paquet#Init_image(ArrayList)
	 */
	public Paquet(int test){	//Paquet de test
		cartes = new ArrayList<Carte>();
		ArrayList<Image> Img = new ArrayList<Image>();
		Init_image(Img);
		Carte c;
		int i;
		for(i = 0 ; i < 84 ; i++){
			c = new Carte(i,test,Img.get(test));
			cartes.add(c);
		}
	}
	/**
	 * Constructeur initialisant un paquet avec une seul carte en argument
	 * @param c carte inserer dans le nouveau paquet
	 * @see Paquet#cartes
	 * 
	 */
	
	public Paquet(Carte c){
		cartes = new ArrayList<Carte>();
		cartes.add(c);
	}
	
	/**
	 * Accesseur donnant le tableau de cartes d'un paquet.
	 * @return le tableau de carte d'un paquet
	 * @see Paquet#cartes
	 */
	public ArrayList<Carte> Getcartes(){
		return cartes;
	}
	
	/**
	 * Fonction permettant d'ajouter une carte c dans un Paquet
	 * @param c carte ajoute dans le paquet
	 * @see Paquet#cartes
	 * 
	 */
	public void Ajouter_carte(Carte c){
		cartes.add(c);
	}
	
	/**
	 * Fonction permettant de tirer la premiere carte d'un paquet
	 * @return carte tiree
	 * @see Paquet#cartes
	 */
	public Carte Tirer_carte(){
		if(cartes.size() == 0)
			System.out.println("Erreur plus de cartes");
		Carte c = cartes.get(0);
		cartes.remove(0);
		return c;
	}
	/**
	 * Modifie le compteur pour initialiser le paquet de Carte (initialise les vitesses). Tableau contenant la vitesse 
	 * la plus faible dans la premiere case, la vitesse la plus elevee dans la deuxieme case et le pas entre chaque vitesse. Le parametre i
	 * definit le type de carte dont les vitesses sont a initialiser
	 * @param i type de carte dont les vitesses sont a initialiser
	 * @return un tableau d'entier avec de nouveau compteur
	 * @see Paquet#Paquet()
	 */
	public int[] modifier_compteur(int i){
		int res[]= {0,0,0};
		if(i + 1 == 1){
			res[0] = 70;
			res[1] = 410;
			res[2] = 20;
		}
		if(i + 1 == 2){
			res[0] = 80;
			res[1] = 420;
			res[2] = 20;
		}
		if(i + 1 == 3){
			res[0] = 430;
			res[1] = 480;
			res[2] = 10;
		}
		if(i + 1 == 4){
			res[0] = 490;
			res[1] = 660;
			res[2] = 10;
		}
		if(i + 1 == 5){
			res[0] = 670;
			res[1] = 780;
			res[2] = 10;
		}
		if(i + 1 == 6){
			res[0] = 790;
			res[1] = 840;
			res[2] = 10;
		}
		return res;
}
	
	/**
	 * Initialise le tableau d'image pour le constructeur du paquet de carte
	 * @param Img tableau d'image a initialiser
	 * @see Paquet#Paquet()
	 * @see Paquet#Paquet(int)
	 */
	
	public void Init_image(ArrayList<Image> Img){
		Image image = Toolkit.getDefaultToolkit().getImage("robot_uturn.png");
		Img.add(image);
		image = Toolkit.getDefaultToolkit().getImage("robot_left.png");
		Img.add(image);
		image = Toolkit.getDefaultToolkit().getImage("robot_right.png");
		Img.add(image);
		image = Toolkit.getDefaultToolkit().getImage("robot_rear.png");
		Img.add(image);
		image = Toolkit.getDefaultToolkit().getImage("robot_fwd1.png");
		Img.add(image);
		image = Toolkit.getDefaultToolkit().getImage("robot_fwd2.png");
		Img.add(image);
		image = Toolkit.getDefaultToolkit().getImage("robot_fwd3.png");
		Img.add(image);
	}
	
	/**
	 * Melange le paquet de carte initialise dans le constructeur
	 * @see Paquet#Paquet()
	 */
	public void Melanger(){	//Methode melangeant les cartes du deck
		ArrayList<Carte> cartes_melange = new ArrayList<Carte>();
		Random r = new Random();
		int indice = r.nextInt(cartes.size());
		for(int i = 0 ; i < 83 ; i++){	//On ajoute les cartes aléatoirement 
			//dans un nouveau tableau 
			cartes_melange.add(cartes.get(indice));
			cartes.remove(cartes_melange.get(i));	//On enleve la carte ajoute
			indice = r.nextInt(cartes.size());
		}
		cartes_melange.add(cartes.get(0));	//Pour la derniere carte on sort de la
		//boucle pour eviter les erreurs avec la selection du chiffre aleatoire
		cartes.remove(cartes_melange.get(83));
		cartes = cartes_melange;
	}
	
	/**
	 * Fonction permettant de changer le tableau de carte d'un Paquet avec celui en argument de la fonction
	 * @param cartes_
	 * @see Paquet#cartes
	 */
	public void recuperer_paquet(ArrayList<Carte> cartes_){
		cartes = cartes_;		
	}
	/**
	 * Fonction retournant la taille du paquet de carte
	 * @return taille du paquet de carte (int)
	 * @see Paquet#cartes
	 */
	
	public int get_size(){
		return cartes.size();
	}
	
	/**
	 * Fonction permettant de piocher 9 cartes du Paquet
	 * @return un Paquet de 9 cartes
	 * @see Paquet#Ajouter_carte(Carte)
	 * @see Paquet#cartes
	 * @see Carte
	 * @see Paquet#Tirer_carte()
	 * @see Paquet#Paquet(Carte)
	 */
	public Paquet piocher_cartes(){	//Piocher 9 cartes
		Paquet pioche = new Paquet(this.Tirer_carte());
		for(int i = 0 ; i < 8 ; i ++){
			pioche.Ajouter_carte(this.Tirer_carte());
		}
		return pioche;
	}
}