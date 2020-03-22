import java.awt.desktop.SystemEventListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class TestSched {


    public static void readTasks(String filename,
                          ArrayList<Task> t1, ArrayList<Task> t2, ArrayList<Task> t3) throws FileNotFoundException {
        // Create lists where base type is different
        File text = new File(filename);
        Scanner scan = new Scanner(text);

        int id = 1;
        while (scan.hasNextInt()){
            int startTime = scan.nextInt();
            int deadline = scan.nextInt();
            int minutes = scan.nextInt();
            for (int i=0;i<minutes;i++){
                t1.add(new Task1(id, startTime, deadline, minutes));
                t2.add(new Task2(id, startTime, deadline, minutes));
                t3.add(new Task3(id, startTime, deadline, minutes));
            }
            id++;

        }



    }

    public static void main(String args[]) throws FileNotFoundException {
        Scheduler s = new Scheduler();
        String [] files = {"taskset1.txt", "taskset2.txt", "taskset3.txt", "taskset4.txt", "taskset5.txt"};
        for (String f : files) {
            ArrayList<Task> t1 = new ArrayList();    // elements are Task1
            ArrayList<Task> t2 = new ArrayList();    // elements are Task2
            ArrayList<Task> t3 = new ArrayList();    // elements are Task3
            readTasks(f, t1, t2, t3);
            s.makeSchedule("Deadline Priority "+ f, t1);
            s.makeSchedule("Startime Priority " + f, t2);
            s.makeSchedule("Wild and Crazy priority " + f, t3);
       }

    }
}
