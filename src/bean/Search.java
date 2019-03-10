package bean;

import java.lang.reflect.Constructor;
import java.util.List;

public abstract class Search {

    protected  String keywords;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String  getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public List<SearchResult> search(){
        switch (type){
            case "CommonSearch" :
                return new CommonSearch().search(keywords);
            case "GraphSearch" :
                return new GraphSearch().search(keywords);
            default:
                return null;
        }
    }

}
