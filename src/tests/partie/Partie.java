package partie;

import composants.Objet;
import composants.Piece;
import composants.Plateau;
import grafix.interfaceGraphique.IG;
import joueurs.Joueur;
import joueurs.JoueurOrdinateur;

public class Partie {
	static double version=0.0;


	private ElementsPartie elementsPartie; // Les éléments de la partie.

	/**
	 * 
	 * A Faire (Quand Qui Statut)
	 * 
	 * Constructeur permettant de créer et d'initialiser une nouvelle partie.
	 */
	public Partie(){
		// Initialisation de la partie
		parametrerEtInitialiser();

		// On affiche l'ensemble des éléments
		
        //creation et mise en place des pieces du plateau
        for (int i=0; i<7; i++){
            for (int j=0; j<7; j++){
                IG.changerPiecePlateau(i, j, plateau.getPiece(i,j).getModelePiece(), plateau.getPiece(i,j).getOrientationPiece());
            }
        }
        IG.changerPieceHorsPlateau(pieceHorsPlateau.getModelePiece(), pieceHorsPlateau.getOrientationPiece());
         
        // creation et placement des joueurs
        if (nbJoueurs==2){
            joueurs[0].setPosition(0, 0);
            IG.placerJoueurSurPlateau(0, 0, 0);
            joueurs[1].setPosition(0, 6);
            IG.placerJoueurSurPlateau(1, 0, 6);
        }else{
            joueurs[0].setPosition(0, 0);
            IG.placerJoueurSurPlateau(0, 0, 0);
            joueurs[1].setPosition(0, 6);
            IG.placerJoueurSurPlateau(1, 0, 6);
            joueurs[2].setPosition(6, 6);
            IG.placerJoueurSurPlateau(2, 6, 6);
        }
        // mise en place des image/nom/categorie des joueurs
        for (int i=0; i<nbJoueurs;i++){
            int numImageJoueur=(joueurs[i].getNumeroImagePersonnage());
            String nomJoueur=(joueurs[i].getNomJoueur());
            String categorieJoueur=(joueurs[i].getCategorie());
            IG.changerNomJoueur(i, nomJoueur+" ("+categorieJoueur+")");
            IG.changerImageJoueur(i,numImageJoueur);
        }
            
        for (int i = 0; i<tab.length; i++){
            IG.placerObjetPlateau(tab[i].getNumeroObjet(),tab[i].getPosconnePlateau(), tab[i].getPoslePlateau());
        }

		IG.rendreVisibleFenetreJeu();
	}
s
	/**
	 * Méthode permettant de paramètrer et initialiser les éléments de la partie.
	 */
	private void parametrerEtInitialiser(){
		// Saisie des différents paramètres
		Object parametresJeu[];
		parametresJeu=IG.saisirParametres();
		int nombreJoueurs=((Integer)parametresJeu[0]).intValue();
		IG.creerFenetreJeu("- Version "+version, nombreJoueurs);
		// Création des joueurs
		Joueur joueurs[]=Joueur.nouveauxJoueurs(parametresJeu);
		// Création des éléments de la partie
		elementsPartie=new ElementsPartie(joueurs);
	}


	/**
	 * 
	 * A Faire (Quand Qui Statut)
	 * 
	 * Méthode permettant de lancer une partie.
	 */
	public void lancer(){
		// A Compléter
	}

	/**
	 * 
	 * Programme principal permettant de lancer le jeu.
	 * 
	 * @param args Les arguments du programmes.
	 */
	public static void main(String[] args) {
		while(true){
			Partie partie=new Partie();
			partie.lancer();
		}
	}

}