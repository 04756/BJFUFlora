package db;


import org.neo4j.driver.v1.*;


public class Neo4jDB {

    private static Driver driver;

    public static void connectGraphDB(){
        String uri = "bolt://localhost:7687";
        driver = GraphDatabase.driver(uri, AuthTokens.basic("neo4j", "1234"));

    }

    public static void close(){
        driver.close();
    }

    public Driver getDriver(){
        return driver;
    }

    public static void main(String[] args) {

        Neo4jDB.connectGraphDB();
        Neo4jDB db=new Neo4jDB();
        try (Session session = db.getDriver().session()) {
            StatementResult result = session.run("match(n)-[r:isSubClass]->(m) where n.name<>m.name return n.name,m.name");
            while (result.hasNext()) {
                Record record = result.next();
                String name = record.get("n.name").asString();
                String refer = record.get("m.name").asString();

                System.out.println(name + "的" + "父类" + "是" + refer);
            }

        Neo4jDB.close();
        }
    }


}
