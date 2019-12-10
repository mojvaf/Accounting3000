import java.io.*;

public class Earnings {
    String name;
    double amount;
    String type= "Earnings";

    public Earnings(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }

    public void writeToFile() throws IOException {
        File file= new File("earnings.txt");// opening the file
        FileWriter fw= new FileWriter(file, true);
        BufferedWriter bw= new BufferedWriter(fw);
        PrintWriter pw= new PrintWriter(bw);
        pw.println(this.type+" "+this.name+ " "+this.amount );

        pw.close();
        fw.close();
    }
}
