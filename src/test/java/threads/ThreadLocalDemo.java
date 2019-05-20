package threads;

import org.testng.annotations.Test;

/*
*
* Implementing parallel execution using ThreadLocal Class,
* keeping individual instance of WebDriver for each thread (thread safe)
*
* */

public class ThreadLocalDemo {

    @Test
    public void testMethod1() {
        invokeBrowser("http://www.ndtv.com");
    }

    @Test
    public void testMethod2() {
        invokeBrowser("http://www.facebook.com");

    }

    private void invokeBrowser(String url) {
        System.out.println("Thread id = " + Thread.currentThread().getId());
        System.out.println("Hashcode of webDriver instance = " + LocalDriverManager.getDriver().hashCode());
        LocalDriverManager.getDriver().get(url);

    }
}