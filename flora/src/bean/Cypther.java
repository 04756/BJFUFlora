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

    //返回传统生物学分类的根节点
    public static List racetraditionalData(){
        long startTime = System.currentTimeMillis();
        List arr = new ArrayList();
//        返回一个TreeNode的数组，js需要小改动
        try (Session session = db.getDriver().session()) {
            StatementResult result = session.run("match(n:Tradition) where apoc.node.degree.out(n,\"isSubClass\")=0 return n.name");
            while (result.hasNext()) {
                Record record = result.next();
                String son = record.get("n.name").asString();
                TreeNode t=new TreeNode(son,son,son);
                arr.add(t);
                System.out.println(son);
            }
        }
        long endTime = System.currentTimeMillis();    //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
        return arr;
    }

    public static List plantLocation(String plant){
        long startTime = System.currentTimeMillis();
        List arr = new ArrayList();
        try (Session session = db.getDriver().session()) {
            StatementResult result = session.run("match(n:Plant{name:\""+plant+"\"})-[r:located]->(m) return m.name");
            while (result.hasNext()) {
                Record record = result.next();
                String location = record.get("m.name").asString();
                arr.add(location);
                System.out.println(location);
            }
        }
        long endTime = System.currentTimeMillis();    //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
        return arr;
    }

    //返回花叶果根茎
    public static List raceuntraditionalData(){
        long startTime = System.currentTimeMillis();
        List arr = new ArrayList();
//        返回一个TreeNode的数组，js需要小改动
        try (Session session = db.getDriver().session()) {
            StatementResult result = session.run("match(n:Untradition) where apoc.node.degree.out(n,\"isSubClass\")=0 return n.name");
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
    public static List unTrachildData(String nodename){

        long startTime = System.currentTimeMillis();
        List arr = new ArrayList();
//        返回一个TreeNode的数组，js需要小改动

        try (Session session = db.getDriver().session()) {
            StatementResult result = session.run("match(n)-[r:isSubClass]->(m{name:\""+nodename+"\"})return n.name");
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

    public static List TraChildData(String nodename){
        long startTime = System.currentTimeMillis();
        List arr = new ArrayList();
//        返回一个TreeNode的数组，js需要小改动

        try (Session session = db.getDriver().session()) {
            StatementResult result = session.run("match(n)-[r:isSubClass]->(m{name:\""+nodename+"\"})return n.name");
            while (result.hasNext()) {
                Record record = result.next();
                String son = record.get("n.name").asString();
                TreeNode t=new TreeNode(son,son,nodename);
                arr.add(t);
                System.out.println(son+" "+nodename);
            }
        }
        if(arr.isEmpty()){
            System.out.println("是传统叶子节点");
            try (Session session = db.getDriver().session()) {
                StatementResult result = session.run("match(n:Plant)-[r:traditionalType]->(m:Tradition{name:\""+nodename+"\"}) return n.name");
                while (result.hasNext()) {
                    Record record = result.next();
                    String son = record.get("n.name").asString();
                    TreeNode t=new TreeNode(son,son,nodename);
                    arr.add(t);
                    System.out.println(son+" "+nodename);
                }
            }
        }

        long endTime = System.currentTimeMillis();    //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
        return arr;
    }

    public static void main(String[] arge){
        plantLocation("6. 醉鱼草状六道木（中国高等植物图鉴）图版30: 1-2");
    }
}
