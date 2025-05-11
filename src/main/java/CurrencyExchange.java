import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;

public class CurrencyExchange {
    public static void main(String[] args){
        System.out.println(Exchange.calculateExchange());
        Exchange ex1 = new Exchange();
        System.out.println(ex1.timeStamp);
    }
}

class Exchange {

    private static final Scanner sc = new Scanner(System.in);

    LocalDateTime DateTime = LocalDateTime.now();
    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    String timeStamp = DateTime.format(format);

    public static float moneyToBeExchanged() {
        System.out.print("please enter the amount of money you want to exchange: ");
        return sc.nextFloat();
    }
        

    public static float getRate() {
        try {

            
            String apiKey = "76abdb90da458f91cb518b75";
            String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/USD";
            
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject json = new JSONObject(response.body());

            System.out.print("What currency would you like to change to? ");
            sc.nextLine();
            String currencyType = sc.nextLine();
            float rate = json.getJSONObject("conversion_rates").getFloat(currencyType);
            return rate;

        } catch (Exception e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public static float calculateExchange() {
        float money = moneyToBeExchanged();
        float rate = getRate();
        return money * rate;
    }
}
