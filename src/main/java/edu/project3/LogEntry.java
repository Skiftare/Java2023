package edu.project3;

import java.util.Date;

public class LogEntry {
    private String remoteAddr;
    private String remoteUser;
    private Date timeLocal;
    private String request;
    private int status;
    private int bodyBytesSent;
    private String httpReferer;
    private String httpUserAgent;

    public LogEntry() {
    }

    public String getRemoteAddr() {
        return remoteAddr;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }

    public String getRemoteUser() {
        return remoteUser;
    }

    public void setRemoteUser(String incomeUser) {
        this.remoteUser = incomeUser;
    }

    public Date getTimeLocal() {
        return timeLocal;
    }

    public void setTimeLocal(Date timeLocal) {
        this.timeLocal = timeLocal;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getBodyBytesSent() {
        return bodyBytesSent;
    }

    public void setBodyBytesSent(int bodyBytesSent) {
        this.bodyBytesSent = bodyBytesSent;
    }

    public void setHttpReferer(String group) {
        this.httpReferer = group;
    }

    public String getHttpReferer() {
        return this.httpReferer;
    }

    public void setHttpUserAgent(String group) {
        this.httpUserAgent = group;
    }

    public String getHttpUserAgent() {
        return this.httpUserAgent;
    }



}
