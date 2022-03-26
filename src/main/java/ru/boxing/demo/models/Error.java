package ru.boxing.demo.models;

public class Error {
    public final boolean error;
    private String massage;

    public Error(String massage) {
        this.error = true;
        this.massage = massage;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }
}
