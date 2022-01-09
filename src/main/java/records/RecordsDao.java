package records;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;

import domain.ThingsTb;

public class RecordsDao {
    
    private JdbcTemplate jdbcTemplate;

    public RecordsDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /*private RowMapper<ThingsTb> thingsRowMapper = 
        new RowMapper<ThingsTb>() {
            @Override
            public ThingsTb mapRow(ResultSet rs, int rowNum) throws SQLException {
                ThingsTb thingsTb = new ThingsTb()
            }
        }*/

    public void insert(final ThingsTb thingsTb) {
        PreparedStatementCreator pre = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(
                    "INSERT INTO things(user_id, time, content, category_id) " + 
                    "VALUES (?, ?, ?, ?)",
                    new String[] {"things_id"}
                );
                pstmt.setLong(1, thingsTb.getUserId());
                pstmt.setTimestamp(2, Timestamp.valueOf(thingsTb.getTime()));
                pstmt.setString(3, thingsTb.getContent());
                pstmt.setLong(4, thingsTb.getCategoryId());
                return pstmt;
            }
        };
        jdbcTemplate.update(pre);
    }
}
