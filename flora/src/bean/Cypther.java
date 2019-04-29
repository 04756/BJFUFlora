package bean;

import db.Neo4jDB;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;

import java.util.ArrayList;
import java.util.List;

public class Cypther {

    private static Neo4jDB db=new Neo4jDB();

    static{
        Neo4jDB.connectGraphDB();
    }
    //返回花叶果根茎
    public static List raceuntraditionalData(){
        long startTime = System.currentTimeMillis();
        List arr = new ArrayList();
//        返回一个TreeNode的数组，js需要小改动
        try (Session session = db.getDriver().session()) {
            StatementResult result = session.run("match(n)-[r:isSubClass]->(m)  where m.name=n.name return n.name");
            while (result.hasNext()) {
                Record record = result.next();
                String son = record.get("n.name").asString();
                TreeNode t=new TreeNode(son,son,son);
                arr.add(t);
            }
        }
        long endTime = System.currentTimeMillis();    //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
        return arr;
    }

    //返回传入节点的下一层
    public static List childData(String nodename){

        long startTime = System.currentTimeMillis();
        List arr = new ArrayList();
//        返回一个TreeNode的数组，js需要小改动

        try (Session session = db.getDriver().session()) {
            StatementResult result = session.run("match(n)-[r:isSubClass]->(m{name:\""+nodename+"\"}) where m.name<>n.name  return n.name");
            while (result.hasNext()) {
                Record record = result.next();
                String son = record.get("n.name").asString();
                TreeNode t=new TreeNode(son,son,nodename);
                arr.add(t);
                System.out.println(son+" "+nodename);
            }
        }

        long endTime = System.currentTimeMillis();    //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
        return arr;
    }

    public static void main(String[] arge){
        Cypther.raceuntraditionalData();
        Cypther.childData("花");
    }
}
