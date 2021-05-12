package com.example.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RankResult {

    private int idx;
    private String award_rank;
    private String career_rank;
    private String certificate_rank;
    private String education_rank;
    private String experience_rank;
    private String grade_rank;
    private String language_rank;
    private String project_rank;
    private String all_rank;


}