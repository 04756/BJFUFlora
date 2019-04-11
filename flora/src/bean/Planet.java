package bean;

import java.util.List;

/**
 * 植物类
 */
public class Planet {

    private String cName;//植物中文名
    private String eName;//植物英文名
    private String content;//植物介绍内容

    public Planet(String cName, String eName, String content) {
        this.cName = cName;
        this.eName = eName;
        this.content = content;
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
        return null;
    }

}
