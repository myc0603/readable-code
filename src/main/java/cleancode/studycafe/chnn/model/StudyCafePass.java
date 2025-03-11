package cleancode.studycafe.chnn.model;

public class StudyCafePass implements Pass {

    private final StudyCafePassType passType;
    private final int duration;
    private final int price;
    private final double discountRate;

    private StudyCafePass(StudyCafePassType passType, int duration, int price, double discountRate) {
        this.passType = passType;
        this.duration = duration;
        this.price = price;
        this.discountRate = discountRate;
    }

    public static StudyCafePass of(StudyCafePassType passType, int duration, int price, double discountRate) {
        return new StudyCafePass(passType, duration, price, discountRate);
    }

    public boolean isCorrespondingPass(LockerPass lockerPass) {
        return isSameType(lockerPass) && isSameDuration(lockerPass);
    }

    private boolean isSameType(LockerPass lockerPass) {
        return this.passType == lockerPass.getPassType();
    }

    private boolean isSameDuration(LockerPass lockerPass) {
        return this.duration == lockerPass.getDuration();
    }

    public boolean isSameType(StudyCafePassType passType) {
        return this.passType == passType;
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

    public int getDiscountPrice() {
        return (int) (price * discountRate);
    }

    public int getCalculatedPrice() {
        return price - getDiscountPrice();
    }
}