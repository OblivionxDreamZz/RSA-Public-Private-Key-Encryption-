
package RSAApplication;

import java.math.BigInteger;
import java.security.SecureRandom;

public class RSAKeyGeneration 
{
    
    private BigInteger p,q,n,t,e,d;
    private final SecureRandom random = new SecureRandom();
    
    public void generateKey()
    {
        //Generate two large prime numbers 
        p = BigInteger.probablePrime(512, random);
        q = BigInteger.probablePrime(512, random);
        System.out.println("Prime p = " + p);
        System.out.println("Prime q = " + q);
        
        //Calculate n = p* q
        n = p.multiply(q);
        System.out.println("n = p * q = " + n);
        
        //Calcualte totient function (t) = (p-1) * (q-1)
        t = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        System.out.println("Totient Function(t) = " + t);
        
        //Choose e such that 1 < e < t and gcd(e,t) = 1
        do
        {
            e = BigInteger.probablePrime(256, random);
        }while(!t.gcd(e).equals(BigInteger.ONE) || e.compareTo(t) >= 0);
        System.out.println("e = " + e);
        
        //Calculate (d) such that e*dmodt = 1
        d = e.modInverse(t);
        System.out.println("d = " + d);
        
        //Display public and private keys
        System.out.println("RSA Key Pair Generation");
        System.out.println("Public Key (e, n): (" + e + ", " + n + ")");
        System.out.println("Private Key (d) = " + d); 
    }
    
    //Encrypt Method 
     public BigInteger encrypt(BigInteger message)
     {
         return message.modPow(e,n);
     }
     
     //Decrypt Method 
     public BigInteger decrypt(BigInteger cipher)
     {
         return cipher.modPow(d,n);
     }
     
     //Getters
     public BigInteger getN()
     {
         return n;
     }
     
     public BigInteger getE()
     {
         return e;
     }
     
     public BigInteger getD()
     {
         return d;
     }
    
}
