import java.util.Scanner;
public class CRC {
public static int n;
public static void main(String[] args)
{
Scanner sc=new Scanner(System.in);
CRC crc=new CRC();
String copy,rec,code,zero="0000000000000000";
System.out.println("enter the dataword to be sent");
code=sc.nextLine(); // 1 0 0 1
n=code.length();
System.out.println("The dataword length is "+n);
copy=code;
code+=zero; //1001 0000000000000000
System.out.println("The modified dataword is "+code);
code=crc.divide(code);
System.out.println("dataword="+copy);
copy=copy.substring(0,n)+code.substring(n);
System.out.print("CRC=");
System.out.println(code.substring(n));

System.out.println("transmitted frame is="+copy);
System.out.println("enter received data:");
rec=sc.nextLine();
if(zero.equals(crc.divide(rec).substring(n)))
System.out.println("correct bits received");
else
System.out.println("received frame contains one or more error");
sc.close();
}

public String divide(String s)
{
String div="10001000000100001"; // x^16+x^12+x^5+1 crc 16 bits
int i,j;
char x;
for(i=0;i<n;i++) // outer loop
{
x=s.charAt(i);
for(j=0;j<17;j++) // inner loop
{
if(x=='1')
{
    // if 2 bits are same → result is 0
    // else result is 1
    if(s.charAt(i+j)!=div.charAt(j))
        s=s.substring(0,i+j)+"1"+s.substring(i+j+1);
    else
        s=s.substring(0,i+j)+"0"+s.substring(i+j+1);
}
}
}
return s; // remainder
}
}
