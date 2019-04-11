package db;


import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;


public class Neo4jDB {

    private static Driver driver;

    public static void connectGraphDB(){
        driver = GraphDatabase.driver("b", AuthTokens.basic("",""));
    }

    public static void close(){
        driver.close();
    }



}
