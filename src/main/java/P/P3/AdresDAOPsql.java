package P.P3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdresDAOPsql implements AdresDAO {
    private Connection con;
    private ReizigerDAOPsql reizigerDAO;

    public AdresDAOPsql(Connection connection) {
        this.con = connection;
    }

    @Override
    public boolean save(Adres adres) {
        String sql = "INSERT INTO adres (adres_id, postcode, huisnummer, straat, woonplaats, reiziger_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, adres.getAdres_id());
            pst.setString(2, adres.getPostcode());
            pst.setString(3, adres.getHuisnummer());
            pst.setString(4, adres.getStraat());
            pst.setString(5, adres.getWoonplaats());
            pst.setInt(6, adres.getReiziger().getReiziger_id());

            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Adres adres) {
        String sql = "UPDATE adres SET postcode=?, huisnummer=?, straat=?, woonplaats=?, reiziger_id=? WHERE adres_id=?";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, adres.getPostcode());
            pst.setString(2, adres.getHuisnummer());
            pst.setString(3, adres.getStraat());
            pst.setString(4, adres.getWoonplaats());
            pst.setInt(5, adres.getReiziger().getReiziger_id());
            pst.setInt(6, adres.getAdres_id());

            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Adres adres) {
        String sql = "DELETE FROM adres WHERE adres_id=?";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, adres.getAdres_id());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Adres findByReiziger(Reiziger reiziger) {
        String sql = "SELECT adres_id, postcode, huisnummer, straat, woonplaats FROM adres WHERE reiziger_id=?";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, reiziger.getReiziger_id());

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return new Adres(
                        rs.getInt("adres_id"),
                        rs.getString("postcode"),
                        rs.getString("huisnummer"),
                        rs.getString("straat"),
                        rs.getString("woonplaats"),
                        reiziger
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
