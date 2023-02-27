public class RFCDestinationModel {

    private String lang;
    private String peak_limit = "10";
    private String client;
    private String password;
    private String user;
    private String sysnr;
    private String pool_capacity = "3";
    private String ashost;

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
