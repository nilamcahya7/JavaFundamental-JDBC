public class App {
  public static void main(String[] args) throws Exception {
    DbConnection connection = new DbConnection();
    System.out.println(connection.getConnection());

    RegionDAO rdao = new RegionDAO(connection.getConnection());
    // for(Region region : rdao.getAll()){
    //   System.out.println(region.getRegion_id());
    //   System.out.println(region.getRegion_name());
    // }

    // Region region = rdao.getById(12);
    // System.out.println(region.getRegion_name());

    // System.out.println(rdao.update(1, "Bengkulu"));
    System.out.println(rdao.delete(13));

    // System.out.println(region);
    // Region region1 = new Region(12, "Palembang");
    // Region region2 = new Region(13, "Jakarta");
    //   System.out.println(rdao.insert(region1));
    //   System.out.println(rdao.insert(region2));
  }
}
