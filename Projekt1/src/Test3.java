import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Test3 {
    public static void main(String[] args) {
        firstMethod();
        secondMethod();
    }

    private static void firstMethod() {
        try {
            String output;
            Generator g = new Generator();
            BigInteger base;
            BigInteger temp;
            BigInteger addition;
            boolean found;
            long time1 = 0, time2 = 0, time3, tot;
            ArrayList<BigInteger> bigList;
            for(int i = 9; i < 12; i++) {
                bigList = new ArrayList<BigInteger>();
                tot = 0;
                base = new BigInteger("2").pow((int) Math.pow(2, i));
                time3 = System.currentTimeMillis();
                while(bigList.size() <= 10) {
                    found = false;
                    addition = new BigInteger((int) Math.pow(2, i), new Random());
                    if(!addition.testBit(0)) {
                        addition = addition.add(BigInteger.ONE);
                    }
                    temp = base.add(addition);
                    if(sieve(temp) && !bigList.contains(temp)) {
                        time1 = System.currentTimeMillis();
                        found = g.isPrimeA(temp);
                        time2 = System.currentTimeMillis();
                    }
                    if(found) {
                        tot += time2 - time1;
                        bigList.add(temp);
                    }
                }
                System.out.println(tot);
                output = "For x^(j)s, " + bigList.size() + " primes and " + (int) Math.pow(2, i) + " bits\n";
                output += "Total execution time: " + (time2 - time3) + " ms\n";
                output += "Total checking time: " + Objects.toString(tot, null) + " ms\n";
                output += "-----------------DONE-----------------\n";
                File file = new File("data/meth1" + (int) Math.pow(2, i) +".txt");
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

    private static void secondMethod() {
        try {
            String output;
            Generator g = new Generator();
            BigInteger base;
            BigInteger temp;
            BigInteger addition;
            boolean found;
            long time1 = 0, time2 = 0, time3, tot;
            ArrayList<BigInteger> bigList;
            for(int i = 9; i < 12; i++) {
                bigList = new ArrayList<BigInteger>();
                tot = 0;
                base = new BigInteger("2").pow((int) Math.pow(2, i));
                time3 = System.currentTimeMillis();
                while(bigList.size() <= 10) {
                    found = false;
                    addition = new BigInteger((int) Math.pow(2, i), new Random());
                    if(!addition.testBit(0)) {
                        addition = addition.add(BigInteger.ONE);
                    }
                    temp = base.add(addition);
                    if(sieve(temp) && !bigList.contains(temp)) {
                        time1 = System.currentTimeMillis();
                        found = g.isPrimeB(temp);
                        time2 = System.currentTimeMillis();
                    }
                    if(found) {
                        tot += time2 - time1;
                        bigList.add(temp);
                    }
                }
                System.out.println(tot);
                output = "For x^(2)s, " + bigList.size() + " primes and " + (int) Math.pow(2, i) + " bits\n";
                output += "Total execution time: " + (time2 - time3) + " ms\n";
                output += "Total checking time: " + Objects.toString(tot, null) + " ms\n";
                output += "-----------------DONE-----------------\n";
                File file = new File("data/meth2" + (int) Math.pow(2, i) +".txt");
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

    public static boolean sieve(BigInteger b) {
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