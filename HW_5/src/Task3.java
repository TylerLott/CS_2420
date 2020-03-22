
public class Task3 extends Task {
    public Task3(int ID, int start, int deadline, int duration) {
        super(ID,start,deadline,duration);
    }
    // Prioirity is deadline
    @Override
    public int compareTo(Task t2) {
        //System.out.println("Using Task1 compareTo");
        return (start - deadline)-(t2.start - t2.deadline);
    }

}