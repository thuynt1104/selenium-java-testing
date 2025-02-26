package javaTester;

public class Topic_05_Path {
    public static void main(String[] args) {
        String osName = System.getProperty("os.name");
        String javaVersion = System.getProperty("java.version");
        String projectPath = System.getProperty("user.dir");
        String uploadFilePath = System.getProperty("user.dir") + "/UploadFile/";

        System.out.println(osName);
        System.out.println(javaVersion);
        System.out.println(projectPath);
        System.out.println(uploadFilePath);
    }
}
