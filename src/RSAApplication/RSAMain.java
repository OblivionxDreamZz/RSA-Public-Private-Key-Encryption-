
package RSAApplication;

import java.io.File;

public class RSAMain 
{
    public static void main(String[] args) 
    {
        try{
            RSAKeyGeneration rsa = new RSAKeyGeneration();
            rsa.generateKey();
        
            RSAEncryptDecrypt processor = new RSAEncryptDecrypt(rsa);
        
            //Select DES Keys .txt file
            File desKeysFile = processor.selectFile();
            
        if (desKeysFile == null)
        {
            System.out.println("No File Selected, Exiting...");
            return;
        }
        
            //Encrypt DES Keys
            String encryptedFile = "Encrypted_DES_Keys.txt";
            processor.encryptDESKeys(desKeysFile, encryptedFile);
            
            //Select RSA Encrypted File and Decrypt Back To DES Keys
            processor.selectRSAFile();
            
            //Decrypt DES Keys
//            String decryptedFile = "Decrypted_DES_Keys.txt";
//            processor.decryptDESKeys(new File(encryptedFile), decryptedFile);
        
        }catch (Exception e)
        {
            e.printStackTrace();
        }       
    }
}
