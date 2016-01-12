package finder.utility;

import org.jooq.Condition;
import org.jooq.TableLike;

/**
 * Created by adam on 23/12/15.
 */
public class JoinClause {

    private TableLike<?> table;
    private Condition onClause;

    public JoinClause(TableLike<?> table, Condition condition) {
        this.table = table;
        this.onClause = condition;
    }

    public TableLike<?> getTable() {
        return table;
    }

    public void setTable(TableLike<?> table) {
        this.table = table;
    }

    public Condition getOnClause() {
        return onClause;
    }

    public void setOnClause(Condition onClause) {
        this.onClause = onClause;
    }
}
