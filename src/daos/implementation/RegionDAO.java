package daos.implementation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daos.RegionInterface;
import models.Region;

public class RegionDAO implements RegionInterface {
  private Connection connection;

  public RegionDAO(Connection connection) {
    this.connection = connection;
  }

  @Override
  public List<Region> get() {
    List<Region> regions = new ArrayList<>();
    String query = "SELECT * FROM regions";

    try {
      ResultSet resultSet = connection.prepareStatement(query).executeQuery();

      while (resultSet.next()) {
        Region region = new Region(resultSet.getInt("id"), resultSet.getString("name"));
        regions.add(region);
      }
    } catch (SQLException e) {
        e.getMessage();
    }
      return regions;
    }

    @Override
    public Region get(int id) {
      Region region = null;
      String query = "SELECT id, name FROM regions WHERE id = ?";
  
      try {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
          region = new Region(resultSet.getInt("id"), resultSet.getString("name"));
        }
      } catch (SQLException e) {
          e.getMessage();
      }
        return region;
      }

  @Override
  public boolean insert(Region region){
    try{
      PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO regions (id, name) VALUES (?,?)");
      preparedStatement.setInt(1, region.getId());
      preparedStatement.setString(2, region.getName());
      int count = preparedStatement.executeUpdate();
      System.out.println(count);
      return true;
    } catch(SQLException e){
      e.getMessage();
      System.out.println(e.getMessage());
    }
    return false;
  }

  @Override
  public Integer update(Region region){
    Region newRegion = get(region.getId());
    String query = "UPDATE regions SET name = ? WHERE id = ?";

    if (newRegion == null) {
      return 0;
    }

    try{
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(2, region.getId());
      preparedStatement.setString(1, region.getName());
      Integer count = preparedStatement.executeUpdate();
      return count;
      
    } catch (SQLException e) {
      e.getMessage();
    }
    return 0;
  }

  @Override
  public Integer delete(int regionId){
    String query = "DELETE FROM regions WHERE id = ?";
    Region region = get(regionId);

    if (region == null) {
      return 0;
    }

    try{
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1, region.getId());
      int count = preparedStatement.executeUpdate();
      return count;
    } catch (SQLException e) {
      e.getMessage();
    }
    return 0;
  }

  // public Region getById(int regionId){
  //     Region region = null;
  //     String query = "SELECT id, name FROM regions WHERE id = ?";

  //     try{
  //       PreparedStatement preparedStatement = connection.prepareStatement(query);
  //       preparedStatement.setInt(1, regionId);
        
  //       ResultSet resultSet = preparedStatement.executeQuery();
  //       if (resultSet.next()) {
  //         int id = resultSet.getInt("id");
  //         String name = resultSet.getString("name");
  //         region = new Region(id, name);
  //     }
  //     } catch (SQLException e) {
  //       e.getMessage();
  //     }
  //     return region;
  //   }
}

