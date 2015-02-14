import java.math.BigInteger;
import java.util.Random;

public class Test {
    public static void main(String[]args){
        Generator g = new Generator();
        BigInteger base = new BigInteger("2");
        base = base.pow(1024);
        BigInteger temp;
        BigInteger addition;
        boolean found;
        long time1, time2, tot = 0;
        int n = 0;
        long time3 = System.currentTimeMillis();
        while(n < 10) {
            addition = new BigInteger(1023, new Random());
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
            }
        }
        System.out.println("Took " + (System.currentTimeMillis() - time3) + " to execute for " + n +" primes.");
        System.out.println("Took " + tot + " to find " + n + " primes.");
    }
}

