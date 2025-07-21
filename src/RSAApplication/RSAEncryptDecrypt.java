
package RSAApplication;

import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;

public class RSAEncryptDecrypt 
{    
    private final RSAKeyGeneration rsa;
    
    public RSAEncryptDecrypt(RSAKeyGeneration rsa)
    {
        this.rsa = rsa;
    }
    
    public File selectFile()
    {
        JFileChooser filechooser = new JFileChooser();
        int option = filechooser.showOpenDialog(null);
        if (option == JFileChooser.APPROVE_OPTION)
        {
            return filechooser.getSelectedFile();
        }
        return null; 
    }
    
    //Reading The .txt File 
    public List<String> readFile(File file) throws IOException
    {
        List<String> lines = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while((line = reader.readLine()) != null)
        {
            lines.add(line.trim());
        }
        reader.close();
        return lines;   
    }
    
    public void writeFile(List<String> data, String fileName) throws IOException
    {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        for(String line : data)
        {
            writer.write(line);
            writer.newLine();     
        }
        writer.close();  
    }
    
    //RSA Encryption of DES 16 Round Keys
    public void encryptDESKeys(File inputFile, String outputFileName) throws IOException
    {
        List<String> keys = readFile(inputFile);
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));
        for(String key : keys)
        {
            BigInteger keyBigInt = new BigInteger(key, 2);
            BigInteger encrypted = rsa.encrypt(keyBigInt);           
            writer.write(encrypted.toString());
            writer.newLine();
        }
        writer.close();
        System.out.println("DES Keys encrypted and saved to: " + outputFileName);
      
    }
    
    public void selectRSAFile()
    {
        File encryptedFile = selectFile();
        
        if(encryptedFile != null)
        {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Please select location to save decrypted DES Keys");
            int userSelection = fileChooser.showSaveDialog(null);
            
            if (userSelection == JFileChooser.APPROVE_OPTION)
            {
                File outputFile = fileChooser.getSelectedFile();
                try{
                    decryptDESKeys(encryptedFile, outputFile.getAbsolutePath());
                }catch (IOException e ){
                    System.out.println("Error During Decryption " + e.getMessage());
                }
              
            }else
            {
                System.out.println("Decyption Cancelled. No Output File Selected");
            }         
        }else
        {
            System.out.println("No File Selected For Decryption");
        }      
    }

    //RSA Decryption of DES 16 Round Keys 
    public void decryptDESKeys(File encryptedFile, String outputFileName) throws IOException
    {
        List<String> encryptedKeys = readFile(encryptedFile);
        
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));
        
        for(String encryptedkey : encryptedKeys)
        {
            BigInteger encryptedBigInt = new BigInteger(encryptedkey);
            BigInteger decryptedBigInt = rsa.decrypt(encryptedBigInt);
            
            String decryptedBinary = decryptedBigInt.toString(2);
            
            while(decryptedBinary.length() < 48)
            {
                decryptedBinary = "0" + decryptedBinary;
            }
            
            writer.write(decryptedBinary);
            writer.newLine();     
        }
        writer.close();
        System.out.println("DES keys decrypted and saved to: " + outputFileName);   
    }
  
}


