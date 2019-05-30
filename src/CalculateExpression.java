import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class CalculateExpression {
    static final String ANSI_RESET = "\u001B[0m";
    static final String ANSI_GREEN = "\u001B[32m";
    public static void launch () {
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader bis = new BufferedReader(is);
        System.out.println("SuperCalculator: evaluates expression A(a,b,c,d)=(a+b)/(c+d)\n" +
                "input values: integers or floating point values with dot (.) separator");
        String line;
        double a, b, c, d;
        try{
            System.out.println("enter a value of " + ANSI_GREEN + "a" + ANSI_RESET + " ( or enter 'q'<=>quit):");
            line = bis.readLine();
            while(!line.equals("q")){
                a = Double.parseDouble(line);
                System.out.println("enter " + ANSI_GREEN + "b" + ANSI_RESET);
                line = bis.readLine();
                b = Double.parseDouble(line);
                System.out.println("enter " + ANSI_GREEN + "c" + ANSI_RESET);
                line = bis.readLine();
                c = Double.parseDouble(line);
                System.out.println("enter " + ANSI_GREEN + "d" + ANSI_RESET);
                line = bis.readLine();
                d = Double.parseDouble(line);
                if(c+d != 0){
                    System.out.println("(a+b)/(c+d)=" + (a+b)/(c+d));
                } else {
                    System.err.println("Error in calculateExpression: division by zero");
                }
                System.out.println("enter " + ANSI_GREEN + "a" + ANSI_RESET + " ( or enter 'q'<=>quit):");
                line = bis.readLine();
            }
        } catch (IOException e){
            System.err.println("Error: " + e);
        } catch (NumberFormatException ne){
            System.err.println("Error - wrong input format: " + ne);
        }

    }
}
