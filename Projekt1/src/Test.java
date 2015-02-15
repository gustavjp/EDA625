import java.math.BigInteger;
import java.util.Random;

public class Test {
    public static void main(String[]args){
        Generator g = new Generator();
        BigInteger base = new BigInteger("2");
        base = base.pow(1023);
        BigInteger temp;
        BigInteger addition;
        boolean found;
        long time1, time2, tot = 0;
        int n = 0;
        long time3 = System.currentTimeMillis();
        while(n < 10) {
            addition = new BigInteger(1023, new Random());
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
            }
        }
        System.out.println("Took " + (System.currentTimeMillis() - time3) + " to execute for " + n +" primes.");
        System.out.println("Took " + tot + " to find " + n + " primes.");
    }
}
