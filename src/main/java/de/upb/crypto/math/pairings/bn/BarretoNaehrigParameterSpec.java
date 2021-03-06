package de.upb.crypto.math.pairings.bn;

import java.math.BigInteger;

/**
 * Represents parameters in a implementation independent way.
 *
 * @author peter
 */
public class BarretoNaehrigParameterSpec {
    public BigInteger characteristic;
    public BigInteger size;
    public BigInteger alpha;
    public BigInteger beta0;
    public BigInteger beta1;
    public BigInteger b;
    public BigInteger x1;
    public BigInteger y1;
    public BigInteger x20;
    public BigInteger x21;
    public BigInteger y20;
    public BigInteger y21;
    public String pairing;
    public String hash;

    public BarretoNaehrigParameterSpec(BigInteger characteristic, BigInteger size, BigInteger alpha, BigInteger beta0, BigInteger beta1, BigInteger b, BigInteger x1, BigInteger y1, BigInteger x20, BigInteger x21, BigInteger y20, BigInteger y21,
                                       String pairing, String hash) {
        super();
        this.characteristic = characteristic;
        this.size = size;
        this.alpha = alpha;
        this.beta0 = beta0;
        this.beta1 = beta1;
        this.b = b;
        this.x1 = x1;
        this.y1 = y1;
        this.x20 = x20;
        this.x21 = x21;
        this.y20 = y20;
        this.y21 = y21;
        this.pairing = pairing;
        this.hash = hash;
    }

    /*
     * "P1\":{\"x\":[\"<BigInteger>194afbc2e081e6d4327c167b1fd3399d88b5aa73e570101f34cc9db540d64ecefd8\"],\"y\":[\"<BigInteger>1\"],\"z\":[\"<BigInteger>1\"]},\
     * "P2\":{\"x\":[[\"<BigInteger>38216abeb4824dfaceaca25dfede4614e2c7577ff718277c7e9e246608b14e6ec7\"],[\"<BigInteger>1860c7978845fd8526d1f097096e8b8a0b0738785906bbf9aaf7bf5c4030ccf57c1\"]],
     *        \"y\":[[\"<BigInteger>169b69fadcb34eb34f1abfc928660086714afb9b016bba98e866223bfe2d5bac2d2\"],[\"<BigInteger>bf44311e7048ad5827f3ade3dc4c86655735a4ab8dd0c60671a79ee2aaf1bf2207\"]],\"z\":[[\"<BigInteger>1\"]]},
     *        \"alpha\":[\"<BigInteger>1\"],
     *        \"b\":[\"<BigInteger>e9e8726d4b33cd66913d4f313376a585cb983ee62a48809f4e04d39792c527842e\"],
     *        \"beta\":[[\"<BigInteger>2\"],[\"<BigInteger>1\"]],\"n\":\"<BigInteger>2400000000001d76ea000000090b16017b80013bcce1b6930dd02782f6b04f13265\",\"p\":\"<BigInteger>2400000000001d76ea000000090b16017d00013bcce1b73032502782f6c062b4d9b\",\"pairing\":\"de.upb.crypto.math.pairings.bn.BarretoNaehrigTatePairing\"}";
     *
     * The security parameter of the specified group is 128. Resulting is a bit length of the group order of at least 256.
     */
    public static BarretoNaehrigParameterSpec sfc256() {
        return new BarretoNaehrigParameterSpec(
                new BigInteger("2400000000001d76ea000000090b16017d00013bcce1b73032502782f6c062b4d9b", 16),
                new BigInteger("2400000000001d76ea000000090b16017b80013bcce1b6930dd02782f6b04f13265", 16),
                BigInteger.ONE,
                BigInteger.valueOf(2),
                BigInteger.ONE,
                new BigInteger("e9e8726d4b33cd66913d4f313376a585cb983ee62a48809f4e04d39792c527842e", 16),
                new BigInteger("194afbc2e081e6d4327c167b1fd3399d88b5aa73e570101f34cc9db540d64ecefd8", 16),
                BigInteger.ONE,
                new BigInteger("38216abeb4824dfaceaca25dfede4614e2c7577ff718277c7e9e246608b14e6ec7", 16),
                new BigInteger("1860c7978845fd8526d1f097096e8b8a0b0738785906bbf9aaf7bf5c4030ccf57c1", 16),
                new BigInteger("169b69fadcb34eb34f1abfc928660086714afb9b016bba98e866223bfe2d5bac2d2", 16),
                new BigInteger("bf44311e7048ad5827f3ade3dc4c86655735a4ab8dd0c60671a79ee2aaf1bf2207", 16),
                "Tate",
                "SHA-256"
        );
    }

    public static BarretoNaehrigParameterSpec getParameters(String spec) {
        if (spec.equals("SFC-256")) {
            return sfc256();
        }
        throw new IllegalArgumentException("Unknown cipher spec.");

    }

}
