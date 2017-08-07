package cl.daplay.jsurbtc.jackson;

import cl.daplay.jsurbtc.model.withdrawal.BitcoinWithdrawalData;
import cl.daplay.jsurbtc.model.withdrawal.FiatWithdrawalData;
import cl.daplay.jsurbtc.model.withdrawal.WithdrawalData;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DatabindContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.jsontype.impl.TypeIdResolverBase;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WithdrawalDataTypeIdResolver extends TypeIdResolverBase {

    private static final Map<String, Class<? extends WithdrawalData>> ID_2_CLASS = new HashMap<>();
    private static final Map<Class<? extends WithdrawalData>, String> CLASS_2_ID = new HashMap<>();

    static {
        ID_2_CLASS.put("fiat/withdrawal_data", FiatWithdrawalData.class);
        ID_2_CLASS.put("bitcoin_withdrawal_data", BitcoinWithdrawalData.class);

        CLASS_2_ID.put(FiatWithdrawalData.class, "fiat/withdrawal_data");
        CLASS_2_ID.put(BitcoinWithdrawalData.class, "bitcoin_withdrawal_data");
    }

    @Override
    public String idFromValue(Object value) {
        return CLASS_2_ID.get(value.getClass());
    }

    @Override
    public String idFromValueAndType(Object value, Class<?> suggestedType) {
        return idFromValue(value);
    }

    @Override
    public JsonTypeInfo.Id getMechanism() {
        return JsonTypeInfo.Id.CUSTOM;
    }

    @Override
    public JavaType typeFromId(DatabindContext context, String id) throws IOException {
        Class<?> clazz = ID_2_CLASS.get(id);
        return TypeFactory.defaultInstance().constructSimpleType(clazz, null);
    }
}
