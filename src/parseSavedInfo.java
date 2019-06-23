import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class parseSavedInfo {
    ObjectInputStream objectInputStream;

    public parseSavedInfo() throws IOException {
    objectInputStream=new ObjectInputStream(new FileInputStream(new File("saved")));

    }
}
