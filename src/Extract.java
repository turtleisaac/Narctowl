
import java.io.*;
public class Extract
{
    public static void main(String[] args) throws Exception
    {
        Extract extract= new Extract();

    }
    private String path= System.getProperty("user.dir");

    public Extract() throws Exception
    {
        NARC narc= new NARC();
        File[] arr= new File(path).listFiles();

        for(File  file: arr)
        {
            if(file.toString().contains(".narc"))
            {
                String name= file.getName();
                narc.unpack(name);
            }
        }


    }



}
