import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class AlgorithmPMod{
	
	private short[] a;
	private short m, q, x;
	private short n, number;
	private long counter;
	private boolean[] p;
	private Map<short[], short[]> pairs;
	
	public AlgorithmPMod (short n) {
		a = new short[n + 1];
		this.n = n;
		this.number = n;
		counter = 0;
		p = new boolean[6];
		p[0] = true;
		pairs = new LinkedHashMap<short[], short[]>();
	}
	
	public long algorithm() {
		while(true) {
			//P1
			if(p[0]) {
				for(m = 2; m <= n; m++) {
					a[m] = 1;
				}
				m = 1;
				a[0] = 0;
				
				p[0] = false;
				p[1] = true;
			}
			
			//P2
			if(p[1]) {
				a[m] = n;
				q = (short) (m - (n == 1 ? 1 : 0));
				
				p[1] = false;
				p[2] = true;
			}
			
			//P3
			if(p[2]) {
				counter++;
				printPartition();
				p[2] = false;
				if(a[q] == 2)
					p[3] = true;
				else
					p[4] = true;
			}
			
			//P4
			if(p[3]) {
				a[q] = 1;
				q--;
				m++;
				p[3] = false;
				p[2] = true;
			}
			
			//P5
			if(p[4]) {
				if(q == 0) {
					return counter;
				} else {
					x = (short) (a[q] - 1);
					a[q] = x;
					n = (short) (m - q + 1);
					m = (short) (q + 1);
					p[4] = false;
					p[5] = true;
				}
			}
			
			//P6
			if(p[5]) {
				if(n <= x) {
					p[5] = false;
					p[1] = true;
				} else {
					a[m] = x;
					m++;
					n = (short) (n - x);
					//p[5] = true;
				}
			}
			
		}
	}
	
	//od svake particije napravi njezinu konjugiranu te taj par pospremi u mapu pairs
	private void printPartition() {
		short[] current = new short[m];
		for(int i = 1; i <= m; i++)
			current[i-1] = a[i];
		short[] c = new short[number];			//polje "tezina" tj broj x se u particiji current nalazi c[x-1] puta (ovaj -1 je zbog indeksiranja)
		int pairInd = 0;
		short[] pair = new short[current[0]];
		int i;
		for(i = 0; i < (m - 1); i++) {
			c[i] = (short) (current[i] - current[i + 1]);
		}
		short x = current[i];
		c[i] = x;
		for(i = m - 1; i >= 0; i--) {
			for(int j = c[i]; j > 0; j--) {
				pair[pairInd++] = (short) (i + 1);
			}
		}
		
		boolean contains = false;
		for(short[] arr : pairs.keySet()) {
			if(Arrays.equals(current, arr)) {
				contains = true;
				break;
			} else if (Arrays.equals(pair, arr)) {
				contains = true;
				break;
			}
		}
		
		//ako je particija sama sebi konjugirani par, umjesto para se posprema new short[1] sto je zapravo 0
		if(!contains) {
			if(Arrays.equals(current, pair)) {
				pairs.put(current, new short[1]);
			} else if(m <= pairInd) {
				pairs.put(current, pair);
			} else {
				pairs.put(pair, current);
			}
		}
	}
	
	public Map<short[], short[]> getPairs (){
		return pairs;
	}
}