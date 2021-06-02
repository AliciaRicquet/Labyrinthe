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
	 * A Faire (01/06/2021 EH Finalisée)
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
				IG.changerPiecePlateau(i, j, elementsPartie.getPlateau().getPiece(i,j).getModelePiece(), elementsPartie.getPlateau().getPiece(i,j).getOrientationPiece());
			}
		}
		IG.changerPieceHorsPlateau(elementsPartie.getPieceLibre().getModelePiece(), elementsPartie.getPieceLibre().getOrientationPiece());
		
		// creation et placement des joueurs
		int nbJoueurs = elementsPartie.getNombreJoueurs();
		Joueur[] joueurs = elementsPartie.getJoueurs();
		
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

		Objet[] tabObjet = elementsPartie.getObjets();
		
		for (int i = 0; i<tabObjet.length; i++){
			IG.placerObjetPlateau(tabObjet[i].getNumeroObjet(),tabObjet[i].getPoslePlateau(), tabObjet[i].getPosconnePlateau());
		}

		for(int j= 0; j<(tabObjet.length/nbJoueurs); j++ ){
			IG.changerObjetJoueur(0,joueurs[0].getObjetsJoueur()[j].getNumeroObjet(), j);
			IG.changerObjetJoueur(1,joueurs[1].getObjetsJoueur()[j].getNumeroObjet(), j);
			IG.changerObjetJoueur(2,joueurs[2].getObjetsJoueur()[j].getNumeroObjet(), j);
		}

		IG.rendreVisibleFenetreJeu();
	}

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
	 * A Faire (02/06/2021 SA/IB/AR EnCours)
	 * 
	 * Méthode permettant de lancer une partie.
	 */
	public void lancer(){
		int nbJoueurs = elementsPartie.getNombreJoueurs();
		Joueur[] joueurs = elementsPartie.getJoueurs();
		Objet[] tabObjet = elementsPartie.getObjets();
		Plateau plateau = elementsPartie.getPlateau();
		boolean gagnant = false;
		while (gagnant == false){
			for(int numJoueurs = 0; numJoueurs < nbJoueurs; numJoueurs ++){
				String messageJoueur[]={
				"",
				"Au tour de " + joueurs[numJoueurs].getNomJoueur(),
				"Cliquez pour continuer ...",
				""
				};
				IG.afficherMessage(messageJoueur);
				IG.miseAJourAffichage();
				IG.attendreClic();

				IG.changerJoueurSelectionne(0);
				IG.changerObjetSelectionne(joueurs[numJoueurs].getNombreObjetsRecuperes());
				
				String messagePieceHorsPlateau[]={
					"",
					"Choisissez une orientation", 
					"de la piece libre",
					"Ensuite, selectionnez une flèche",
					""
				};
				IG.afficherMessage(messagePieceHorsPlateau);

				IG.miseAJourAffichage();
				int choix = IG.attendreChoixEntree();
				elementsPartie.insertionPieceLibre(choix);
				for (int i = 0; i<tabObjet.length; i++){
					for(int ligne=0; ligne<7;ligne++){
						for (int colonne=0; colonne<7;colonne++){
							if (tabObjet[i].getPoslePlateau()!=ligne && tabObjet[i].getPosconnePlateau()!=colonne){
								IG.enleverObjetPlateau(ligne, colonne);
							}
						}
					}
				}
				for (int i = 0; i<tabObjet.length; i++){
					IG.placerObjetPlateau(tabObjet[i].getNumeroObjet(),tabObjet[i].getPoslePlateau(), tabObjet[i].getPosconnePlateau());
				}
				IG.miseAJourAffichage();
				
				for (int i=0; i<7; i++){
					for (int j=0; j<7; j++){
						IG.changerPiecePlateau(i, j, elementsPartie.getPlateau().getPiece(i,j).getModelePiece(), elementsPartie.getPlateau().getPiece(i,j).getOrientationPiece());
					}
				}
				IG.changerPieceHorsPlateau(elementsPartie.getPieceLibre().getModelePiece(), elementsPartie.getPieceLibre().getOrientationPiece());
				for (int i=0; i<nbJoueurs;i++){
					IG.placerJoueurSurPlateau(i, joueurs[i].getPosLigne(), joueurs[i].getPosColonne());
					
				}

				IG.miseAJourAffichage();

				for (int i=0; i<nbJoueurs;i++){
					if(joueurs[i].getCategorie()=="ordinateur"){
						IG.placerBilleSurPlateau(joueurs[i].getPosLigne(), joueurs[i].getPosColonne(), 1, 1, 0);
					}else{
						int[] casArr = new int[2];
						casArr = joueurs[i].choisirCaseArrivee(null);
		
						int[][] resultat = plateau.calculeChemin(joueurs[i].getPosLigne(), joueurs[i].getPosColonne(), casArr[0], casArr[1]);
						while(resultat==null){
							casArr = joueurs[i].choisirCaseArrivee(null);
							resultat = plateau.calculeChemin(joueurs[i].getPosLigne(), joueurs[i].getPosColonne(), casArr[0], casArr[1]);
						}
						for (int x=0;x<7;x++){
							for (int j=0;j<7;j++){
								if (resultat!=null){
									IG.placerJoueurSurPlateau(joueurs[i].getNumJoueur(), resultat[resultat.length-1][0], resultat[resultat.length-1][1]);
									for (int k=0; k<resultat.length;k++){
										IG.placerBilleSurPlateau(resultat[k][0], resultat[k][1], 1, 1, i);
									}
								}
							}
						}
					}
				}

				if(joueurs[numJoueurs].getPosLigne() == joueurs[numJoueurs].getObjetsJoueur()[joueurs[numJoueurs].getNombreObjetsRecuperes()].getPoslePlateau() && joueurs[numJoueurs].getPosColonne() == joueurs[numJoueurs].getObjetsJoueur()[joueurs[0].getNombreObjetsRecuperes()].getPosconnePlateau()){
					joueurs[numJoueurs].recupererObjet();
				}

				if(joueurs[numJoueurs].getNombreObjetsRecuperes() == joueurs[numJoueurs].getObjetsJoueur().length){
					gagnant = true;
				}
			}
		}
			
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