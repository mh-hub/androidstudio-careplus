package com.example.Careplus.AddMedicin;

/*주기별 약추가를 위한 맞춤객체 생성*/
public class AddPeriodInfo {
    private String 약종류;
    private String 복용시작날짜;
    private String 복용주기;
    private String 약이름;
    private String 시간1;
    private String 시간2;
    private String 시간3;

    public AddPeriodInfo(String 약이름, String 약종류, String 복용시작날짜, String 복용주기, String 시간1, String 시간2, String 시간3){
        this.약종류 = 약종류;
        this.복용시작날짜 = 복용시작날짜;
        this.복용주기 = 복용주기;
        this.약이름 = 약이름;
        this.시간1 = 시간1;
        this.시간2 =시간2;
        this.시간3 =시간3;
    }

    public String get약종류(){
        return this.약종류;
    }
    public void set약종류(String 약종류){
        this.약종류 = 약종류;
    }

    public String get복용시작날짜(){
        return this.복용시작날짜;
    }
    public void set복용시작날짜(String 복용시작날짜){
        this.복용시작날짜 = 복용시작날짜;
    }

    public String get복용주기(){
        return this.복용주기;
    }
    public void set복용주기(String 복용주기){
        this.복용주기 = 복용주기;
    }


    public String get약이름(){
        return this.약이름;
    }
    public void set약이름(String 약이름){
        this.약이름 = 약이름;
    }

    public String get시간1(){
        return this.시간1;
    }
    public void set시간1(String 시간1){
        this.시간1 = 시간1;
    }

    public String get시간2(){ return this.시간2; }
    public void set시간2(String 시간2) { this.시간2 = 시간2; }

    public String get시간3(){ return this.시간3; }
    public void set시간3(String 시간3) { this.시간3 = 시간3; }

}
