package P.P3;

import P.P2.Reiziger;
import P.P2.ReizigerDAO;
import P.P2.ReizigerDAOPsql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main {
    private static void testReizigerDAO(ReizigerDAO rdao) throws SQLException {
        System.out.println("\n---------- Test ReizigerDAO -------------");

        // Haal alle reizigers op uit de database
        List<Reiziger> reizigers = rdao.findAll();
        System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers:");
        for (Reiziger r : reizigers) {
            System.out.println(r);
        }
        System.out.println();

        // Maak een nieuwe reiziger aan en persisteer deze in de database
        String gbdatum = "1981-03-14";
        Reiziger sietske = new Reiziger(77, "S", "", "Boers", java.sql.Date.valueOf(gbdatum).toLocalDate());
        System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.save() ");
        rdao.save(sietske);
        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + " reizigers\n");

        // Update de reiziger en persisteer deze in de database
        System.out.println("[Test] Opgehaalde reiziger met ID 77");
        sietske.setAchternaam("Bakker");
        rdao.update(sietske);
        Reiziger updatedReiziger = rdao.findById(77);
        System.out.println("Updated reiziger: " + updatedReiziger);
        System.out.println();

        // Zoek een reiziger op met de id
        System.out.println("[Test] Retrieving reiziger by ID 77");
        Reiziger fetchedReiziger = rdao.findById(77);
        System.out.println("Opgehaalde reiziger: " + fetchedReiziger);
        System.out.println();

        // verwijder een reiziger uit de database
        System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.delete() ");
        rdao.delete(sietske);
        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + " reizigers\n");
    }

    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost/ovchip?user=postgres&password=fifaUT15");
        ReizigerDAO rdao = new ReizigerDAOPsql(connection);
        testReizigerDAO(rdao);
    }
}
