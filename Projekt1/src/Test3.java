import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Test3 {

    public static Generator g = new Generator();

    public static void main(String[] args) {
        //Assignment 2:
        primeFinder();
        
        //Assignment 3
        ArrayList<BigInteger> list = twoPrimes(512);
        for (BigInteger b : list) {
            System.out.println(b.toString());
        }
        
        //Assignment 4
        BigInteger inv = g.inv_mod(new BigInteger("420"), new BigInteger("17"));
        System.out.println(inv.toString());
        
        //Assignment 5
        RSA();
    }

    /**
     * Generates a random message, encrypts it and decrypts it.
     */
    private static void RSA() {
        ArrayList<BigInteger> bigList = twoPrimes(512);
        BigInteger e = new BigInteger("2").pow(16).add(BigInteger.ONE);
        BigInteger p = bigList.get(0);
        BigInteger q = bigList.get(1);
        BigInteger N = p.multiply(q);
        BigInteger d = g.inv_mod(e, (p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE))).divide(new BigInteger("2")));
        BigInteger s;
        do{
            s = new BigInteger(N.bitLength(), new Random());
        } while(s.compareTo(N) >= 0 || s.compareTo(BigInteger.ZERO) < 1);
        System.out.println("Message: " + s.toString());
        BigInteger c = g.exp_mod(s, e, N);
        System.out.println("Encrypted message: " + c.toString());
        BigInteger z = g.exp_mod(c, d, N);
        System.out.println("Decrypted message: " + z.toString());
        if(s.equals(z)) {
            System.out.println("Message successfully encrypted and decrypted.");
        }
    }

    /**
     * Generates two random primes.
     *
     * @param bitLength
     *                  the bitlength of the two primes
     * @return
     *          an arraylist with two primes
     */
    private static ArrayList<BigInteger> twoPrimes(int bitLength) {
        BigInteger base = new BigInteger("2").pow(bitLength - 1);
        BigInteger addition;
        BigInteger temp;
        boolean found;
        ArrayList<BigInteger> bigList = new ArrayList<BigInteger>();
        while(bigList.size() < 2) {
            addition = new BigInteger(bitLength - 1, new Random());
            if(!addition.testBit(0)) {
                addition = addition.add(BigInteger.ONE);
            }
            temp = base.add(addition);
            if(sieve(temp) && !bigList.contains(temp)) {
                found = g.isPrime(temp);
                if(found) {
                    bigList.add(temp);
                }
            }
        }
        return bigList;
    }

    /**
     * Does a check for randomly generated BigIntegers until 100
     * of bitlenghts 512, 1024 and 2048 are found.
     *
     * Results are stored to a .txt-file.
     */
    private static void primeFinder() {
        try {
            String output;
            BigInteger base;
            BigInteger temp;
            BigInteger addition;
            boolean found;
            long time1 = 0, time2 = 0, time3, tot;
            ArrayList<BigInteger> bigList;
            for(int i = 9; i < 12; i++) {
                bigList = new ArrayList<BigInteger>();
                tot = 0;
                base = new BigInteger("2").pow((int) Math.pow(2, i) - 1);
                time3 = System.currentTimeMillis();
                while(bigList.size() <= 100) {
                    found = false;
                    addition = new BigInteger((int) Math.pow(2, i) - 1, new Random());
                    if(!addition.testBit(0)) {
                        addition = addition.add(BigInteger.ONE);
                    }
                    temp = base.add(addition);
                    if(sieve(temp) && !bigList.contains(temp)) {
                        time1 = System.currentTimeMillis();
                        found = g.isPrime(temp);
                        time2 = System.currentTimeMillis();
                    }
                    if(found) {
                        tot += time2 - time1;
                        bigList.add(temp);
                    }
                }
                System.out.println(tot);
                output = "For x^2, " + bigList.size() + " primes and " + (int) Math.pow(2, i) + " bits\n";
                output += "Total execution time: " + (time2 - time3) + " ms\n";
                output += "Total checking time: " + Objects.toString(tot, null) + " ms\n";
                output += "-----------------DONE-----------------\n";
                File file = new File("data/" + (int) Math.pow(2, i) +".txt");
                FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
                BufferedWriter bw = new BufferedWriter(fw);
                // if file doesn't exists, then create it
                if (!file.exists()) {
                    file.createNewFile();
                }
                bw.append(output);
                bw.close();
                System.out.println("Data written to file.");
            }
            System.out.println("Done");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A very simple sieve to check a potential prime.
     *
     * @param b
     *          the potential prime
     * @return
     *          true if not divisible by anything between 0 and 65000
     *          false if composite
     */
    private static boolean sieve(BigInteger b) {
        if(!b.testBit(0)) {
            return false;
        }
        for(int i = 3; i < 65000; i += 2) {
            if(b.mod(new BigInteger(Integer.toString(i))).equals(BigInteger.ZERO)) {
                return false;
            }
        }
        return true;
    }
}