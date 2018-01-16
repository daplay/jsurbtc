package cl.daplay.jsurbtc.jackson.model.withdrawal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BitcoinWithdrawalData extends WithdrawalData {

    private static final long serialVersionUID = 2017_08_06;

    @JsonProperty("target_address")
    private final String targetAddress;
    @JsonProperty("tx_hash")
    private final String txHash;

    @JsonCreator
    public BitcoinWithdrawalData(@JsonProperty("type") String type,
                                 @JsonProperty("id") String targetAddress,
                                 @JsonProperty("created_at") String txHash){
        super();
        this.targetAddress = targetAddress;
        this.txHash = txHash;
    }

    public String getTargetAddress() {
        return targetAddress;
    }

    public String getTxHash() {
        return txHash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BitcoinWithdrawalData that = (BitcoinWithdrawalData) o;

        if (targetAddress != null ? !targetAddress.equals(that.targetAddress) : that.targetAddress != null)
            return false;
        return txHash != null ? txHash.equals(that.txHash) : that.txHash == null;
    }

    @Override
    public int hashCode() {
        int result = targetAddress != null ? targetAddress.hashCode() : 0;
        result = 31 * result + (txHash != null ? txHash.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BitcoinWithdrawalData{" +
                "targetAddress='" + targetAddress + '\'' +
                ", txHash='" + txHash + '\'' +
                '}';
    }
}