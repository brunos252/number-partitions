//radi za sve isprobane brojeve
public class AlgorithmP extends Algorithm{
	
	private short[] a;
	private short m, q, x;
	private short n;
	private long counter;
	private boolean[] p;
	
	public AlgorithmP (short n) {
		a = new short[n + 1];
		this.n = n;
		counter = 0;
		p = new boolean[6];
		p[0] = true;
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
				for(int i = 1; i <= m; i++) {
					System.out.print(a[i] + " ");
				}
				System.out.println();
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
}