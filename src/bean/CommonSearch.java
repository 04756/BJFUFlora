package bean;

import java.util.ArrayList;
import java.util.List;

public class CommonSearch extends Search{

    public List<SearchResult> search(){
//        return null;
        List<SearchResult> temp = new ArrayList<>();

        for (int i = 0; i < 5; ++i){
            temp.add(new SearchResult("Amon ssss", "/planet/Amonssss"));
        }

        return temp;
    }

}
