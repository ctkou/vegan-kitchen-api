/**
 * This class is generated by jOOQ
 */
package model.mapping.tables.records;


import javax.annotation.Generated;

import model.mapping.tables.UserTable;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.7.1"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UserRecord extends UpdatableRecordImpl<UserRecord> implements Record5<Integer, String, String, String, Byte> {

	private static final long serialVersionUID = 1922463886;

	/**
	 * Setter for <code>vegan_kitchen_api.user.user_id</code>.
	 */
	public void setUserId(Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>vegan_kitchen_api.user.user_id</code>.
	 */
	public Integer getUserId() {
		return (Integer) getValue(0);
	}

	/**
	 * Setter for <code>vegan_kitchen_api.user.user_name</code>.
	 */
	public void setUserName(String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>vegan_kitchen_api.user.user_name</code>.
	 */
	public String getUserName() {
		return (String) getValue(1);
	}

	/**
	 * Setter for <code>vegan_kitchen_api.user.email</code>.
	 */
	public void setEmail(String value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>vegan_kitchen_api.user.email</code>.
	 */
	public String getEmail() {
		return (String) getValue(2);
	}

	/**
	 * Setter for <code>vegan_kitchen_api.user.password</code>.
	 */
	public void setPassword(String value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>vegan_kitchen_api.user.password</code>.
	 */
	public String getPassword() {
		return (String) getValue(3);
	}

	/**
	 * Setter for <code>vegan_kitchen_api.user.is_blocked</code>.
	 */
	public void setIsBlocked(Byte value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>vegan_kitchen_api.user.is_blocked</code>.
	 */
	public Byte getIsBlocked() {
		return (Byte) getValue(4);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Record1<Integer> key() {
		return (Record1) super.key();
	}

	// -------------------------------------------------------------------------
	// Record5 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row5<Integer, String, String, String, Byte> fieldsRow() {
		return (Row5) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row5<Integer, String, String, String, Byte> valuesRow() {
		return (Row5) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field1() {
		return UserTable.USER.USER_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field2() {
		return UserTable.USER.USER_NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field3() {
		return UserTable.USER.EMAIL;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field4() {
		return UserTable.USER.PASSWORD;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Byte> field5() {
		return UserTable.USER.IS_BLOCKED;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value1() {
		return getUserId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value2() {
		return getUserName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value3() {
		return getEmail();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value4() {
		return getPassword();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Byte value5() {
		return getIsBlocked();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserRecord value1(Integer value) {
		setUserId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserRecord value2(String value) {
		setUserName(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserRecord value3(String value) {
		setEmail(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserRecord value4(String value) {
		setPassword(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserRecord value5(Byte value) {
		setIsBlocked(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserRecord values(Integer value1, String value2, String value3, String value4, Byte value5) {
		value1(value1);
		value2(value2);
		value3(value3);
		value4(value4);
		value5(value5);
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached UserRecord
	 */
	public UserRecord() {
		super(UserTable.USER);
	}

	/**
	 * Create a detached, initialised UserRecord
	 */
	public UserRecord(Integer userId, String userName, String email, String password, Byte isBlocked) {
		super(UserTable.USER);

		setValue(0, userId);
		setValue(1, userName);
		setValue(2, email);
		setValue(3, password);
		setValue(4, isBlocked);
	}
}
