package utility;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibatis.common.jdbc.ScriptRunner;
import org.codehaus.plexus.util.IOUtil;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * Created by adam on 22/11/15.
 */
public class TestUtility {

    private static ObjectMapper objectMapper = new ObjectMapper();
    private static SecureRandom secureRandom = new SecureRandom();

    private String TEST_DATA_PATH = "../resources/test_data/";
    private String PROPERTIES_FILE = "testconfig/test.config.properties";
    private String connectionUrl;
    private String mysqlConnectorDriver;
    private String mysqlUser;
    private String mysqlPassword;

    private void loadDatabaseConfiguration() {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE);
        Properties properties = new Properties();
        if (inputStream != null) {
            try {
                properties.load(inputStream);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        connectionUrl = properties.getProperty("mysql_url");
        mysqlConnectorDriver = properties.getProperty("mysql_connector_driver");
        mysqlUser = properties.getProperty("mysql_user");
        mysqlPassword = properties.getProperty("mysql_password");
    }

    public Connection getDatabaseConnection() throws Exception{
        loadDatabaseConfiguration();
        Class.forName(mysqlConnectorDriver).newInstance();
        return DriverManager.getConnection(connectionUrl, mysqlUser, mysqlPassword);
    }

    public void resetDataBase() throws Exception{
        runSQLScript("reset.sql");
    }

    public void resetTestData(String testDataSetupScript) throws Exception {
        runSQLScript("reset.sql");
        runSQLScript(testDataSetupScript);
    }

    public void runSQLScript(String sqlScript) throws Exception{
        try (Connection connection = getDatabaseConnection()) {
            ScriptRunner scriptRunner = new ScriptRunner(connection, false, false);
            InputStream scriptInputStream = this.getClass().getClassLoader().getResourceAsStream("resources/data_scripts/" + sqlScript);
            InputStreamReader inputStreamReader = new InputStreamReader(scriptInputStream);
            scriptRunner.runScript(inputStreamReader);
        }
    }

    public String getRandomString(int stringLength) {
        int encodingBase = 32;
        return new BigInteger(stringLength * 5, secureRandom).toString(encodingBase);
    }

    public String getStringFromFile(String fileName) throws Exception {
        return IOUtil.toString(this.getClass().getResourceAsStream(TEST_DATA_PATH + fileName));
    }

    public <T> T getPojoFromFile(String fileName, TypeReference typeReference) throws Exception {
        String jsonString = getStringFromFile(fileName);
        return objectMapper.readValue(jsonString, typeReference);
    }

    public <T> T convertObjectByReferenceType(Object object, TypeReference typeReference) throws Exception {
        return objectMapper.convertValue(object, typeReference);
    }

}
