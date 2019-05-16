package com.halas.model;

import java.util.Objects;

public class Message {
    private String to;
    private String cc;
    private String bcc;
    private String subject;
    private String message;

    public Message() {
    }

    public Message(String to, String cc, String bcc, String subject, String message) {
        this.to = to;
        this.cc = cc;
        this.bcc = bcc;
        this.subject = subject;
        this.message = message;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getBcc() {
        return bcc;
    }

    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message1 = (Message) o;
        return Objects.equals(to, message1.to) &&
                Objects.equals(cc, message1.cc) &&
                Objects.equals(bcc, message1.bcc) &&
                Objects.equals(subject, message1.subject) &&
                Objects.equals(message, message1.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(to, cc, bcc, subject, message);
    }
}
