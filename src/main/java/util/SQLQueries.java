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
}
