package cl.daplay.jsurbtc;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public final class Signer {

    private static final String HMAC_SHA384_ALGORITHM = "HmacSHA384";

    private final String secret;

    public Signer(final String secret) {
        this.secret = secret;
    }

    public String sign(final String body,
                       final String method,
                       final String path,
                       final long nonce) throws InvalidKeyException, NoSuchAlgorithmException {
        final String message = buildMessage(body, method, path, nonce);


        System.out.printf("signing...%n");
        System.out.printf("- body='%s'%n", body);
        System.out.printf("- method='%s'%n", method);
        System.out.printf("- path='%s'%n", path);
        System.out.printf("- nonce='%s'%n", nonce);

        return sign(message);
    }

    private String sign(final String message) throws NoSuchAlgorithmException, InvalidKeyException {
        // final byte[] bytes = HmacUtils.hmacSha384(secret.getBytes(), message.getBytes());
        // return String.format("%040x", new BigInteger(1, bytes));

        final Key signingKey = new SecretKeySpec(secret.getBytes(), HMAC_SHA384_ALGORITHM);
        final Mac mac = Mac.getInstance(HMAC_SHA384_ALGORITHM);

        mac.init(signingKey);

        byte[] rawHmac = mac.doFinal(message.getBytes());
        return Hex.encodeHexString(rawHmac);
    }

    private String buildMessage(final String body,
                                final String method,
                                final String path,
                                final long nonce) {
        final boolean containsBody = !body.isEmpty();

        if (containsBody) {
            final String base64EncodedBody = Base64.getEncoder().encodeToString(body.getBytes());
            return String.format("%s %s %s %d", method, path, base64EncodedBody, nonce);
        } else {
            return String.format("%s %s %d", method, path, nonce);
        }
    }


}
