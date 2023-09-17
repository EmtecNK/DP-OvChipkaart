package P.P3;

import P.P2.Reiziger;

import java.util.List;

public interface ReizigerDAO {
    boolean save(P.P2.Reiziger reiziger);
    boolean update(P.P2.Reiziger reiziger);
    boolean delete(P.P2.Reiziger reiziger);
    P.P2.Reiziger findById(int id);
    List<Reiziger> findAll();
}
