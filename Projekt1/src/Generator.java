import java.util.Random;
import java.math.BigInteger;

public class Generator {
    final BigInteger two = new BigInteger("2");
    BigInteger a;
    Random rand = new Random();

    public BigInteger exp_mod(BigInteger a, BigInteger x, BigInteger N) {
        if (a.equals(BigInteger.ZERO)) {
            return BigInteger.ZERO;
        }
        if (x.equals(BigInteger.ZERO)) {
            return BigInteger.ONE;
        }
        if (x.testBit(0)) {
            return (a.multiply(exp_mod(a, x.subtract(BigInteger.ONE), N))
                    .mod(N));
        } else {
            return exp_mod(a, x.divide(two), N).pow(2).mod(N);
        }
    }

    /**
     * A Rabin-Miller test
     *
     * @param n 
     *          the potential prime to be checked
     * @return
     *          true if n is prime, false if not
     */
    public boolean isPrime(BigInteger n) {
        if (!n.testBit(0)) {
            return false;
        }
        BigInteger r = BigInteger.ZERO;
        BigInteger s = n.subtract(BigInteger.ONE);
        while (!s.testBit(0)) {
            s = s.divide(new BigInteger("2"));
            r = r.add(BigInteger.ONE);
        }
        for (int i = 0; i < 20; i++) {
            do {
                a = new BigInteger(n.bitLength(), rand);
            } while (a.compareTo(two) < 0 || a.compareTo(n.subtract(two)) > 0);
            BigInteger x = exp_mod(a, s, n);
            if (x.equals(BigInteger.ONE) || x.equals(n.subtract(BigInteger.ONE))) {
                continue;
            }
            BigInteger j = new BigInteger("1");
            for (; j.compareTo(r) == -1; j = j.add(BigInteger.ONE)) {
                x = x.pow(2).mod(n);
                //x = exp_mod(a, two.pow(j.intValue()).multiply(s), n);
                if (x.equals(BigInteger.ONE)) {
                    return false;
                } else if (x.equals(n.subtract(BigInteger.ONE))) {
                    break;
                }
            }
            if (r.equals(j)) {
                return false;
            }
        }
        return true;
    }

    public BigInteger inv_mod(BigInteger a, BigInteger m) {
        BigInteger d1 = m, d2 = a, v1 = BigInteger.ZERO, v2 = BigInteger.ONE;
        BigInteger q, t2, t3;
        while(!d2.equals(BigInteger.ZERO)) {
            q = d1.divide(d2);
            t2 = v1.subtract(q.multiply(v2));
            t3 = d1.subtract(q.multiply(d2));
            v1 = v2;
            d1 = d2;
            v2 = t2; 
            d2 = t3;
        }
        if(d1.equals(BigInteger.ONE)) {
            while(v1.compareTo(BigInteger.ZERO) < 0) {
                v1 = v1.add(m);
            }
            return v1;
        }
        return BigInteger.ZERO;
    }
}
