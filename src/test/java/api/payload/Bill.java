package api.payload;

public class Bill {
    private String currency_id;
    private String currency_code;
    private String name;
    private String amount_min;
    private String amount_max;
    private String date;
    private String end_date;
    private String extension_date;
    private String repeat_freq;
    private int skip;
    private boolean active;
    private String notes;
    private String object_group_id;
    private String object_group_title;

    public String getCurrency_id() {
        return currency_id;
    }

    public void setCurrency_id(String currency_id) {
        this.currency_id = currency_id;
    }

    public String getCurrency_code() {
        return currency_code;
    }

    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount_min() {
        return amount_min;
    }

    public void setAmount_min(String amount_min) {
        this.amount_min = amount_min;
    }

    public String getAmount_max() {
        return amount_max;
    }

    public void setAmount_max(String amount_max) {
        this.amount_max = amount_max;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getExtension_date() {
        return extension_date;
    }

    public void setExtension_date(String extension_date) {
        this.extension_date = extension_date;
    }

    public String getRepeat_freq() {
        return repeat_freq;
    }

    public void setRepeat_freq(String repeat_freq) {
        this.repeat_freq = repeat_freq;
    }

    public int getSkip() {
        return skip;
    }

    public void setSkip(int skip) {
        this.skip = skip;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getObject_group_id() {
        return object_group_id;
    }

    public void setObject_group_id(String object_group_id) {
        this.object_group_id = object_group_id;
    }

    public String getObject_group_title() {
        return object_group_title;
    }

    public void setObject_group_title(String object_group_title) {
        this.object_group_title = object_group_title;
    }
}
