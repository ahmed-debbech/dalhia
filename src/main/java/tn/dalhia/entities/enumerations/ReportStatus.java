package tn.dalhia.entities.enumerations;

public enum ReportStatus {
    CONFIRMED(1), DECLINED(2), PENDING(3);
    
    private int value;    

    private ReportStatus(int value) {
      this.value = value;
    }

    public int getValue() {
      return value;
    }
}
