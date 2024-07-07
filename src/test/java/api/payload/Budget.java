package api.payload;

public class Budget {
    private String name;
    private boolean active;
    private String notes;
    private String auto_budget_type;
    private String auto_budget_currency_id;
    private String auto_budget_currency_code;
    private String auto_budget_amount;
    private String auto_budget_period;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getAuto_budget_type() {
        return auto_budget_type;
    }

    public void setAuto_budget_type(String auto_budget_type) {
        this.auto_budget_type = auto_budget_type;
    }

    public String getAuto_budget_currency_id() {
        return auto_budget_currency_id;
    }

    public void setAuto_budget_currency_id(String auto_budget_currency_id) {
        this.auto_budget_currency_id = auto_budget_currency_id;
    }

    public String getAuto_budget_currency_code() {
        return auto_budget_currency_code;
    }

    public void setAuto_budget_currency_code(String auto_budget_currency_code) {
        this.auto_budget_currency_code = auto_budget_currency_code;
    }

    public String getAuto_budget_amount() {
        return auto_budget_amount;
    }

    public void setAuto_budget_amount(String auto_budget_amount) {
        this.auto_budget_amount = auto_budget_amount;
    }

    public String getAuto_budget_period() {
        return auto_budget_period;
    }

    public void setAuto_budget_period(String auto_budget_period) {
        this.auto_budget_period = auto_budget_period;
    }
}
