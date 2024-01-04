/*Projet fait par l'etudiant:
	MESSAOUDENE Abderrahmane
	L2 INFO
	Section B
	Groupe 4
*/

package testtpthl;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test {
    public static String reverseString(String str) {
        StringBuilder reversed = new StringBuilder(str);
        return reversed.reverse().toString();
    }

    public static List<String> generermots(int k) {
        List<String> mots = new ArrayList<>();
        int debut = 1;
        mots.add("");
        if (k >= 1)
            mots.add("c");
        for (int i = 4; i <= k; i += 3) {
            int taille = mots.size();
            for (int j = debut; j < taille; j++) {
                mots.add("a" + mots.get(j) + "aa");
                mots.add("bb" + mots.get(j) + "b");
                debut = taille;
            }
        }
        return mots;
    }

    public static List<String> generermotsmirroir(int k) {
        List<String> mots = new ArrayList<>();
        List<String> motsmirroir = new ArrayList<>();
        mots = generermots(k);
        for (String word : mots) {
            motsmirroir.add(reverseString(word));
        }
        return motsmirroir;
    }

    public static List<String> generermotspuissance(int k, int n) {
        List<String> mots = new ArrayList<>();
        List<String> sousmots = new ArrayList<>();
        List<String> motspuissance = new ArrayList<>();
        mots = generermots(k);
        sousmots = mots.subList(1, mots.size() - 1);

        for (int i = 1; i < n; i++) {
            for (String word : mots) {
                for (String suffix : sousmots) {
                	motspuissance.add(word + suffix);
                }
            }
            mots = motspuissance;
            motspuissance = new ArrayList<>();
        }
        return mots;
    }

    public static void main(String[] args) {
        List<String> mots = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int k, n = 0;
        String input;

        System.out.println("PARTIE 1 : LANGAGE ET GRAMAIRE");
        System.out.println("1- Generer tous les mots du langage Lk:");
        System.out.print("Entrer k = ");
        k = scanner.nextInt();
        mots = generermots(k);
        mots.set(0, "\u03B5");
        System.out.println("Le language genere est: \n" + mots);

        System.out.println("2- Generer tous les mots du langage Mir(Lk):");
        System.out.print("Entrer k = ");
        k = scanner.nextInt();
        mots = generermotsmirroir(k);
        mots.set(0, "\u03B5");
        System.out.println("Le language genere est: \n" + mots);

        System.out.println("3- Generer tous les mots du langage Lk puissance n:");
        System.out.print("3.1- Entrer k = ");
        k = scanner.nextInt();
        System.out.print("3.2- Entrer n = ");
        n = scanner.nextInt();
        mots = generermotspuissance(k, n);
        mots.set(0, "\u03B5");
        System.out.println("Le language genere est: \n" + mots);


        System.out.println("PART 2 : ANALYSEUR SYNTAXIQUE");
        System.out.print("1- Entrer un mot: ");
        scanner.nextLine(); // Consume the newline character
        input = scanner.nextLine();
        mots = generermots(input.length());
        if (mots.contains(input))
            System.out.println("Le mot "+ input +" est syntaxiquement \u001B[32mCORRECTE\u001B[0m , car il appartient au L(G)");
        else
            System.out.println("Le mot "+ input +" est syntaxiquement \u001B[31mINCORRECTE\u001B[0m , car il n'appartient pas au L(G)");

        scanner.close();
        System.out.println("THE END!");
    }
}
