package bean;

import java.util.ArrayList;
import java.util.List;

public class CommonSearch implements SInterface{
    @Override
    public List<SearchResult> search(String keyWords) {
        List<SearchResult> temp = new ArrayList<SearchResult>();

        for (int i = 0; i < 5; ++i){
            temp.add(new SearchResult("Amon ssss", "planet/Amonssss"));
        }

        return temp;
    }

}
