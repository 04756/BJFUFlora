package bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 基于数据文本挖掘以及分析，和图数据库作为数据载体
 * 用户可键入模糊词语，系统通过联想相关节点获取结果
 * 例如圆形叶子 红色果实
 */
public class GraphSearch implements SInterface{

    @Override
    public List<SearchResult> search(String keyWords){
//        return null;
        List<SearchResult> temp = new ArrayList<>();

        for (int i = 0; i < 5; ++i){
            temp.add(new SearchResult("Amon ssss", "planet/Amonssss"));
        }

        return temp;
    }

}
