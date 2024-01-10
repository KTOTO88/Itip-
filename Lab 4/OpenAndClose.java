import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

class FileCloseException extends Exception {
    public FileCloseException(String message) {
        super(message);
    }
}

public class OpenAndClose {
    public static void main(String[] args) {
        String sourceFilePath = "C:\\Users\\Александр\\Desktop\\lab4\\src\\file.txt";
        String destinationFilePath = "C:\\Users\\Александр\\Desktop\\lab4\\src\\newFile.txt";
        FileInputStream sourceFile = null;
        FileOutputStream destinationFile = null;

        try {
            sourceFile = new FileInputStream(sourceFilePath);
            destinationFile = new FileOutputStream(destinationFilePath);

            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = sourceFile.read(buffer)) != -1) {
                destinationFile.write(buffer, 0, bytesRead);
            }

            System.out.println("Файл скопирован.");
        } catch (IOException e) {
            System.out.println("Ошибка при копировании файла: " + e.getMessage());
        } finally {
            if (sourceFile != null) {
                try {
                    sourceFile.close();
                } catch (IOException e) {
                    System.out.println("Ошибка при закрытии исходного файла: " + e.getMessage());
                }
            }

            if (destinationFile != null) {
                try {
                    destinationFile.close();
                } catch (IOException e) {
                    try {
                        throw new FileCloseException("Ошибка при закрытии файла назначения: " + e.getMessage());
                    } catch (FileCloseException fileCloseException) {
                        System.out.println(fileCloseException.getMessage());
                    }
                }
            }
        }
    }
}
