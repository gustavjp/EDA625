import java.util.Random;
import java.math.BigInteger;

public class Generator {
    final BigInteger two = new BigInteger("2");
    BigInteger a;
    Random rand = new Random();

    private BigInteger exp_mod(BigInteger a, BigInteger x, BigInteger N) {
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

    public boolean isPrimeA(BigInteger n) {
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
            if (x.compareTo(BigInteger.ONE) == 0) {
                return true;
            }
            if (x.equals(n.subtract(BigInteger.ONE))) {
                return true;
            }
            for (BigInteger j = new BigInteger("1"); j.compareTo(r) == -1; j = j
                    .add(BigInteger.ONE)) {
                x = exp_mod(a, two.pow(j.intValue()).multiply(s), n);
                if (x.equals(n.subtract(BigInteger.ONE))) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isPrimeB(BigInteger n) {
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
            if (x.compareTo(BigInteger.ONE) == 0) {
                return true;
            }
            if (x.equals(n.subtract(BigInteger.ONE))) {
                return true;
            }
            for (BigInteger j = new BigInteger("1"); j.compareTo(r) == -1; j = j
                    .add(BigInteger.ONE)) {
                x = x.pow(2).mod(n);
                if (x.equals(n.subtract(BigInteger.ONE))) {
                    return true;
                }
            }
        }
        return false;
    }

    public BigInteger inv_mod(BigInteger a, BigInteger m) {
        BigInteger d1 = m, d2 = a, v1 = BigInteger.ZERO, v2 = BigInteger.ONE;
        BigInteger q, t2, t3;
        while(!d2.equals(BigInteger.ZERO)) {
            q = d1.divide(d2);
            System.out.println(q.toString() + " = " + d1.toString() + " / " + d2.toString());
            t2 = v1.subtract(q.multiply(v2));
            System.out.println(t2.toString() + " = " + v1.toString() + " - " + q.toString() + " * " + v2.toString());
            t3 = d1.subtract(q.multiply(d2));
            System.out.println(t3.toString() + " = " + d1.toString() + " - " + q.toString() + " * " + d2.toString());
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
        System.out.println("NoPe");
        return BigInteger.ZERO;
    }
}
