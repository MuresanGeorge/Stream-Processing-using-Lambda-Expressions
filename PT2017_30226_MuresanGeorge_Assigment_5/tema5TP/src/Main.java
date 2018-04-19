import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by George on 5/28/2017.
 */
public class Main {
    public static void main (String args[]) throws IOException {
            System.out.println("bunaaa");
        Test t=new Test();
        List<MonitoredData>lmd=new ArrayList<>();
       lmd= t.citeste();
        lmd.forEach(System.out::println);
        System.out.println();
        System.out.println(lmd.stream().map(el -> el.getEndtime().getDayOfMonth()).distinct().count());


        //-------------------------------------cerinta2-------------------------------------------
        Map<String, Long> Mapp = lmd.stream().collect(Collectors.groupingBy(el -> el.getActivitylab().toString(), Collectors.counting()));
       // System.out.println(Mapp);

        FileWriter fstream;
        BufferedWriter out;

        // create your filewriter and bufferedreader
        fstream = new FileWriter("cerinta2.txt");
        out = new BufferedWriter(fstream);

        // create your iterator for your map
        Iterator<Map.Entry<String, Long>> it = Mapp.entrySet().iterator();

       // then use the iterator to loo through the map, stopping when we reach the
        // last record in the map or when we have printed enough records
        while (it.hasNext()) {
            // the key/value pair is stored here in pairs
            Map.Entry<String, Long> pairs = it.next();
            //System.out.println("   "+pairs.getKey() +"=" + pairs.getValue());
            // since you only want the value, we only care about pairs.getValue(), which is written to out
            out.write("   "+pairs.getKey() +"=" + pairs.getValue() + "\n");

        }
        // lastly, close the file and end
        out.close();
        //https://stackoverflow.com/questions/15413467/writing-from-hashmap-to-a-txt-file



//-------------------------------------------------------------------cerinta 3-----------------------------------------------------

        Map<Integer, Map<String, Long>> Mapp1 = lmd.stream().collect(Collectors.groupingBy(el->el.getStarttime().getDayOfMonth(), Collectors.groupingBy(x -> x.getActivitylab().toString(), Collectors.counting())));
        //System.out.println(Mapp1);
        /*FileWriter fstream1;
        BufferedWriter out1;

        // create your filewriter and bufferedreader
        fstream1 = new FileWriter("cerinta3.txt");
        out1 = new BufferedWriter(fstream1);

        // create your iterator for your map
        Iterator<Map.Entry<Integer, Map<String, Long>>> it1 = Mapp1.entrySet().iterator();

        // then use the iterator to loop through the map, stopping when we reach the
        // last record in the map or when we have printed enough records
        while (it1.hasNext()) {
            // the key/value pair is stored here in pairs
            Map.Entry<Integer, Map<String, Long>> p = it1.next();
            System.out.println("   "+p.getKey() +"=" + p.getValue());
            // since you only want the value, we only care about pairs.getValue(), which is written to out
            out.write("   "+p.getKey() +"=" + p.getValue() + "\n");

        }
        // lastly, close the file and end
        out1.close();
        //https://stackoverflow.com/questions/15413467/writing-from-hashmap-to-a-txt-file*/


        //----------------------alta varianta
        t.method1(Mapp1);



        ///-----------------------------------------------------------cerinta 4------------------------------------------

        Map<String, Long> Map11 = lmd.stream().collect((Collectors.groupingBy(MonitoredData::getActivitylab, Collectors.summingLong(MonitoredData::functie))));//le-am grupat dupa activitati si mi-am insumat toate de la o activitate
        Map<String, Long> dd = Map11.entrySet().stream().filter(map -> map.getValue() >= 36000)
                .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));//mi-am facut in mapul 2 numai alea ce au mai multe de 10 ore
        System.out.println(dd);
    }

    }
