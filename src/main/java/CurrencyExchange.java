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
        System.out.println();
    }
}

class Exchange {

    private static final Scanner sc = new Scanner(System.in);

    public static String getOriginalCurrency() {
        System.out.print("What currency would you like to exchange? ");
        return sc.nextLine();
    }

    public static float moneyToBeExchanged() {
        System.out.print("please enter the amount of money you want to exchange: ");
        return sc.nextFloat();
    }

    public static String getTargetCurrency() {
        System.out.print("What currency would you like to change your money into? ");
        sc.nextLine();
        return sc.nextLine();
    }

    public static float getRate(String originalCurrency, String targetCurrency) {
        try {
            String apiKey = "76abdb90da458f91cb518b75";
            String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + originalCurrency;
            
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject json = new JSONObject(response.body());

            float rate = json.getJSONObject("conversion_rates").getFloat(targetCurrency);
            return rate;

        } catch (Exception e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public static String getDateTime() {
    LocalDateTime DateTime = LocalDateTime.now();
    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    String timeStamp = DateTime.format(format);
    return timeStamp;
    }

    public static float calculateExchange() {
        String originalCurrency = getOriginalCurrency();
        float money = moneyToBeExchanged();
        String targetCurrency = getTargetCurrency();
        float rate = getRate(originalCurrency, targetCurrency);
        String timeDate = getDateTime();
        System.out.println(timeDate);
        return money * rate;
    }
}