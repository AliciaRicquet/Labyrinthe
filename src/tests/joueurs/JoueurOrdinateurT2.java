package joueurs;

import composants.Objet;
import composants.Piece;
import composants.Plateau;
import composants.Utils;
import grafix.interfaceGraphique.IG;
import partie.ElementsPartie;

/**
 * 
 * Cette classe permet de reprÃ©senter un joueur ordinateur de type T2.
 * 
 * @author Jean-FranÃ§ois Condotta - 2021
 *
 */

public class JoueurOrdinateurT2 extends JoueurOrdinateur {

	/**
	 * Constructeur permettant de crÃ©er un joueur.
	 * 
	 * @param numJoueur Le numÃ©ro du joueur.
	 * @param nomJoueur Le nom du joueur.
	 * @param numeroImagePersonnage Le numÃ©ro de l'image reprÃ©sentant le joueur.
	 * @param posLignePlateau La ligne du plateau sur laquelle est positionnÃ©e le joueur.
	 * @param posColonnePlateau La colonne du plateau sur laquelle est positionnÃ©e le joueur.

	 */
	public JoueurOrdinateurT2(int numJoueur,String nomJoueur, int numeroImagePersonnage,int posLignePlateau,int posColonnePlateau) {
				super(numJoueur,nomJoueur, numeroImagePersonnage,posLignePlateau,posColonnePlateau);
	}

	@Override
	public String getCategorie() {
		return "OrdiType2";
	}
	@Override
	public int[] choisirOrientationEntree(ElementsPartie elementsPartie){
		int resultat[]=new int[2];
		resultat[1]=3;
		resultat[0]=IG.recupererOrientationPieceHorsPlateau();
		return resultat;
	}
	@Override
	public int[] choisirCaseArrivee(ElementsPartie elementsPartie) {
		int resultat[]=new int[2];
		resultat[0]=this.getPosLigne();
		resultat[1]=this.getPosColonne();
		return resultat;
	}


	@Override
	public Joueur copy(Objet objets[]){
		Joueur nouveauJoueur=new JoueurOrdinateurT2(getNumJoueur(),getNomJoueur(), getNumeroImagePersonnage(),getPosLigne(),getPosColonne());
		nouveauJoueur.setObjetsJoueur(this.getObjetsJoueurGeneral(objets));
		while (nouveauJoueur.getNombreObjetsRecuperes()!=this.getNombreObjetsRecuperes())
			nouveauJoueur.recupererObjet();
		return nouveauJoueur;
	}

}
