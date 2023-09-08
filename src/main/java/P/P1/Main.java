package P.P1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        System.out.println("Alle reizigers:");

        String url = "jdbc:postgresql://localhost/ovchip?user=postgres&password=fifaUT15";


        try {
            Connection con = DriverManager.getConnection(url);

            Statement statement = con.createStatement();

            String query = "SELECT * FROM reiziger";

            ResultSet resultSet = statement.executeQuery(query);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            int count = 0;

            while (resultSet.next()) {
                int reizigerId = resultSet.getInt("reiziger_id");
                String voorletters = resultSet.getString("voorletters");
                String tussenvoegsel = resultSet.getString("tussenvoegsel");
                String achternaam = resultSet.getString("achternaam");
                Date geboortedatum = resultSet.getDate("geboortedatum");

                String fullName = voorletters + (tussenvoegsel != null ? " " + tussenvoegsel : "") + " " + achternaam;
                String formattedDate = dateFormat.format(geboortedatum);

                System.out.println("#" + reizigerId + ": " + fullName + " (" + formattedDate + ")");
                count++;
            }

            resultSet.close();
            statement.close();
            con.close();

            if (count == 0) {
                System.out.println("No reizigers found.");
            }
        } catch (SQLException e) {
            System.err.println("[SQLException] De reizigers konden niet worden opgehaald: " + e.getMessage());
        }
    }
}
