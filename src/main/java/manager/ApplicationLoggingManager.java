package manager;

import finder.AbstractFinder;
import model.ApplicationLogging;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wendywang on 2015-11-14.
 */
public class ApplicationLoggingManager {

    public List<ApplicationLogging> getApplicationLogging(Connection con) throws SQLException {
        ArrayList<ApplicationLogging> applicationLoggings = new ArrayList<ApplicationLogging>();
        try (Connection connection = AbstractFinder.getDatabaseConnection()) {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM applicationLogging");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ApplicationLogging applicationLoggingObj = new ApplicationLogging();
                applicationLoggingObj.setSessionID(rs.getString("session_id"));
                applicationLoggingObj.setUserID(rs.getInt("user_id"));
                applicationLoggingObj.setMessage(rs.getString("message"));
                applicationLoggingObj.setException(rs.getString("exception"));
                applicationLoggings.add(applicationLoggingObj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return applicationLoggings;
    }


}
