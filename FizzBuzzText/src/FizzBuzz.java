import java.lang.Integer;

public class FizzBuzz {
    public String fizzBuzz(int number) {
        String rStr = "";
        if(number % 3 == 0) {
            rStr += "fizz";
        }
        if(number % 5 == 0) {
            rStr += "buzz";
        }
        rStr = (rStr == "") ? Integer.toString(number) : rStr;
        return rStr;
    }

}
