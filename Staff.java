public class Staff extends Person {
    private String school ;
    private double pay ;

    Staff(String name, String address, String school, double pay){
        super(name, address);
        this.school = school ;
        this.pay = pay ;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public double getPay() {
        return pay;
    }

    public void setPay(double pay) {
        this.pay = pay;
    }

    public String toString(){
        String s9 = "Staff[" ;
        String s10 = ",school=" ;
        String s11 = ",pay=" ;
        String s12 = "]" ;
        return (s9 + super.toString() + s10 + this.school + s11 + this.pay + s12) ;
    }
    
}
