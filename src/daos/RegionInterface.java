package daos;

import java.util.List;

import models.Region;

public interface RegionInterface {
  public Integer update(Region region);
  public Integer delete(int id);
  public boolean insert(Region region);
  public List<Region> get();
  public Region get(int regionId);
  // public Region getById(int regionId);
}
