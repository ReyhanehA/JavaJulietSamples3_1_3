/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE259_Hard_Coded_Password__kerberosKey_45.java
Label Definition File: CWE259_Hard_Coded_Password.label.xml
Template File: sources-sink-45.tmpl.java
*/
/*
 * @description
 * CWE: 259 Hard Coded Password
 * BadSource: hardcodedPassword Hardcoded password in String
 * GoodSource: Password is read from stdin
 * Sinks: kerberosKey
 *    BadSink : password used in Kerberos exchange
 * Flow Variant: 45 Data flow: data passed as a private class member variable from one function to another in the same class
 *
 * */

package testcases.CWE259_Hard_Coded_Password;

import testcasesupport.*;

import java.sql.*;
import javax.servlet.http.*;
import java.util.Properties;
import java.io.*;

import javax.security.auth.kerberos.KerberosPrincipal;
import javax.security.auth.kerberos.KerberosKey;

public class CWE259_Hard_Coded_Password__kerberosKey_45 extends AbstractTestCase
{

    private String bad_data;
    private String goodG2B_data;

    private void bad_sink() throws Throwable
    {
        String data = bad_data;

        KerberosPrincipal principal = new KerberosPrincipal("test");
        /* The use of null as the 3rd parameter to KerberosKey causes the algorithm to default to "DES" */
        /* POTENTIAL FLAW: Hard-coded password */
        KerberosKey key = new KerberosKey(principal, data.toCharArray(), null);
        IO.writeLine(key.toString());

    }

    /* uses badsource and badsink */
    public void bad() throws Throwable
    {
        String data;

        data = "pass";

        bad_data = data;
        bad_sink();
    }

    public void good() throws Throwable
    {
        goodG2B();
    }

    private void goodG2B_sink() throws Throwable
    {
        String data = goodG2B_data;

        KerberosPrincipal principal = new KerberosPrincipal("test");
        /* The use of null as the 3rd parameter to KerberosKey causes the algorithm to default to "DES" */
        /* POTENTIAL FLAW: Hard-coded password */
        KerberosKey key = new KerberosKey(principal, data.toCharArray(), null);
        IO.writeLine(key.toString());

    }

    /* goodG2B() - use goodsource and badsink */
    private void goodG2B() throws Throwable
    {
        String data;

        java.util.logging.Logger log_good_source = java.util.logging.Logger.getLogger("local-logger");

        BufferedReader bufread2 = null;
        InputStreamReader inread2 = null;

        Properties prop = new Properties();
        IO.writeLine("Enter the password: ");

        data = "";

        try
        {
            inread2 = new InputStreamReader(System.in);
            bufread2 = new BufferedReader(inread2);
            /* FIX: password is read from stdin */
            data = bufread2.readLine();
        }
        catch(Exception e)
        {
            log_good_source.warning("Exception in try");
        }
        finally
        {
            try
            {
                if( bufread2 != null )
                {
                    bufread2.close();
                }
            }
            catch( IOException e )
            {
                log_good_source.warning("Error closing bufread2");
            }
            finally
            {
                try
                {
                    if( inread2 != null )
                    {
                        inread2.close();
                    }
                }
                catch( IOException e )
                {
                    log_good_source.warning("Error closing inread2");
                }
            }
        }

        goodG2B_data = data;
        goodG2B_sink();
    }

    /* Below is the main(). It is only used when building this testcase on
       its own for testing or for building a binary to use in testing binary
       analysis tools. It is not used when compiling all the testcases as one
       application, which is how source code analysis tools are tested. */
    public static void main(String[] args) throws ClassNotFoundException,
           InstantiationException, IllegalAccessException
    {
        mainFromParent(args);
    }

}
