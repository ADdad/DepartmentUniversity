package util;

public abstract class UniverConst {
    public final static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public final static String JDBC_URL = "jdbc:mysql://localhost:3306/starostiuk_db?verifyServerCertificate=false"+
            "&useSSL=false"+
            "&requireSSL=false"+
            "&useLegacyDatetimeCode=false"+
            "&amp"+
            "&serverTimezone=UTC";
    public static final String USER = "root";
    public static final String PASSWORD = "root";
}
