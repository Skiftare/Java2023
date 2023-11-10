package edu.hw6;

public class PortDescription {
    private String port;
    private String description;
    private String protocol;
    private Integer isOpened;



    public PortDescription(String port, String description, String protocol, int flag) {
        this.port = port;
        this.description = description;
        this.protocol = protocol;
        this.isOpened = flag;

    }

    public String getPort() {
        return port;
    }

    public String getDescription() {
        return description;
    }

    public String getProtocol() {
        return protocol;
    }
}
