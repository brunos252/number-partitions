import java.util.Scanner;

public class Tester {
	public static void main(String[] args) {
		long counter;
		Algorithm alg;
		Scanner sc = new Scanner(System.in);
		System.out.println("unesite broj: ");
		short n = sc.nextShort();
		System.out.println("izaberite algoritam: ");
		short i = sc.nextShort();
		
		switch(i) {
		case 1:
			//algoritam P iz skenirane knjige, za ispis svih particija broja
			alg = new AlgorithmP(n);
			counter = alg.algorithm();
			System.out.println("count: " + counter);
			break;
		case 2:
			//algoritam H, za ispis particija odredene duzine
			System.out.println("duzina particije: ");
			alg = new AlgorithmH(n, sc.nextShort());
			counter = alg.algorithm();
			System.out.println("count: " + counter);
			break;
		case 3:
			//korištenje algoritma H za ispis svih particija tako da se unosi duzina particije od 1(2) do n
			counter = 1;
			for(short j = 2; j <= n; j++) {
				alg = new AlgorithmH(n, j);
				counter += alg.algorithm();
			}
			System.out.println("count: " + counter);
		}
		sc.close();
	}
}
