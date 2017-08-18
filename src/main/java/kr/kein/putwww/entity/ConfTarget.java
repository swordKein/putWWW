package kr.kein.putwww.entity;

import javax.persistence.*;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import static javax.persistence.GenerationType.*;


@Entity
@Table(name="conf_target")
public class ConfTarget {
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    @Id
    @GeneratedValue(strategy=IDENTITY)
    private long tg_id;

    @Column
    private String title;

    @Column
    private String descript;

    @Column
    private String tg_url;

    @Column
    private String tg_url_param1;

    @Column
    private String tg_url_param2;

    @Column
    private String tg_url_param3;

    @Column
    private Timestamp regdate;

    @Column
    private String regid;

    @Column
    private String param1;
    @Column
    private String param2;
    @Column
    private int param3;
    @Column
    private double param4;
    @Column
    private Timestamp param5;

    public ConfTarget() {}

    public ConfTarget(String title, String descript, String tg_url, Timestamp regdate, String regid) {
        this.title = title;
        this.descript = descript;
        this.tg_url = tg_url;
        this.regdate = regdate;
        this.regid = regid;
    }

    public long getTg_id() {
        return tg_id;
    }

    public void setTg_id(long tg_id) {
        this.tg_id = tg_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public String getTg_url() {
        return tg_url;
    }

    public void setTg_url(String tg_url) {
        this.tg_url = tg_url;
    }

    public String getTg_url_param1() {
        return tg_url_param1;
    }

    public void setTg_url_param1(String tg_url_param1) {
        this.tg_url_param1 = tg_url_param1;
    }

    public String getTg_url_param2() {
        return tg_url_param2;
    }

    public void setTg_url_param2(String tg_url_param2) {
        this.tg_url_param2 = tg_url_param2;
    }

    public String getTg_url_param3() {
        return tg_url_param3;
    }

    public void setTg_url_param3(String tg_url_param3) {
        this.tg_url_param3 = tg_url_param3;
    }

    public Timestamp getRegdate() {
        return regdate;
    }

    public void setRegdate(Timestamp regdate) {
        this.regdate = regdate;
    }

    public String getRegid() {
        return regid;
    }

    public void setRegid(String regid) {
        this.regid = regid;
    }

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public String getParam2() {
        return param2;
    }

    public void setParam2(String param2) {
        this.param2 = param2;
    }

    public int getParam3() {
        return param3;
    }

    public void setParam3(int param3) {
        this.param3 = param3;
    }

    public double getParam4() {
        return param4;
    }

    public void setParam4(double param4) {
        this.param4 = param4;
    }

    public Timestamp getParam5() {
        return param5;
    }

    public void setParam5(Timestamp param5) {
        this.param5 = param5;
    }
}