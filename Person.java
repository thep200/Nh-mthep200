public class Person {
    private String name, address ;

    Person(String name, String address){
        this.name = name ;
        this.address = address ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String toString(){
        String s1 = "Person[name=" ;
        String s2 = ",address=" ;
        String s3 = "]" ;
        return (s1 + this.name  + s2 + this.address + s3) ;
    }
}
