package task.darwinlabs;

/**
 * Created by SS0088 on 9/3/2017.
 */
public class TransactionHistoryDataModel {

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    String date,time,value;
}
