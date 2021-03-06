package de.upb.crypto.math.standalone.test;

import de.upb.crypto.math.serialization.StandaloneRepresentable;

/**
 * Parameters for an execution of the standalone representable test The test
 * requires an instance of a standalone representable. Every class implementing
 * this interface should have a constructor that recreates an Representation
 * into an Object. By definition the recreated object and the provided object
 * should be the same (i.e. equals yields true).
 *
 * @author ---
 */
public class StandaloneTestParams {
    protected Class<? extends StandaloneRepresentable> toTest;
    protected Object instance;

    public StandaloneTestParams(Class<? extends StandaloneRepresentable> toTest, Object instance) {
        super();
        this.toTest = toTest;
        this.instance = instance;
    }

    public StandaloneTestParams(StandaloneRepresentable toTest) {
        this(toTest.getClass(), toTest);
    }

    @Override
    public String toString() {
        return toTest.getName();
    }
}
