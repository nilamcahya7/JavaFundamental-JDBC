package tools;
import daos.RegionInterface;
import daos.implementation.RegionDAO;
import models.Region;

public class App {
  public static void main(String[] args) throws Exception {
    DbConnection connection = new DbConnection();
    System.out.println(connection.getConnection());

    RegionDAO rdao = new RegionDAO(connection.getConnection());

    // //INSERT
    Region region1 = new Region(13, "Palembang");
    Region region2 = new Region(16, "Jakarta");
    System.out.println(rdao.insert(region1));
    System.out.println(rdao.insert(region2));

    //GET DATA ALL
    for(Region region3 : rdao.get()){
      System.out.println(region3.getId()+ " " + region3.getName());
    }

    //GET DATA BY ID
    Region region4 = rdao.get(15);
    System.out.println(region4.getId() + " " + region4.getName());

    //UPDATE DATA
    Region region5 = new Region(13, "Belanda");
    System.out.println("Affected Row(s) = " + rdao.update(region5));

    //DELETE DATA
    System.out.println("Affected Row(s) = "  + rdao.delete(15));


    System.out.println("--------------USE INTERFACE----------------");

    RegionInterface rdao2 = new RegionDAO(connection.getConnection());
    Region region6 = rdao2.get(14);
    System.out.println(region6.getName());

    for(Region region7 : rdao2.get()){
      System.out.println(region7.getId()+ " " + region7.getName());
    }
  }
}
