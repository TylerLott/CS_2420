
public class Task1 extends Task {
    public Task1(int ID, int start, int deadline, int duration) {
        super(ID,start,deadline,duration);
    }
    // Prioirity is deadline
    @Override
    public int compareTo(Task t2) {
         //System.out.println("Using Task1 compareTo");
         return deadline-t2.deadline;
      }

}
