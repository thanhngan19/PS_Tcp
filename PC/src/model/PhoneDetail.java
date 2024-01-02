package model;

import java.util.List;

public class PhoneDetail {
    private int id;
    private VersionPhone ver;
    private ImportNote imPort;
    private ExportNote exPort;
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public ImportNote getImPort() {
        return imPort;
    }

    public void setImPort(ImportNote imPort) {
        this.imPort = imPort;
    }

    public ExportNote getExPort() {
        return exPort;
    }

    public void setExPort(ExportNote exPort) {
        this.exPort = exPort;
    }

    public PhoneDetail(int id, VersionPhone ver, ImportNote imPort, ExportNote exPort, int status) {
        this.id = id;
        this.ver = ver;
        this.imPort = imPort;
        this.exPort = exPort;
        this.status = status;
    }

    public PhoneDetail(int id, VersionPhone ver, ImportNote imPort, int status) {
        this.id = id;
        this.ver = ver;
        this.imPort = imPort;
        this.status = status;
    }

    public PhoneDetail() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public VersionPhone getVer() {
        return ver;
    }

    public void setVer(VersionPhone ver) {
        this.ver = ver;
    }
}
