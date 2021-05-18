package com.example.backend.service.link;

import com.example.backend.advice.exception.CUserNotFoundException;
import com.example.backend.domain.Grade;
import com.example.backend.domain.User;
import com.example.backend.mapper.collspec.UserMapper;
import com.example.backend.service.grade.GradeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class LinkServiceTest {

    @Autowired
    LinkService linkservice;

    @Autowired
    UserMapper userMapper;

    @Autowired
    GradeService gradeService;

    @Test
    void 어플로그인() {

        //given
        String id = "abc";
        String pw = "abc";


        //when
        int appidx = linkservice.linkLogin(id, pw);

        //then
        assertThat(appidx).isEqualTo(1);

    }

    @Test
    void 기본정보입력() {

        //given
        int appidx = 1;
        int idx = 35;


        //when
       linkservice.linkInfo(appidx,idx);


        //then
        User check = userMapper.findByIdx(25)
                .orElseThrow(CUserNotFoundException::new);

        assertThat(check.getCollege()).isEqualTo("경상국립대학교");
        assertThat(check.getMajor()).isEqualTo("컴퓨터과학과");
        assertThat(check.getGrade()).isEqualTo(3);


    }

    @Test
    void 학점정보입력() {

        //given
        int appidx = 1;
        int idx = 35;
        Grade test = new Grade();
        test.setUser_idx(23);
        test.setGrade("1");
        test.setSemester("1");
        test.setAvg_score(4.5);

        //when
        gradeService.save(test);
        linkservice.linkGrade(appidx,idx);


        //then

        assertThat(gradeService.findByUserIdx(35).size()).isEqualTo(4);


    }


}

