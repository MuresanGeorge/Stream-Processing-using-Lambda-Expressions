import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by George on 5/28/2017.
 */
public class Test {

    List<MonitoredData> lmd=new ArrayList<>();

    public List<MonitoredData> citeste() {
        Path path = Paths.get("activitati.txt");
        List<String> date = new ArrayList<>();
        List<String[]> linie;
        try {
            linie = Files.lines(path).map(line -> line.split("\t\t")).collect(Collectors.toList());
            //citire linie din fisier
            linie.stream().forEach((strings) -> {
                Arrays.stream(strings).forEach(str -> {
                    String[] splits = str.split("\t\t");
                    for (String a : splits) {
                        date.add(a);
                    }
                });
            });
            int i=0;
            while (i<date.size()){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String s1 = date.get(i);
                String s2 = date.get(i + 1);
                String s3 = date.get(i + 2);
                LocalDateTime data1 = LocalDateTime.parse(s1, formatter);
                LocalDateTime data2 = LocalDateTime.parse(s2, formatter);
                formatter.format(data1);
                formatter.format(data2);
                //System.out.println(formatter.format(data1));
                lmd.add(new MonitoredData(data1, data2, s3));
                i=i+3;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lmd;
    }

    public void method1(Map<Integer, Map<String, Long>> Mapp1) throws IOException {
        //write to file : "fileone"
        try {
            File fileOne = new File("cerinta333.txt");
            FileOutputStream fos = new FileOutputStream(fileOne);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            Mapp1.forEach((key, value) -> {
                try {
                    oos.writeObject(key + " - " + value + System.lineSeparator());
                } catch (IOException e) {
                    e.printStackTrace();
                }});
            oos.flush();
            oos.close();
            fos.close();
        } catch (Exception e) {
        }
    }


}
