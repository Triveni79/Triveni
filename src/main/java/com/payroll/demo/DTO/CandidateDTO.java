package com.payroll.demo.DTO;

public class CandidateDTO {
    private String candidateId;
    private String lastName;

    public CandidateDTO(String candidateId, String lastName) {
        this.candidateId = candidateId;
        this.lastName = lastName;
    }

    public String getCandidateId() {
        return candidateId;
    }

    public String getLastName() {
        return lastName;
    }
}
