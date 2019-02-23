package util;

public abstract class SQLQueries {
    public static final String GET_MASTER_BY_ID = "SELECT * FROM scientists s INNER JOIN masters m ON s.scientist_id = m.scientist_id  WHERE s.scientist_id=?";
    public static final String INSERT_SCIENSIST = "INSERT INTO scientists VALUES (?, ?, ?, ?)";
    public static final String INSERT_MASTER = "INSERT INTO masters VALUES (?, ?, ?, ?, ?, ?, ?)";
    public static final String DELETE_MASTER = "DELETE FROM masters WHERE scientist_id = ?";
    public static final String DELETE_SCIENTIST = "DELETE FROM scientists WHERE scientist_id = ?";
    public static final String GET_ALL_SCIENTISTS = "SELECT * FROM scientists";
    public static final String GET_ALL_MASTERS = "SELECT * FROM scientists s INNER JOIN masters m ON s.scientist_id = m.scientist_id";
    public static final String GET_SCIENTIST_BY_ID = "SELECT * FROM scientists WHERE scientist_id = ?";
    public static final String UPDATE_SCIENTIST = "UPDATE scientists SET second_name = ?, phone_number = ?, gender = ? " +
            "WHERE scientist_id = ?";
    public static final String UPDATE_MASTER = "UPDATE masters SET cathedra_id = ?, chief_id = ?, diploma_theme = ?, " +
            "start_date = ?, end_date = ?, end_reason = ? WHERE scientist_id = ?";
    public static final String GET_CATHEDRA_BY_ID = "SELECT * FROM cathedras WHERE id = ?";
    public static final String GET_ALL_CATHEDRAS = "SELECT * FROM cathedras";
    public static final String INSERT_CATHEDRA = "INSERT INTO cathedras VALUES (?, ?, ?)";
    public static final String DELETE_CATHEDRA = "DELETE FROM cathedras WHERE id = ?";
    public static final String UPDATE_CATHEDRA = "UPDATE cathedras name = ?, phone_number = ? WHERE id = ?";
    public static final String GET_SCIENCE_THEME_BY_ID = "SELECT * FROM science_themes WHERE id = ?";
    public static final String GET_ALL_SCIENCE_THEMES = "SELECT * FROM science_themes";
    public static final String INSERT_SCIENCE_THEME = "INSERT INTO science_themes VALUES (?, ?, ?, ?, ?, ?, ?)";
    public static final String DELETE_SCIENCE_THEME = "DELETE FROM science_themes WHERE id = ?";
    public static final String UPDATE_SCIENCE_THEME = "UPDATE science_themes SET chief_id = ?, cathedra_id = ?, name = ?, " +
            "customer = ?, start_date = ?, end_date = ? WHERE id = ?";
    public static final String GET_SCIENTIST_JOBS_BY_SCIENTIST_ID = "SELECT * FROM sc_themes_scientists WHERE worker_id = ?";
    public static final String GET_SCIENTIST_JOBS_BY_THEME_ID = "SELECT * FROM sc_themes_scientists WHERE science_theme_id = ?";
    public static final String GET_ALL_SCIENTIST_JOBS = "SELECT * FROM sc_themes_scientists";
    public static final String DELETE_SCIENTIST_JOB = "DELETE FROM sc_themes_scientists WHERE science_theme_id = ? AND " +
            "worker_id = ?";
    public static final String UPDATE_SCIENTIST_JOB = "UPDATE sc_themes_scientists SET start_date = ?, end_date = ? WHERE " +
            "science_theme_id = ? AND worker_id = ?";
    public static final String INSERT_SCIENCE_JOB = "INSERT INTO sc_themes_scientists VALUES (?, ?, ?, ?)";
    public static final String ADD_JOB_TO_SCIENTIST = "INSERT INTO sc_works_scientists VALUES (?, ?)";
    public static final String ADD_JOB_TO_THEME = "INSERT INTO sc_works_sc_themes VALUES (?, ?)";
    public static final String DELETE_SCIENTIST_FROM_JOB = "DELETE FROM sc_works_scientists WHERE work_id = ? AND author_id = ?";
    public static final String DELETE_THEME_FROM_JOB = "DELETE FROM sc_works_sc_themes WHERE work_id = ? AND theme_id = ?";
}
