package models;

import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class Milestones {
    @Expose
    private long completed_on; //??
    @Expose
    private String description;
    @Expose
    private long due_on;
    @Expose
    private int id;
    @Expose
    private boolean is_completed;
    @Expose
    private String name;
    @Expose
    private int project_id;
    @Expose
    private String refs;
    @Expose
    private String url;
    @Expose
    private boolean is_started;
    @Expose
    private long start_on; //= new Timestamp(System.currentTimeMillis()); //??
}

