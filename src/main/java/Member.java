package member;

public class Member extends Customer {
    private final String nama;
    private final String noTelp;
    private int poin;
    private int jumlahTransaksi;

    public Member(String nama, String telp) {
        super();
        this.nama = nama;
        this.noTelp = telp;
        this.poin = 0;
        this.jumlahTransaksi = 1;
    }

    public String getNama() {
        return nama;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public int getPoin() {
        return poin;
    }

    public void tambahPoin(double totalBayar) {
        int newPoin = (int) (totalBayar * 0.01);
        poin += newPoin;
    }

    public double pakaiPoin(int pakePoin) {
        double disc = pakePoin;
        if (pakePoin > poin) {
            disc = poin;
        }
        poin -= (int) disc;
        return disc;
    }

    public int getJumlahTransaksi() {
        return jumlahTransaksi;
    }

    public void tambahTransaksi() {
        jumlahTransaksi++;
    }
}

