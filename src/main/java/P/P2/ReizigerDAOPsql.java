package P.P2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReizigerDAOPsql implements ReizigerDAO {
    private Connection con;

    public ReizigerDAOPsql(Connection connection) {
        this.con = connection;
    }

    @Override
    public boolean save(Reiziger reiziger) {
        String sql = "INSERT INTO reiziger (reiziger_id, voorletters, tussenvoegsel, achternaam, geboortedatum) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, reiziger.getReiziger_id());
            pst.setString(2, reiziger.getVoorletters());
            pst.setString(3, reiziger.getTussenvoegsel());
            pst.setString(4, reiziger.getAchternaam());
            pst.setDate(5, java.sql.Date.valueOf(reiziger.getGeboortedatum()));
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Reiziger reiziger) {
        String sql = "UPDATE reiziger SET voorletters=?, tussenvoegsel=?, achternaam=?, geboortedatum=? WHERE reiziger_id=?";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, reiziger.getVoorletters());
            pst.setString(2, reiziger.getTussenvoegsel());
            pst.setString(3, reiziger.getAchternaam());
            pst.setDate(4, java.sql.Date.valueOf(reiziger.getGeboortedatum()));
            pst.setInt(5, reiziger.getReiziger_id());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Reiziger reiziger) {
        String sql = "DELETE FROM reiziger WHERE reiziger_id=?";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, reiziger.getReiziger_id());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Reiziger findById(int id) {
        String sql = "SELECT * FROM reiziger WHERE reiziger_id=?";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return new Reiziger(rs.getInt("reiziger_id"),
                            rs.getString("voorletters"),
                            rs.getString("tussenvoegsel"),
                            rs.getString("achternaam"),
                            rs.getDate("geboortedatum").toLocalDate());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Reiziger> findAll() {
        List<Reiziger> reizigers = new ArrayList<>();
        String sql = "SELECT * FROM reiziger";
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                reizigers.add(new Reiziger(rs.getInt("reiziger_id"),
                        rs.getString("voorletters"),
                        rs.getString("tussenvoegsel"),
                        rs.getString("achternaam"),
                        rs.getDate("geboortedatum").toLocalDate()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reizigers;
    }
}
