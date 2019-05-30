import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static void exercise1(){
        ex1Labyrinth.launch();
    }

    private static void exercise2(){
        CalculateExpression.launch();
    }

    public static void main(String[] args) {
        String hint = "This is Valerii Zuev's magic toolbox!\n" +
                "Available commands:\n" +
                "1 - launch Labyrinth game\n" +
                "2 - launch SuperCalculator to evaluate expression (a+b)/(c+d)\n" +
                "a - launch all one by one\n" +
                "h - display this help message" +
                "q - quit toolbox";
        System.out.println(hint);
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader bis = new BufferedReader(is);
        String input;
        int sessions = 0;
        final int maxSessions = 100;
        try{
            process:while(sessions < maxSessions){
                sessions++;
                input = bis.readLine();
                switch(input){
                    case "1":
                        exercise1();
                        break;
                    case "2":
                        exercise2();
                        break;
                    case "a":
                        exercise1();
                        exercise2();
                        break;
                    case "h":
                        System.out.println(hint);
                        break;
                    case "q":
                        break process;
                    default:
                        System.out.println("unknown command, type 'h' to list all commands");
                }
                System.out.println("Enter the next directive");
            }
        } catch (IOException e) {
            System.err.println("Error trying to process user's input: " + e);
        }
        String goodbye = "";
        if(sessions >= maxSessions){
            goodbye += "You must be tired.\n" +
                    "National Gaming association worries about your health.\n" +
                    "Please take a break for a while.\n";
        }
        goodbye += "Goodbye! \n";
        System.out.println(goodbye);
    }
}
