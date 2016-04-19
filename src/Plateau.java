import java.util.*;




	


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;
import java.awt.geom.AffineTransform;
/**
 * Classe generant la partie "graphique" du code , Creation de la table de jeu et affichage des differentes animations, cartes et vies.
 * La fonction gere aussi les differentes interactions avec la souris et l'utilisateur
 * @author nico
 */
public class Plateau extends JPanel implements MouseListener, MouseMotionListener {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L; 	//demandé pour éviter un warning
	
	
	/**
	 * Differentes images utilisees pour le programme
	 */
	private Image img0, img1, img_trou, img_flag, img_arrive, img_barriere, img_coeur, img_explos, img_gameover;
	/**
	 * Tableau d'entier gerant la selection des differentes cartes par le joueur
	 */
    int [] cpt_cartes;
    /**
     * Compteur pour l'affichage d'un gif d'une explosion
     */
    int [] explos;
    /**
     * cpt_main est le compteur pour le nombre de cartes selectionnees par l'utilisateur.
     * fin : Entier s'incrementant a chaque fin de tour
     * game_over : compteur pour l'affichage du gif de game_over
     */
    int cpt_main, fin, game_over;
    /**
     * Thread pour la partie graphique
     */
    private Thread th;
    /**
     * Runnable pour la partie graphique
     */
    private Runnable r;
    /**
     * Tableau de cases pour representer chaque case du plateau
     */
    private ArrayList<Case> c;
    /**
     * Tableaux de cases stockant les drapeaux, les trous et les obstacles sur le plateau.
     */
    private ArrayList<Case> pose_drapeau, pose_trou, pose_obstacle;
    /**
     * Joueur 1, Joueur 2 et le joueur courant (joueur 1 ou 2)
     */
    private Joueur j1,j2,j_cour;
    /**
     * Paquet ou les joueurs piochent a chaques tours
     */
    private Paquet pioche;
    
    /**
     * Constructeur du plateau, le tableau de case est initialise pour former un carree de 7x7 cases avec des trous places a des positions donnes
     * , des drapeaux positionne a des position aléatoires ou fixes en fonction du drapeau, les deux robots positionnes a des positions fixes et les 
     * obstacles place aleatoirement.
     * @param j1_ joueur 1
     * @param j2_ joueur 2
     * @see Plateau#cpt_cartes
     * @see Plateau#cpt_main
     * @see Plateau#explos
     * @see Plateau#fin
     * @see Plateau#game_over
     * @see Plateau#th
     * @see Plateau#img0
     * @see Plateau#img1
     * @see Plateau#c
     * @see Plateau#img_arrive
     * @see Plateau#img_barriere
     * @see Plateau#img_coeur
     * @see Plateau#img_explos
     * @see Plateau#img_flag
     * @see Plateau#r
     * @see Plateau#pose_trou
     * @see Plateau#img_gameover
     * @see Plateau#img_trou
     * @see Plateau#j1
     * @see Plateau#j2
     * @see Plateau#j_cour
     * @see Plateau#pioche
     * @see Plateau#pose_drapeau
     * @see Plateau#pose_obstacle
     */
	public Plateau(Joueur j1_, Joueur j2_){
		j1 = j1_;
		j2 = j2_;
		ArrayList<Case> c_ = new ArrayList<Case>();
		ArrayList<Case> pose_drapeau_ = new ArrayList<Case>();
		ArrayList<Case> pose_trou_ = new ArrayList<Case>();
		ArrayList<Case> pose_obstacle_ = new ArrayList<Case>();
		Random r1 = new Random();
		int x = r1.nextInt(4), y, tot;
		cpt_cartes = new int[9];
		explos = new int[4];
		explos[0] = 0;
		explos[3] = 0;
		game_over = 0;
		for(int i = 0 ; i < 8 ; i++){
			for(int j = 0 ; j < 8 ; j++){
				Case c1 = new Case(i,j,0,0);
				c_.add(c1);
			}
			cpt_cartes[i] = 0;
		}
		cpt_main = 1;
		//Ajout des trous et des flags
		c_.get(3).change_type(1);
		pose_trou_.add(new Case(3,0,1,1));
		c_.get(3+8).change_type(1);
		pose_trou_.add(new Case(3,1,1,1));
		c_.get(1+8*7).change_type(1);
		pose_trou_.add(new Case(1,7,1,1));
		c_.get(3+8*3).change_type(1);
		pose_trou_.add(new Case(3,3,1,1));
		c_.get(8 * 7 + 7).change_type(5);
		pose_drapeau_.add(new Case(7,7,5,1));
		c_.get(8 * x + 7).change_type(4);
		pose_drapeau_.add(new Case(7,x,4,1));
		x = r1.nextInt(4);
		c_.get(4 + 8 * x).change_type(3);
		pose_drapeau_.add(new Case(4,x,3,1));
		x = r1.nextInt(4);
		c_.get(1 + 8 * x).change_type(2);
		pose_drapeau_.add(new Case(1,x,2,1));
		for(int i = 0 ; i < 6 ; i ++){	//Place les obstacles
			do{
				x = r1.nextInt(8);
				y = r1.nextInt(8);
				if(x == 0)
					tot = y * 8;
				else
					if(y == 0)
						tot = x;
					else
						tot = x + 8 * y;
			}while(c_.get(tot).Getoccupe() != 0 || tot == 55 || tot == 48 || tot == 59 || tot == 58 || tot == 56);
			c_.get(tot).change_type(6);
			pose_obstacle_.add(new Case(x,y,6,1));
			System.out.println(x + " " + y + " " + tot);
		}
		pose_obstacle = pose_obstacle_;
		pose_trou = pose_trou_;
		pose_drapeau = pose_drapeau_;
		c = c_;
		
		setBackground(Color.WHITE); // Make the background color white
		
		// Necessary for mouse interaction:
		    addMouseListener(this);
		    addMouseMotionListener(this);
		
		
		img0 = Toolkit.getDefaultToolkit().getImage("bb8.png");
		img1 = Toolkit.getDefaultToolkit().getImage("r2d2.png");
		img_trou = Toolkit.getDefaultToolkit().getImage("trou.png");
		img_flag = Toolkit.getDefaultToolkit().getImage("flag2.png");
		img_arrive = Toolkit.getDefaultToolkit().getImage("flag.png");
		img_barriere = Toolkit.getDefaultToolkit().getImage("barriere-en-bois.jpg");
		img_coeur = Toolkit.getDefaultToolkit().getImage("life.png");
		img_explos = Toolkit.getDefaultToolkit().getImage("explosion.gif");
		img_gameover = Toolkit.getDefaultToolkit().getImage("gameover.gif");
		r = new Runnable() {
			public void run() {
				try {
		  			runWork();
				} 
				catch (Exception x) {
		  		// in case ANY exception slips through
	      			x.printStackTrace();
	    		}
	  		}
		};
		
		th = new Thread(r);
			th.start();
	}
	
	
	/**
	 * Fonction changeant la pioche avec le nouveau Paquet p
	 * @param p nouvelle pioche
	 * @see Paquet
	 */
	public void change_pioche(Paquet p){
		pioche = p;
	}
	
	/**
	 * Fonction changeant le joueur courant, remet a 0 cpt_cartes et cpt_main
	 * @param j nouveau joueur courant
	 * @see Plateau#j1
	 * @see Plateau#j2
	 * @see Plateau#j_cour
	 * @see Plateau#cpt_cartes
	 * @see Plateau#cpt_main
	 */
	public void change_jcour(Joueur j){
		j_cour = j;
		cpt_main = 1;
		for(int i = 0 ; i < 9 ; i++){
			cpt_cartes[i] = 0;
		}
		//fin = 0;
	}
    
	/**
	 * Lancement de la partie graphique et sleep pour voir ce qu'il s'y passe
	 */
	private void runWork() {
		while (true) {
			repaint();
			try {
				Thread.sleep(1);
			}
			catch (InterruptedException x) {
				Thread.currentThread().interrupt();
			}
		}
	}

	/**
	 * Fonction principal pour la partie graphique, a chaque repaint cette fonction est appele
	 * La fonction dessine le plateau avec toutes les cases, elle cree les robots, les cartes et les animations dans une fenetre.
	 * La fonction affiche aussi les vies restantes et les cartes a selectionner.
	 */
    ///// DRAWIN
    @Override
    public void paintComponent(Graphics gfx) {
        // This stuff is standard, and should be in any paintComponent method. 
        super.paintComponent(gfx);
        Graphics2D g = (Graphics2D) gfx;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(Color.BLACK);       	
        for(int i = 0 ; i <= 63 ; i ++){
        	if(this.getCase(i).Gettype() == 1){
        		g.drawImage(img_trou,this.getCasey(i)*60+61,this.getCasex(i)*60+61,this);
        		//g.fillRect(this.getCasex(i)*60+60, this.getCasex(i)*60+60, 60, 60);
        	}
        	if(this.getCase(i).Gettype() == 5)
        		g.drawImage(img_arrive,this.getCasey(i)*60+61,this.getCasex(i)*60+61,this);
        	else
        		if(this.getCase(i).Gettype() == 6)
            		g.drawImage(img_barriere,this.getCasey(i)*60+61,this.getCasex(i)*60+61,this);
        		else
        			if(this.getCase(i).Gettype() > 1)
        				g.drawImage(img_flag,this.getCasey(i)*60+61,this.getCasex(i)*60+61,this);
        }
        g.setColor(Color.ORANGE);
        g.drawString("Vie BB8", 550,90);
        for(int i = 0 ; i < j1.Getnb_vie() ; i++)
        	g.drawImage(img_coeur,625 + 60 *i, 60,this);
        g.drawString("Nb drapeaux BB8", 790,90);
        for(int i = 0 ; i < j1.Getcpt_drapeaux() ; i++)
        	g.drawImage(img_flag,900 + 60 *i, 60,this);
        
        
        g.setColor(Color.BLUE);
        g.drawString("Vie R2D2", 550,160);
        for(int i = 0 ; i < j2.Getnb_vie() ; i++)
        	g.drawImage(img_coeur,625 + 60 *i, 130,this);
        g.drawString("Nb drapeaux R2D2", 790,160);
        for(int i = 0 ; i < j2.Getcpt_drapeaux() ; i++)
        	g.drawImage(img_flag,900 + 60 *i, 130,this);
        AffineTransform old= g.getTransform();
        AffineTransform tr1= new AffineTransform();
    	AffineTransform tr2= new AffineTransform();
    	
    	tr1.rotate(Math.toRadians((j1.getr().Getorient() - 1) * 90),j1.getr().Getx()*60+91, j1.getr().Gety()*60+91);
    	tr2.rotate(Math.toRadians((j2.getr().Getorient() - 1) * 90),j2.getr().Getx()*60+91, j2.getr().Gety()*60+91);
    	 
    	g.transform(tr1);
        g.drawImage(img0, j1.getr().Getx()*60+61, j1.getr().Gety()*60+61,this);
        g.setTransform(old);
        g.transform(tr2);
        g.drawImage(img1, j2.getr().Getx()*60+61, j2.getr().Gety()*60+61,this);
        g.setTransform(old);
        g.setColor(Color.BLACK);
        for(int i = 60 ; i <= 540 ; i += 60)
        	g.drawLine(60,i,540,i);

        for(int i = 60 ; i <= 540 ; i += 60)
        	g.drawLine(i,60,i,540);
        //if(j_cour.get_carte_joue().size() != 2)
        	afficher_cartes(g);
        
      //  afficher_cartes_jouees(g);
       if(explos[0] == 1 ){
    	   g.drawImage(img_explos, (explos[1]+1)*60 , explos[2]* 60 + 61,this);
    	   if(explos[3] == 0){
    	        Thread playWave=new AePlayWave("explosion.wav");
    	        playWave.start();
    	   }
    	   if(explos[3] > 50){
    		   explos[0] = 0;
    		   explos[3] = 0;
    	   }
    	   else
    		   explos[3]++;
       }
       if(game_over == 1)
    	   g.drawImage(img_gameover, 450 , 250,this);
    }
    
    /**
     * Fonction affichant les cartes jouees par l'utilisateur (ne fonctionnait pas bien)
     * @param g 
     */
    public void afficher_cartes_jouees(Graphics2D g){
    	if(j_cour.get_carte_joue().size() == 2){
    		g.drawImage(j_cour.get_carte_joue().get(0).getImg(), 800, 60,this);
    		g.drawImage(j_cour.get_carte_joue().get(1).getImg(), 1000, 60,this);
    	}
    }
    
    /**
     * Fonction affichant les cartes selectionnable par l'utilisateur puis affichant les cartes selectionnes par l'utilisateur
     * @param g
	 * @see Plateau#cpt_cartes
	 * @see Plateau#pioche
	 * @see Plateau#fin
	 * @see Plateau#j_cour
	 * @see Plateau#cpt_cartes
	 * @see Plateau#cpt_main
     */
    public void afficher_cartes(Graphics2D g){
    	Image img_carte = Toolkit.getDefaultToolkit().getImage("blanc.png");
    	g.setColor(Color.WHITE);
		for(int j = 0 ; j < 9 ; j++){
			switch(cpt_cartes[j]){
			case 0 :
				if(pioche.Getcartes().get(j).gettype_fleche() >= 0 && pioche.Getcartes().get(j).gettype_fleche() < 7)
						img_carte = pioche.Getcartes().get(j).getImg();
				g.drawImage(img_carte, j*101, 540,this);
				g.drawString(String.valueOf(pioche.Getcartes().get(j).getVitesse()), 101 * j + 55, 565);
				break;
			case 1 :
				img_carte = pioche.Getcartes().get(j).getImg();
				g.drawImage(img_carte, 600, 170,this);
				g.drawString(String.valueOf(pioche.Getcartes().get(j).getVitesse()), 655, 195);
				break;
			case 2 :
				img_carte = pioche.Getcartes().get(j).getImg();
				g.drawImage(img_carte, 700, 170,this);
				g.drawString(String.valueOf(pioche.Getcartes().get(j).getVitesse()), 755, 195);
				break;
			case 3 :
				img_carte = pioche.Getcartes().get(j).getImg();
				g.drawImage(img_carte, 800, 170,this);
				g.drawString(String.valueOf(pioche.Getcartes().get(j).getVitesse()), 855, 195);
				break;
			case 4 : 
				img_carte = pioche.Getcartes().get(j).getImg();
				g.drawImage(img_carte, 900, 170,this);
				g.drawString(String.valueOf(pioche.Getcartes().get(j).getVitesse()), 955, 195);
				break;
			case 5 :
				img_carte = pioche.Getcartes().get(j).getImg();
				g.drawImage(img_carte, 1000, 170,this);
				g.drawString(String.valueOf(pioche.Getcartes().get(j).getVitesse()), 1055, 195);
				break;
			default :
				img_carte = Toolkit.getDefaultToolkit().getImage("blanc.png");
				g.drawImage(img_carte, j*101, 540,this);
			}
		}
		if(cpt_main == 6){
			System.out.println("fin_selection");
			ArrayList<Carte> c = new ArrayList<Carte>();
			int cpt = 1;
			for(int j = 0 ; cpt != 6 ; j++){
				if(j >= 9)
					j = 0;
				if(cpt_cartes[j] == cpt){
					cpt++;
					c.add(pioche.Getcartes().get(j));
					//pioche.Getcartes().get(j).affiche_carte();
					//System.out.println(pioche.Getcartes().get(j).gettype_fleche());
					j = 0;
				}
			}
			j_cour.Getp().recuperer_paquet(c);
			//System.out.println(c.size());
			cpt_main++;
			fin++;
		}
    }
    ///// MOUSE INTERACTION
    // Implementation of MouseListener
    /**
     * Fonction gerant l'appui continue sur la souris
     * On n'utilise pas cette fonction
     */
    @Override
    public void mouseClicked(MouseEvent me) {
    }
    
    /**
     * Fonction gerant l'entree de la souris dans la fenetre
     * On n'utilise pas cette fonction
     */
    @Override
    public void mouseEntered(MouseEvent me) {
    }

    /**
     * Fonction gerant la sortie de la souris de la fenetre
     * On n'utilise pas cette fonction
     */
    @Override
    public void mouseExited(MouseEvent me) {
    }
    
    /**
     * Fonction gerant le clique de souris
     * A chaque clique de souris, on recupere la position du curseur
     * On délimite la zone d'action de l'tilisateur a la partie basse de la fenetre correspondant a la zone ou se trouve les cartes.
     * A chaque clique, on ajoute la carte cliquee au tableau cpt_cartes avec l'indice cpt_main qu'on incremente.
     * @see Plateau#cpt_cartes
     * @see Plateau#cpt_main
     * @see MouseEvent
     */
    @Override
    public void mousePressed(MouseEvent me) {
		int nx=me.getX()/101;
		int ny=me.getY();
		//System.out.println(nx+" "+ny);
		if(nx < 9 && nx >= 0 && ny >= 543){
			if(cpt_cartes[nx] == 0 && cpt_main < 6)
				cpt_cartes[nx] = cpt_main++;
		}
		repaint();
    }

    /**
     * Fonction gerant le relachement du boutton de la souris
     * On n'utilise pas cette fonctio
     */
    @Override
    public void mouseReleased(MouseEvent me) {
    }

    // Implementation of MouseMotionListener
    @Override
    /**
     * Fonction gerant l'appui continu de la souris + deplacement dans la fenetre
     * On actualise la fenetre a chaque fois que la souris est deplace
     */
    public void mouseDragged(MouseEvent me) {
        repaint();
    }

    @Override
    /**
     * Fonction gerant les mouvements de la souris dans la fenetre
     * On n'uitilise pas cette fonction
     */
    public void mouseMoved(MouseEvent me) {        
    }

	
    /**
     * Fonction retournant la taille du tableau de cases (verification de la bonne initialisation du plateau en debuggage)
     * @return taille du plateau
     * @see Plateau#c
     */
	public int getsize(){
		return c.size();
	}
	
	/**
	 * Retourne la case a la position i sur le tableau de case
	 * @param i position de la case dans le tableau
	 * @return la case du tableau a la position i
	 * @see Case
	 * @see Plateau#c
	 */
	public Case getCase(int i){
		return c.get(i);
	}
	
	/**
	 * Retourne la position x de la case a la position i sur le tableau
	 * @param i position de la case dans le tableau
	 * @return la coordonnee x de la case 
	 * @see Case
	 * @see Plateau#c
	 */
	public int getCasex(int i){
		return c.get(i).Getx();
	}
	
	/**
	 * Retourne la position y de la case a la position i sur le tableau
	 * @param i position de la case dans le tableau
	 * @return la coordonnee y de la case 
	 * @see Case
	 * @see Plateau#c
	 */
	public int getCasey(int i){
		return c.get(i).Gety();
	}
	
	/**
	 * Retourne la position du dernier drapeau atteint par le joueur ou sa case de depart si aucun drapeau n'a ete atteint
	 * @param index nombre de drapeau restant a attrape
	 * @param joueur joueur concerne
	 * @return la case du dernier drapeau ou position initial
	 * @see Case
	 * @see Plateau#pose_drapeau
	 */
	public Case Getdrapeau(int index, int joueur){
		if(index == -1){	
			return new Case(-1,-1,0,0);
		}
		if(index == 4 && joueur == 1)
			return new Case(0,7,0,1);
		else
			if(index == 4 && joueur == 2)
				return new Case(2,7,0,1);
			else
				return pose_drapeau.get(index);
	}
	
	/**
	 * Accesseur pour le tableau contenant les cases ou se trouvent les trous
	 * @return tableau de case
	 * @see Case
	 * @see Plateau#pose_trou
	 */
	public ArrayList<Case> Get_pose_trou(){
		return pose_trou;
	}
    
	/**
	 * Accesseur pour le compteur de carte en main
	 * @return cpt_main
	 * @see Plateau#cpt_main
	 */
    public int get_cpt_main(){
    	return cpt_main;
    }
    
    /**
     * Retourne le joueur jouant ce tour
     * @return joueur jouant le tour
     * @see Plateau#j_cour
     */
    public Joueur get_j(){
    	return j_cour;
    }
    
    /**
     * Fonction incrementant le compteur de carte en main
     * @see Plateau#cpt_main
     */
    public void incr(){
    	cpt_main++;
    }
    
	/**
	 * Accesseur pour le tableau contenant les cases ou se trouvent les obstacles
	 * @return tableau de case
	 * @see Case
	 * @see Plateau#pose_obstacle
	 */
    public ArrayList<Case> Get_pose_obs(){
    	return pose_obstacle;
    }
    
    /**
     * Fonction placant le gif d'explosion a la position x y
     * @param x coordonnee x de l'explosion sur le plateau
     * @param y coordonnee y de l'explosion sur le plateau
     * @see Plateau#explos
     * @see Plateau#paintComponent(Graphics)
     */
    public void explosion(int x, int y){
    	explos[0] = 1;
    	explos[1] = x;
    	explos[2] = y;
    }

    /**
     * Fonction mettant l'entier game_over a 1 indiquant la fin de partie et activant le gif de fin de partie
     * @see Plateau#game_over
     * @see Plateau#paintComponent(Graphics)
     */
    public void game_over(){
    	game_over = 1;
    }
}

