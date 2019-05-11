package bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 基于数据文本挖掘以及分析，和图数据库作为数据载体
 * 用户可键入模糊词语，系统通过联想相关节点获取结果
 * 例如圆形叶子 红色果实
 */
public class GraphSearch implements SInterface{

    @Override
    public List<SearchResult> search(String keyWords, int page){
//        return null;
        Cypther cypther=new Cypther();
        try {
            return cypther.graphSearch(keyWords,page);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
