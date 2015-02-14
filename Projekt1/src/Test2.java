import java.math.BigInteger;

public class Test2 {
    public static void main(String[] args) {
        Generator g = new Generator();
        BigInteger b = new BigInteger("2");
        b = b.pow(4);
        b = b.add(BigInteger.ONE);
        System.out.println("b is " + b.toString());
        System.out.println(b + " is " + g.isPrime(b));
    }
}
