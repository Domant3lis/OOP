import java.io.File; // Must use
import java.util.ArrayList;
import java.util.List;

// Show all files recursively with a certain file extension
public class Lab1 {
	static final private String OS = System.getProperty("os.name");
	static final private char SEPARATOR = OS.equals("win") ? '\\' : '/';

	public static void main(String[] args) {

		String ext;

		if (args.length == 2) {
			ext = args[1];

			// Gets all files
			List<File> fileList = Lab1.getFilesRec(args[0], 0);

			// Prints all files2
			System.out.println("Showing files ending in \"." + ext + "\"");
			for (File file : fileList) {
				if (file.isDirectory())
					System.out.println("--" + file);

				if (Lab1.fileExtension(file.getName(), SEPARATOR).equals(ext))
					System.out.println("  " + file);
			}

		} else {
			System.out.println("Not enough arguments");
		}
	}

	// Gets a list of all files within a `root` directory
	static public List<File> getFilesRec(String rootPath, int level) {
		File root = new File(rootPath);

		List<File> ret = new ArrayList<File>();

		for (String fileName : root.list()) {
			File file = new File(fileName);

			ret.add(file);

			if (file.isDirectory()) {
				Lab1.getFilesRec(file.getAbsolutePath(), level + 1).forEach(el -> ret.add(el));
			}
		}

		return ret;
	}

	// IMPORTANT: Does not support multi-part extensions, like `tar.xz`
	static private String fileExtension(String fileName, char separator) {
		// Gets file extension in reverse order
		List<Character> extReversed = new ArrayList<Character>();
		int fIter;
		for (fIter = fileName.length() - 1; fIter > 0; --fIter) {
			char ch = fileName.charAt(fIter);

			if (ch == '.') {
				// Detects hidden UNIX files
				// by seeing if '.' is the first symbol
				if (fIter == 0)
					return new String("");

				break;
			}
			// then no '.' symbol is found
			// it is assumed that the file has no extension
			else if (ch == separator) /// asd/asd
				return new String("");

			extReversed.add(ch);
		}

		if (fIter == 0)
			return new String("");

		// Reverses into correct form
		// and constructs a String from List of Characters
		char[] ret = new char[extReversed.size()];
		for (int iter = extReversed.size() - 1, at = 0; iter >= 0; --iter, ++at)
			ret[at] = extReversed.get(iter);

		return new String(ret);
	}

	// static public String stringMul(String str, int times) {
	// if (times == 0)
	// return new String("");

	// for (int iter = 0; iter < times; ++iter) {
	// str += str;
	// }
	// return str;
	// }

}