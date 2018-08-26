package entity;

public class Mobile {
    private int mid;
    private String mname;
    private float mprice;
    private String img;
    private String ram;
    private String brief;
    private int amount;
    public Mobile(){

    }

    public Mobile(int mid, String mname, float mprice, String img, String ram, String brief, int amount) {
        this.mid = mid;
        this.mname = mname;
        this.mprice = mprice;
        this.img = img;
        this.ram = ram;
        this.brief = brief;
        this.amount = amount;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public float getMprice() {
        return mprice;
    }

    public void setMprice(float mprice) {
        this.mprice = mprice;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Mobile{" +
                "mid=" + mid +
                ", mname='" + mname + '\'' +
                ", mprice=" + mprice +
                ", img='" + img + '\'' +
                ", ram='" + ram + '\'' +
                ", brief='" + brief + '\'' +
                ", amount=" + amount +
                '}';
    }
}
