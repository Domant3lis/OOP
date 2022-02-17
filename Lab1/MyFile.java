import java.io.File;
import java.util.ArrayList;
import java.util.List;

class FileIsDirectoryException extends Exception {
    public FileIsDirectoryException(String errorMessage) {
        super(errorMessage);
    }

    public FileIsDirectoryException() {
        super();
    }
}

public class MyFile extends File
{
    static final private String OS = System.getProperty("os.name");
    static final private char SEPARATOR = OS.equals("win") ? '\\' : '/';

    File file;
    int level;

    MyFile(String path)
    {
        super(path);
        this.level = 0;
    }
    
    MyFile(String path, int level)
    {
        super(path);
        this.level = level;
    }

    MyFile(File file)
    {
        super(file.getName());
        this.level = 0;
    }
    
    MyFile(File file, int level)
    {
        super(file.getName());
        this.level = level;
    }

    static private String fileExtensionPriv(String fileName, char separator)
    {
        // Gets file extension in reverse order
        List<Character> extReversed = new ArrayList<Character>();
        for (int iter = fileName.length() - 1; iter > 0; --iter) {
            char ch = fileName.charAt(iter);

            if (ch == '.') {
                // Detects hidden UNIX files
                // by seeing if '.' is the first symbol
                if (iter == 0)
                    return new String("");

                break;
            }
            // then no '.' symbol is found
            // it is assumed that the file has no extension
            else if (ch == separator)
                return new String("");

            extReversed.add(ch);
        }

        // Reverses into correct form
        // and constructs a String from List of Characters
        char[] ret = new char[extReversed.size()];
        for (int iter = extReversed.size() - 1, at = 0; iter >= 0; --iter, ++at)
            ret[at] = extReversed.get(iter);

        return new String(ret);
    }

    // IMPORTANT: Does not support multi-part extensions, like `tar.xz`
    static public String fileExtension(File file) throws FileIsDirectoryException
    {
        if (file.isDirectory())
            throw new FileIsDirectoryException();

        return MyFile.fileExtensionPriv(file.getName(), file.separatorChar);        
    }
    
    // IMPORTANT: Does not support multi-part extensions, like `tar.xz`
    static public String fileExtension(String file)
    {
        return MyFile.fileExtensionPriv(file, SEPARATOR);        
    }

    public String fileExtension() throws FileIsDirectoryException
    {
        if(this.file.isDirectory())
        {
            return fileExtension();
        }
        else
        {
            throw new FileIsDirectoryException();
        }
    }

    static public List<MyFile> getFileTree(MyFile root, int level)
    {
        List<MyFile> ret = new ArrayList<MyFile>();        

        for (String fileName : root.list())
        {
            MyFile file = new MyFile(fileName, level);
            
            ret.add(file);
            System.out.println( "FILE:" + file + " | \t" + file.isDirectory());

            if ( file.isDirectory() )
            {
                System.out.println( "DIR:" + file);
                MyFile.getFileTree(file, level + 1).forEach( el -> ret.add(el));
            }
        }

        return ret;
    }


}
