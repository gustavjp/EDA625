import java.io.*;
import java.net.*;
import java.security.*;
import javax.net.ssl.*;
public class tlsserver {
    // likely this port number is ok to use
    private static final int PORT = 8043;
    public static void main (String[] args)  throws Exception {
        //set necessary truststore properties - using JKS
        //System.setProperty("javax.net.ssl.trustStore","truststore.jks");
        //System.setProperty("javax.net.ssl.trustStorePassword","changeit");
        // set up key manager to do server authentication
        SSLContext context;
        KeyManagerFactory kmf;
        KeyStore ks;
        // First we need to load a keystore
        char[] passphrase = "svettig".toCharArray();
        ks = KeyStore.getInstance("JKS");
        ks.load(new FileInputStream("serverKeyStore.jks"), passphrase);
        // Initialize a KeyManagerFactory with the KeyStore
        kmf = KeyManagerFactory.getInstance("SunX509");
        kmf.init(ks, passphrase);
        // Create an SSLContext to run TLS and initialize it with KeyManagers from the KeyManagerFactory
        context = SSLContext.getInstance("TLS");
        KeyManager[] keyManagers = kmf.getKeyManagers();
        context.init(keyManagers, null, null);
        // First we need a SocketFactory that will create SSL server sockets.
        SSLServerSocketFactory ssf = context.getServerSocketFactory();
        // Create socket and Wait for a connection
        ServerSocket ss = ssf.createServerSocket(PORT);
        // Socket s = ss.accept();

        // alterternative: needed to set SSL/TLS behaviour
        SSLSocket  s  =  (SSLSocket)ss.accept();


// Get the input stream. En/Decryption happens transparently.
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

// Read through the input from the client and display it to the screen.
        String line = null;
        while (((line = in.readLine())!= null)) {
            System.out.println(line);
        }
        in.close();
        s.close();
    }
}