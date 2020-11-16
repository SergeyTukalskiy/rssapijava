package org.rssapijava.repository;

import org.rssapijava.entity.RssSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.ArrayList;

@Repository
public class RssSourceRepository implements IRestRepository<org.rssapijava.entity.RssSource> {
    protected final JdbcOperations jdbcOperations;
    
    private static String selectQuery = "SELECT \"id\", \"name\", \"link\" " +
            "FROM \"rss_source\" " +
            "ORDER BY \"id\"";

    private static String selectByIdQuery = "SELECT \"id\", \"name\", \"link\" " +
            "FROM \"rss_source\" " +
            "WHERE \"id\" = ?";

    private static String insertQuery = "INSERT INTO \"rss_source\"(\"name\", \"link\") " +
            "VALUES (?, ?) " +
            "RETURNING \"id\", \"name\", \"link\"";

    private static String updateQuery = "UPDATE \"rss_source\" " +
            "SET \"name\" = ?, \"link\" = ? " +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"name\", \"link\"";

    private static String deleteQuery = "DELETE FROM \"rss_source\" " +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"name\", \"link\"";

    public RssSourceRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public RssSource[] select() {
        ArrayList<RssSource> values = new ArrayList<RssSource>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectQuery);
        while (rowSet.next()) {
            values.add(new RssSource(
                    rowSet.getInt(1),
                    rowSet.getString(2),
                    rowSet.getString(3)
            ));
        }
        RssSource[] result = new RssSource[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public RssSource select(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByIdQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new RssSource(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getString(3)
        );
    }

    @Override
    public RssSource insert(RssSource entity) {
        Object[] params = new Object[] { entity.getName(), entity.getLink() };
        int[] types = new int[] { Types.VARCHAR, Types.VARCHAR };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(insertQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new RssSource(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getString(3)
        );
    }

    @Override
    public RssSource update(Integer id, RssSource entity) {
        Object[] params = new Object[] { entity.getName(), entity.getLink(), id };
        int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(updateQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new RssSource(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getString(3)
        );
    }

    @Override
    public RssSource delete(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(deleteQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new RssSource(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getString(3)
        );
    }
}
