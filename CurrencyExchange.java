import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
        System.out.print("please enter the current exchange rate: ");
        return sc.nextFloat();
    }

    public static float calculateExchange() {
        float money = moneyToBeExchanged();
        float rate = getRate();
        return money * rate;
    }
}