package com.example.Careplus.AddMedicin;

/*요일별 약추가를 위한 맞춤객체 생성*/
public class AddDayInfo {
    private String 약종류;
    private String 복용요일;
    private String 약이름;
    private String 시간1;
    private String 시간2;
    private String 시간3;

    public AddDayInfo(String 약이름, String 약종류, String 복용요일, String 시간1, String 시간2, String 시간3){
        this.약종류 = 약종류;
        this.복용요일 = 복용요일;
        this.약이름 = 약이름;
        this.시간1 = 시간1;
        this.시간2 = 시간2;
        this.시간3 =시간3;
    }


    public String get약종류(){
        return this.약종류;
    }
    public void set약종류(String 약종류){
        this.약종류 = 약종류;
    }


    public String get복용요일(){
        return this.복용요일;
    }
    public void set복용요일(String 복용요일){
        this.복용요일 = 복용요일;
    }

    public String get약이름(){
        return this.약이름;
    }
    public void set약이름(String dayname){
        this.약이름 = 약이름;
    }


    public String get시간1(){
        return this.시간1;
    }
    public void set시간1(String 시간1){
        this.시간1 = 시간1;
    }

    public String get시간2(){
        return this.시간2;
    }
    public void set시간2(String 시간2){
        this.시간2 = 시간2;
    }

    public String get시간3(){
        return this.시간3;
    }
    public void set시간3(String 시간3) {
        this.시간3 = 시간3;
    }


}
