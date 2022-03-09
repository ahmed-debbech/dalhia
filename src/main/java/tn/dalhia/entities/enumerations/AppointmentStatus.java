package tn.dalhia.entities.enumerations;

public enum AppointmentStatus {
	CONFIRMED(1), DECLINED(2),CANCELLED(3),PENDING(4);
	 private int value;    

    private AppointmentStatus(int value) {
      this.value = value;
    }

    public int getValue() {
      return value;
    }
}
