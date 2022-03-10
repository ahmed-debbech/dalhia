package tn.dalhia.entities.enumerations;

public enum Role {
    EXPERT(1), //can be also a COACH
    WOMAN(2),
    ADMIN(3),
    ASSOCIATION(4);
    
    private int value;    

    private Role(int value) {
      this.value = value;
    }

    public int getValue() {
      return value;
    }
}
