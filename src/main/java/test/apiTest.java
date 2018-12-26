package test;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class apiTest {

    String url = "http://localhost:8080/transfer";

    @Test
    public void testConnection(){

        int resCode = 0;
        HttpURLConnection httpURLConnection = null;
        try {
            httpURLConnection = (HttpURLConnection) new URL(url+"/listTransactions").openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            resCode = httpURLConnection.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert (resCode == 200);
    }
}
