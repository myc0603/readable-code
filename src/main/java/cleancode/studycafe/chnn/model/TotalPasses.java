package cleancode.studycafe.chnn.model;

public class TotalPasses {

    private final StudyCafePass studyCafePass;
    private final LockerPass lockerPass;

    public TotalPasses(StudyCafePass studyCafePass, LockerPass lockerPass) {
        this.studyCafePass = studyCafePass;
        this.lockerPass = lockerPass;
    }

    public int getTotalPrice() {
        return studyCafePass.getPrice() - getDiscountPrice() + (lockerPass != null ? lockerPass.getPrice() : 0);
    }

    public int getDiscountPrice() {
        return studyCafePass.getDiscountPrice();
    }

    public StudyCafePass getStudyCafePass() {
        return studyCafePass;
    }

    public LockerPass getLockerPass() {
        return lockerPass;
    }

    public boolean hasLockerPass() {
        return lockerPass != null;
    }

}
