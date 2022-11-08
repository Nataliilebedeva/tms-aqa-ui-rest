package models;

import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Section {
    @Expose
    private int id;
    @Expose
    private String description;
    @Expose
    private String name;
}
