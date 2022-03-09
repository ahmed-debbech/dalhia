package tn.dalhia.entities.enumerations;

public enum RequestStatus {
    APPROVED(2), PENDING(1), DECLINED(3);
    private int value;    

    private RequestStatus(int value) {
      this.value = value;
    }

    public int getValue() {
      return value;
    }
}
