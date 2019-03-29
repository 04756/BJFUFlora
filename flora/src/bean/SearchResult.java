package bean;

public class SearchResult {

    private String resultName;
    private String resultLink;

    public SearchResult(String resultName, String resultLink) {
        this.resultName = resultName;
        this.resultLink = resultLink;
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
}
