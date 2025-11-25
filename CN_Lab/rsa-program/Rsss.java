import java.io.DataInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;
public class Rsss{
private BigInteger p,q,n,phi,e,d;
private int bitlength=128 ;
private Random r;
public Rsss(){
r=new Random();
p=BigInteger.probablePrime(bitlength,r);
q=BigInteger.probablePrime(bitlength,r);
System.out.println("Prime number p is : "+p);
System.out.println("Prime number q is : "+q);
n=p.multiply(q);
phi=p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
e=BigInteger.probablePrime(bitlength/2,r);
while(phi.gcd(e).compareTo(BigInteger.ONE)>0&&e.compareTo(phi)<0){
e.add(BigInteger.ONE);
}
System.out.println("Public key is : "+e);
d=e.modInverse(phi);
System.out.println("Private key is : "+d);
}
public Rsss(BigInteger e,BigInteger d,BigInteger n){
this.e=e;
this.d=d;
this.n=n;
}
public static void main(String[] args) throws IOException {
Rsss r=new Rsss();
DataInputStream in=new DataInputStream(System.in);
String testString;
System.out.println("Enter the plain text : ");
testString=in.readLine();
System.out.println("Encrypting string : "+testString);
System.out.println("Strrsaing in bytes : "+ bytesToString(testString.getBytes()));
byte[] encrypted=r.encrypt(testString.getBytes());
byte[] decrypted=r.decrypt(encrypted);
System.out.println("Decrypting Bytes : "+bytesToString(decrypted));
System.out.println("Decrypted String : "+new String(decrypted));
}
private static String bytesToString(byte[] encrypted){
String test="";
for(byte b:encrypted){
test+=Byte.toString(b);
}
return test;

}
public byte[] encrypt(byte[] message){
return (new BigInteger(message)).modPow(e,n).toByteArray();
}
public byte[] decrypt(byte[] message){
return (new BigInteger(message)).modPow(d,n).toByteArray();
}
}