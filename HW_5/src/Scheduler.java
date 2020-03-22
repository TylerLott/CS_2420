import java.util.ArrayList;

public class Scheduler {

    public void makeSchedule(String name, ArrayList<Task> tasks){

        System.out.println(name);

        LeftistQueue qu = new LeftistQueue();

        for (Task task : tasks){
            qu.enqueue(task);
        }

        int[] count = new int[100];
        int time = 1;
        int taskLate = 0;
        int totLate = 0;

        while (!qu.isEmpty()){
            LeftistNode node = qu.dequeue();
            Task thisOne = (Task) node.element;
            count[thisOne.ID]++;

            if (count[thisOne.ID] >= thisOne.duration){
                System.out.print("Time: " + time + " " + thisOne + "**");
                if (time > thisOne.deadline){
                    System.out.print(" Late " + (time - thisOne.deadline));
                    taskLate++;
                }
            } else {
                System.out.print("Time: " + time + " " + thisOne);

            }

            if (time > thisOne.deadline){
                totLate++;
            }

            System.out.println();

            time++;

        }

        // recap
        System.out.println("Tasks Late " + taskLate + " Total Late " + totLate);
        System.out.println(" ");


    }

}
