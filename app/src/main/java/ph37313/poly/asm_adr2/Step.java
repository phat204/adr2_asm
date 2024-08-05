package ph37313.poly.asm_adr2;

public class Step {
    private int id;
    private String date;
    private int steps;
    private int goal;

    public Step(int id, String date, int steps, int goal) {
        this.id = id;
        this.date = date;
        this.steps = steps;
        this.goal = goal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }
}
