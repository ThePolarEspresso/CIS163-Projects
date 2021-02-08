package project1;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestStopWatch {

    /******************************************************************
     Test Default Constructor
     *******************************************************************/

    @Test
    public void Constructor_Default() {
        StopWatch s = new StopWatch();

        assertTrue(s.getMinutes() == 0);
        assertTrue(s.getSeconds() == 0);
        assertTrue(s.getMilliseconds() == 0);
    }

    /******************************************************************
     Test Constructor with String Parameter
     *******************************************************************/

    @Test (expected = IllegalArgumentException.class)
    public void BADConstructor_stringParam_noInput() { new StopWatch(""); }

    //BROKEN
//    @Test (expected = IllegalArgumentException.class)
//    public void BADConstructor_stringParam_null() { new StopWatch(null); }

    @Test (expected = IllegalArgumentException.class)
    public void BADConstructor_stringParam_excessColon() { new StopWatch(":"); }

    @Test (expected = IllegalArgumentException.class)
    public void BADConstructor_stringParam_excessRightColon() { new StopWatch("1:2:"); }

    @Test (expected = IllegalArgumentException.class)
    public void BADConstructor_stringParam_excessLeftColon() { new StopWatch(":1:2"); }

    @Test (expected = IllegalArgumentException.class)
    public void BADConstructor_stringParam_excessInput() { new StopWatch("3:3:3:3"); }

    @Test (expected = IllegalArgumentException.class)
    public void BADConstructor_stringParam_characters() { new StopWatch("!@#$%^&*()"); }

    @Test
    public void Constructor_stringParam_3Input() {
        StopWatch s = new StopWatch("1:2:3");

        assertTrue(s.getMinutes() == 1);
        assertTrue(s.getSeconds() == 2);
        assertTrue(s.getMilliseconds() == 3);
    }

    @Test (expected = IllegalArgumentException.class)
    public void BADConstructor_stringParam3Input_negMin() { StopWatch s = new StopWatch("-1:2:3"); }

    @Test (expected = IllegalArgumentException.class)
    public void BADConstructor_stringParam3Input_negMinSec()
    {
        StopWatch s = new StopWatch("-1:-2:3");
    }

    @Test (expected = IllegalArgumentException.class)
    public void BADConstructor_stringParam3Input_negMinMilli()
    {
        StopWatch s = new StopWatch("-1:2:-3");
    }

    @Test (expected = IllegalArgumentException.class)
    public void BADConstructor_stringParam3Input_negMinSecMilli()
    {
        StopWatch s = new StopWatch("-1:-2:-3");
    }

    @Test (expected = IllegalArgumentException.class)
    public void BADConstructor_stringParam3Input_negSec()
    {
        StopWatch s = new StopWatch("1:-2:3");
    }

    @Test (expected = IllegalArgumentException.class)
    public void BADConstructor_stringParam3Input_negSecMilli()
    {
        StopWatch s = new StopWatch("1:-2:-3");
    }

    @Test (expected = IllegalArgumentException.class)
    public void BADConstructor_stringParam3Input_negMilli()
    {
        StopWatch s = new StopWatch("1:2:-3");
    }

    @Test (expected = IllegalArgumentException.class)
    public void BADConstructor_stringParam3Input_excessSec()
    {
        StopWatch s = new StopWatch("1:60:3");
    }

    @Test (expected = IllegalArgumentException.class)
    public void BADConstructor_stringParam3Input_excessMilli()
    {
        StopWatch s = new StopWatch("1:2:1000");
    }

    @Test (expected = IllegalArgumentException.class)
    public void BADConstructor_stringParam3Input_excessSecMilli()
    {
        StopWatch s = new StopWatch("1:60:1000");
    }

    @Test (expected = IllegalArgumentException.class)
    public void BADConstructor_stringParam3Input_alpha() { new StopWatch("a:a:a"); }

    @Test
    public void Constructor_stringParam_2Input() {
        StopWatch s = new StopWatch("1:2");

        assertTrue(s.getMinutes() == 0);
        assertTrue(s.getSeconds() == 1);
        assertTrue(s.getMilliseconds() == 2);
    }

    @Test (expected = IllegalArgumentException.class)
    public void BADConstructor_stringParam2Input_negSec() {
        new StopWatch("-1:2");
    }

    @Test (expected = IllegalArgumentException.class)
    public void BADConstructor_stringParam2Input_negMilli() {
        new StopWatch("1:-2");
    }

    @Test (expected = IllegalArgumentException.class)
    public void BADConstructor_stringParam2Input_negSecMilli() {
        new StopWatch("-1:-2");
    }

    @Test (expected = IllegalArgumentException.class)
    public void BADConstructor_stringParam2Input_excessSec() {
        new StopWatch("60:2");
    }

    @Test (expected = IllegalArgumentException.class)
    public void BADConstructor_stringParam2Input_excessMilli() {
        new StopWatch("1:1000");
    }

    @Test (expected = IllegalArgumentException.class)
    public void BADConstructor_stringParam2Input_excessSecMilli() {
        new StopWatch("60:1000");
    }

    @Test (expected = IllegalArgumentException.class)
    public void BADConstructor_stringParam2Input_alpha() { new StopWatch("a:a"); }

    @Test
    public void Constructor_stringParam_1Input() {
        StopWatch s = new StopWatch("1");

        assertTrue(s.getMinutes() == 0);
        assertTrue(s.getSeconds() == 0);
        assertTrue(s.getMilliseconds() == 1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void BADConstructor_stringParam1Input_negSec() { new StopWatch("-1"); }

    @Test (expected = IllegalArgumentException.class)
    public void BADConstructor_stringParam1Input_excessSec() { new StopWatch("1000"); }

    @Test (expected = IllegalArgumentException.class)
    public void BADConstructor_stringParam1Input_alpha() { new StopWatch("a"); }

    /******************************************************************
     Test Constructor with Triple int Parameters
     *******************************************************************/

    @Test
    public void Constructor_3Param() {
        StopWatch s = new StopWatch(1,2,3);

        assertTrue(s.getMinutes() == 1);
        assertTrue(s.getSeconds() == 2);
        assertTrue(s.getMilliseconds() == 3);
    }

    @Test (expected = IllegalArgumentException.class)
    public void BADConstructor_3Param_negMin()
    {
        StopWatch s = new StopWatch(-1,2,3);
    }

    @Test (expected = IllegalArgumentException.class)
    public void BADConstructor_3Param_negMinSec()
    {
        StopWatch s = new StopWatch(-1,-2,3);
    }

    @Test (expected = IllegalArgumentException.class)
    public void BADConstructor_3Param_negMinMilli()
    {
        StopWatch s = new StopWatch(-1,2,-3);
    }

    @Test (expected = IllegalArgumentException.class)
    public void BADConstructor_3Param_negMinSecMilli()
    {
        StopWatch s = new StopWatch(-1,-2,-3);
    }

    @Test (expected = IllegalArgumentException.class)
    public void BADConstructor_3Param_negSec()
    {
        StopWatch s = new StopWatch(1,-2,3);
    }

    @Test (expected = IllegalArgumentException.class)
    public void BADConstructor_3Param_negSecMilli()
    {
        StopWatch s = new StopWatch(1,-2,-3);
    }

    @Test (expected = IllegalArgumentException.class)
    public void BADConstructor_3Param_negMilli()
    {
        StopWatch s = new StopWatch(1,2,-3);
    }

    @Test (expected = IllegalArgumentException.class)
    public void BADConstructor_3Param_excessSec() {
        StopWatch s = new StopWatch(1,60,3);
    }

    @Test (expected = IllegalArgumentException.class)
    public void BADConstructor_3Param_excessMilli() {
        StopWatch s = new StopWatch(1,2,1000);
    }

    @Test (expected = IllegalArgumentException.class)
    public void BADConstructor_3Param_excessSecMilli() {
        StopWatch s = new StopWatch(1,60,1000);
    }

    /******************************************************************
     Test Constructor with Double int Parameters
     *******************************************************************/

    @Test
    public void Constructor_2Param() {
        StopWatch s = new StopWatch(1, 2);

        assertTrue(s.getMinutes() == 0);
        assertTrue(s.getSeconds() == 1);
        assertTrue(s.getMilliseconds() == 2);
    }

    @Test (expected = IllegalArgumentException.class)
    public void BADConstructor_2Param_gegSec() {
        new StopWatch(-1, 2);
    }

    @Test (expected = IllegalArgumentException.class)
    public void BADConstructor_2Param_negMill() {
        new StopWatch(1, -2);
    }

    @Test (expected = IllegalArgumentException.class)
    public void BADConstructor_2Param_negSecMill() {
        new StopWatch(-1, -2);
    }

    @Test (expected = IllegalArgumentException.class)
    public void BADConstructor_2Param_excessSec() {
        new StopWatch(60, 2);
    }

    @Test (expected = IllegalArgumentException.class)
    public void BADConstructor_2Param_excessMill() {
        new StopWatch(1, 1000);
    }

    @Test (expected = IllegalArgumentException.class)
    public void BADConstructor_2Param_excessSecMill() {
        new StopWatch(60, 1000);
    }

    /******************************************************************
     Test Constructor with Single int Parameter
     *******************************************************************/

    @Test
    public void Constructor_1Param() {
        StopWatch s = new StopWatch(1);

        assertTrue(s.getMinutes() == 0);
        assertTrue(s.getSeconds() == 0);
        assertTrue(s.getMilliseconds() == 1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void BADConstructor_1Param_negMilli() {
        new StopWatch(-1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void BADConstructor_1Param_excessMilli() {
        new StopWatch(1000);
    }

    @Test (expected = IllegalArgumentException.class)
    public void BADConstructor_1Param_excessNegMilli() {
        new StopWatch(-1000);
    }

    /******************************************************************
     Test Constructor with Single StopWatch Parameter
     *******************************************************************/

    @Test
    public void Constructor_singleStopWatch() {
        StopWatch s1 = new StopWatch(1, 2, 3);
        StopWatch s2 = new StopWatch(s1);

        assertTrue(s2.getMinutes() == 1);
        assertTrue(s2.getSeconds() == 2);
        assertTrue(s2.getMilliseconds() == 3);
    }

    @Test (expected = IllegalArgumentException.class)
    public void BADConstructor_SWParam_null() {
        StopWatch s1 = null;
        StopWatch s2 = new StopWatch(s1);
    }

    /******************************************************************
     Test Equals with Double StopWatch Parameters
     *******************************************************************/

    @Test
    public void Equals_doubleStopWatch_equals() {
        StopWatch s1 = new StopWatch(1,2,3);
        StopWatch s2 = new StopWatch(1, 2, 3);

        assertTrue(StopWatch.equals(s1, s2));
        assertTrue(StopWatch.equals(s2, s1));
    }

    @Test
    public void Equals_doubleStopWatch_notEquals() {
        StopWatch s1 = new StopWatch(1,2,3);
        StopWatch s2 = new StopWatch(3, 2, 1);

        assertFalse(StopWatch.equals(s1, s2));
        assertFalse(StopWatch.equals(s2, s1));
    }

    @Test (expected = IllegalArgumentException.class)
    public void BADEquals_doubleStopWatch_1stNull () {
        StopWatch s = new StopWatch(1,2,3);
        StopWatch.equals(s, null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void BADEquals_doubleStopWatch_2ndNull () {
        StopWatch s = new StopWatch(1,2,3);
        StopWatch.equals(null, s);
    }

    @Test (expected = IllegalArgumentException.class)
    public void BADEquals_doubleStopWatch_doubleNull () {
        StopWatch.equals(null, null);
    }

    /******************************************************************
     Test Equals with Single Object Parameter
     *******************************************************************/

    @Test
    public void Equals_singleObject_equals() {
        StopWatch s1 = new StopWatch(1,2,3);
        StopWatch s2 = new StopWatch(1, 2, 3);

        assertTrue(s1.equals(s2));
        assertTrue(s2.equals(s1));
    }

    @Test
    public void Equals_singleObject_notEquals() {
        StopWatch s1 = new StopWatch(1,2,3);
        StopWatch s2 = new StopWatch(3, 2, 1);

        assertFalse(s1.equals(s2));
        assertFalse(s2.equals(s1));
    }

    @Test (expected = IllegalArgumentException.class)
    public void BADEquals_singleObject_null() {
        StopWatch s = new StopWatch(1,2,3);
        s.equals(null);
    }

    /******************************************************************
     Test compareTo with Single StopWatch Parameter
     *******************************************************************/

    @Test
    public void CompareTo_bigMin() {
        StopWatch s1 = new StopWatch(1, 1, 1);
        StopWatch s2 = new StopWatch(0, 1, 1);
        assertTrue(s1.compareTo(s2) > 0);
    }

    @Test
    public void CompareTo_bigSec() {
        StopWatch s1 = new StopWatch(1, 1, 1);
        StopWatch s2 = new StopWatch(1, 0, 1);
        assertTrue(s1.compareTo(s2) > 0);
    }

    @Test
    public void CompareTo_bigMilli() {
        StopWatch s1 = new StopWatch(1, 1, 1);
        StopWatch s2 = new StopWatch(1, 1, 0);
        assertTrue(s1.compareTo(s2) > 0);
    }

    @Test
    public void CompareTo_bigMin_reverse() {
        StopWatch s1 = new StopWatch(1, 1, 1);
        StopWatch s2 = new StopWatch(0, 1, 1);
        assertTrue(s2.compareTo(s1) < 0);
    }

    @Test
    public void CompareTo_bigSec_reverse() {
        StopWatch s1 = new StopWatch(1, 1, 1);
        StopWatch s2 = new StopWatch(1, 0, 1);
        assertTrue(s2.compareTo(s1) < 0);
    }

    @Test
    public void CompareTo_bigMilli_reverse() {
        StopWatch s1 = new StopWatch(1, 1, 1);
        StopWatch s2 = new StopWatch(1, 1, 0);
        assertTrue(s2.compareTo(s1) < 0);
    }

    @Test
    public void CompareTo_bigMilli_equal() {
        StopWatch s1 = new StopWatch(1, 1, 1);
        StopWatch s2 = new StopWatch(1, 1, 1);
        assertTrue(s2.compareTo(s1) == 0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void BADCompareTo_null() {
        StopWatch s1 = new StopWatch(1, 1, 1);
        s1.compareTo(null);
    }

    /******************************************************************
     Test (private) convertToMilli with StopWatch Parameter
     *******************************************************************/

    //Any way to test this?? BROKEN
//    @Test (expected = IllegalArgumentException.class)
//    public void convertToMilli_SWParam_null() {
//        StopWatch s1 = new StopWatch();
//        s1.add(null);
//    }
//
//    @Test (expected = IllegalArgumentException.class)
//    public void convertToMilli_SWParam_null2() {
//        StopWatch s1 = new StopWatch();
//        StopWatch s2 = null;
//        s1.add(s2);
//    }

    /******************************************************************
     Test add with Single int Parameter
     *******************************************************************/

    @Test
    public void add_intParam() {
        StopWatch.setSuspend(false);
        StopWatch s = new StopWatch(1, 2, 3);
        s.add(62003);

        assertTrue(s.getMinutes() == 2);
        assertTrue(s.getSeconds() == 4);
        assertTrue(s.getMilliseconds() == 6);
    }

    @Test (expected = IllegalArgumentException.class)
    public void BADadd_intParam_negInput() {
        StopWatch.setSuspend(false);
        StopWatch s = new StopWatch(1, 2, 3);
        s.add(-1);
    }

    /******************************************************************
     Test sub with Single int Parameter
     *******************************************************************/

    @Test
    public void sub_intParam() {
        StopWatch.setSuspend(false);
        StopWatch s = new StopWatch(1, 2, 3);
        s.sub(62003);

        assertTrue(s.getMinutes() == 0);
        assertTrue(s.getSeconds() == 0);
        assertTrue(s.getMilliseconds() == 0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void BADsub_intParam_negInput() {
        StopWatch.setSuspend(false);
        StopWatch s = new StopWatch(1, 2, 3);
        s.sub(-1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void BADsub_intParam_negRemainder() {
        StopWatch.setSuspend(false);
        StopWatch s = new StopWatch(0, 0, 1);
        s.sub(10);
    }

    /******************************************************************
     Test add with Single StopWatch Parameter
     *******************************************************************/

    @Test
    public void add_SWParam() {
        StopWatch.setSuspend(false);
        StopWatch s1 = new StopWatch(1, 2, 3);
        StopWatch s2 = new StopWatch(1, 2, 3);
        s1.add(s2);

        assertTrue(s1.getMinutes() == 2);
        assertTrue(s1.getSeconds() == 4);
        assertTrue(s1.getMilliseconds() == 6);
    }

    @Test (expected = IllegalArgumentException.class)
    public void BADadd_SWParam_null() {
        StopWatch.setSuspend(false);
        StopWatch s = new StopWatch(1, 2, 3);
        s.add(null);
    }

    /******************************************************************
     Test sub with Single StopWatch Parameter
     *******************************************************************/

    @Test
    public void sub_SWParam() {
        StopWatch.setSuspend(false);
        StopWatch s1 = new StopWatch(1, 2, 3);
        StopWatch s2 = new StopWatch(1, 2, 3);
        s1.sub(s2);

        assertTrue(s1.getMinutes() == 0);
        assertTrue(s1.getSeconds() == 0);
        assertTrue(s1.getMilliseconds() == 0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void BADsub_SWParam_negRemainder() {
        StopWatch.setSuspend(false);
        StopWatch s1 = new StopWatch(1, 2, 3);
        StopWatch s2 = new StopWatch(10, 2, 3);
        s1.sub(s2);
    }

    @Test (expected = IllegalArgumentException.class)
    public void BADsub_SWParam_null() {
        StopWatch.setSuspend(false);
        StopWatch s = new StopWatch(1, 2, 3);
        s.sub(null);
    }

    /******************************************************************
     Test inc with No Parameters
     *******************************************************************/

    @Test
    public void inc_noParam() {
        StopWatch.setSuspend(false);
        StopWatch s1 = new StopWatch(1, 2, 3);
        s1.inc();

        assertTrue(s1.getMinutes() == 1);
        assertTrue(s1.getSeconds() == 2);
        assertTrue(s1.getMilliseconds() == 4);
    }

    @Test
    public void inc_noParam_looped() {
        StopWatch.setSuspend(false);
        StopWatch s1 = new StopWatch(1, 2, 3);

        for (int i = 0; i < 10000; i++)
            s1.inc();

        assertTrue(s1.getMinutes() == 1);
        assertTrue(s1.getSeconds() == 12);
        assertTrue(s1.getMilliseconds() == 3);
    }

    /******************************************************************
     Test dec with No Parameters
     *******************************************************************/

    @Test
    public void dec_noParam() {
        StopWatch.setSuspend(false);
        StopWatch s = new StopWatch(1, 2, 3);
        s.dec();

        assertTrue(s.getMinutes() == 1);
        assertTrue(s.getSeconds() == 2);
        assertTrue(s.getMilliseconds() == 2);
    }

    @Test
    public void dec_noParam_looped() {
        StopWatch.setSuspend(false);
        StopWatch s = new StopWatch(1, 20, 3);

        for (int i = 0; i < 10000; i++)
            s.dec();

        assertTrue(s.getMinutes() == 1);
        assertTrue(s.getSeconds() == 10);
        assertTrue(s.getMilliseconds() == 3);
    }

    @Test (expected = IllegalArgumentException.class)
    public void dec_noParam_negRemainder() {
        StopWatch.setSuspend(false);
        StopWatch s = new StopWatch(0, 0, 0);
        s.dec();
    }

    /******************************************************************
     Test toString with No Parameters
     *******************************************************************/

    @Test
    public void toString_noParam() {
        StopWatch s = new StopWatch(1, 2, 3);
        assertEquals(s.toString(),"1:02:003");

        s = new StopWatch(10, 20, 30);
        assertEquals(s.toString(),"10:20:030");

        s = new StopWatch(100, 20, 300);
        assertEquals(s.toString(),"100:20:300");

        s = new StopWatch(0, 20, 300);
        assertEquals(s.toString(),"0:20:300");

        s = new StopWatch(0, 0, 300);
        assertEquals(s.toString(),"0:00:300");

        s = new StopWatch(0, 0, 0);
        assertEquals(s.toString(),"0:00:000");
    }

    /******************************************************************
     Test save & load with Single String Parameters
     *******************************************************************/

    @Test
    public void saveload_singleString() {
        StopWatch s1 = new StopWatch(1,2,3);
        StopWatch s2 = new StopWatch(1,2,3);

        s1.save("file1");
        s1 = new StopWatch();

        s1.load("file1");
        assertTrue (s1.equals(s2));
    }

    @Test (expected = IllegalArgumentException.class)
    public void save_singleString_noInput() {
        StopWatch s1 = new StopWatch(1,2,3);
        s1.save("");
    }

    @Test (expected = IllegalArgumentException.class)
    public void save_singleString_null() {
        StopWatch s1 = new StopWatch(1,2,3);
        s1.save(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void load_singleString_noInput() {
        StopWatch s1 = new StopWatch(5,59,300);
        s1.load("");
    }

    @Test (expected = IllegalArgumentException.class)
    public void load_singleString_null() {
        StopWatch s1 = new StopWatch(5,59,300);
        s1.load(null);
    }

    /******************************************************************
     Test setSuspend with Single boolean Parameter
     *******************************************************************/

    @Test
    public void setSuspended_boolParam_isTrue() {
        StopWatch.setSuspend(true);

        assertTrue(StopWatch.isSuspended() == true);
    }

    @Test
    public void setSuspended_boolParam_isFalse() {
        StopWatch.setSuspend(false);

        assertTrue(StopWatch.isSuspended() == false);
    }

    @Test
    public void setSuspended_boolParam_addintParam() {
        StopWatch.setSuspend(false);
        StopWatch s = new StopWatch(1, 2, 3);
        s.add(10000);

        assertTrue(s.getMinutes() == 1);
        assertTrue(s.getSeconds() == 12);
        assertTrue(s.getMilliseconds() == 3);

        StopWatch.setSuspend(true);
        s.add(10000);

        assertTrue(s.getMinutes() == 1);
        assertTrue(s.getSeconds() == 12);
        assertTrue(s.getMilliseconds() == 3);
    }

    @Test
    public void setSuspended_boolParam_addSWParam() {
        StopWatch.setSuspend(false);
        StopWatch s1 = new StopWatch(1, 2, 3);
        StopWatch s2 = new StopWatch(10, 20, 30);
        s1.add(s2);

        assertTrue(s1.getMinutes() == 11);
        assertTrue(s1.getSeconds() == 22);
        assertTrue(s1.getMilliseconds() == 33);

        StopWatch.setSuspend(true);
        s1.add(s2);

        assertTrue(s1.getMinutes() == 11);
        assertTrue(s1.getSeconds() == 22);
        assertTrue(s1.getMilliseconds() == 33);
    }

    @Test
    public void setSuspended_boolParam_subintParam() {
        StopWatch.setSuspend(false);
        StopWatch s = new StopWatch(1, 2, 3);
        s.sub(10000);

        assertTrue(s.getMinutes() == 0);
        assertTrue(s.getSeconds() == 52);
        assertTrue(s.getMilliseconds() == 3);

        StopWatch.setSuspend(true);
        s.sub(10000);

        assertTrue(s.getMinutes() == 0);
        assertTrue(s.getSeconds() == 52);
        assertTrue(s.getMilliseconds() == 3);
    }

    @Test
    public void setSuspended_boolParam_subSWParam() {
        StopWatch.setSuspend(false);
        StopWatch s1 = new StopWatch(10, 20, 30);
        StopWatch s2 = new StopWatch(1, 2, 3);
        s1.sub(s2);

        assertTrue(s1.getMinutes() == 9);
        assertTrue(s1.getSeconds() == 18);
        assertTrue(s1.getMilliseconds() == 27);

        StopWatch.setSuspend(true);
        s1.sub(s2);

        assertTrue(s1.getMinutes() == 9);
        assertTrue(s1.getSeconds() == 18);
        assertTrue(s1.getMilliseconds() == 27);
    }

    @Test
    public void setSuspend_boolParam_inc() {
        StopWatch.setSuspend(false);
        StopWatch s = new StopWatch(1, 2, 3);
        s.inc();

        assertTrue(s.getMinutes() == 1);
        assertTrue(s.getSeconds() == 2);
        assertTrue(s.getMilliseconds() == 4);

        StopWatch.setSuspend(true);
        s.inc();

        assertTrue(s.getMinutes() == 1);
        assertTrue(s.getSeconds() == 2);
        assertTrue(s.getMilliseconds() == 4);
    }

    @Test
    public void setSuspend_boolParam_dec() {
        StopWatch.setSuspend(false);
        StopWatch s = new StopWatch(1, 2, 3);
        s.dec();

        assertTrue(s.getMinutes() == 1);
        assertTrue(s.getSeconds() == 2);
        assertTrue(s.getMilliseconds() == 2);

        StopWatch.setSuspend(true);
        s.dec();

        assertTrue(s.getMinutes() == 1);
        assertTrue(s.getSeconds() == 2);
        assertTrue(s.getMilliseconds() == 2);
    }

    /******************************************************************
     Test setMinutes with Single int Parameter
     *******************************************************************/

    @Test
    public void setMinutes_1Param() {
        StopWatch s = new StopWatch();
        s.setMinutes(1);

        assertTrue(s.getMinutes() == 1);
        assertTrue(s.getSeconds() == 0);
        assertTrue(s.getMilliseconds() == 0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void BADsetMinutes_1Param_negMin() {
        StopWatch s = new StopWatch();
        s.setMinutes(-1);
    }

    /******************************************************************
     Test setSeconds with Single int Parameter
     *******************************************************************/

    @Test
    public void setSeconds_1Param() {
        StopWatch s = new StopWatch();
        s.setSeconds(1);

        assertTrue(s.getMinutes() == 0);
        assertTrue(s.getSeconds() == 1);
        assertTrue(s.getMilliseconds() == 0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void BADsetSeconds_1Param_negSec() {
        StopWatch s = new StopWatch();
        s.setSeconds(-1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void BADsetSeconds_1Param_excessSec() {
        StopWatch s = new StopWatch();
        s.setSeconds(60);
    }

    /******************************************************************
     Test setMilliseconds with Single int Parameter
     *******************************************************************/

    @Test
    public void setMilliseconds_1Param() {
        StopWatch s = new StopWatch();
        s.setMilliseconds(1);

        assertTrue(s.getMinutes() == 0);
        assertTrue(s.getSeconds() == 0);
        assertTrue(s.getMilliseconds() == 1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void BADsetMilliseconds_1Param_negMilli() {
        StopWatch s = new StopWatch();
        s.setMilliseconds(-1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void BADsetMilliseconds_1Param_excessMilli() {
        StopWatch s = new StopWatch();
        s.setMilliseconds(1000);
    }
}