package de.upb.crypto.math.random.interfaces;

import java.math.BigInteger;

/**
 * Collection of utility functions for random operations
 */
public abstract class RandomUtil {
    /**
     * Generate a random number from the set {0,...,l-1}
     *
     * @param rnd the randomness to be used
     * @param l   the upper bound excluded
     * @return a uniformly distributed number from the set {0,...,l-1}
     */
    public static BigInteger getRandomElement(RandomGenerator rnd, BigInteger l) {

        int n = l.subtract(BigInteger.ONE).bitLength();

        /*account for sign bit. BigIntegers are constructed with sign bit*/
        n++;

        int byte_length = n / 8;
        //round up to byte boundary
        if (0 != n % 8) {
            byte_length++;
        }

        /*zero out BigEndian  MSBs including reserved sign bit to get range 0...2^n-1*/
        byte mask = (byte) 0x7f;
        if (0 != n % 8) {
            mask = (byte) (mask >> (8 - (n % 8)));
        }

        BigInteger result;
        do {
            byte[] random_bytes = rnd.getRandomByteArray(byte_length);


            /* by always masking out sign bit, we get a positive number*/
            /*furthermore, by masking out MSB of Big Endian representation, we get an integer
             * in the range 0...2^(n-1)-1 */

            random_bytes[0] = (byte) (random_bytes[0] & mask);


            /*create big integer */
            result = new BigInteger(random_bytes);

            /*now check if we are smaller than l or in the range l..2^(n-1)-1*/
            /*discard the latter*/
        } while (result.compareTo(l) >= 0);

        return result;
    }

    /**
     * Generate a random number from the set {0,...,l-1}
     *
     * @param l the upper bound excluded
     * @return a uniformly distributed number from the set {0,...,l-1}
     */
    public static BigInteger getRandomElement(BigInteger l) {
        return getRandomElement(RandomGeneratorSupplier.getRnd(), l);
    }

    /**
     * Generate a (uniformly) random n-bit prime number from the interval [2^(n-1), 2^n-1]
     *
     * @param n desired number of bits for the prime number
     * @return a BigInteger that is probably prime
     */
    public static BigInteger getRandomPrime(RandomGenerator rnd, int n) {
        BigInteger lowerBound = BigInteger.ONE.shiftLeft(n - 1); // constant 2^(n-1)

        BigInteger candidate;
        do {
            candidate = getRandomElement(rnd, lowerBound).add(lowerBound); // => candidate in [0 + 2^(n-1), 2^(n-1)-1 + 2^(n-1)]
        } while (!candidate.isProbablePrime(10000)); // someone may want to tweak this constant

        return candidate;
    }

    /**
     * Generate a (uniformly) random n-bit prime number from the interval [2^(n-1), 2^n-1]
     *
     * @param n desired number of bits for the prime number
     * @return a BigInteger that is probably prime
     */
    public static BigInteger getRandomPrime(int n) {
        return getRandomPrime(RandomGeneratorSupplier.getRnd(), n);
    }
}
