package postman.models;

import java.sql.Timestamp;
import java.util.List;

public class MilestoneModel {
    public int id = 15;
    public String name = "Milestone 3.0";
    public String description = null;
    public Timestamp start_on = null;
    public Timestamp started_on = new Timestamp(1625586529);
    public boolean is_started = true;
    public Timestamp due_on = new Timestamp(1626783718);
    public boolean is_completed = false;
    public Timestamp completed_on = null;
    public int project_id = 0;
    public int parent_id;
    public String refs = null;
    public String url = "https://aqa06onl01.testrail.io/index.php?/milestones/view/15";
    public List<Object> milestones;

    public MilestoneModel() {
    }
}

