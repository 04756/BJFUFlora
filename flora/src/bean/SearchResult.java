package bean;

public class SearchResult {

    private String resultName;
    private String resultLink;
    private String imageLink;

    public SearchResult(String resultName, String resultLink) {
        this.resultName = resultName;
        this.resultLink = resultLink;
    }

    public SearchResult(String resultName, String resultLink, String imageLink) {
        this.resultName = resultName;
        this.resultLink = resultLink;
        this.imageLink = imageLink;
    }

    public String getResultName() {
        return resultName;
    }

    public void setResultName(String resultName) {
        this.resultName = resultName;
    }

    public String getResultLink() {
        return resultLink;
    }

    public void setResultLink(String resultLink) {
        this.resultLink = resultLink;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
}
