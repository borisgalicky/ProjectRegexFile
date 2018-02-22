import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String [] args) throws FileNotFoundException {
        String filename = "rawText.txt";
        String line = null;
        PrintWriter writespz = new PrintWriter("spz.txt");
        PrintWriter writerc = new PrintWriter("rc.txt");
        PrintWriter writemac = new PrintWriter("mac.txt");
        Pattern rc = Pattern.compile("^\\d{2}(([0][1-9])|([1][012])|([5][1-9])|([6][012]))([0][1-9]|[12][0-9]|[3][01])[\\/]?\\d{4}$");
        Pattern spz = Pattern.compile("^[A-Z]{2}\\d{3}[A-Z]{2}$");
        Pattern mac = Pattern.compile("^([A-F0-9][A-F0-9][:]){5}[A-F0-9][A-F0-9]$");
        try {

            FileReader fileReader =
                    new FileReader(filename);
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                String []parts = line.split(" ");
                for(String w : parts) {
                    Matcher matchspz = spz.matcher((String)w);
                    Matcher matchrc = rc.matcher((String)w);
                    Matcher matchmac = mac.matcher((String)w);
                    if(matchmac.matches()){
                        writemac.println(w);
                    }
                    if(matchrc.matches()){
                        writerc.println(w);
                    }
                    if(matchspz.matches()){
                        writespz.println(w);
                    }
                }
            }
            bufferedReader.close();
            writerc.close();
            writemac.close();
            writespz.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Can't open file '"+filename+"'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Can't read file '"+filename+"'");

        }
    }
}
