package bean;

public class TreeNode {

    private String id;
    private String text;
    private String parent;

    public TreeNode(String id, String text, String parent) {
        this.id = id;
        this.text = text;
        this.parent = parent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }
}
