package cl.daplay.jsurbtc.jackson.dto;

import cl.daplay.jsurbtc.model.Ticker;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public final class TickerDTO {

    @JsonProperty("ticker")
    private final Ticker ticker;

    @JsonCreator
    public TickerDTO(@JsonProperty("ticker") Ticker ticker) {
        this.ticker = ticker;
    }

    public Ticker getTicker() {
        return ticker;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TickerDTO tickerDTO = (TickerDTO) o;

        return ticker.equals(tickerDTO.ticker);
    }

    @Override
    public int hashCode() {
        return ticker.hashCode();
    }
}