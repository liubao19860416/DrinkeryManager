package entity;

/** 第二部创建实体 */
public class ManagerSystem_gsl {
    private String accountno;

    private String pwd;

    public ManagerSystem_gsl() {
        super();
    }

    public ManagerSystem_gsl(String accountno, String pwd) {
        super();
        this.accountno = accountno;
        this.pwd = pwd;
    }

    public String getAccountno() {
        return accountno;
    }

    public void setAccountno(String accountno) {
        this.accountno = accountno;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "ManagerSystem_gsl [" + accountno + "," + pwd + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((accountno == null) ? 0 : accountno.hashCode());
        result = prime * result + ((pwd == null) ? 0 : pwd.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (obj instanceof ManagerSystem_gsl) {
            ManagerSystem_gsl manager = (ManagerSystem_gsl) obj;
            return manager.accountno == accountno && manager.pwd == pwd;
        }
        return false;
    }
}
