package entity;

public class Vigatableseries {

    private int seriesNo;
    private String series;

    public Vigatableseries() {
        // TODO Auto-generated constructor stub
    }

    public Vigatableseries(int seriesNo, String series) {
        super();
        this.seriesNo = seriesNo;
        this.series = series;
    }

    public int getSeriesNo() {
        return seriesNo;
    }

    public void setSeriesNo(int seriesNo) {
        this.seriesNo = seriesNo;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    @Override
    public String toString() {
        return "Vigatableseries [" + seriesNo + "," + series + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((series == null) ? 0 : series.hashCode());
        result = prime * result + seriesNo;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (obj instanceof Vigatableseries) {
            Vigatableseries vi = (Vigatableseries) obj;
        }
        return false;
    }

}
