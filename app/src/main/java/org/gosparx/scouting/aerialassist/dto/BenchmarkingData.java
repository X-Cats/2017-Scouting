package org.gosparx.scouting.aerialassist.dto;

import android.os.Parcel;
import android.os.Parcelable;

public class BenchmarkingData{
    private String nameOfScouter;
    private String driveSystem;
    private double drivesSpeed = Double.MAX_VALUE;
    private boolean canPlayDefenseBenchButton;
    private boolean abilityToShootHighGoalBenchButton;
    private String typeOfShooterBenchInput;
    private double ballsPerSecondBenchInput = Double.MAX_VALUE;
    private int ballsInCycleBenchInput = Integer.MAX_VALUE;
    private int cycleTimeHighBenchInput = Integer.MAX_VALUE;
    private double shootingRangeBenchInput = Double.MAX_VALUE;
    private String preferredShootingLocationBenchInput;
    private double accuracyHighBenchInput = Double.MAX_VALUE;
    private boolean pickupBallHopperBenchButton;
    private boolean pickupBallFloorBenchButton;
    private boolean pickupBallHumanBenchButton;
    private String pickupBallPreferredBenchInput;
    private int maximumBallCapacityBenchInput = Integer.MAX_VALUE;
    private boolean canScoreGearsBenchButton;
    private boolean pickupGearFloorBenchButton;
    private boolean pickupGearRetrievalBenchButton;
    private boolean pickupGearPreferredBenchButton;
    private boolean canGearLeftBench;
    private boolean canGearCenterBench;
    private boolean canGearRightBench;
    private String radioPreferredGear;
    private String radioPreferredBall;
    private int cycleTimeGearsBenchInput = Integer.MAX_VALUE;
    private boolean abilityToShootLowGoalBenchButton;
    private int cycleTimeLowBenchInput = Integer.MAX_VALUE;
    private int cycleNumberLowBenchInput = Integer.MAX_VALUE;
    private boolean abilityScaleBenchButton;
    private String placesCanScaleBenchInput;
    private String preferredPlacesScaleInput;
    private String autoAbilitiesBench;
    private String commentsBench;

    BenchmarkingData(String student) {
        this.nameOfScouter = student;
    }

    public String getNameOfScouter() {
        return nameOfScouter;
    }

    public void setNameOfScouter(String nameOfScouter) {
        this.nameOfScouter = nameOfScouter;
    }

    public String getDriveSystem() {
        return driveSystem;
    }

    public void setDriveSystem(String driveSystem) {
        this.driveSystem = driveSystem;
    }

    public double getDrivesSpeed() {
        return drivesSpeed;
    }

    public void setDrivesSpeed(double drivesSpeed) {
        this.drivesSpeed = drivesSpeed;
    }

    public boolean isCanPlayDefenseBenchButton() {
        return canPlayDefenseBenchButton;
    }

    public void setCanPlayDefenseBenchButton(boolean canPlayDefenseBenchButton) {
        this.canPlayDefenseBenchButton = canPlayDefenseBenchButton;
    }

    public boolean isAbilityToShootHighGoalBenchButton() {
        return abilityToShootHighGoalBenchButton;
    }

    public void setAbilityToShootHighGoalBenchButton(boolean abilityToShootHighGoalBenchButton) {
        this.abilityToShootHighGoalBenchButton = abilityToShootHighGoalBenchButton;
    }

    public String getTypeOfShooterBenchInput() {
        return typeOfShooterBenchInput;
    }

    public void setTypeOfShooterBenchInput(String typeOfShooterBenchInput) {
        this.typeOfShooterBenchInput = typeOfShooterBenchInput;
    }

    public double getBallsPerSecondBenchInput() {
        return ballsPerSecondBenchInput;
    }

    public void setBallsPerSecondBenchInput(double ballsPerSecondBenchInput) {
        this.ballsPerSecondBenchInput = ballsPerSecondBenchInput;
    }

    public int getBallsInCycleBenchInput() {
        return ballsInCycleBenchInput;
    }

    public void setBallsInCycleBenchInput(int ballsInCycleBenchInput) {
        this.ballsInCycleBenchInput = ballsInCycleBenchInput;
    }

    public int getCycleTimeHighBenchInput() {
        return cycleTimeHighBenchInput;
    }

    public void setCycleTimeHighBenchInput(int cycleTimeHighBenchInput) {
        this.cycleTimeHighBenchInput = cycleTimeHighBenchInput;
    }

    public double getShootingRangeBenchInput() {
        return shootingRangeBenchInput;
    }

    public void setShootingRangeBenchInput(double shootingRangeBenchInput) {
        this.shootingRangeBenchInput = shootingRangeBenchInput;
    }

    public String getPreferredShootingLocationBenchInput() {
        return preferredShootingLocationBenchInput;
    }

    public void setPreferredShootingLocationBenchInput(String preferredShootingLocationBenchInput) {
        this.preferredShootingLocationBenchInput = preferredShootingLocationBenchInput;
    }

    public double getAccuracyHighBenchInput() {
        return accuracyHighBenchInput;
    }

    public void setAccuracyHighBenchInput(double accuracyHighBenchInput) {
        this.accuracyHighBenchInput = accuracyHighBenchInput;
    }

    public boolean isPickupBallHopperBenchButton() {
        return pickupBallHopperBenchButton;
    }

    public void setPickupBallHopperBenchButton(boolean pickupBallHopperBenchButton) {
        this.pickupBallHopperBenchButton = pickupBallHopperBenchButton;
    }

    public boolean isPickupBallFloorBenchButton() {
        return pickupBallFloorBenchButton;
    }

    public void setPickupBallFloorBenchButton(boolean pickupBallFloorBenchButton) {
        this.pickupBallFloorBenchButton = pickupBallFloorBenchButton;
    }

    public boolean isPickupBallHumanBenchButton() {
        return pickupBallHumanBenchButton;
    }

    public void setPickupBallHumanBenchButton(boolean pickupBallHumanBenchButton) {
        this.pickupBallHumanBenchButton = pickupBallHumanBenchButton;
    }

    public String getPickupBallPreferredBenchInput() {
        return pickupBallPreferredBenchInput;
    }

    public void setPickupBallPreferredBenchInput(String pickupBallPreferredBenchInput) {
        this.pickupBallPreferredBenchInput = pickupBallPreferredBenchInput;
    }

    public int getMaximumBallCapacityBenchInput() {
        return maximumBallCapacityBenchInput;
    }

    public void setMaximumBallCapacityBenchInput(int maximumBallCapacityBenchInput) {
        this.maximumBallCapacityBenchInput = maximumBallCapacityBenchInput;
    }

    public boolean isCanScoreGearsBenchButton() {
        return canScoreGearsBenchButton;
    }

    public void setCanScoreGearsBenchButton(boolean canScoreGearsBenchButton) {
        this.canScoreGearsBenchButton = canScoreGearsBenchButton;
    }

    public boolean isPickupGearFloorBenchButton() {
        return pickupGearFloorBenchButton;
    }

    public void setPickupGearFloorBenchButton(boolean pickupGearFloorBenchButton) {
        this.pickupGearFloorBenchButton = pickupGearFloorBenchButton;
    }

    public boolean isPickupGearRetrievalBenchButton() {
        return pickupGearRetrievalBenchButton;
    }

    public void setPickupGearRetrievalBenchButton(boolean pickupGearRetrievalBenchButton) {
        this.pickupGearRetrievalBenchButton = pickupGearRetrievalBenchButton;
    }

    public boolean isPickupGearPreferredBenchButton() {
        return pickupGearPreferredBenchButton;
    }

    public void setPickupGearPreferredBenchButton(boolean pickupGearPreferredBenchButton) {
        this.pickupGearPreferredBenchButton = pickupGearPreferredBenchButton;
    }

    public boolean isCanGearLeftBench() {
        return canGearLeftBench;
    }

    public void setCanGearLeftBench(boolean canGearLeftBench) {
        this.canGearLeftBench = canGearLeftBench;
    }

    public boolean isCanGearCenterBench() {
        return canGearCenterBench;
    }

    public void setCanGearCenterBench(boolean canGearCenterBench) {
        this.canGearCenterBench = canGearCenterBench;
    }

    public boolean isCanGearRightBench() {
        return canGearRightBench;
    }

    public void setCanGearRightBench(boolean canGearRightBench) {
        this.canGearRightBench = canGearRightBench;
    }

    public String getRadioPreferredGear() {
        return radioPreferredGear;
    }

    public void setRadioPreferredGear(String radioPreferredGear) {
        this.radioPreferredGear = radioPreferredGear;
    }

    public String getRadioPreferredBall() {
        return radioPreferredBall;
    }

    public void setRadioPreferredBall(String radioPreferredBall) {
        this.radioPreferredBall = radioPreferredBall;
    }

    public int getCycleTimeGearsBenchInput() {
        return cycleTimeGearsBenchInput;
    }

    public void setCycleTimeGearsBenchInput(int cycleTimeGearsBenchInput) {
        this.cycleTimeGearsBenchInput = cycleTimeGearsBenchInput;
    }

    public boolean isAbilityToShootLowGoalBenchButton() {
        return abilityToShootLowGoalBenchButton;
    }

    public void setAbilityToShootLowGoalBenchButton(boolean abilityToShootLowGoalBenchButton) {
        this.abilityToShootLowGoalBenchButton = abilityToShootLowGoalBenchButton;
    }

    public int getCycleTimeLowBenchInput() {
        return cycleTimeLowBenchInput;
    }

    public void setCycleTimeLowBenchInput(int cycleTimeLowBenchInput) {
        this.cycleTimeLowBenchInput = cycleTimeLowBenchInput;
    }

    public int getCycleNumberLowBenchInput() {
        return cycleNumberLowBenchInput;
    }

    public void setCycleNumberLowBenchInput(int cycleNumberLowBenchInput) {
        this.cycleNumberLowBenchInput = cycleNumberLowBenchInput;
    }

    public boolean isAbilityScaleBenchButton() {
        return abilityScaleBenchButton;
    }

    public void setAbilityScaleBenchButton(boolean abilityScaleBenchButton) {
        this.abilityScaleBenchButton = abilityScaleBenchButton;
    }

    public String getPlacesCanScaleBenchInput() {
        return placesCanScaleBenchInput;
    }

    public void setPlacesCanScaleBenchInput(String placesCanScaleBenchInput) {
        this.placesCanScaleBenchInput = placesCanScaleBenchInput;
    }

    public String getPreferredPlacesScaleInput() {
        return preferredPlacesScaleInput;
    }

    public void setPreferredPlacesScaleInput(String preferredPlacesScaleInput) {
        this.preferredPlacesScaleInput = preferredPlacesScaleInput;
    }

    public String getAutoAbilitiesBench() {
        return autoAbilitiesBench;
    }

    public void setAutoAbilitiesBench(String autoAbilitiesBench) {
        this.autoAbilitiesBench = autoAbilitiesBench;
    }

    public String getCommentsBench() {
        return commentsBench;
    }

    public void setCommentsBench(String commentsBench) {
        this.commentsBench = commentsBench;
    }
}
