package ATMInterface;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TransactionHistory {
    private static final String FILE_NAME = "D:\\GitHub\\REpos\\OIBSIP\\ATMInterface\\Data.txt";
    public String getHistory(){
        String line;
        String tmp = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            line = reader.readLine();
            line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                tmp += line + "\n";
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return tmp;
    }
    public int getBalance() {
        String line = "0";
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            line = reader.readLine();
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(line);
    }
}
