package ATMInterface;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Transfer {
    private static final String FILE_NAME = "D:\\GitHub\\REpos\\OIBSIP\\ATMInterface\\Data.txt";
    public boolean transferTo(String toPerson,int amount,String date,String time){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
            int balance = Integer.parseInt(reader.readLine()) - amount;
            if (balance<0) {
                reader.close();
                return false;
            }
            String newLine = String.format("tran    "+date+"    "+time+"    %-6s"+"     -%-6s"+"      %-6s",toPerson,amount,balance);
            String newData = balance +"\n\n"+"Type     Data       Time        To        Amount        Balance \n"+"----------------------------------------------------------------\n"+newLine;
            StringBuilder existingData = new StringBuilder();
            String line;

            line = reader.readLine();
            line = reader.readLine();
            line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                existingData.append(line).append("\n");
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME));
            
            existingData.insert(0, newData + "\n");

            writer.write(existingData.toString());

            reader.close();
            writer.close();

            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
