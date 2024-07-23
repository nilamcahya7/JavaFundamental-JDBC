import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegionDAO {
  private Connection connection;

  public RegionDAO(Connection connection) {
    this.connection = connection;
  }

  public List<Region> getAll() {
    List<Region> regions = new ArrayList<>();
    String query = "SELECT * FROM regions";

    try {
      ResultSet resultSet = connection.prepareStatement(query).executeQuery();

      while (resultSet.next()) {
        Region region = new Region(resultSet.getInt("region_id"), resultSet.getString("region_name"));
        regions.add(region);
      }
    } catch (SQLException e) {
        e.getMessage();
    }
      return regions;
    }

    public Region getById(int regionId){
      Region region = null;
      String query = "SELECT region_id, region_name FROM regions WHERE region_id = ?";

      try{
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, regionId);
        
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
          int region_id = resultSet.getInt("region_id");
          String region_name = resultSet.getString("region_name");
          region = new Region(region_id, region_name);
      }
      } catch (SQLException e) {
        e.getMessage();
      }
      return region;
    }

  public boolean insert(Region region){
    try{
      PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO regions (region_id, region_name) VALUES (?,?)");
      preparedStatement.setInt(1, region.getRegion_id());
      preparedStatement.setString(2, region.getRegion_name());
      int count = preparedStatement.executeUpdate();
      System.out.println(count);
      return true;
    } catch(SQLException e){
      e.getMessage();
      System.out.println(e.getMessage());
    }
    return false;
  }

  public String update(int regionId, String new_region){
    Region region = this.getById(regionId);
    String query = "UPDATE regions SET region_name = ? WHERE region_id = ?";

    if (region == null) {
      return "Data tidak ditemukan";
    }

    try{
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(2, region.getRegion_id());
      preparedStatement.setString(1, new_region);
      int count = preparedStatement.executeUpdate();
      return ("Data berhasil di update " + count);
      
    } catch (SQLException e) {
      e.getMessage();
    }
    return "Data tidak ditemukan";
  }

  public String delete(int regionId){
    String query = "DELETE FROM regions WHERE region_id = ?";
    Region region = getById(regionId);

    if (region == null) {
      return "Data tidak ditemukan";
    }

    try{
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1, region.getRegion_id());
      int count = preparedStatement.executeUpdate();
      return ("Data berhasil di hapus" + count);
    } catch (SQLException e) {
      e.getMessage();
    }
    return "Data tidak ditemukan";
  }
}

