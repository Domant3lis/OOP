import java.io.File; // Must use
import java.util.ArrayList;
import java.util.List;

class MyFileUtil
{
	// static public List<MyFile> getFilesWithExt(MyFile dir, String ext)
	// {
		

	// 	// return ret;
	// }
}

public class Lab1
{
	public static void main(String[] args)
	{

		MyFile dir;
		String ext;

		// Via command line 
		if (args.length == 2)
		{
			dir = new MyFile(args[0]);
			ext = args[1];
			MyFile.getFileTree(dir, 0).forEach(System.out::println );
		}
		else
		{
			System.out.println("Not enough arguments");
		}


		// Via GUI
		// else
		// {
		// 	dir = new MyFile("test0");
		// 	ext = "txt";
		// }

		// List<MyFile> ret = ;
		
		// for (String fileName : dir.list())
		// {
		// 	MyFile file = new MyFile(); 
		// 	if () 
		// 	try {
		// 		if (MyFile.fileExtension(file).equals(ext))
		// 		{
		// 			ret.add(new MyFile(file));
		// 		}
		// 	} catch (FileIsDirectoryException fileIsDir) {
		// 		System.out.println("TO HANDLE");;
		// 	}
		// }

		// List<File> files = MyFileUtil.getFilesWithExt(dir, ext);

		// files.forEach(System.out::println);
	}
}
