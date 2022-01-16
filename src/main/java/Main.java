import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Main {

    static void modifyFile(String filePath, int period) throws ParseException {
        String oldContent="";
        FileWriter writer = null;
        int counter = 0;
        int secC = 0;
        try {
            File myObj = new File(filePath);
            Scanner reader = new Scanner(myObj);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                if(line.contains("time")){
                    String myTime =line.substring(21,29);
                    System.out.println(myTime);
                    String oldTime = myTime;
                    SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
                    Date d = df.parse(myTime);
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(d);
                    counter++;
                    if(counter>period) {
                        counter=0;
                        secC--;
                        cal.add(Calendar.SECOND,secC);
                    }
                    else{
                        cal.add(Calendar.SECOND,secC);
                    }
                    String newTime = df.format(cal.getTime());
                    String novy = line.replaceAll(oldTime,newTime);
                    System.out.println(novy);  //na kontrolu
                    oldContent = oldContent + novy + System.lineSeparator();
                }
                else {
                    oldContent = oldContent + line + System.lineSeparator();
                }
            }
            reader.close();
            writer = new FileWriter("run.gpx");
            writer.write(oldContent);
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws ParseException {
        modifyFile("/Users/user/Downloads/opopoo.rtf", 20);
        System.out.println("done");
    }
}
