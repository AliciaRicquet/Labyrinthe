package joueurs;


import grafix.interfaceGraphique.IG;
import composants.Objet;
import composants.Plateau;
import partie.ElementsPartie;

/**
 * 
 * Cette classe permet de reprÃ©senter un joueur ordinateur de type T3.
 * 
 * @author Jean-FranÃ§ois Condotta - 2021
 *
 */

public class JoueurOrdinateurT3 extends JoueurOrdinateur {

	/**
	 * Constructeur permettant de crÃ©er un joueur.
	 * 
	 * @param numJoueur Le numÃ©ro du joueur.
	 * @param nomJoueur Le nom du joueur.
	 * @param numeroImagePersonnage Le numÃ©ro de l'image reprÃ©sentant le joueur.
	 * @param posLignePlateau La ligne du plateau sur laquelle est positionnÃ©e le joueur.
	 * @param posColonnePlateau La colonne du plateau sur laquelle est positionnÃ©e le joueur.

	 */
	public JoueurOrdinateurT3(int numJoueur,String nomJoueur, int numeroImagePersonnage,int posLignePlateau,int posColonnePlateau) {
				super(numJoueur,nomJoueur, numeroImagePersonnage,posLignePlateau,posColonnePlateau);
	}

	@Override
	public String getCategorie() {
		return "OrdiType3";
	}
	@Override
	public int[] choisirOrientationEntree(ElementsPartie elementsPartie){
		int resultat[]=new int[2];
		for (int i=0; i<=27;i++){ // test toutes les entrées du plateau
			for (int j=0; j<4; j++){ // test toutes les orientation de la piece hors plateau

				// copie des elements de la partie pour simuler une insertion
				ElementsPartie copyElementsPartie = elementsPartie.copy();
				copyElementsPartie.getPieceLibre().setOrientation(j);
				copyElementsPartie.insertionPieceLibre(i);
				int[][] testInsertion1=null;
				int[][] testInsertion2=null;
				int[][] testInsertion3=null;
				int[][] testInsertion4=null;
				int[][] testInsertion0 = copyElementsPartie.getPlateau().calculeChemin(copyElementsPartie.getJoueurs()[getNumJoueur()].getPosLigne(), 
															copyElementsPartie.getJoueurs()[getNumJoueur()].getPosLigne(), 
															copyElementsPartie.getJoueurs()[getNumJoueur()].getProchainObjet().getPoslePlateau(), 
															copyElementsPartie.getJoueurs()[getNumJoueur()].getProchainObjet().getPoslePlateau());
				if (copyElementsPartie.getJoueurs()[getNumJoueur()].getProchainObjet().getPoslePlateau()!=0){
					testInsertion1 = copyElementsPartie.getPlateau().calculeChemin(copyElementsPartie.getJoueurs()[getNumJoueur()].getPosLigne(), 
															copyElementsPartie.getJoueurs()[getNumJoueur()].getPosLigne(), 
															copyElementsPartie.getJoueurs()[getNumJoueur()].getProchainObjet().getPoslePlateau()-1, 
															copyElementsPartie.getJoueurs()[getNumJoueur()].getProchainObjet().getPoslePlateau());
				}
				if(copyElementsPartie.getJoueurs()[getNumJoueur()].getProchainObjet().getPoslePlateau()!=6){
					testInsertion2 = copyElementsPartie.getPlateau().calculeChemin(copyElementsPartie.getJoueurs()[getNumJoueur()].getPosLigne(), 
															copyElementsPartie.getJoueurs()[getNumJoueur()].getPosLigne(), 
															copyElementsPartie.getJoueurs()[getNumJoueur()].getProchainObjet().getPoslePlateau()+1, 
															copyElementsPartie.getJoueurs()[getNumJoueur()].getProchainObjet().getPoslePlateau());
				}
				
				if(copyElementsPartie.getJoueurs()[getNumJoueur()].getProchainObjet().getPoslePlateau()!=0){
					testInsertion3 = copyElementsPartie.getPlateau().calculeChemin(copyElementsPartie.getJoueurs()[getNumJoueur()].getPosLigne(), 
															copyElementsPartie.getJoueurs()[getNumJoueur()].getPosLigne(), 
															copyElementsPartie.getJoueurs()[getNumJoueur()].getProchainObjet().getPoslePlateau(), 
															copyElementsPartie.getJoueurs()[getNumJoueur()].getProchainObjet().getPoslePlateau()-1);
				}
				if(copyElementsPartie.getJoueurs()[getNumJoueur()].getProchainObjet().getPoslePlateau()!=6){
					testInsertion4 = copyElementsPartie.getPlateau().calculeChemin(copyElementsPartie.getJoueurs()[getNumJoueur()].getPosLigne(), 
															copyElementsPartie.getJoueurs()[getNumJoueur()].getPosLigne(), 
															copyElementsPartie.getJoueurs()[getNumJoueur()].getProchainObjet().getPoslePlateau(), 
															copyElementsPartie.getJoueurs()[getNumJoueur()].getProchainObjet().getPoslePlateau()+1);
				}
				
				//Si un chemin vers sont objet est possible on stock i et j (l'entrée et l'orientation de la piece libre)
				if (testInsertion0!=null || testInsertion1!=null || testInsertion2!=null || testInsertion3!=null || testInsertion4!=null){
					resultat[1]=i;
					resultat[0]=j;
					return resultat;
				}
			}
		}
		return resultat;
	}
	@Override
	public int[] choisirCaseArrivee(ElementsPartie elementsPartie) {
		int[] resultatMethode = new int[2];
		int[][] resultat = null;
		Plateau plateau = elementsPartie.getPlateau();
		Joueur[] joueurs = elementsPartie.getJoueurs();
		int[][] resultatPrecis = null;
		resultat = plateau.calculeChemin(joueurs[getNumJoueur()].getPosLigne(), joueurs[getNumJoueur()].getPosColonne(), joueurs[getNumJoueur()].getProchainObjet().getPoslePlateau(),joueurs[getNumJoueur()].getProchainObjet().getPosconnePlateau());
		if (resultat!=null){
			resultatMethode[0]=resultat[resultat.length-1][0];
			resultatMethode[1]=resultat[resultat.length-1][1];
		}else{
			if(joueurs[getNumJoueur()].getProchainObjet().getPoslePlateau()!=0){
				resultat=plateau.calculeChemin(joueurs[getNumJoueur()].getPosLigne(), joueurs[getNumJoueur()].getPosColonne(), 
					joueurs[getNumJoueur()].getProchainObjet().getPoslePlateau()-1,joueurs[getNumJoueur()].getProchainObjet().getPosconnePlateau());
			}
			// si il peut aller sur une ligne juste à coter il fait me déplacement
			// ligne -1
			if (resultat!= null){
				resultatMethode[0]=resultat[resultat.length-1][0];
				resultatMethode[1]=resultat[resultat.length-1][1];
			}else{
				// ligne +1
				if(joueurs[getNumJoueur()].getProchainObjet().getPoslePlateau()!=6){
					resultat=plateau.calculeChemin(joueurs[getNumJoueur()].getPosLigne(), joueurs[getNumJoueur()].getPosColonne(), 
					joueurs[getNumJoueur()].getProchainObjet().getPoslePlateau()+1,joueurs[getNumJoueur()].getProchainObjet().getPosconnePlateau());
				}
				if (resultat!= null){
					resultatMethode[0]=resultat[resultat.length-1][0];
					resultatMethode[1]=resultat[resultat.length-1][1];
				}else{
					// Sinon si il peut aller sur une colonne à coter
					// colonne -1
					if(joueurs[getNumJoueur()].getProchainObjet().getPosconnePlateau()!=0){
						resultat=plateau.calculeChemin(joueurs[getNumJoueur()].getPosLigne(), joueurs[getNumJoueur()].getPosColonne(), 
							joueurs[getNumJoueur()].getProchainObjet().getPoslePlateau(),joueurs[getNumJoueur()].getProchainObjet().getPosconnePlateau()-1);
					}
					if (resultat!= null){
						resultatMethode[0]=resultat[resultat.length-1][0];
						resultatMethode[1]=resultat[resultat.length-1][1];
					}else{
						// colonne +1
						if(joueurs[getNumJoueur()].getProchainObjet().getPosconnePlateau()!=6){
							resultat=plateau.calculeChemin(joueurs[getNumJoueur()].getPosLigne(), joueurs[getNumJoueur()].getPosColonne(), 
								joueurs[getNumJoueur()].getProchainObjet().getPoslePlateau(),joueurs[getNumJoueur()].getProchainObjet().getPosconnePlateau()+1);
						}
						if (resultat!= null){
							resultatMethode[0]=resultat[resultat.length-1][0];
							resultatMethode[1]=resultat[resultat.length-1][1];
						}else{
							// Sinon prend le chemin le plus long
							int maxi=0;
							for (int i=0;i<7;i++){
								for (int j=0;j<7;j++){
									resultat = plateau.calculeChemin(joueurs[getNumJoueur()].getPosLigne(), joueurs[getNumJoueur()].getPosColonne(), i, j);
									if (plateau.calculeChemin(joueurs[getNumJoueur()].getPosLigne(), joueurs[getNumJoueur()].getPosColonne(), i, j)!=null && resultat.length>maxi){
										maxi=resultat.length;
									}
								}
							}
							
							for (int i=0;i<7;i++){
								for (int j=0;j<7;j++){
									resultat = plateau.calculeChemin(joueurs[getNumJoueur()].getPosLigne(), joueurs[getNumJoueur()].getPosColonne(), i, j);
									if (plateau.calculeChemin(joueurs[getNumJoueur()].getPosLigne(), joueurs[getNumJoueur()].getPosColonne(), i, j)!=null && resultat.length==maxi){
										resultatMethode[0]=resultat[resultat.length-1][0];
										resultatMethode[1]=resultat[resultat.length-1][1];
									}
								}
							}
						}
					}
				}
			}
		}
		return resultatMethode;
	}

	
	@Override
	public Joueur copy(Objet objets[]){
		Joueur nouveauJoueur=new JoueurOrdinateurT3(getNumJoueur(),getNomJoueur(), getNumeroImagePersonnage(),getPosLigne(),getPosColonne());
		nouveauJoueur.setObjetsJoueur(this.getObjetsJoueurGeneral(objets));
		while (nouveauJoueur.getNombreObjetsRecuperes()!=this.getNombreObjetsRecuperes())
			nouveauJoueur.recupererObjet();
		return nouveauJoueur;
	}

}
