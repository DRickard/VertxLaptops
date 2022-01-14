package edu.ucla.drickard.laptops;

import io.vertx.core.json.JsonObject;

public class AvailableItems
{
  private String location;
  private int chromeBooks;
  private int macs;
  private int windows;
  private int iPads;

  public AvailableItems()
  {
    super();
  }

  public AvailableItems(JsonObject json)
  {
    super();
    this.location = json.getString("loc");
    this.chromeBooks = json.getInteger("chromebooks_in");
    this.macs = json.getInteger("mac_laptops_in");
    this.windows = json.getInteger("win_laptops_in");
    this.iPads = json.getInteger("ipads_in");
  }

  public AvailableItems(String location,int chromeBooks,int macs,int windows,int iPads)
  {
    super();
    this.location = location;
    this.chromeBooks = chromeBooks;
    this.macs = macs;
    this.windows = windows;
    this.iPads = iPads;
  }

  public void setLocation( String location )
  {
    this.location = location;
  }

  public String getLocation()
  {
    return location;
  }

  public void setChromeBooks( int chromeBooks )
  {
    this.chromeBooks = chromeBooks;
  }

  public int getChromeBooks()
  {
    return chromeBooks;
  }

  public void setMacs( int macs )
  {
    this.macs = macs;
  }

  public int getMacs()
  {
    return macs;
  }

  public void setWindows( int windows )
  {
    this.windows = windows;
  }

  public int getWindows()
  {
    return windows;
  }

  public void setIPads( int iPads )
  {
    this.iPads = iPads;
  }

  public int getIPads()
  {
    return iPads;
  }
}
