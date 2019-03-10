package bean;

import org.springframework.context.annotation.Bean;

public class GraphSearch {

    private String keywords;

//    @Bean(name = "graphSearchBean")
//    public GraphSearch graphSearch() {
//        return new GraphSearch();
//    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public void check(){
        System.out.println("check");
    }

}
