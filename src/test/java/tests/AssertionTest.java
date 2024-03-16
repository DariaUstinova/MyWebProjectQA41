package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AssertionTest {

   public static void main(String [] args){
       Assert.assertEquals(myCalc(5,5), 10);
   }

    public static int myCalc(int a, int b){
        return a+b;
    }
    public static boolean myValue(){
       return true;
    }
    @Test
    public void testCalc(){
//       Assert.assertEquals(myCalc(5,5),10);
     //  Assert.assertTrue(myValue());
        Assert.assertThrows(ArithmeticException.class, ()->myTest() );
    }
    @Test
    public void testDevideByZero(){

       Assert.assertThrows(ArithmeticException.class, ()->myTest());
   }
    /**
     * Таким образом, этот тест проверяет, что при выполнении метода myTest() действительно выбрасывается исключение
     * ArithmeticException, что является ожидаемым поведением в случае деления на ноль.
     */
    @Test
    public void testDivideByZero() {
        Assert.assertThrows(ArithmeticException.class, new Assert.ThrowingRunnable() {
            @Override
            public void run() {
                myTest();
            }
        });
    }

    public static int myTest(){
       return 10/0;
    }
    @Test
    public void failTest(){
       int actualResult = someFunction();
       int expectedResult = 10;
       Assert.assertEquals(actualResult, expectedResult,"my comment");
       Assert.fail("The test is failed");
    }
    public static int someFunction(){
       return 5;
    }



}

