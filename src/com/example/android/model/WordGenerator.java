package word;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

/**
 * Cette classe va g�n�rer les mots qui seront utilis�s dans le jeu
 * Elle r�pond au pattern Singleton
 * 
 * @author Jessica CHERRUAU
 *
 */
public class WordGenerator {
	
	/*****************************************************************************************
	 * Attributs *****************************************************************************
	 */
	
	/**
	 * DICTIONNARY_PATH contient le chemin vers le fichier texte contenant le dictionnaire
	 * Il s'agit d'une liste de mots, chaque mot est s�par� d'un retour � la ligne
	 */
	private final static String DICTIONNARY_PATH = "resources/dictionnary.txt";
	
	/**
	 * ALPHABET est une chaine de caract�res contenant les lettres de l'alphabet
	 * utile pour choisir une lettre au hasard
	 */
	private final static String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
	
	/**
	 * Une r�f�rence vers un unique objet WordGenerator, entre dans l'impl�mentation du pattern Singleton
	 */
	private static WordGenerator wg = null;
	
	/**
	 * ArrayList contenant la liste des mots du fichier
	 */
	private ArrayList<String> dictionnary;
	
	/*****************************************************************************************
	 * M�thodes ******************************************************************************
	 *****************************************************************************************/
	
	public static void main(String[] args){
		WordGenerator wg = WordGenerator.getInstance();
		ArrayList<String> partie = wg.generateWordList(3);
		System.out.println(partie);
		System.out.println(wg.reverseWord(partie.get(1)));
		System.out.println(wg.addMalusToWord(partie.get(1)));
	}
	
	/**
	 * Constructeur priv�
	 * Instancie la array list dictionnary � partir des mots d'un fichier
	 */
	private WordGenerator()
	{
		this.dictionnary = new ArrayList<String>();
		BufferedReader is = null;
		
		// lecture du fichier, on ins�re tous les mots dans la liste
		try {
			is = new BufferedReader(new InputStreamReader(new FileInputStream(new File(WordGenerator.DICTIONNARY_PATH))));
		} catch (FileNotFoundException e) {
			System.err.println("Le fichier "+WordGenerator.DICTIONNARY_PATH+" n'a pas �t� trouv�");
		}
		String str = "";
		try {
			while((str = is.readLine()) != null)	// tant que le fichier n'est pas vide
			{
				this.dictionnary.add(str);
			}
		} catch (IOException e) {
			System.err.println("Erreur entr�e/sortie : "+e.getMessage());
		}
	}
	
	/**
	 * Permet d'acc�der � l'unique instance de WordGenerator
	 * Entre dans l'impl�mentation du pattern Singleton
	 * @return l'objet WordGenerator
	 */
	public static WordGenerator getInstance()
	{
		// si l'objet n'a pas encore �t� instanci�, on le cr�e
		if(WordGenerator.wg == null)
			WordGenerator.wg = new WordGenerator();
		
		return WordGenerator.wg;
	}
	
	/**
	 * G�n�re une liste de mots al�atoires
	 * @param length longueur de la liste souhait�e
	 * @return ArrayList<String> la liste
	 */
	public ArrayList<String> generateWordList(int length)
	{
		ArrayList<String> list = new ArrayList<String>();
		for(int i = 0 ; i < length ; i++){
			String r = this.getRandomWord(list);
			list.add(r);
		}
		return list;
	}
	
	/**
	 * Choisit un mot au hasard dans le dictionnaire
	 * @return String un mot
	 */
	public String getRandomWord()
	{
		return this.getRandomWord(new ArrayList<String>());
	}
	
	/**
	 * Choisi un mot au hasard dans le dictionnaire n'�tant pas dans la liste pass�e en param�tre
	 * @param alreadyInserted liste des mots que l'on ne veut pas retourner
	 * @return String un mot
	 */
	public String getRandomWord(ArrayList<String> alreadyInserted)
	{
		String random = "";
		Random r = new Random();
		int low = 0;
		int high = this.dictionnary.size();
		
		do
		{
			int randomIndex = r.nextInt(high-low) + low;
			random = this.dictionnary.get(randomIndex);
			// on sort du while seulement si le mot n'est pas vide ou si le mot n'existe pas d�j� dans la liste
		} while(random.equals("") || alreadyInserted.contains(random));
		
		return random;
	}
	
	/**
	 * Retourne les lettres d'un mot : la derni�re lettre devient la premi�re , etc
	 * par exemple : exemple devient elpmexe
	 * @param s le mot
	 * @return le mot retourn�
	 */
	public String reverseWord(String s)
	{
		String reversed = "";
		
		// parcours � l'envers de la chaine pass�e en param�tre
		for(int i = s.length() - 1 ; i >= 0 ; i--)
		{
			reversed = reversed + s.charAt(i);
		}
		return reversed;
	}
	
	/**
	 * Remplace une lettre al�atoire du mot par une autre lettre al�atoire
	 * @param s le mot auquel il faut donner un malus
	 * @return le mot "malus�"
	 */
	public String addMalusToWord(String s)
	{
		char[] malused = s.toCharArray();
		Random rand = new Random();
		char randomLetter;
		int randomIndex = rand.nextInt(s.length());		// l'indice de la lettre � remplacer dans le mot
		// une lettre random va �tre chang�e par une autre, choisie al�atoirement dans l'alphabet
		// il ne faut pas que la lettre qui remplace soit la m�me que celle a remplac�
		do
		{
			randomLetter = WordGenerator.ALPHABET.charAt(rand.nextInt(WordGenerator.ALPHABET.length()));
		} while (randomLetter == malused[randomIndex]);
		malused[randomIndex] = randomLetter;

		return new String(malused);
	}
}
