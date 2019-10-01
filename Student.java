public class Student extends Person {
    private String program ;
    private int year ;
    private double fee ;

    Student(String name, String address, String program, int year, double fee){
        super(name, address);
        this.program = program ;
        this.year = year ;
        this.fee = fee ;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public String toString(){
        String s4 = "Student[" ;
        String s5 = ",program=" ;
        String s6 = ",year=" ;
        String s7 = ",fee=" ;
        String s8 = "]" ;
        return (s4 + super.toString() + s5 + this.program + s6 + this.year + s7 + this.fee + s8) ;
    }

}
