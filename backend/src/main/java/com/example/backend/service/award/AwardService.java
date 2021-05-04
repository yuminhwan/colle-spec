package com.example.backend.service.award;

import com.example.backend.domain.Award;
import com.example.backend.mapper.AwardMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AwardService {

    private final AwardMapper awardMapper;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    public Award save(Award award) {

        logger.info("수상 등록");

        award.setScore(changetoNumber(award));

        awardMapper.save(award);

        return award;

    }

    public List<Award> findByUserIdx(int user_idx) {

        logger.info("수상 조회");

        checkAwardUserIdx(user_idx);

        return awardMapper.findByUserIdx(user_idx);

    }

    public Award modify(Award award) {

        logger.info("수상 수정");

        checkAwardUserIdx(award.getUser_idx());

        checkAll(award.getIdx(), award.getUser_idx());

        award.setScore(changetoNumber(award));

        awardMapper.update(award);

        return award;

    }

    public void delete (int idx , int user_idx) {

        logger.info("수상 삭제");

        checkAwardUserIdx(user_idx);

        checkAll(idx,user_idx);

        awardMapper.delete(idx, user_idx);

    }


    public int changetoNumber (Award award) {




        return 10;

    }

    public void checkAwardUserIdx(int user_idx) {
        if ( awardMapper.findByUserIdx(user_idx).isEmpty()){
            throw new IllegalStateException("해당 회원의 수상 정보가 없습니다.");
        }

    }


    public void checkAll(int idx , int user_idx) {

        if (awardMapper.finduser_idxByIdx(idx).isPresent() ) {
            if (awardMapper.finduser_idxByIdx(idx).get() != user_idx)
                throw new IllegalStateException("해당 회원의 수상 번호가 아닙니다.");
        }
        else {
            throw new IllegalStateException("해당 수상 번호의 정보가 없습니다.");
        }
    }

}