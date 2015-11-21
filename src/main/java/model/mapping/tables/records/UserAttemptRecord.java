/**
 * This class is generated by jOOQ
 */
package model.mapping.tables.records;


import javax.annotation.Generated;

import model.mapping.tables.UserAttemptTable;

import org.jooq.Field;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.TableRecordImpl;


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
public class UserAttemptRecord extends TableRecordImpl<UserAttemptRecord> implements Record4<Integer, Integer, String, String> {

	private static final long serialVersionUID = 1001398132;

	/**
	 * Setter for <code>vegan_kitchen_api.user_attempt.user_id</code>.
	 */
	public void setUserId(Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>vegan_kitchen_api.user_attempt.user_id</code>.
	 */
	public Integer getUserId() {
		return (Integer) getValue(0);
	}

	/**
	 * Setter for <code>vegan_kitchen_api.user_attempt.attempt_number</code>.
	 */
	public void setAttemptNumber(Integer value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>vegan_kitchen_api.user_attempt.attempt_number</code>.
	 */
	public Integer getAttemptNumber() {
		return (Integer) getValue(1);
	}

	/**
	 * Setter for <code>vegan_kitchen_api.user_attempt.message</code>.
	 */
	public void setMessage(String value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>vegan_kitchen_api.user_attempt.message</code>.
	 */
	public String getMessage() {
		return (String) getValue(2);
	}

	/**
	 * Setter for <code>vegan_kitchen_api.user_attempt.session_id</code>.
	 */
	public void setSessionId(String value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>vegan_kitchen_api.user_attempt.session_id</code>.
	 */
	public String getSessionId() {
		return (String) getValue(3);
	}

	// -------------------------------------------------------------------------
	// Record4 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row4<Integer, Integer, String, String> fieldsRow() {
		return (Row4) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row4<Integer, Integer, String, String> valuesRow() {
		return (Row4) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field1() {
		return UserAttemptTable.USER_ATTEMPT.USER_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field2() {
		return UserAttemptTable.USER_ATTEMPT.ATTEMPT_NUMBER;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field3() {
		return UserAttemptTable.USER_ATTEMPT.MESSAGE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field4() {
		return UserAttemptTable.USER_ATTEMPT.SESSION_ID;
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
	public Integer value2() {
		return getAttemptNumber();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value3() {
		return getMessage();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value4() {
		return getSessionId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserAttemptRecord value1(Integer value) {
		setUserId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserAttemptRecord value2(Integer value) {
		setAttemptNumber(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserAttemptRecord value3(String value) {
		setMessage(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserAttemptRecord value4(String value) {
		setSessionId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserAttemptRecord values(Integer value1, Integer value2, String value3, String value4) {
		value1(value1);
		value2(value2);
		value3(value3);
		value4(value4);
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached UserAttemptRecord
	 */
	public UserAttemptRecord() {
		super(UserAttemptTable.USER_ATTEMPT);
	}

	/**
	 * Create a detached, initialised UserAttemptRecord
	 */
	public UserAttemptRecord(Integer userId, Integer attemptNumber, String message, String sessionId) {
		super(UserAttemptTable.USER_ATTEMPT);

		setValue(0, userId);
		setValue(1, attemptNumber);
		setValue(2, message);
		setValue(3, sessionId);
	}
}