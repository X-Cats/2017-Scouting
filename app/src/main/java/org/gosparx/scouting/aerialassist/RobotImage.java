package org.gosparx.scouting.aerialassist;

public class RobotImage {
    private String file_name = "";
    private String blob = "";

    public RobotImage(String file_name, String blob) {
        this.file_name = file_name;
        this.blob = blob;
    }

    public String getFileName() {
        return file_name;
    }

    public void setFileName(String file_name) {
        this.file_name = file_name;
    }

    public String getBlob() {
        return blob;
    }

    public void setBlob(String blob) {
        this.blob = blob;
    }
}
