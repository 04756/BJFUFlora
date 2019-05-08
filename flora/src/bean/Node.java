package bean;

public class Node {
    private String name;
    private String e_name;
    private int status = 0;
    private int r;

    public Node(String name) {
        this.name = name;
        this.r = this.name.length() * 8;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getE_name() {
        return e_name;
    }

    public void setE_name(String e_name) {
        this.e_name = e_name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }
}
