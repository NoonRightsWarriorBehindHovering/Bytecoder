/*
 * Copyright 2017 Mirko Sertic
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.mirkosertic.bytecoder.core;

import de.mirkosertic.bytecoder.core.test.UnitTestRunner;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.Array;

@RunWith(UnitTestRunner.class)
public class ArrayTest {

    public static class Entry {

        public int test;
    }

    public static class StaticInitHost {

        static float[][] floats = {
            {10f, 20f, 30f},
            {100f, 200f, 300f}
        };
    }

    public static class MemberWithArray {

        transient Object[] elementData;

        MemberWithArray() {
            elementData = new Object[10];
        }

        public Object testGetAndPut() {
            final Object[] theData = elementData;
            Object theResult = null;
            try {
                theResult = theData[1];
            } catch (final Exception e) {
                theResult = theData[2];
            } finally {
                theResult = theData[3];
            }
            return theResult;
        }
    }

    private static class Holder {
        private static final byte[] bytes = new byte[10];
        private static final short[] shorts = new short[10];
        private static final char[] chars = new char[10];
        private static final int[] ints = new int[10];
        private static final boolean[] booleans = new boolean[10];
        private static final float[] floats = new float[10];
        private static final Entry[] entries = new Entry[10];
        private static final long[] longs = new long[10];
        private static final double[] doubles = new double[10];
    }

    @Test
    public void testLength() {
        Assert.assertEquals(10, Holder.bytes.length, 0);
    }

    @Test
    public void testBytes() {
        Holder.bytes[4] = (byte) 10;
        Assert.assertEquals(10, Holder.bytes[4], 0);
        Assert.assertEquals(0, Holder.bytes[0], 0);
    }

    @Test
    public void testShorts() {
        Holder.shorts[4] = (short) 10;
        Assert.assertEquals(10, Holder.shorts[4], 0);
        Assert.assertEquals(0, Holder.shorts[0], 0);
    }

    @Test
    public void testChars() {
        Holder.chars[4] = (char) 10;
        Assert.assertEquals(10, Holder.chars[4], 0);
        Assert.assertEquals(0, Holder.chars[0], 0);
    }

    @Test
    public void testInts() {
        Holder.ints[4] = 10;
        Assert.assertEquals(10, Holder.ints[4], 0);
        Assert.assertEquals(0, Holder.ints[0], 0);
    }

    @Test
    public void testFloats() {
        Holder.floats[4] = (float) 10;
        Assert.assertEquals(10, Holder.floats[4], 0);
        Assert.assertEquals(0, Holder.floats[0], 0);
    }

    @Test
    public void testBooleans() {
        Holder.booleans[4] = true;
        Assert.assertTrue(Holder.booleans[4]);
        Assert.assertFalse(Holder.booleans[0]);
    }

    @Test
    public void testEntries() {
        final Entry theEntry = new Entry();
        Assert.assertNull(Holder.entries[0]);
        theEntry.test = 10;
        Holder.entries[4] = theEntry;
        Assert.assertEquals(10, Holder.entries[4].test, 0);

        final Object[] theEmpty = new Object[100];

        final Object theEntry0 = theEmpty[12];
        Assert.assertNull(theEntry0);
    }

    @Test
    public void testObjectArray() {
        final String theA = "AB";
        final String theC = "ABC";
        final String[] theStrings = new String[3];
        theStrings[0] = theA;
        theStrings[2] = theC;
        Assert.assertNotNull(theStrings[0]);
        Assert.assertNull(theStrings[1]);
        Assert.assertNotNull(theStrings[2]);

        Assert.assertSame(theA, theStrings[0]);
        Assert.assertSame(theC, theStrings[2]);
        Assert.assertEquals(2, theStrings[0].length(), 0);
        Assert.assertEquals(3, theStrings[2].length(), 0);
    }

    @Test
    public void testTypeAndLength() {
        final Object[] theOArray = new Object[12];
        Assert.assertEquals(12, theOArray.length, 0);
        final Object[] theOther = theOArray.clone();
        Assert.assertEquals(12, theOther.length, 0);
    }

    @Test
    public void testStaticMultiDimInit() {
        final float[][] theFloats = StaticInitHost.floats;
        Assert.assertEquals(2, theFloats.length, 0);
        Assert.assertEquals(3, theFloats[0].length, 0);
        Assert.assertEquals(3, theFloats[1].length, 0);
    }

    @Test
    @Ignore
    public void testReflectiveCreation() {
        final Object[] theArray = (Object[]) Array.newInstance(Object.class, 10);
        Assert.assertEquals(10, theArray.length, 0);
    }

    @Test
    public void testIndirectAccess() {
        final MemberWithArray theInstance = new MemberWithArray();
        theInstance.testGetAndPut();
    }

    @Test
    public void testLongs() {
        System.out.println("Starting test");
        Holder.longs[0] = 10L;
        Holder.longs[1] = 20L;
        System.out.println((int) Holder.longs[0]);
        System.out.println((int) Holder.longs[1]);
        Assert.assertEquals(10L, Holder.longs[0]);
        Assert.assertEquals(20L, Holder.longs[1]);
    }

    @Test
    public void testDoubles() {
        System.out.println("Starting test");
        Holder.doubles[0] = 10.5d;
        Holder.doubles[1] = 20.5d;
        Assert.assertEquals(10.5f, Holder.doubles[0], 0);
        Assert.assertEquals(20.5d, Holder.doubles[1], 0);
    }
}
