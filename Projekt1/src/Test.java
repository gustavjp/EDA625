import java.math.BigInteger;
import java.util.Random;

public class Test {
    public static void main(String[]args){
        Generator g = new Generator();
        BigInteger base = new BigInteger("2");
<<<<<<< HEAD
        base = base.pow(1023);
=======
        base = base.pow(1024);
>>>>>>> efbcdf43bec550e4322d63e2946a116abbcc3f8a
        BigInteger temp;
        BigInteger addition;
        boolean found;
        long time1, time2, tot = 0;
        int n = 0;
        long time3 = System.currentTimeMillis();
        while(n < 10) {
            addition = new BigInteger(1023, new Random());
<<<<<<< HEAD
            if(addition.mod(new BigInteger("2")).equals(BigInteger.ZERO)) {
                addition = addition.add(BigInteger.ONE);
            }
            temp = base.add(addition);
            if(temp.bitLength() > 1024) {
                System.out.println("ERROR: " + temp.bitLength());
            }
            time1 = System.currentTimeMillis();
            found = g.isPrimeA(temp);
            time2 = System.currentTimeMillis();
            if(found) {
                tot += time2 - time1;
                System.out.println((time2 - time1) + " : " + n);
                System.out.println(tot);
                n++;
=======
            temp = base.add(addition);
            time1 = System.currentTimeMillis();
            found = g.isPrime(temp);
            time2 = System.currentTimeMillis();
            if(found) {
                tot += time2 - time1;
                n++;
                System.out.println("Took " + (time2 - time1) + " to find prime " + n);
                System.out.println((System.currentTimeMillis() - time3) + " since start.");
                System.out.println("Prime found: " + temp.toString());
>>>>>>> efbcdf43bec550e4322d63e2946a116abbcc3f8a
            }
        }
        System.out.println("Took " + (System.currentTimeMillis() - time3) + " to execute for " + n +" primes.");
        System.out.println("Took " + tot + " to find " + n + " primes.");
    }
<<<<<<< HEAD
}
=======
}

>>>>>>> efbcdf43bec550e4322d63e2946a116abbcc3f8a
