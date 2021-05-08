package base.math;

public class Eratosthenes {
	public static void main(String[] args) {
		int numb = 120;
		sieve(numb);
	}

	private static void sieve(int n) {
		boolean prime[] = new boolean[n + 1];
		prime[1] = true;
		for (int i = 2; i <= n; i++) {
			if (!prime[i]) {
				for (int j = 2; j <= n; j++) {
					if (i*j > n) {
						break;
					}
					prime[i * j] = true;
				}
			}
		}
		for (int i = 2; i <= n; i++) {
			if (!prime[i]) {
				System.out.print(i + " ");
			}
		}
	}

}
