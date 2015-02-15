import java.math.BigInteger;

public class Test2 {
    public static void main(String[] args) {
        Generator g = new Generator();
        BigInteger b = new BigInteger("2");
        b = b.pow(4);
<<<<<<< HEAD
        b = b.add(new BigInteger("7"));
        System.out.println("b is " + b.toString());
        System.out.println(b + " is " + g.isPrimeA(b));
    }
}
=======
        b = b.add(BigInteger.ONE);
        System.out.println("b is " + b.toString());
        System.out.println(b + " is " + g.isPrime(b));
    }
}
>>>>>>> efbcdf43bec550e4322d63e2946a116abbcc3f8a
