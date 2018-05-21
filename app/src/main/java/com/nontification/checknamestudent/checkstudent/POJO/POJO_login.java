package com.nontification.checknamestudent.checkstudent.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class POJO_login {

    @SerializedName("member_id")
    @Expose
    private String memberId;
    @SerializedName("member_firstname")
    @Expose
    private String memberFirstname;
    @SerializedName("member_lastname")
    @Expose
    private String memberLastname;
    @SerializedName("member_type")
    @Expose
    private String memberType;
    @SerializedName("term_num")
    @Expose
    private String termNum;
    @SerializedName("term_year")
    @Expose
    private String termYear;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberFirstname() {
        return memberFirstname;
    }

    public void setMemberFirstname(String memberFirstname) {
        this.memberFirstname = memberFirstname;
    }

    public String getMemberLastname() {
        return memberLastname;
    }

    public void setMemberLastname(String memberLastname) {
        this.memberLastname = memberLastname;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public String getTermNum() {
        return termNum;
    }

    public void setTermNum(String termNum) {
        this.termNum = termNum;
    }

    public String getTermYear() {
        return termYear;
    }

    public void setTermYear(String termYear) {
        this.termYear = termYear;
    }
}
