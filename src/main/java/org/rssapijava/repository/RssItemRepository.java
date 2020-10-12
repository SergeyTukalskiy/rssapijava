package org.rssapijava.repository;

import org.rssapijava.entity.RssItem;
import org.rssapijava.type.RssSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Repository("rssItemRepository")
public class RssItemRepository implements IRestRepository<RssItem> {
    private static String selectQuery = "SELECT \"id\", \"source\", \"title\", \"link\", \"date\" " +
            "FROM \"rss_item\" " +
            "ORDER BY \"id\"";

    private static String selectByIdQuery = "SELECT \"id\", \"source\", \"title\", \"link\", \"date\" " +
            "FROM \"rss_item\" " +
            "WHERE \"id\" = ?";

    private static String insertQuery = "INSERT INTO \"rss_item\"(\"source\", \"title\", \"link\", \"date\") " +
            "VALUES (?, ?, ?, ?) " +
            "RETURNING \"id\", \"source\", \"title\", \"link\", \"date\"";

    private static String updateQuery = "UPDATE \"rss_item\" " +
            "SET \"source\" = ?, \"title\" = ?, \"link\" = ?, \"date\" = ? " +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"source\", \"title\", \"link\", \"date\"";

    private static String deleteQuery = "DELETE FROM \"rss_item\" " +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"source\", \"title\", \"link\", \"date\"";

    @Autowired
    protected JdbcOperations jdbcOperations;

    @Override
    public RssItem[] select() {
        ArrayList<RssItem> values = new ArrayList<RssItem>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectQuery);
        while (rowSet.next()) {
            values.add(new RssItem(
                    rowSet.getInt(1),
                    RssSource.fromInt(rowSet.getInt(2)),
                    rowSet.getString(3),
                    rowSet.getString(4),
                    rowSet.getTimestamp(5)
            ));
        }
        RssItem[] result = new RssItem[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public RssItem select(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByIdQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new RssItem(
                rowSet.getInt(1),
                RssSource.fromInt(rowSet.getInt(2)),
                rowSet.getString(3),
                rowSet.getString(4),
                rowSet.getTimestamp(5)
        );
    }

    @Override
    public RssItem insert(RssItem entity) {
        Object[] params = new Object[] { entity.getSource().getNumVal(), entity.getTitle(), entity.getLink(), entity.getDate() };
        int[] types = new int[] {Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(insertQuery, params, types);
        if (rowSet.next()) {
            return new RssItem(
                    rowSet.getInt(1),
                    RssSource.fromInt(rowSet.getInt(2)),
                    rowSet.getString(3),
                    rowSet.getString(4),
                    rowSet.getTimestamp(5)
            );
        }
        return null;
    }

    @Override
    public RssItem update(Integer id, RssItem entity) {
        Object[] params = new Object[] { entity.getSource().getNumVal(), entity.getTitle(), entity.getSource(), entity.getDate(), id };
        int[] types = new int[] { Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP, Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(updateQuery, params, types);
        if (rowSet.next()) {
            return new RssItem(
                    rowSet.getInt(1),
                    RssSource.fromInt(rowSet.getInt(2)),
                    rowSet.getString(3),
                    rowSet.getString(4),
                    rowSet.getTimestamp(5)
            );
        }
        return null;
    }

    @Override
    public RssItem delete(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(deleteQuery, params, types);
        if (rowSet.next()) {
            return new RssItem(
                    rowSet.getInt(1),
                    RssSource.fromInt(rowSet.getInt(2)),
                    rowSet.getString(3),
                    rowSet.getString(4),
                    rowSet.getTimestamp(5)
            );
        }
        return null;
    }
}
