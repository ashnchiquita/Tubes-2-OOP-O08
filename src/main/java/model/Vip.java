package model;

public class Vip extends Member{
    public Vip(String nama, String telp) {
        super(nama, telp);
    }
    
    @Override
    public double pakaiPoin(int pakePoin) {
        double disc = super.pakaiPoin(pakePoin);
        return disc * 1.1;
    }    
}

