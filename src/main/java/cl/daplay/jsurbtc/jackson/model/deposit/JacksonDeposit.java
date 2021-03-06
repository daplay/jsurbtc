package cl.daplay.jsurbtc.jackson.model.deposit;

import cl.daplay.jsurbtc.jackson.model.JacksonAmount;
import cl.daplay.jsurbtc.model.Deposit;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class JacksonDeposit  implements Deposit, Serializable {

    private static final long serialVersionUID = 2017_10_22;

    @JsonProperty("id")
    private final long id;
    @JsonProperty("state")
    private final String state;
    @JsonProperty("currency")
    private final String currency;
    @JsonProperty("created_at")
    private final Instant createdAt;
    @JsonProperty("deposit_data")
    private final JacksonDepositData depositData;
    @JsonProperty("amount")
    private final JacksonAmount amount;
    @JsonProperty("fee")
    private final JacksonAmount fee;

    @JsonCreator
    public JacksonDeposit(@JsonProperty("id") final long id,
                          @JsonProperty("state") final String state,
                          @JsonProperty("currency") final String currency,
                          @JsonProperty("created_at") final Instant createdAt,
                          @JsonProperty("deposit_data") final JacksonDepositData depositData,
                          @JsonProperty("amount") final JacksonAmount amount,
                          @JsonProperty("fee") final JacksonAmount fee) {
        this.id = id;
        this.state = state;
        this.amount = amount;
        this.currency = currency;
        this.createdAt = createdAt;
        this.depositData = depositData;
        this.fee = fee;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getState() {
        return state;
    }

    @Override
    public BigDecimal getAmount() {
        return amount.getAmount();
    }

    @Override
    public String getAmountCurrency() {
        return amount.getCurrency();
    }

    @Override
    public String getCurrency() {
        return currency;
    }

    @Override
    public Instant getCreatedAt() {
        return createdAt;
    }

    @Override
    public JacksonDepositData getDepositData() {
        return depositData;
    }

    @Override
    public BigDecimal getFee() {
        return fee.getAmount();
    }

    @Override
    public String getFeeCurrency() {
        return fee.getCurrency();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final JacksonDeposit deposit = (JacksonDeposit) o;

        if (id != deposit.id) return false;
        if (state != null ? !state.equals(deposit.state) : deposit.state != null) return false;
        if (currency != null ? !currency.equals(deposit.currency) : deposit.currency != null) return false;
        if (createdAt != null ? !createdAt.equals(deposit.createdAt) : deposit.createdAt != null) return false;
        if (depositData != null ? !depositData.equals(deposit.depositData) : deposit.depositData != null) return false;
        if (amount != null ? !amount.equals(deposit.amount) : deposit.amount != null) return false;
        return fee != null ? fee.equals(deposit.fee) : deposit.fee == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (depositData != null ? depositData.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (fee != null ? fee.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Deposit{" +
                "id=" + id +
                ", state='" + state + '\'' +
                ", currency=" + currency +
                ", createdAt=" + createdAt +
                ", depositData=" + depositData +
                ", amount=" + amount +
                ", fee=" + fee +
                '}';
    }
}
