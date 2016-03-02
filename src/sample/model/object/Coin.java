package sample.model.object;

public class Coin {

    private int id;
    private int coinSetID;
    private String currency;
    private String nominal;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCoinSetID() {
        return coinSetID;
    }

    public void setCoinSetID(int coinSetID) {
        this.coinSetID = coinSetID;
    }
}
