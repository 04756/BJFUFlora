package bean;

public class TreeNode {

    private String id;//植物id
    private String text;//植物名字
    private String parent;//植物父亲id（上级节点id）

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
