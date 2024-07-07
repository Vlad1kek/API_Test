package api.payload;

public class Account {
    private String name;
    private String iban;
    private String bic;
    private String account_number;
    private String opening_balance;
    private String opening_balance_date;
    private String virtual_balance;
    private String currency_id;
    private String currency_code;
    private boolean active;
    private int order;
    private boolean include_net_worth;
    private String account_role;
    private String credit_card_type;
    private String monthly_payment_date;
    private String liability_type;
    private String interest;
    private String interest_period;
    private String notes;
    private double latitude;
    private double longitude;
    private int zoom_level;
    private String type;

    public String getLiability_direction() {
        return liability_direction;
    }

    public void setLiability_direction(String liability_direction) {
        this.liability_direction = liability_direction;
    }

    private String liability_direction;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public String getOpening_balance() {
        return opening_balance;
    }

    public void setOpening_balance(String opening_balance) {
        this.opening_balance = opening_balance;
    }

    public String getOpening_balance_date() {
        return opening_balance_date;
    }

    public void setOpening_balance_date(String opening_balance_date) {
        this.opening_balance_date = opening_balance_date;
    }

    public String getVirtual_balance() {
        return virtual_balance;
    }

    public void setVirtual_balance(String virtual_balance) {
        this.virtual_balance = virtual_balance;
    }

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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public boolean isInclude_net_worth() {
        return include_net_worth;
    }

    public void setInclude_net_worth(boolean include_net_worth) {
        this.include_net_worth = include_net_worth;
    }

    public String getAccount_role() {
        return account_role;
    }

    public void setAccount_role(String account_role) {
        this.account_role = account_role;
    }

    public String getCredit_card_type() {
        return credit_card_type;
    }

    public void setCredit_card_type(String credit_card_type) {
        this.credit_card_type = credit_card_type;
    }

    public String getMonthly_payment_date() {
        return monthly_payment_date;
    }

    public void setMonthly_payment_date(String monthly_payment_date) {
        this.monthly_payment_date = monthly_payment_date;
    }

    public String getLiability_type() {
        return liability_type;
    }

    public void setLiability_type(String liability_type) {
        this.liability_type = liability_type;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getInterest_period() {
        return interest_period;
    }

    public void setInterest_period(String interest_period) {
        this.interest_period = interest_period;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getZoom_level() {
        return zoom_level;
    }

    public void setZoom_level(int zoom_level) {
        this.zoom_level = zoom_level;
    }
}
