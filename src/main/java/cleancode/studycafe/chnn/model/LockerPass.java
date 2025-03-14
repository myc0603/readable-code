package cleancode.studycafe.chnn.model;

public class LockerPass implements Pass {

    public static final LockerPass UNSELECTED_LOCKER_PASS = of(null, 0, 0);

    private final StudyCafePassType passType;
    private final int duration;
    private final int price;

    private LockerPass(StudyCafePassType passType, int duration, int price) {
        this.passType = passType;
        this.duration = duration;
        this.price = price;
    }

    public static LockerPass of(StudyCafePassType passType, int duration, int price) {
        return new LockerPass(passType, duration, price);
    }

    @Override
    public StudyCafePassType getPassType() {
        return passType;
    }

    @Override
    public int getDuration() {
        return duration;
    }

    @Override
    public int getPrice() {
        return price;
    }

}
