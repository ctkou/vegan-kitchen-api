package manager;

import application.MyApplication;
import exception.DatabaseException;
import exception.InvalidUserCreationException;
import finder.AbstractFinder;
import model.User;
import org.jooq.Condition;
import org.jooq.types.UInteger;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import static model.mapping.tables.UserTable.USER;

/**
 * Created by wendywang on 2015-11-14.
 */
public class UserManager {

    private final int MINIMUM_PASSWORD_LENGTH = 8;
    private final int MAXIMUM_PASSWORD_LENGTH = 255;
    private static SecureRandom secureRandom = new SecureRandom();

    /**
     * get user object based on user name and password
     * @param userName : user name
     * @param password : password
     * @return user object
     * @throws DatabaseException
     */
    public User getUser(String userName, String password) throws DatabaseException {
        try {
            Condition condition = USER.USER_NAME.equal(userName).and(USER.PASSWORD.equal(getEncryptPassword(password)));
            return AbstractFinder.getDataObject(USER, condition, User.class);
        }
        catch (NoSuchAlgorithmException exception) {
            throw new DatabaseException("Unable access user data due to password encryption error.");
        }
    }
    
    /**
     * get user object based on user name
     * @param userName : user name
     * @return return the corresponding user object
     * @throws DatabaseException
     */
    public User getUser(String userName) throws DatabaseException {
        Condition condition = USER.USER_NAME.equal(userName);
        return AbstractFinder.getDataObject(USER, condition, User.class);
    }

    /**
     * create user account, password assumed to be encrypted
     * @param user : new user to be created by user
     */
    public void createUser(User user) throws InvalidUserCreationException {
        try {
            AbstractFinder.storeDataObject(USER, user);
        } catch (DatabaseException exception) {
            if (exception.getMessage().contains("Duplicate")) {
                throw new InvalidUserCreationException("username/email already in use");
            }
            else {
                throw new InvalidUserCreationException("Unexpected database error");
            }
        }
    }

    /**
     * authenticate authorization token
     * @param authorization : authorization token issued in user login
     */
    public String authenticateUser(String authorization) {
        return null; // TODO
    }

    /**
     * encrypt password using MD5 (no decryption is required, password validation should be done using the encrypted password)
     * @param password : un-encrypted password
     * @return : encrypted password
     * @throws NoSuchAlgorithmException
     */
    public String getEncryptPassword(String password) throws NoSuchAlgorithmException{
        String encryptedPassword;
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.reset();
        byte[] passwordBytes = password.getBytes();
        byte[] digestedPasswordBytes = messageDigest.digest(passwordBytes);
        StringBuilder stringBuffer = new StringBuilder();
        for (byte digestedPasswordByte : digestedPasswordBytes) {
            stringBuffer.append(Integer.toHexString(0xff & digestedPasswordByte));
        }
        encryptedPassword = stringBuffer.toString();
        return encryptedPassword;
    }

    /**
     * create/update login session for user
     * @param userName : user name supplied by user
     * @param password : password supplied by user
     * @return : authorization token
     */
    public String login(String userName, String password) throws DatabaseException{
        // verity user name and password
        User user = getUser(userName, password);
        user.setLastAccess(UInteger.valueOf(System.currentTimeMillis() / 1000L));
        AbstractFinder.updateDataObject(USER, user);
        String authorization = generateAuthorizationToken();
        MyApplication.memcachedManager.storeAuthorizationToken(userName, authorization);
        return authorization;
    }

    /**
     * end a login session
     * @param authorization : authorization token of existing session
     */
    public void logout(String authorization) throws DatabaseException{
        MyApplication.memcachedManager.removeAuthorizationToken(authorization);
    }

    /**
     * throw exception if the new user's information is invalid and cannot be created
     * @param user : new user
     * @throws InvalidUserCreationException
     * @throws DatabaseException
     */
    public void validateNewUser(User user) throws InvalidUserCreationException, DatabaseException {
        if (!isEmail(user.getEmail())) {
            throw new InvalidUserCreationException("invalid email format");
        }
        else if (!isValidPassword(user.getPassword())) {
            throw new InvalidUserCreationException("invalid password");
        }
        else if (user.getUserId() != null) {
            throw new InvalidUserCreationException("invalid attempt to supply user id for user creation");
        }
    }

    /**
     * return true if the supplied email is valid
     * @param email : email string
     * @return
     */
    private boolean isEmail(String email) {
        return email.toUpperCase().matches("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$");
    }

    /**
     * return true if the supplied password is valid
     * @param password : password string
     * @return
     */
    private boolean isValidPassword(String password) {
        return password.length() > MINIMUM_PASSWORD_LENGTH && password.length() < MAXIMUM_PASSWORD_LENGTH;
    }

    /**
     * generate a new authorization token
     * @return : authorization token string
     */
    private String generateAuthorizationToken() {
        int numberOfBits = 130;
        int authorizationTokenLength = 32;
        return new BigInteger(numberOfBits, secureRandom).toString(authorizationTokenLength);
    }

}
