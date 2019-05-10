package bean;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.lang.reflect.Constructor;
import java.util.List;

public class Search {

    private String keywords;
    private String type;
    private int page;

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

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List search(){
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");

        SInterface s = (SInterface) context.getBean(type);
        return s.search(keywords, page);
    }

}
