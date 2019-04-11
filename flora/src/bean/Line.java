package bean;

/**
 * 图谱可视化中的连线类，用以定义节点之间的连线
 */
public class Line {
    private String source;//线段起始点id，在图谱可视化中为植物的名字
    private String target;//线段终点id，在图谱可视化中为植物的名字
    private String text;//线段文字介绍

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
