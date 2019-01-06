package test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import domain.AccountDto;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class apiTest {

    String url = "http://localhost:8080/transfer";
    static Long newCredit = 5000L;

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
    @Test
    public void testTransfer(){

        HttpURLConnection httpURLConnection = null;
        try {
            httpURLConnection = (HttpURLConnection) new URL(url+"/makeTransfer/12/1/2").openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.connect();
            httpURLConnection.getResponseCode();
            httpURLConnection.disconnect();
            httpURLConnection = (HttpURLConnection) new URL(url+"/listAccounts").openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            InputStream inputStream = httpURLConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream);
            BufferedReader b = new BufferedReader(reader);
            String line = b.readLine();
            List<AccountDto> accounts = null;
            accounts = new Gson().fromJson(line, new TypeToken<List<AccountDto>>(){}.getType());
            newCredit = newCredit - 12;
            System.out.println(newCredit+ "  " + accounts.get(0).getCredit());
            assert (accounts.get(0).getCredit().compareTo(newCredit)==0);
            httpURLConnection = (HttpURLConnection) new URL(url+"/makeTransfer/12/2/1").openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.connect();
            httpURLConnection.getResponseCode();
            httpURLConnection.disconnect();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
