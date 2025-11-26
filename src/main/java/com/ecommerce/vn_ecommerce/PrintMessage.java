package com.ecommerce.vn_ecommerce;

public class PrintMessage {

    private String message;

    public PrintMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "PrintMessage{" +
                "message='" + message + '\'' +
                '}';
    }
}
