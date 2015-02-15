import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Objects;
import java.util.Random;

/**
 * Created by gus on 2015-02-15.
 */
public class Test3 {
    public static void main(String[] args) {
        firstMethod();
        secondMethod();
    }

    private static void firstMethod() {
        try {
            String output;
            File file = new File("/home/gus/code/EDA625/data/collectionFirst.txt");
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            Generator g = new Generator();
            BigInteger base;
            BigInteger two = new BigInteger("2");
            BigInteger temp;
            BigInteger addition;
            boolean found;
            long time1, time2 = 0, time3, tot;
            int n;

            // if file doesn't exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            BufferedWriter bw = new BufferedWriter(fw);
            for(int i = 9; i < 12; i++) {
                //for(int i = 2; i < 5; i++) {
                n = 0;
                tot = 0;
                base = two.pow((int) Math.pow(2.0, (double) i));
                time3 = System.currentTimeMillis();
                while(n < 10) {
                    addition = new BigInteger((int) Math.pow(2.0, (double) i), new Random());
                    if(addition.mod(new BigInteger("2")).equals(BigInteger.ZERO)) {
                        addition = addition.add(BigInteger.ONE);
                    }
                    temp = base.add(addition);
                    time1 = System.currentTimeMillis();
                    found = g.isPrimeA(temp);
                    time2 = System.currentTimeMillis();
                    if(found) {
                        tot += time2 - time1;
                        n++;
                    }
                }
                System.out.println(tot);
                output = "For x^(j)s and " + (int) Math.pow(2.0, (double) i) + " bits\n";
                output += "Total execution time: " + (time2 - time3) + " ms\n";
                output += "Total checking time: " + Objects.toString(tot, null) + " ms\n";
                output += "-----------------DONE-----------------\n";
                bw.write(output);
                System.out.println("Data written to file.");
            }
            bw.close();
            System.out.println("Done");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void secondMethod() {
        try {
            String output;
            File file = new File("/home/gus/code/EDA625/data/collectionSecond.txt");
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            Generator g = new Generator();
            BigInteger base;
            BigInteger two = new BigInteger("2");
            BigInteger temp;
            BigInteger addition;
            boolean found;
            long time1, time2 = 0, time3, tot;
            int n;

            // if file doesn't exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            BufferedWriter bw = new BufferedWriter(fw);
            for(int i = 9; i < 12; i++) {
                //for(int i = 2; i < 5; i++) {
                n = 0;
                tot = 0;
                base = two.pow((int) Math.pow(2.0, (double) i));
                time3 = System.currentTimeMillis();
                while(n < 10) {
                    addition = new BigInteger((int) Math.pow(2.0, (double) i), new Random());
                    if(addition.mod(new BigInteger("2")).equals(BigInteger.ZERO)) {
                        addition = addition.add(BigInteger.ONE);
                    }
                    temp = base.add(addition);
                    time1 = System.currentTimeMillis();
                    found = g.isPrimeB(temp);
                    time2 = System.currentTimeMillis();
                    if(found) {
                        tot += time2 - time1;
                        n++;
                    }
                }
                System.out.println(tot);
                output = "For x^(2)s and " + (int) Math.pow(2.0, (double) i) + " bits\n";
                output += "Total execution time: " + (time2 - time3) + " ms\n";
                output += "Total checking time: " + Objects.toString(tot, null) + " ms\n";
                output += "-----------------DONE-----------------\n";
                bw.write(output);
                System.out.println("Data written to file.");
            }
            bw.close();
            System.out.println("Done");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
