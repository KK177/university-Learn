import java.io.Serializable;

public class MessageInfo implements Serializable {
    private String from; //发送者
    private String to; //接收者
    private int type; //消息类型
    private String info; //消息内容

    public MessageInfo() {

    }

    public MessageInfo(String from, String to, int type, String info) {
        this.from = from;
        this.to = to;
        this.type = type;
        this.info = info;
    }

    public String MessageInfo() {
        return "Message{" +
                "from='" + from +'\'' +
                ", to='" + to + '\'' +
                ", type=" +type +
                ", info='" + info + '\'' +
                '}';
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
