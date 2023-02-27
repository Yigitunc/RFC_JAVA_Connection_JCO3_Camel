
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

public class RFCFileCreator {
    private String lang;
    private String peak_limit;
    private String client;
    private String password;
    private String user;
    private String sysnr;
    private String pool_capacity;
    private String ashost;

    private String random;

    public void setRandom(String random) {
        this.random = random;
    }

    public String getRandom() {
        return random;
    }

    public RFCFileCreator() {

    }

    public String CreateFile() {
        random = UUID.randomUUID().toString();

        try {
            File myObj = new File("YOUR\\PATH", random + ".jcoDestination");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (
                IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try {
            FileWriter myWriter = new FileWriter(random + ".jcoDestination");
            myWriter.write("jco.client.lang=" + lang + "\n" +
                    "jco.destination.peak_limit=" + peak_limit + "\n" +
                    "jco.client.client=" + client + "\n" +
                    "jco.client.passwd=" + password + "\n" +
                    "jco.client.user=" + user + "\n" +
                    "jco.client.sysnr=" + sysnr + "\n" +
                    "jco.destination.pool_capacity=" + pool_capacity + "\n" +
                    "jco.client.ashost=" + ashost + "");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return random;
    }

    public RFCFileCreator(String lang, String peak_limit, String client, String password, String user, String sysnr, String pool_capacity, String ashost) {
        this.lang = lang;
        this.peak_limit = peak_limit;
        this.client = client;
        this.password = password;
        this.user = user;
        this.sysnr = sysnr;
        this.pool_capacity = pool_capacity;
        this.ashost = ashost;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getPeak_limit() {
        return peak_limit;
    }

    public void setPeak_limit(String peak_limit) {
        this.peak_limit = peak_limit;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSysnr() {
        return sysnr;
    }

    public void setSysnr(String sysnr) {
        this.sysnr = sysnr;
    }

    public String getPool_capacity() {
        return pool_capacity;
    }

    public void setPool_capacity(String pool_capacity) {
        this.pool_capacity = pool_capacity;
    }

    public String getAshost() {
        return ashost;
    }

    public void setAshost(String ashost) {
        this.ashost = ashost;
    }
}
