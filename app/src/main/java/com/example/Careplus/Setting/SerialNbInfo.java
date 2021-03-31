package com.example.Careplus.Setting;

/*고유번호 생성을 위한 맞춤객체 생성*/
public class SerialNbInfo {
    private String serialNb;

    public SerialNbInfo(String serialNb){
        this.serialNb = serialNb;

    }
    public String getSerialNb(){
        return this.serialNb;
    }
    public void setSerialNb(String serialNb){
        this.serialNb = serialNb;
    }
}
