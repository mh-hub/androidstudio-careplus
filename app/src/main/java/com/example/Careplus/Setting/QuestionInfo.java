package com.example.Careplus.Setting;

/*문의내역 저장을 위한 맞춤객체 생성*/
public class QuestionInfo {
    private String name;
    private String email;
    private String title;
    private String content;
    private String spinner_question;

    public QuestionInfo(String name, String email, String title, String content, String spinner_question){
        this.name = name;
        this.email = email;
        this.title = title;
        this.content = content;
        this.spinner_question =spinner_question;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getspinner_question(){
        return this.spinner_question;
    }
    public void setSpinner_question(String spinner_question){
        this.spinner_question = spinner_question;
    }

    public String getTitle(){
        return this.title;
    }
    public void setTitle(String title){
        this.title = title;
    }

    public String getContent(){
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
