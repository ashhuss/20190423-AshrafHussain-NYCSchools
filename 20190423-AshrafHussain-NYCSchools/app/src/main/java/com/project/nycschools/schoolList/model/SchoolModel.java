package com.project.nycschools.schoolList.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SchoolModel implements Serializable {

    @SerializedName("dbn")

    private String dbn;
    @SerializedName("num_of_sat_test_takers")

    private String numOfSatTestTakers;
    @SerializedName("sat_critical_reading_avg_score")

    private String satCriticalReadingAvgScore;
    @SerializedName("sat_math_avg_score")

    private String satMathAvgScore;
    @SerializedName("sat_writing_avg_score")

    private String satWritingAvgScore;
    @SerializedName("school_name")

    private String schoolName;

    public String getDbn() {
        return dbn;
    }

    public void setDbn(String dbn) {
        this.dbn = dbn;
    }

    public String getNumOfSatTestTakers() {
        return numOfSatTestTakers;
    }

    public void setNumOfSatTestTakers(String numOfSatTestTakers) {
        this.numOfSatTestTakers = numOfSatTestTakers;
    }

    public String getSatCriticalReadingAvgScore() {
        return satCriticalReadingAvgScore;
    }

    public void setSatCriticalReadingAvgScore(String satCriticalReadingAvgScore) {
        this.satCriticalReadingAvgScore = satCriticalReadingAvgScore;
    }

    public String getSatMathAvgScore() {
        return satMathAvgScore;
    }

    public void setSatMathAvgScore(String satMathAvgScore) {
        this.satMathAvgScore = satMathAvgScore;
    }

    public String getSatWritingAvgScore() {
        return satWritingAvgScore;
    }

    public void setSatWritingAvgScore(String satWritingAvgScore) {
        this.satWritingAvgScore = satWritingAvgScore;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}
