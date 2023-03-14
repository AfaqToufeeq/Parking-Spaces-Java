package app.developer.parkingspaces.dataclass;

public class CityArea {
    String areaID="",areaName="",areaUrl="",description="",uid="";

    public CityArea(){}

    public CityArea(String areaID, String areaName, String areaUrl, String description, String uid) {
        this.areaID = areaID;
        this.areaName = areaName;
        this.areaUrl = areaUrl;
        this.description = description;
        this.uid = uid;
    }

    public String getAreaID() {
        return areaID;
    }

    public void setAreaID(String areaID) {
        this.areaID = areaID;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaUrl() {
        return areaUrl;
    }

    public void setAreaUrl(String areaUrl) {
        this.areaUrl = areaUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
