//==========================================================================
//Sample tlsclient using sslsockets
import java.io.*;
import java.net.*;
import java.security.*;

import javax.net.ssl.*;
public class tlsclient {
    private static final String HOST = "localhost";
    private static final int PORT = 8043;
    public static void main(String[] args) throws Exception {
        // TrustStore
        char[] passphrase_ts = "changeit".toCharArray();
        KeyStore ts = KeyStore.getInstance("JKS");
        ts.load(new FileInputStream("truststore.jks"), passphrase_ts);
        TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
        tmf.init(ts);
        // Keystore  ????

        SSLContext context = SSLContext.getInstance("TLS");
        TrustManager[] trustManagers = tmf.getTrustManagers();
        //KeyManager[] keyManagers   = kmf.getKeyManagers();
        KeyManager[] keyManagers = null;

        context.init(keyManagers, trustManagers, new SecureRandom());
        SSLSocketFactory sf = context.getSocketFactory();
        Socket s = sf.createSocket(HOST,PORT);

        OutputStream toserver = s.getOutputStream();


        toserver.write("\nConnection established.\n\n".getBytes());
        System.out.print("\nConnection established.\n\n");

        int inCharacter=0;
        inCharacter = System.in.read();
        while (inCharacter != '~')
        {
            toserver.write(inCharacter);
            toserver.flush();
            inCharacter = System.in.read();
        }
        toserver.close();
        s.close();
    }
}