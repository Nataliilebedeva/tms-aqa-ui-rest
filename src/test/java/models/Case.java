package models;

import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Case {
    @Expose
    int id;
    @Expose
    String title;
    @Expose
    int type_id;
    @Expose
    int priority_id;
    @Expose
    String custom_preconds;
    @Expose
    String custom_steps;
    @Expose
    String custom_expected;
    @Expose
    String cases_ids;
}
