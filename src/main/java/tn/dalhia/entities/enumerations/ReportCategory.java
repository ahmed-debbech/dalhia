package tn.dalhia.entities.enumerations;

public enum ReportCategory {
    VIOLENCE(1), ABUSE(2), OTHER(3), SHELTER(4);
    
    private int value;    

    private ReportCategory(int value) {
      this.value = value;
    }

    public int getValue() {
      return value;
    }
}
