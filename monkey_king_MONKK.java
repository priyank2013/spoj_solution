import java.util.*;
import java.util.Comparator;
import java.io.*;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
class JavaApplication39 {       
   
   static Reader in=new Reader();
   
    private static PrintWriter out;
  public static void main(String[] args) throws IOException {
      int t=in.nextInt();
      for(int i=0;i<t;i++){
          int r=in.nextInt(),c=in.nextInt();
         
          int ii[][]=new int[r][c];
          for(int j=0;j<r;j++){
              String s=in.readLine();
              char arr[]=s.toCharArray();
              for(int k=0;k<c;k++){
                  char aaa=s.charAt(k);
                 if(aaa=='B'){
                     ii[j][k]=1; 
                  } else if(aaa=='T'){
                      ii[j][k]=-1;
                  }
                 if(j>0)ii[j][k]+=ii[j-1][k];
                 if(k>0)ii[j][k]+=ii[j][k-1];
                 if(j>0 && k>0)ii[j][k]-=ii[j-1][k-1];
              }
              
          }
          
          int sub,ma=0;
          for(int j=0;j<r;j++){
              for(int k=0;k<c;k++){
                  int tt=j,q=k;
                  while(tt<r&&q<c){
                     sub=ii[tt][q]; 
                      if(j>0)sub-=ii[j-1][q];
                      if(k>0)sub-=ii[tt][k-1];
                      if(j>0 && k>0)sub+=ii[j-1][k-1];    
                     
                      ma=ma>sub?ma:sub;
                      tt++;q++;
                  }
              }
          }
          System.out.println("Case "+(i+1)+": "+ma);
      }
    }
}
class Reader {
    final private int BUFFER_SIZE = 1 << 16;
    private DataInputStream din;
    private byte[] buffer;
    private int bufferPointer, bytesRead;
    public Reader(){
        din=new DataInputStream(System.in);
        buffer=new byte[BUFFER_SIZE];
        bufferPointer=bytesRead=0;
    }
 
    public Reader(String file_name) throws IOException{
        din=new DataInputStream(new FileInputStream(file_name));
        buffer=new byte[BUFFER_SIZE];
        bufferPointer=bytesRead=0;
    }
 
    public String readLine() throws IOException{
        byte[] buf=new byte[110]; // line length
        int cnt=0,c;
        while((c=read())!=-1){
            if(c=='\n')break;
            buf[cnt++]=(byte)c;
        }
        return new String(buf,0,cnt);
    }
     public int nextChar() throws IOException{
        byte c=read();
        while(c<=' ')c=read();
        char ch=(char)c;
        return c;
    }
    public int nextInt() throws IOException{
        int ret=0;byte c=read();
        while(c<=' ')c=read();
        boolean neg=(c=='-');
        if(neg)c=read();
        do{ret=ret*10+c-'0';}while((c=read())>='0'&&c<='9');
        if(neg)return -ret;
        return ret;
    } 
 
    public long nextLong() throws IOException{
        long ret=0;byte c=read();
        while(c<=' ')c=read();
        boolean neg=(c=='-');
        if(neg)c=read();
        do{ret=ret*10+c-'0';}while((c=read())>='0'&&c<='9');
        if(neg)return -ret;
        return ret;
    }
 
    public double nextDouble() throws IOException{
        double ret=0,div=1;byte c=read();
        while(c<=' ')c=read();
        boolean neg=(c=='-');
        if(neg)c = read();
        do {ret=ret*10+c-'0';}while((c=read())>='0'&&c<='9');
        if(c=='.')while((c=read())>='0'&&c<='9')
            ret+=(c-'0')/(div*=10);
        if(neg)return -ret;
        return ret;
    }
    
    private void fillBuffer() throws IOException{
        bytesRead=din.read(buffer,bufferPointer=0,BUFFER_SIZE);
        if(bytesRead==-1)buffer[0]=-1;
    }
    
    private byte read() throws IOException{
        if(bufferPointer==bytesRead)fillBuffer();
        return buffer[bufferPointer++];
    }
    
    public void close() throws IOException{
        if(din==null) return;
        din.close();
    }
}