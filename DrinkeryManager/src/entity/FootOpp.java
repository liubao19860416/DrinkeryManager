package entity;

public class FootOpp {

    private String productNo;

    private String productName;

    private String monad;

    private double unitPrice;

    private int seriesNo;

    public FootOpp() {
    }

    public FootOpp(String productNo, String productName, String manad,
            double unitPrice, int seriesNo) {
        super();
        this.productNo = productNo;
        this.productName = productName;
        this.monad = manad;
        this.unitPrice = unitPrice;
        this.seriesNo = seriesNo;
    }

    public int getSeriesNo() {
        return seriesNo;
    }

    public void setSeriesNo(int seriesNo) {
        this.seriesNo = seriesNo;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productNama) {
        this.productName = productNama;
    }

    public String getManad() {
        return monad;
    }

    public void setManad(String manad) {
        this.monad = manad;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double d) {
        this.unitPrice = d;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((monad == null) ? 0 : monad.hashCode());
        result = prime * result
                + ((productName == null) ? 0 : productName.hashCode());
        result = prime * result
                + ((productNo == null) ? 0 : productNo.hashCode());
        long temp;
        temp = Double.doubleToLongBits(unitPrice);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (obj instanceof FootOpp) {
            FootOpp foot = (FootOpp) obj;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "FootOpp [" + productNo + "," + productName + "," + monad + ","
                + unitPrice + "," + seriesNo + "]";
    }

}
