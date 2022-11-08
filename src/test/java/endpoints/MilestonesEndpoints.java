package endpoints;

public interface MilestonesEndpoints {
    String ADD_MILESTONES = "index.php?/api/v2/add_milestone/%d";
    String GET_MILESTONE = "index.php?/api/v2/get_milestone/%d";
    String GET_MILESTONES = "index.php?/api/v2/get_milestones/%d";
    String UPDATE_MILESTONES = "index.php?/api/v2/update_milestone/%d";
    String DELETE_MILESTONES = "index.php?/api/v2/delete_milestone/%d";
}
