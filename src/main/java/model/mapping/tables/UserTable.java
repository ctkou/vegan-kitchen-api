/**
 * This class is generated by jOOQ
 */
package model.mapping.tables;


import model.mapping.Keys;
import model.mapping.VeganKitchenApiSchema;
import model.mapping.tables.records.UserRecord;
import org.jooq.*;
import org.jooq.impl.TableImpl;
import org.jooq.types.UInteger;

import javax.annotation.Generated;
import java.util.Arrays;
import java.util.List;


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
public class UserTable extends TableImpl<UserRecord> {

	private static final long serialVersionUID = -182422140;

	/**
	 * The reference instance of <code>vegan_kitchen_api.user</code>
	 */
	public static final UserTable USER = new UserTable();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<UserRecord> getRecordType() {
		return UserRecord.class;
	}

	/**
	 * The column <code>vegan_kitchen_api.user.user_id</code>.
	 */
	public final TableField<UserRecord, Integer> USER_ID = createField("user_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>vegan_kitchen_api.user.user_name</code>.
	 */
	public final TableField<UserRecord, String> USER_NAME = createField("user_name", org.jooq.impl.SQLDataType.VARCHAR.length(50).nullable(false), this, "");

	/**
	 * The column <code>vegan_kitchen_api.user.email</code>.
	 */
	public final TableField<UserRecord, String> EMAIL = createField("email", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");

	/**
	 * The column <code>vegan_kitchen_api.user.password</code>.
	 */
	public final TableField<UserRecord, String> PASSWORD = createField("password", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");

	/**
	 * The column <code>vegan_kitchen_api.user.authorization_token</code>.
	 */
	public final TableField<UserRecord, String> AUTHORIZATION_TOKEN = createField("authorization_token", org.jooq.impl.SQLDataType.VARCHAR.length(225), this, "");

	/**
	 * The column <code>vegan_kitchen_api.user.last_access</code>.
	 */
	public final TableField<UserRecord, UInteger> LAST_ACCESS = createField("last_access", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false).defaulted(true), this, "");

	/**
	 * Create a <code>vegan_kitchen_api.user</code> table reference
	 */
	public UserTable() {
		this("user", null);
	}

	/**
	 * Create an aliased <code>vegan_kitchen_api.user</code> table reference
	 */
	public UserTable(String alias) {
		this(alias, USER);
	}

	private UserTable(String alias, Table<UserRecord> aliased) {
		this(alias, aliased, null);
	}

	private UserTable(String alias, Table<UserRecord> aliased, Field<?>[] parameters) {
		super(alias, VeganKitchenApiSchema.VEGAN_KITCHEN_API, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Identity<UserRecord, Integer> getIdentity() {
		return Keys.IDENTITY_USER;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<UserRecord> getPrimaryKey() {
		return Keys.KEY_USER_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<UserRecord>> getKeys() {
		return Arrays.<UniqueKey<UserRecord>>asList(Keys.KEY_USER_PRIMARY, Keys.KEY_USER_USER_ID, Keys.KEY_USER_USER_NAME, Keys.KEY_USER_EMAIL, Keys.KEY_USER_AUTHORIZATION_TOKEN);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserTable as(String alias) {
		return new UserTable(alias, this);
	}

	/**
	 * Rename this table
	 */
	public UserTable rename(String name) {
		return new UserTable(name, null);
	}
}
