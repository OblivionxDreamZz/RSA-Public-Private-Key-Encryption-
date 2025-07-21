# RSA-Public-Private-Key-Encryption-

🔐 RSA Encryption & Decryption of DES Keys in Java

This Java application implements RSA public-key cryptography to securely encrypt and decrypt 16-round DES keys stored in a text file. The system features key generation, file selection, encryption, and decryption, allowing for secure hybrid cryptographic integration.
📂 Project Structure

    RSAKeyGeneration.java: Generates RSA key pairs (public and private) and provides encryption/decryption methods.

    RSAEncryptDecrypt.java: Handles file selection, reading/writing, and applies RSA encryption to DES keys.

    RSAMain.java: Main execution class that ties the key generation and encryption/decryption processes together.

⚙️ How It Works
🔑 Key Generation

    Generates two large prime numbers p and q.

    Computes modulus n = p * q and totient t = (p-1)*(q-1).

    Randomly selects a public exponent e where gcd(e, t) = 1.

    Computes the private key d, the modular inverse of e modulo t.

🔐 Encryption

    Reads each DES key from a .txt file (48-bit binary strings).

    Converts each key to a BigInteger.

    Encrypts it using RSA: cipher = message^e mod n.

    Saves encrypted keys to a new .txt file.

🔓 Decryption

    Reads encrypted RSA values from a .txt file.

    Decrypts each key using: message = cipher^d mod n.

    Converts the decrypted BigInteger back to a 48-bit binary string.

    Outputs the restored DES keys into a new file.

📁 File Flow

    Select a .txt file containing 16 DES round keys (binary format).

    Encrypt the keys using RSA and store them in Encrypted_DES_Keys.txt.

    Select the RSA-encrypted file to decrypt and restore the original DES keys in a new file.

🚀 Running the Program

    Compile and run RSAMain.java.

    Follow the file selection dialogs:

        First to select the input DES keys file.

        Then, save the encrypted keys.

        Finally, select the RSA-encrypted file and output path to decrypt the keys.

🧪 Example Input

Input file (DESKeys.txt):

011010100101101010101010101010101010101010101010
...

Output (Encrypted_DES_Keys.txt):

289472983472398472398472384728374823
...

Decrypted Output:

011010100101101010101010101010101010101010101010
...

📌 Features

    1024-bit RSA key generation using BigInteger.

    Modular exponentiation for secure encryption/decryption.

    File I/O integration using JFileChooser for user-friendly interaction.

    Easily integratable with symmetric DES encryption modules.

📎 Dependencies

    No external libraries are required — uses core Java (java.math.BigInteger, java.io, javax.swing.JFileChooser).

👨‍💻 Author

Luthando Mletshe
Java Security and Cryptography Enthusiast
