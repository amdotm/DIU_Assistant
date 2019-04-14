package free.mathsolution.com.diuassistant;

public class Model {

    String ans, qsn;

    public Model() {
    }

    public Model(String ans, String qsn) {
        this.ans = ans;
        this.qsn = qsn;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    public String getQsn() {
        return qsn;
    }

    public void setQsn(String qsn) {
        this.qsn = qsn;
    }
}
