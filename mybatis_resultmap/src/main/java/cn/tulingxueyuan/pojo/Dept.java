package cn.tulingxueyuan.pojo;

public class Dept {
    private Integer id;
    private String deptName;
    private Emp emps;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Emp getEmps() {
        return emps;
    }

    public void setEmps(Emp emps) {
        this.emps = emps;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "id=" + id +
                ", deptName='" + deptName + '\'' +
                ", emps=" + emps +
                '}';
    }
}
