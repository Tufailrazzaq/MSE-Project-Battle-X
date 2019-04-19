package com.example.omen.battlex;

import java.util.ArrayList;

public class NewRegister {

    private String teamName;
    private String universityName;
    private String competitionName;
    private String id;
    private ArrayList<TeamMember> teamMember;


    public NewRegister() {
    }

    public NewRegister(String id, String teamName, String universityName, String competitionName, ArrayList<TeamMember> teamMemberList){
        teamMember = new ArrayList<>();
        this.teamName = teamName;
        this.universityName = universityName;
        this.competitionName = competitionName;
        this.teamMember = teamMemberList;
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getUniversityName() {
        return universityName;
    }

    public String getCompetitionName() {
        return competitionName;
    }

    public String getId() {
        return id;
    }

//    public ArrayList<TeamMember> getTeamMemberList() {
//        return teamMemberList;
//    }


    public ArrayList<TeamMember> getTeamMember() {
        return teamMember;
    }

    public void setTeamMember(ArrayList<TeamMember> teamMember) {
        this.teamMember = teamMember;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    public void setId(String id) {
        this.id = id;
    }

//    public void setTeamMemberList(ArrayList<TeamMember> teamMemberList) {
//        this.teamMemberList = teamMemberList;
//    }
}
