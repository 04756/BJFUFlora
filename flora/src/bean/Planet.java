package bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 植物类
 */
public class Planet {

    private String cName;//植物中文名
    private String eName;//植物英文名
    private String content;//植物介绍内容
    private List race;
    private List imglist;



    public Planet(){
        race = new ArrayList();
        imglist = new ArrayList();
    }

    public Planet(String cName, String eName, String content) {
        race = new ArrayList();
        imglist = new ArrayList();
        this.cName = cName;
        this.eName = eName;
        this.content = content;
    }

    public List getRace() {
        return race;
    }

    public void setRace(List race) {
        this.race = race;
    }

    public List getImglist() {
        return imglist;
    }

    public void setImglist(List imglist) {
        this.imglist = imglist;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static List getGrowing(String pName){
        List place = new ArrayList();
        place.add("广西");
        place.add("贵州");
        place.add("北京");
        return place;
    }

}
