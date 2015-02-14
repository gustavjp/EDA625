import java.util.Random;
import java.math.BigInteger;

/**
 *
 * @author san11sba
 *
 */
public class Generator {
    final BigInteger two = new BigInteger("2");
    BigInteger a;
    int o;
    Random rand = new Random();

    /**
     *
     * @param a
     *        	bas
     * @param x
     *        	exponent
     * @param N
     *        	diva
     * @return
     */
    private BigInteger exp_mod(BigInteger a, BigInteger x, BigInteger N) {
        if (a.compareTo(BigInteger.ZERO) == 0) {
            return BigInteger.ZERO;
        }
        if (x.compareTo(BigInteger.ZERO) == 0) {
            return BigInteger.ONE;
        }
        if (x.mod(two).compareTo(BigInteger.ZERO) != 0) {
            return (a.multiply(exp_mod(a, x.subtract(BigInteger.ONE), N))
                    .mod(N));
        } else {
            return exp_mod(a, x.divide(two), N).pow(2).mod(N);
        }
    }

    public int getRandomOdd() {
        do {
            o = rand.nextInt();
        } while (o % 2 == 0 || o <= 0);

        return o;
    }

    /**
     *
     * @param n
     * @return
     */
    public boolean isPrime(BigInteger n) {
        if (n.mod(two).compareTo(BigInteger.ZERO) == 0) {
            System.out.println("Divisible by 2.");
            return false;
        }
        BigInteger r = BigInteger.ZERO;
        BigInteger s = n.subtract(BigInteger.ONE);
        System.out.println("s set to " + s);
        System.out.println("s mod 2 " + s.mod(two));
        while (s.mod(two).compareTo(BigInteger.ZERO) == 0) {
            s = s.divide(new BigInteger("2"));
            r = r.add(BigInteger.ONE);
        }
        System.out.println("r set to " + r);
        System.out.println("s set to " + s);
        for (int i = 0; i < 20; i++) {
            System.out.println("cycle " + i);
            do {
                a = new BigInteger(n.bitLength(), rand);
            } while (a.compareTo(two) < 0 || a.compareTo(n.subtract(two)) == 1);
            System.out.println("a set to " + a);

            BigInteger x = exp_mod(a, s, n);
            System.out.println("exp done");
            if (x.compareTo(BigInteger.ONE) == 0) {
                return true;
            }
            if (x.compareTo(n.subtract(BigInteger.ONE)) == 0) {
                return true;
            }
            for (BigInteger j = new BigInteger("1"); j.compareTo(r) == -1; j = j
                    .add(BigInteger.ONE)) {
                System.out.println("j cycle " + j);
                x = exp_mod(a, two.pow(j.intValue()).multiply(s), n);
                //x = x.pow(2).mod(n);
                if (x.equals(BigInteger.ONE)) {
                    System.out.println("x is one");
                    return false;
                } else if (x.equals(n.subtract(BigInteger.ONE))) {
                    return true;
                }
            }
        }
        System.out.println("nutting");
        return false;
    }
}



