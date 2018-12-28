import java.util.Map;
import java.util.Scanner;

public class Tester {
	final static String[] algorithms = {"1 -> P (ispisuje leksikografskim poretkom)",
										"2 -> H (ispis particija broja odredene duzine)",
										"3 -> ispis svih particija pomocu algoritma H (po duzinama)",
										"4 -> P, ali ispis u konjugiranim parovima, leksikografskim poretkom"};
	public static void main(String[] args) {
		long counter;
		Algorithm alg;
		Scanner sc = new Scanner(System.in);
		System.out.println("unesite broj: ");
		short n = sc.nextShort();
		System.out.println("izaberite algoritam: ");
		for(int j = 0; j < algorithms.length; j++) {
			System.out.println("    " + algorithms[j]);
		}
		System.out.print(">");
		short i = sc.nextShort();
		
		switch(i) {
		case 1:
			//algoritam P iz skenirane knjige, za ispis svih particija broja
			//takoder, ispisuje particije u leksikografskom poretku(isprobaj pa ces vidjeti sto to znaci)
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
			System.out.println(n);			//algoritam H ne radi za trivijalnu duljinu particije 1 pa treba posebno ispisat
			for(short j = 2; j <= n; j++) {
				alg = new AlgorithmH(n, j);
				counter += alg.algorithm();
			}
			System.out.println("count: " + counter);
			break;
		case 4:
			//generira sve particije ali u konjugiranim parovima spremljenim u mapi pairs
			//ako je particija sama sebi par onda je kao njezin par pospremljeno 0
			AlgorithmPMod al = new AlgorithmPMod(n);
			counter = al.algorithm();
			System.out.println("count: " + counter);
			Map<short[], short[]> pairs = al.getPairs();
			
			//ispis parova, posto je LinkedHashMap odrzava se poredak kojim algoritam P dolazi do njih, a to je leksikografski poredak
			for(Map.Entry<short[], short[]> entry : pairs.entrySet()) {
				short[] element = entry.getKey();
				for(int j = 0; j < element.length; j++) {
					System.out.print(element[j] + " ");
				}
				System.out.print(", pair: ");
				element = entry.getValue();
				for(int j = 0; j < element.length; j++) {
					System.out.print(element[j] + " ");
				}
				System.out.println();
			}
			break;
		}
		sc.close();
	}
}
