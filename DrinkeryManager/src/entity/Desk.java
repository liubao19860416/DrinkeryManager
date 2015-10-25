package entity;

import java.sql.Date;
import java.sql.Time;

public class Desk {

    private int deskNo;
    private Date date;
    private Time time;

    public Desk() {
    }

    public Desk(int deskNo) {
        this.deskNo = deskNo;
    }

    public Desk(int deskNo, Date date, Time time) {
        super();
        this.deskNo = deskNo;
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Desk [" + deskNo + "," + time + "," + date + "]";
    }

    public int getDeskNo() {
        return deskNo;
    }

    public void setDeskNo(int deskNo) {
        this.deskNo = deskNo;
    }

    public Time getDeskTime() {
        return time;
    }

    public void setDeskTime(Time time) {
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((time == null) ? 0 : time.hashCode());
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + deskNo;
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (obj instanceof Desk) {
            Desk d = (Desk) obj;
            return true;
        }
        return true;
    }

}
