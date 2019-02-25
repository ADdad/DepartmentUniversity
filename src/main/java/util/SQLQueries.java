package util;

public abstract class SQLQueries {
    public static final String GET_MASTER_BY_ID = "SELECT * FROM scientists s INNER JOIN masters m " +
            "ON s.scientist_id = m.scientist_id  WHERE s.scientist_id=?";
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
    public static final String GET_SCIENTIST_JOBS_BY_WORKER_ID = "SELECT * FROM sc_themes_scientists WHERE worker_id = ?";
    public static final String GET_SCIENTIST_JOBS_BY_THEME_ID = "SELECT * FROM sc_themes_scientists WHERE science_theme_id = ?";
    public static final String GET_ALL_SCIENTIST_JOBS = "SELECT * FROM sc_themes_scientists";
    public static final String DELETE_SCIENTIST_JOB = "DELETE FROM sc_themes_scientists WHERE id = ?";
    public static final String UPDATE_SCIENTIST_JOB = "UPDATE sc_themes_scientists SET start_date = ?, end_date = ?, " +
            "science_theme_id = ?, worker_id = ?, name = ? WHERE id = ?";
    public static final String INSERT_SCIENTIST_JOB = "INSERT INTO sc_themes_scientists VALUES (?, ?, ?, ?, ?, ?)";
    public static final String INSERT_SCIENTIFIC_WORK = "INSERT INTO scientific_works VALUES (?, ?, ?, ?)";
    public static final String GET_SCIENTIFIC_WORKS_BY_AUTHOR_ID = "SELECT * FROM scientific_works sw INNER JOIN " +
            "sc_works_scientists ss ON sw.id = ss.work_id WHERE ss.author_id = ?";
    public static final String GET_SCIENTIFIC_WORKS_BY_THEME_ID = "SELECT * FROM scientific_works sw INNER JOIN" +
            "sc_works_sc_themes st ON sw.id = st.work_id WHERE st.theme_id = ?";
    public static final String ADD_WORK_TO_SCIENTIST = "INSERT INTO sc_works_scientists VALUES (?, ?)";
    public static final String ADD_WORK_TO_THEME = "INSERT INTO sc_works_sc_themes VALUES (?, ?)";
    public static final String UPDATE_SCIENTIFIC_WORK = "UPDATE scientific_works SET name = ?, work_type = ?, year_of_job = ? " +
            "WHERE id = ?";
    public static final String DELETE_SCIENTIFIC_WORK = "DELETE FROM scientific_works WHERE id = ?";
    public static final String DELETE_SCIENTIST_FROM_WORK = "DELETE FROM sc_works_scientists WHERE work_id = ? AND author_id = ?";
    public static final String DELETE_THEME_FROM_WORK = "DELETE FROM sc_works_sc_themes WHERE work_id = ? AND theme_id = ?";
    public static final String GET_TEACHER_BY_ID = "SELECT * FROM scientists s INNER JOIN teachers t " +
            "ON s.scientist_id = t.scientist_id  WHERE s.scientist_id=?";
    public static final String GET_ALL_TEACHERS = "SELECT * FROM scientists s INNER JOIN teachers m " +
            "ON s.scientist_id = m.scientist_id";
    public static final String GET_CATHEDRA_BY_NAME = "SELECT * FROM cathedras WHERE name = ?";
    public static final String GET_TEACHER_BY_NAME = "SELECT * FROM scientists s INNER JOIN teachers m " +
            "ON s.scientist_id = m.scientist_id WHERE second_name = ?";
    public static final String GET_SCIENCE_THEME_BY_NAME = "SELECT * FROM science_themes WHERE name = ?";
    public static final String GET_SCIENTIFIC_WORK_BY_ID = "SELECT * FROM scientific_works sw WHERE id = ?";
    public static final String GET_SCIENCE_JOB_BY_ID = "SELECT * FROM sc_themes_scientists WHERE id = ?";
    public static final String GET_SCIENCE_THEMES_OF_WORK = "SELECT * FROM science_themes st INNER JOIN sc_works_sc_themes wt " +
            "ON st.id=wt.theme_id WHERE work_id = ?";
    public static final String GET_SCIENTIST_BY_NAME = "SELECT * FROM scientists WHERE second_name = ?";
    public static final String DELETE_ALL_THEMES_FROM_WORK = "DELETE FROM sc_works_sc_themes WHERE work_id = ?";
    public static final String DELETE_ALL_AUTHORS_FROM_WORK = "DELETE FROM sc_works_scientists WHERE work_id = ?";
}
