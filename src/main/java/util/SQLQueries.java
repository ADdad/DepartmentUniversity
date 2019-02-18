package util;

public abstract class SQLQueries {
    public static final String GET_MASTER_BY_ID = "SELECT * FROM scientists s INNER JOIN masters m ON s.scientist_id = m.scientist_id  WHERE s.scientist_id=";
    public static final String INSERT_SCIENSIST = "INSERT INTO scientists VALUES (?, ?, ?, ?)";
    public static final String INSERT_MASTER = "INSERT INTO masters VALUES (?, ?, ?, ?, ?, ?, ?)";
}
