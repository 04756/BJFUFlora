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
        temp.add(new SearchResult("百山祖冷杉", "planet/百山祖冷杉"));
        temp.add(new SearchResult("相思子", "planet/百山祖冷杉"));
        temp.add(new SearchResult("广州相思子", "planet/百山祖冷杉"));
        temp.add(new SearchResult("美丽相思子", "planet/百山祖冷杉"));
        temp.add(new SearchResult("无齿华苘麻", "planet/百山祖冷杉"));
        temp.add(new SearchResult("尖叶相思", "planet/百山祖冷杉"));
        temp.add(new SearchResult("金合欢", "planet/百山祖冷杉"));
        temp.add(new SearchResult("阿拉伯金合欢", "planet/百山祖冷杉"));
        temp.add(new SearchResult("窄果脆兰", "planet/百山祖冷杉"));
        temp.add(new SearchResult("短序脆兰", "planet/百山祖冷杉"));

//        for (int i = 0; i < 5; ++i){
//            temp.add(new SearchResult("Amon ssss", "planet/Amonssss"));
//        }

        return temp;
    }

}
