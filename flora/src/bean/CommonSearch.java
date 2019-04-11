package bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 普通搜索方式，只能通过特定或清晰的专有词搜索结果。
 * 例如某些植物名
 */
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
