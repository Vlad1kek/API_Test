package api.payload;

public class Attachment {
    private String filename;
    private String attachable_type;
    private String attachable_id;
    private String title;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getAttachable_type() {
        return attachable_type;
    }

    public void setAttachable_type(String attachable_type) {
        this.attachable_type = attachable_type;
    }

    public String getAttachable_id() {
        return attachable_id;
    }

    public void setAttachable_id(String attachable_id) {
        this.attachable_id = attachable_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    private String notes;
}
