package repo.impl;

import entity.Item;
import repo.ItemRepository;

import java.sql.*;

import static utility.DataCredentials.*;

/**
 * Created by Moon on 25/04/2021
 */
public class ItemRepositoryImpl implements ItemRepository {
    @Override
    public int save(Item item) {
        try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {

            String sql= "INSERT INTO javadevt_genk_08.Item (orderId, songId, amount) VALUES (?, ?, ?)";

            PreparedStatement ps= conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, item.getOrderId());
            ps.setLong(2,item.getSongId());
            ps.setInt(3,item.getAmount());

            int result = ps.executeUpdate();
            int generatedId=-1;
            if (result > 0) {

                try {
                    ResultSet rs = ps.getGeneratedKeys();
                    if (rs.next()) {
                        generatedId = (int) rs.getLong(1);

                    }

                }catch (SQLException throwables) {
                    throwables.printStackTrace();
                    throw  new RuntimeException(throwables);
                }
            }
            return generatedId;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw  new RuntimeException(throwables);
        }
    }
}
